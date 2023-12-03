package com.cydeo.mapper;

import com.cydeo.dto.AccountDTO;
import com.cydeo.entity.Account;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper {

    private ModelMapper modelMapper;

    public AccountMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public AccountDTO convertToDTO(Account account){
        return modelMapper.map(account,AccountDTO.class);
    }

    public Account convertToEntity(AccountDTO dto){
        return modelMapper.map(dto,Account.class);
    }


}
