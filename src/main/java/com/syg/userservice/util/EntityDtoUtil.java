package com.syg.userservice.util;

import com.syg.userservice.dto.TransactionRequestDto;
import com.syg.userservice.dto.TransactionResponseDto;
import com.syg.userservice.dto.TransactionStatus;
import com.syg.userservice.dto.UserDto;
import com.syg.userservice.entity.User;
import com.syg.userservice.entity.UserTransaction;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;

public class EntityDtoUtil {

    public static UserDto toDto(User user){
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(user, userDto);
        return userDto;
    }

    public static User toEntity(UserDto userDto){
        User user = new User();
        BeanUtils.copyProperties(userDto, user);
        return user;
    }

    public static UserTransaction toEntity(TransactionRequestDto transactionRequestDto){
        UserTransaction ut = new UserTransaction();
        ut.setUserId(transactionRequestDto.getUserId());
        ut.setAmount(transactionRequestDto.getAmount());
        ut.setTransactionDate(LocalDateTime.now());
        return ut;
    }

    public static TransactionResponseDto toDto(TransactionRequestDto transactionRequestDto,
                                               TransactionStatus status){
        TransactionResponseDto dto = new TransactionResponseDto();
        dto.setStatus(status);
        dto.setAmount(transactionRequestDto.getAmount());
        dto.setUserId(transactionRequestDto.getUserId());
        return dto;
    }
}
