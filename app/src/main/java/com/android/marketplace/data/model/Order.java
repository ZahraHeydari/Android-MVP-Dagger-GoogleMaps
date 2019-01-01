package com.android.marketplace.data.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.location.Address;
import android.text.TextUtils;

import java.util.List;

import javax.inject.Inject;

@Entity
public class Order {

    @PrimaryKey(autoGenerate = true)
    private Long id;
    private String title;
    private int productId;
    private int categoryId;
    private int status;
    private String city;
    private String state;
    private String zip;
    private String country;
    private String phone;
    private String addressLine;

    public Order() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddressLine() {
        return addressLine;
    }

    public void setAddressLine(String addressLine) {
        this.addressLine = addressLine;
    }

    public void updateByProductDate(Product product) {
        productId = product.getId();
        categoryId = product.getCategoryId();
        title = product.getTitle();
    }

    public void updateByAddressesData(List<Address> addresses) {
        city = addresses.get(0).getLocality();
        state = addresses.get(0).getAdminArea();
        zip = addresses.get(0).getPostalCode();
        country = addresses.get(0).getCountryName();
        addressLine = addresses.get(0).getAddressLine(0);
    }

    public String getFormattedAddressLine() {
        if (TextUtils.isEmpty(addressLine)) return "Not defined location!";
        return new StringBuilder()
                .append("Address: ")
                .append(addressLine)
                .toString();
    }

    /**
     * Considered status as an int variable
     * and it defined by the conventional statusText value based on its int value
     *
     * @return
     */
    public String getFormattedStatus() {
        String statusText = null;
        switch (status) {
            default:
            case 0:
                statusText = "Pending";
                break;
            case 1:
                statusText = "In-process";
                break;
            case 2:
                statusText = "Delivery";
                break;
            case 3:
                statusText = "Delivered";
                break;
        }
        return new StringBuilder()
                .append("Delivery status: ")
                .append(statusText)
                .toString();
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", status=" + status +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zip='" + zip + '\'' +
                ", country='" + country + '\'' +
                ", phone='" + phone + '\'' +
                ", addressLine='" + addressLine + '\'' +
                '}';
    }


}
