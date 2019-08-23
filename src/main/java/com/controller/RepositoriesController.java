package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 仓库控制器
 * 时间：2019年8月23日14:13:49
 */
@Controller
public class RepositoriesController {

    @GetMapping("/newRepo")
    public String newRepo(){
        return "/newRepo";
    }

}
