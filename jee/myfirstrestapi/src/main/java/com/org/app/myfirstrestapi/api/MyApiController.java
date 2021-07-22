package com.org.app.myfirstrestapi.api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.org.app.myfirstrestapi.config.MyConfig;
import com.org.app.myfirstrestapi.service.CustomerManager;
import com.org.app.myfirstrestapi.vo.Customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class MyApiController extends BaseController {

    @Autowired
    private CustomerManager service;

    @Autowired
    private MyConfig myConfig;




    @PutMapping(value = "/customer",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Customer>> regiserCustomer(@RequestBody Customer cust){
        System.out.println(cust);
        service.persitCustomer(cust);
        return new ResponseEntity<List<Customer>>(service.fetchAllCustomer(),HttpStatus.OK);
    }

    @PostMapping(value = "/customer",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Customer>> updateCustomer(@RequestBody Customer cust){
        System.out.println(cust);
        service.persitCustomer(cust);
        return new ResponseEntity<List<Customer>>(service.fetchAllCustomer(),HttpStatus.OK);
    }


    @GetMapping(value = "/names", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<String>> getNames() throws Exception {
        
        List<String> lstConfig = new ArrayList<>();
        lstConfig.add(myConfig.getEc2Url());
        lstConfig.add(myConfig.getEc2Uname());
        lstConfig.add(myConfig.getEc2Pwd());

        return new ResponseEntity<List<String>>(lstConfig, HttpStatus.OK);
    }

    @GetMapping(value = "/names/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<String>> getNamesById(@PathVariable int id) throws Exception {
        System.out.println(id);
        return new ResponseEntity<List<String>>(service.getNames(), HttpStatus.OK);
    }


}
