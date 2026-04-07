package com.example.BatteryStateOfHealth.Battery.vehiclebrand;

import com.example.BatteryStateOfHealth.Battery.baseEntity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class VehicleBrand extends BaseEntity {
    @NotNull(message = "name is required")
    @NotBlank(message = "name is required")
    @Column(nullable = false)
    String name;

    @NotNull(message = "code is required")
    @NotBlank(message = "code is required")
    @Column(nullable = false)
    String code;

    @NotNull(message = "active is required")
    @NotBlank(message = "active is required")
    @Column(nullable = false)
    Boolean active;

    public VehicleBrand() {
    }

    public VehicleBrand(String name, String code, Boolean active) {
        this.name = name;
        this.code = code;
        this.active = active;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
