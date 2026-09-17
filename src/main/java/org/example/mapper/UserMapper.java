package org.example.mapper;

import org.example.dto.UserDto;
import org.example.model.User;

public class UserMapper {
    // This class can be used to map between User and UserDto if needed in the future.

    public static UserDto toDto(User user) {
        return new UserDto(user.getId(), user.getName(), user.getEmail());
    }

    public static User toEntity(UserDto userDto) {
        return new User(userDto.getId(), userDto.getName(), userDto.getEmail());
    }
}
