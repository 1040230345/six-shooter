package com.controller;

import com.dto.RepositoriesDto;
import com.dto.UserDto;
import com.service.RepositoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 仓库控制器
 * 时间：2019年8月23日14:13:49
 */
@Controller
public class RepositoriesController {
    @Autowired
    private RepositoriesService repositoriesService;

    /**
     * 创建仓库页面
     * @param request
     * @param model
     * @return
     */
    @GetMapping("/newRepo")
    public String newRepo(HttpServletRequest request, Model model){
        //获取session内容返回用户信息
        HttpSession session = request.getSession();
        UserDto userDto = (UserDto) session.getAttribute("User");
        model.addAttribute("USER",userDto);
        return "/newRepo";
    }

    /**
     * 创建仓库请求
     * @param repositoriesDto
     * @param request
     * @return
     */
    @PostMapping("/createRepo")
    public String createRepo(RepositoriesDto repositoriesDto,HttpServletRequest request,Model model){
        //获取session内容返回用户信息
        HttpSession session = request.getSession();
        UserDto userDto = (UserDto) session.getAttribute("User");
        //设置创建者
        repositoriesDto.setRep_creator(userDto.getName());
        //开始创建
        boolean bl = repositoriesService.mkdirRepositories(repositoriesDto);
        if(bl){
            model.addAttribute("USER",userDto);
            return "/home";
        }
        return "createRepo";
    }
    /**
     * 仓库页面
     */
    @GetMapping("/{user_id}/{rep_id}")
    public String rep_index(@PathVariable int user_id, @PathVariable int rep_id){

        return "/thisRepo";
    }



}
