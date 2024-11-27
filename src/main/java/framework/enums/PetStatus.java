package framework.enums;

import lombok.Getter;

@Getter
public enum PetStatus {

    AVAILABLE("available"),
    PENDING("pending"),
    SOLD("sold"),
    WRONG_STATUS("wrong status !!! !$$#4");

    private final String status;

    PetStatus(String status) {
        this.status = status;
    }

}
