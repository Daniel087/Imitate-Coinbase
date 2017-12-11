package com.example.demo1.service;

import com.example.demo1.DemoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class DemoService {

    @Autowired
    private DemoRepository demoRepository;

    @Transactional
    public void updatecn(String email,String cn){
        demoRepository.updatecn(email,cn);

    }

    @Transactional
    public void updatebtc(String email,String cn,String btc){
        demoRepository.updatebtc(email,cn,btc);

    }

    @Transactional
    public void sendcn(String email1,String email2,String cn){
        demoRepository.subtractcn(email1,cn);
        demoRepository.addcn(email2,cn);
    }

    @Transactional
    public void incomecn(String email1,String email2,String cn){
        demoRepository.addcn(email1,cn);
        demoRepository.subtractcn(email2,cn);
    }

    @Transactional
    public void sendbtc(String email1,String email2,String btc){
        demoRepository.subtractbtc(email1,btc);
        demoRepository.addbtc(email2,btc);
    }

    @Transactional
    public void incomebtc(String email1,String email2,String btc){
        demoRepository.addbtc(email1,btc);
        demoRepository.subtractbtc(email2,btc);
    }
}
