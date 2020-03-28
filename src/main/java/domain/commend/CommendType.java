package domain.commend;

import domain.commend.exceptions.CommendTypeException;
import java.util.Arrays;
import java.util.List;

public enum CommendType {
    START("start"),
    END("end"),
    MOVE("move");

    private String commend;

    CommendType(String commend) {
        this.commend = commend;
    }

    public static CommendType find(String input) {
        List<String> inputSplit = Arrays.asList(input.split(" "));
        return Arrays.stream(CommendType.values())
            .filter(type -> type.isSameType(inputSplit.get(0)))
            .findFirst()
            .orElseThrow(() -> new CommendTypeException(""));
    }

    private boolean isSameType(String input) {
        return commend.equals(input);
    }
}
