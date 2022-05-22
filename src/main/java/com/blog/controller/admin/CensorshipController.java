package com.blog.controller.admin;

import com.blog.controller.Constant;
import com.blog.pojo.Censorship;
import com.blog.pojo.User;
import com.blog.service.CensorshipService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class CensorshipController {
    @Autowired
    CensorshipService censorshipService;


    @GetMapping("/censorships")
    public String censorships(@RequestParam(required = false,defaultValue = "1",value = "pagenum")int pagenum, Model model){
        PageHelper.startPage(pagenum, 10);
        List<Censorship> all = censorshipService.getAll();
        //得到分页结果对象
        PageInfo<Censorship> pageInfo = new PageInfo<>(all);
        model.addAttribute("pageInfo", pageInfo);
        return "admin/censors";
    }

    @GetMapping("/censorships/input")
    public String toAddCensorship(Model model){
        model.addAttribute("censorship", new Censorship());   //返回一个tag对象给前端th:object
        return "admin/censors-input";
    }

    @GetMapping("/censorships/{id}/input")
    public String toEditCensorship(@PathVariable int id, Model model){
        model.addAttribute("censorship", censorshipService.getById(id));
        return "admin/censors-input";
    }

    @PostMapping("/censorships")
    public String addCensorship(Censorship censorship, RedirectAttributes attributes){   //新增
        Censorship t = censorshipService.getByWord(censorship.getWord());
        if(t != null){
            attributes.addFlashAttribute("msg", "不能添加重复的标签");
            return "redirect:/admin/censorships/input";
        }else {
            attributes.addFlashAttribute("msg", "添加成功");
        }
        censorshipService.add(censorship);
        return "redirect:/admin/censorships";   //不能直接跳转到tags页面，否则不会显示tag数据(没经过tags方法)
    }

    @PostMapping("/censorships/{id}")
    public String editCensorship(@PathVariable int id, Censorship censorship, RedirectAttributes attributes){  //修改
        Censorship t = censorshipService.getByWord(censorship.getWord());
        if(t != null){
            attributes.addFlashAttribute("msg", "不能添加重复的标签");
            return "redirect:/admin/censorships/input";
        }else {
            attributes.addFlashAttribute("msg", "修改成功");
        }
        censorshipService.update(censorship);
        return "redirect:/admin/censorships";   //不能直接跳转到tags页面，否则不会显示tag数据(没经过tags方法)
    }

    @GetMapping("/censorships/{id}/delete")
    public String delete(@PathVariable int id, RedirectAttributes attributes){
        censorshipService.delete(id);
        attributes.addFlashAttribute("msg", "删除成功");
        return "redirect:/admin/censorships";
    }
}
