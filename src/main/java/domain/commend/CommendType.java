package domain.commend;

import domain.commend.exceptions.CommendTypeException;

import java.util.Arrays;

public enum CommendType {
    START("start"),
    END("end");

    private String commend;

    CommendType(String commend) {
        this.commend = commend;
    }

    public static CommendType answer(String input) {
        return Arrays.stream(CommendType.values())
            .filter(commendType -> commendType.commend.equals(input))
            .findFirst()
            .orElseThrow(() -> new CommendTypeException("start 또는 end를 입력하세요."));
    }
}
