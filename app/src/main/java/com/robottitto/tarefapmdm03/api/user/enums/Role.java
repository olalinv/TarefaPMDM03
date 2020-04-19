package com.robottitto.tarefapmdm03.api.user.enums;

public enum Role {

    CUSTOMER(0),
    ADMIN(1);

    private final int role;

    Role(int role) {
        this.role = role;
    }

    public int getRole() {
        return role;
    }

}
