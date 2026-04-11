package com.example.BatteryStateOfHealth.Battery.vehiclecategory;

import com.example.BatteryStateOfHealth.Battery.baseEntity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Entity
public class VehicleCategory extends BaseEntity {
    @NotNull(message = "name is required")
    @NotBlank(message = "name is required")
    @Column(nullable = false)
    String name;

    @NotNull(message = "length is required")
    @NotBlank(message = "length is required")
    @Positive(message = "length must be a positive number")
    @Column(nullable = false)
    int length;

    @NotNull(message = "width is required")
    @NotBlank(message = "width is required")
    @Positive(message = "width must be a positive number")
    @Column(nullable = false)
    int width;

    @NotNull(message = "height is required")
    @NotBlank(message = "height is required")
    @Positive(message = "height must be a positive number")
    @Column(nullable = false)
    int height;

    public VehicleCategory() {
    }

    public VehicleCategory(String name, int length, int width, int height) {
        this.name = name;
        this.length = length;
        this.width = width;
        this.height = height;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
