package com.syg.userservice.service;

import com.syg.userservice.dto.UserDto;
import com.syg.userservice.repository.UserRepository;
import com.syg.userservice.util.EntityDtoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Flux<UserDto> getAllUsers(){
        return this.userRepository.findAll()
                .map(EntityDtoUtil::toDto);
    }

    public Mono<UserDto> getUserById(int userId){
        return this.userRepository.findById(userId)
                .map(EntityDtoUtil::toDto);
    }

    public Mono<UserDto> saveUser(Mono<UserDto> userDtoMono){
        return userDtoMono
                .map(EntityDtoUtil::toEntity)
                .flatMap(this.userRepository::save)
                .map(EntityDtoUtil::toDto);

    }

    public Mono<UserDto> updateUser(int id, Mono<UserDto> userDtoMono){
       return this.userRepository.findById(id)
                .flatMap(u->userDtoMono
                        .map(EntityDtoUtil::toEntity)
                        .doOnNext(e->e.setId(id)))
                .flatMap(this.userRepository::save)
                .map(EntityDtoUtil::toDto);
    }

    public Mono<Void> deleteUser(int id){
        return this.userRepository.deleteById(id);
    }
}
