package domain.commend;

import domain.commend.exceptions.CommendTypeException;
import java.util.Arrays;

public class Commend {
    private CommendType commendType;

    public Commend() {
        this.commendType = CommendType.START;
    }

    public CommendType findCommend(String input) {
        return Arrays.stream(CommendType.values())
            .filter(type -> type.isCommend(input))
            .findFirst()
            .orElseThrow(() -> new CommendTypeException("잘못된 입력"));
    }

    public boolean isStart() {
        return commendType == CommendType.START;
    }
}
