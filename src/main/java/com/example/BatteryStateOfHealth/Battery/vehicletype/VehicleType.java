package com.example.BatteryStateOfHealth.Battery.vehicletype;

import com.example.BatteryStateOfHealth.Battery.baseEntity.BaseEntity;
import com.example.BatteryStateOfHealth.Battery.vehiclecategory.VehicleCategory;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class VehicleType extends BaseEntity {
    @NotNull(message = "name is required")
    @NotBlank(message = "name is required")
    @Column(nullable = false)
    String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vehicle_category_id", nullable = true)
    VehicleCategory vehicleCategory;

    public VehicleType() {
    }

    public VehicleType(String name, VehicleCategory vehicleCategory) {
        this.name = name;
        this.vehicleCategory = vehicleCategory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public VehicleCategory getVehicleCategory() {
        return vehicleCategory;
    }

    public void setVehicleCategory(VehicleCategory vehicleCategory) {
        this.vehicleCategory = vehicleCategory;
    }
}
