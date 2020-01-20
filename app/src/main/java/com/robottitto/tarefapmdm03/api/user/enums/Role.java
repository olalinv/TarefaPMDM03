package com.robottitto.tarefapmdm03.api.user.enums;

public enum Role {

    CLIENTE(0),
    ADMINISTRADOR(1);

    private final int role;

    Role(int role) {
        this.role = role;
    }

    public int getRole() {
        return role;
    }

}
