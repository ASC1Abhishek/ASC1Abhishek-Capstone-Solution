package com.ats.entity;

import jakarta.persistence.*;

@Entity
@Table (name = "planes")
public class PlaneEntity {

    @Id
    private String registrationNumber;

    @Column(unique = true, nullable = false)
    private String planeMaker;

    @Column(nullable = false)
    private String model;

    @Column(nullable = false)
    private String imagePath;

    @Column(nullable = false)
    private int seatingCapacity;

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getPlaneMaker() {
        return planeMaker;
    }

    public void setPlaneMaker(String planeMaker) {
        this.planeMaker = planeMaker;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public int getSeatingCapacity() {
        return seatingCapacity;
    }

    public void setSeatingCapacity(int seatingCapacity) {
        this.seatingCapacity = seatingCapacity;
    }
}
