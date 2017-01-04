package com.utopian.springframework.controllers;

import com.utopian.springframework.domain.Ground;
import com.utopian.springframework.services.GroundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by garamasu on 2017-01-03.
 */
@Controller
public class GroundController {

    private GroundService groundService;

    @Autowired
    public void setGroundService(GroundService groundService) {
        this.groundService = groundService;
    }

    @RequestMapping(value = "/grounds", method = RequestMethod.GET)
    public String list(Model model){
        model.addAttribute("grounds", groundService.listAllGrounds());
        return "grounds";
    }

    @RequestMapping("ground/{id}")
    public String showGround(@PathVariable Integer id, Model model){
        model.addAttribute("ground", groundService.getGroundById(id));
        return "groundshow";
    }

    @RequestMapping("ground/edit/{id}")
    public String edit(@PathVariable Integer id, Model model){
        model.addAttribute("ground", groundService.getGroundById(id));
        return "groundform";
    }

    @RequestMapping("ground/new")
    public String newGround(Model model){
        model.addAttribute("ground", new Ground());
        return "groundform";
    }

    @RequestMapping(value = "ground", method = RequestMethod.POST)
    public String saveGround(Ground ground){
        groundService.saveGround(ground);
        return "redirect:/ground/" + ground.getId();
    }

    @RequestMapping("ground/delete/{id}")
    public String delete(@PathVariable Integer id){
        groundService.deleteGround(id);
        return "redirect:/grounds";
    }

}
