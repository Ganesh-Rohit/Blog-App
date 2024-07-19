package com.travel.blog.Mapper;

import com.travel.blog.Model.UserModel;
import com.travel.blog.entity.User;

public class UserMapper {

    public static UserModel toDto(User user) {
        UserModel userDto = new UserModel();
        userDto.setId(user.getId());
        userDto.setUserId(user.getUserId());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setEmailId(user.getEmailId());
        userDto.setPassWord(user.getPassWord());

        return userDto;
    }
}
