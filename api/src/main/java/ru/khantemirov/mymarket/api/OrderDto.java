package ru.khantemirov.mymarket.api;


import java.util.List;

public class OrderDto {
    private Long id;
    private UserDto userdto;
    private List<OrderItemDto> items;
    private String address;
    private String phone;
    private int totalPrice;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserDto getUserdto() {
        return userdto;
    }

    public void setUserdto(UserDto userdto) {
        this.userdto = userdto;
    }

    public List<OrderItemDto> getItems() {
        return items;
    }

    public void setItems(List<OrderItemDto> items) {
        this.items = items;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }
}
