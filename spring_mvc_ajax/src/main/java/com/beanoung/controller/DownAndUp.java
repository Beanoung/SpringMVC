package com.beanoung.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;


@Controller
public class DownAndUp {

    /**
     * ResponseEntity:作为控制器方法的返回值,表示完整的响应报文
     */
    //下载功能,可以作为模板
    @RequestMapping("/test/down")
    public ResponseEntity<byte[]> testDown(HttpSession session) throws IOException {
        //获取ServletContext对象
        ServletContext servletContext = session.getServletContext();
        //获取真实路径
        String realPath = servletContext.getRealPath("img");
        //这样可以避免文件符号打错
        realPath = realPath + File.separator + "学习计划.png";
        //创建输入流
        InputStream is = new FileInputStream(realPath);
        //创建字节数组,is.available()获取输入流字节数
        byte[] bytes = new byte[is.available()];
        //读
        is.read(bytes);
        //创建响应头信息
        MultiValueMap<String, String> headers = new HttpHeaders();
        //设置下载方式(attachment)和文件名(学习计划.png)
        headers.add("Content-Disposition", "attachment;filename=学习计划.png");
        //设置响应状态码
        HttpStatus status = HttpStatus.OK;
        //创建ResponseEntity对象
        ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(bytes, headers, status);
        //关闭输入流
        is.close();
        return responseEntity;
    }

    //上传功能  需要配置文件上传解析器
    @RequestMapping("/test/up")
    public String testUp(MultipartFile photo, HttpSession session) throws IOException {
        //设置文件名
        String filename = photo.getOriginalFilename();

        //有一个问题: 重名的文件上传,会覆盖内容,以下解决重名问题
        //后缀名
        String hzName = filename.substring(filename.lastIndexOf("."));
        //uuid
        String uuid = UUID.randomUUID().toString();
        //拼接
        filename = uuid + hzName;

        //设置文件路径
        ServletContext servletContext = session.getServletContext();
        String photoPath = servletContext.getRealPath("photo");
        //如果没有目录则创建
        File file = new File(photoPath);
        if (!file.exists()) {
            file.mkdir();
        }
        //最终路径
        String finalPath = photoPath + File.separator + filename;
        //上传
        photo.transferTo(new File(finalPath));
        return "success";
    }
}
