package chess.model.status;

import java.util.Arrays;
import java.util.Locale;
import java.util.function.Supplier;

public enum StatusType {

    END(End::new),
    READY(Ready::new),
    RUNNING(Running::new),
    ;

    private final Supplier<Status> statusMapper;

    StatusType(Supplier<Status> statusMapper) {
        this.statusMapper = statusMapper;
    }

    public static Status findStatus(String status) {
        return Arrays.stream(values())
                .filter(value -> value.name().toLowerCase(Locale.ROOT).equals(status))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("상태를 찾을 수 없습니다: " + status))
                .statusMapper.get();
    }

}
