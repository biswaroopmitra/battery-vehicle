package com.example.BatteryStateOfHealth.Battery.authentication;

import com.example.BatteryStateOfHealth.Battery.user.AppUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginDto {
    Boolean loginSuccess;
    AppUser user;
    String jwt;
}
