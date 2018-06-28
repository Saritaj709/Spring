package com.bridgelabz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.bridgelabz.drivers.DataBaseDriver;

@Service
public class UserService {

    @Autowired
    @Qualifier("oracleDriver")
    private DataBaseDriver dataBaseDriver;

    public String getDriverInfo(){
        return dataBaseDriver.getInfo();
    }
}