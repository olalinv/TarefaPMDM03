package com.robottitto.tarefapmdm03.api.order.enums;

public enum Status {

    PENDING(0),
    ACCEPTED(1),
    REJECTED(2);

    private final int status;

    Status(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

}
