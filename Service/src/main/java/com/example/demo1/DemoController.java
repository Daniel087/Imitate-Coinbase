package com.example.demo1;


import com.example.demo1.service.DemoService;
import org.hibernate.sql.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sun.awt.SunHints;

import javax.transaction.Transactional;

@RestController
public class DemoController {

    @Autowired
    private DemoRepository demoRepository;
    @Autowired
    private DemoService demoService;

    @PostMapping(value = "/data")
    public Demo Add(@RequestParam("email") String email,
                      @RequestParam("surname") String surname,
                      @RequestParam("name") String name,
                      @RequestParam("password") String password){

        Demo demo = new Demo();
        demo.setEmail(email);
        demo.setSurname(surname);
        demo.setName(name);
        demo.setPassword(password);
        demo.setCn("0.00");
        demo.setBtc("0.00");
        demo.setEth("0.00");
        demo.setLtc("0.00");

        return demoRepository.save(demo);
    }

    @PostMapping(value = "/login")
    public Demo login(@RequestParam("email") String email){
        return demoRepository.findByEmail(email);
    }


    @PostMapping(value = "/updatecn")
    public void updatecn(@RequestParam("email") String email,
                         @RequestParam("cn") String cn){
//        demoRepository.updatecn(email,cn);
        demoService.updatecn(email,cn);
    }


    @PostMapping(value = "/updatebtc")
    public void updatebtc(@RequestParam("email") String email,
                          @RequestParam("cn") String cn,
                          @RequestParam("btc") String btc){
        demoService.updatebtc(email, cn, btc);
    }


    @PostMapping(value = "/sendcn")
    public void sendcn(@RequestParam("email1") String email1,
                       @RequestParam("email2") String email2,
                       @RequestParam("cn") String cn){
        demoService.sendcn(email1, email2, cn);

    }


    @PostMapping(value = "/incomecn")
    public void incomecn(@RequestParam("email1") String email1,
                       @RequestParam("email2") String email2,
                       @RequestParam("cn") String cn){
        demoService.incomecn(email1, email2, cn);

    }


    @PostMapping(value = "/sendbtc")
    public void sendbtc(@RequestParam("email1") String email1,
                       @RequestParam("email2") String email2,
                       @RequestParam("btc") String btc){
        demoService.sendbtc(email1, email2, btc);

    }


    @PostMapping(value = "/incomebtc")
    public void incomebtc(@RequestParam("email1") String email1,
                       @RequestParam("email2") String email2,
                       @RequestParam("btc") String btc){
        demoService.incomebtc(email1, email2, btc);

    }



}
