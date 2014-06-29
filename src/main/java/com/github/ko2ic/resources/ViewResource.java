package com.github.ko2ic.resources;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ViewResource {

    @Value("${spring.freemarker.templateEncoding:UTF-8}")
    private String charset;

    @RequestMapping(value = "/views/freemarker")
    public String freemarkerUTF8(Map<String, Object> model) {
        model.put("charset", charset);
        return "ftl/utf8";
    }
}
