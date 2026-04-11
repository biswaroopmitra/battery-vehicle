package com.example.BatteryStateOfHealth.Battery.vehicle;

import com.example.BatteryStateOfHealth.Battery.baseEntity.BaseEntity;
import com.example.BatteryStateOfHealth.Battery.vehicletype.VehicleType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Vehicle extends BaseEntity {
    @NotNull(message = "name is required")
    @NotBlank(message = "name is required")
    @Column(nullable = false)
    String modelName;

    @NotNull(message = "code is required")
    @NotBlank(message = "code is required")
    @Column(nullable = false)
    String modelCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vehicle_type_id", nullable = true)
    VehicleType vehicleType;

    public Vehicle() {
    }

    public Vehicle(String modelName, String modelCode, VehicleType vehicleType) {
        this.modelName = modelName;
        this.modelCode = modelCode;
        this.vehicleType = vehicleType;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getModelCode() {
        return modelCode;
    }

    public void setModelCode(String modelCode) {
        this.modelCode = modelCode;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }
}
