package com.syg.userservice.service;

import com.syg.userservice.dto.TransactionRequestDto;
import com.syg.userservice.dto.TransactionResponseDto;
import com.syg.userservice.dto.TransactionStatus;
import com.syg.userservice.repository.UserRepository;
import com.syg.userservice.repository.UserTransactionRepository;
import com.syg.userservice.util.EntityDtoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class TransactionService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserTransactionRepository userTransactionRepository;

    public Mono<TransactionResponseDto> createTransaction(final TransactionRequestDto dto){
        return this.userRepository.updateUserBalance(dto.getUserId(), dto.getAmount())
                .filter(Boolean::booleanValue)
                .map(b -> EntityDtoUtil.toEntity(dto))
                .flatMap(this.userTransactionRepository::save)
                .map(ut -> EntityDtoUtil.toDto(dto, TransactionStatus.APPROVED))
                .defaultIfEmpty(EntityDtoUtil.toDto(dto, TransactionStatus.DECLONED));
    }
}
