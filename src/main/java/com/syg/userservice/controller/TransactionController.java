package com.syg.userservice.controller;

import com.syg.userservice.dto.TransactionRequestDto;
import com.syg.userservice.dto.TransactionResponseDto;
import com.syg.userservice.dto.TransactionStatus;
import com.syg.userservice.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("user/transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping
    public Mono<TransactionResponseDto> createTransaction(@RequestBody Mono<TransactionRequestDto> dtoMono){
        return dtoMono.flatMap(this.transactionService::createTransaction);
    }
}
