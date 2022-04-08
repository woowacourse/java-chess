package chess.dto;

import java.util.Objects;

public class MoveDto {

    private final String source;
    private final String target;

    public MoveDto(final String source, final String target) {
        this.source = source;
        this.target = target;
    }

    public void validateMoveDto() {
        if (Objects.isNull(source) || Objects.isNull(target)) {
            throw new IllegalArgumentException("[ERROR] 이동 입력값은 빈칸일 수 없습니다.");
        }
        if (source.length() != 2 || target.length() != 2) {
            throw new IllegalArgumentException("[ERROR] 이동 입력 커맨드가 잘못돠었습니다.");
        }
    }

    public String getSource() {
        return source;
    }

    public String getTarget() {
        return target;
    }
}
