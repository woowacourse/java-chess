package chess.controller.web.dto;

import java.util.Objects;

public class StartResponseDto implements WebResponseDto{
    private final boolean isStart;

    public StartResponseDto(boolean isStart) {
        this.isStart = isStart;
    }

    public boolean isStart() {
        return isStart;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StartResponseDto)) return false;
        StartResponseDto that = (StartResponseDto) o;
        return isStart() == that.isStart();
    }

    @Override
    public int hashCode() {
        return Objects.hash(isStart());
    }
}
