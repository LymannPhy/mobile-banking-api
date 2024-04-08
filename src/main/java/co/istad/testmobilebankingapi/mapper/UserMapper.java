package co.istad.testmobilebankingapi.mapper;

import co.istad.testmobilebankingapi.domain.User;
import co.istad.testmobilebankingapi.features.user.dto.UserCreateRequest;
import co.istad.testmobilebankingapi.features.user.dto.UserDetailsResponse;
import co.istad.testmobilebankingapi.features.user.dto.UserResponse;
import co.istad.testmobilebankingapi.features.user.dto.UserUpdateRequest;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User fromUserCreateRequest(UserCreateRequest userCreateRequest);
    UserDetailsResponse toUserDetailsResponse(User user);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void fromUserUpdateRequest(UserUpdateRequest userUpdateRequest, @MappingTarget User user);

    UserResponse toUserResponse(User user);
}
