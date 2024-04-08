package co.istad.testmobilebankingapi.features.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class UserEditProfileRequest {
    private Long userId;
    private String cityOrProvince;
    private String khanOrDistrict;
    private String sangkatOrCommune;
    private String village;
    private String street;
    private String employeeType;
    private String position;
    private String companyName;
    private String mainSourceOfIncome;
    private BigDecimal monthlyIncomeRange;
}
