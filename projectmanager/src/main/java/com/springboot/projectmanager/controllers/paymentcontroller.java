package com.springboot.projectmanager.controllers;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.razorpay.PaymentLink;
import com.razorpay.RazorpayClient;
import com.springboot.projectmanager.model.Plantype;
import com.springboot.projectmanager.model.user;
import com.springboot.projectmanager.response.paymentlinkresonse;
import com.springboot.projectmanager.service.userservice;

@RestController
@RequestMapping("/api")
public class paymentcontroller {

    @Value("${razorpay.api.key}")
    private String api_key;

    @Value("${razorpay.api.secret}")
    private String api_secret;

    @Autowired
    private userservice userservice;

    @RequestMapping(value = "/paymentlink/{plantype}", method = RequestMethod.POST)
    public ResponseEntity<paymentlinkresonse> createpaymentlink(
            @PathVariable Plantype plantype,
            @RequestHeader("Authorization") String jwt) throws Exception {

        user u = userservice.finduserbyjwt(jwt);
        int amount = 799;

        if (plantype.equals(Plantype.ANNUALLY)) {
            amount = amount * 12;
            amount = (int) (amount * 0.7);
        }

     
            RazorpayClient client = new RazorpayClient(api_key, api_secret);
            JSONObject paymnetlinkrequest = new JSONObject();
            paymnetlinkrequest.put("amount", amount * 100); // amount in paise
            paymnetlinkrequest.put("currency", "INR");

            JSONObject customer = new JSONObject();
            customer.put("name", u.getFullname());
            customer.put("email", u.getEmail());
            paymnetlinkrequest.put("customer", customer);

            JSONObject notify = new JSONObject();
            notify.put("email", true);
            paymnetlinkrequest.put("notify", notify);
            paymnetlinkrequest.put("callback_url", "http://localhost:5173/upgrade_plan/success?plantype=" + plantype);

            PaymentLink payment = client.paymentLink.create(paymnetlinkrequest);

            String paymentid= payment.get("id");
            String shorturl= payment.get("short_url");
            paymentlinkresonse paymentlinkresonse= new paymentlinkresonse(shorturl, paymentid);
            return new ResponseEntity<paymentlinkresonse>(paymentlinkresonse,  HttpStatus.CREATED);
           
            
  
}
    }

