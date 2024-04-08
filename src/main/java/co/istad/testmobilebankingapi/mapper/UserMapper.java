package co.istad.testmobilebankingapi.mapper;

import co.istad.testmobilebankingapi.domain.User;
import co.istad.testmobilebankingapi.domain.UserAccount;
import co.istad.testmobilebankingapi.features.user.dto.UserCreateRequest;
import co.istad.testmobilebankingapi.features.user.dto.UserDetailsResponse;
import co.istad.testmobilebankingapi.features.user.dto.UserResponse;
import co.istad.testmobilebankingapi.features.user.dto.UserUpdateRequest;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User fromUserCreateRequest(UserCreateRequest userCreateRequest);
    UserDetailsResponse toUserDetailsResponse(User user);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void fromUserUpdateRequest(UserUpdateRequest userUpdateRequest, @MappingTarget User user);

    UserResponse toUserResponse(User user);

    List<UserResponse> toUserResponseList(List<User> users);

    @Named("mapUserResponse")
    default UserResponse mapUserResponse(List<UserAccount> userAccountList) {
        // YOUR LOGIC OF MAPPING HERE...
        return toUserResponse(userAccountList.get(0).getUser());
    }


}
