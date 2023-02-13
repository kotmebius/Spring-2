package ru.khantemirov.mymarket.api;


import java.util.Collection;

public class  UserDto {
    private Long id;
    private String username;
    private String email;
    private String address;
    private String phone;
    private Collection<RoleDto> roles;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public Collection<RoleDto> getRoles() {
        return roles;
    }

    public void setRoles(Collection<RoleDto> roles) {
        this.roles = roles;
    }
}
