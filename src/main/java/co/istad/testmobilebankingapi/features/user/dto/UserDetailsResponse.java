package co.istad.testmobilebankingapi.features.user.dto;

import java.time.LocalDate;
import java.util.List;

public record UserDetailsResponse(
    String uuid,
    String name,
    String profileImage,
    String gender,
    LocalDate dob,
    String cityOrProvince,
    String khanOrDistrict,
    String sangkatOrCommune,
    String village,
    String street,
    String employeeType,
    String position,
    String companyName,
    String mainSourceOfIncome,
    String monthlyIncomeRange,
    String studentIdCard,
    List<RoleNameResponse> roles

) {
}
