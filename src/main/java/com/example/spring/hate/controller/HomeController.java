package com.example.spring.hate.controller;

import com.example.spring.hate.dto.MemberDto;
import com.example.spring.hate.dto.Tdto;
import com.example.spring.hate.service.MemberService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;
import java.util.ArrayList;

@Controller
public class HomeController {

    private MemberService memberService;

    public HomeController (MemberService memberService){
        this.memberService=memberService;
    }


    @GetMapping("/joinForm")
    public String joinForm(){
        return "form/joinForm";
    }

    @PostMapping("/membersave")
    public ModelAndView membersave(MemberDto memberDto){
        memberService.membersave(memberDto);
        RedirectView redirectView = new RedirectView("/home");
        ModelAndView modelAndView = new ModelAndView(redirectView);
        modelAndView.addObject("test","이거되나");
       // redirectView.setUrl("/home");
        return modelAndView;
    }
    @GetMapping("/home")
    public ModelAndView home(@RequestParam("test") String test){
        ModelAndView modelAndView = new ModelAndView("home/home");
        modelAndView.addObject("test",test);
        return modelAndView;
    }
    @GetMapping("/memberlist")
    public ModelAndView memberList() throws IOException {
        RedirectView redirectView = new RedirectView("/showList");
        ModelAndView modelAndView = new ModelAndView(redirectView);
        ArrayList<MemberDto> arrayList=memberService.memberList();
        Gson gson = new Gson();
        String json = gson.toJson(arrayList);
      modelAndView.addObject("list",json);
        return modelAndView;
    }
    @GetMapping("/showList")
    public String show(@RequestParam("list")String json, Model model){
        Gson gson= new Gson();
        ArrayList<MemberDto> convertedArrayList = gson.fromJson(json, new TypeToken<ArrayList<MemberDto>>() {}.getType());
        model.addAttribute("list",convertedArrayList);
        return "home/list";
    }



    @GetMapping("/updateForm")
    public String updateForm(){
        memberService.updateForm();
        return "form/updateForm";
    }
    @GetMapping("/file")
    public String file(){
        return "form/file";
    }
    @PostMapping("/file")
    public String file(@ModelAttribute Tdto tdto, @RequestPart MultipartFile img ){
        memberService.file(tdto,img);
        return "do";
    }
}
