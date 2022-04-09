package chess.dto;

import java.util.Objects;

public class ResponseDto {

    private final String code;
    private final String message;
    private final String turn;

    public ResponseDto(final String code, final String message, final String turn) {
        this.code = code;
        this.message = message;
        this.turn = turn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ResponseDto that = (ResponseDto) o;
        return Objects.equals(code, that.code) && Objects.equals(message, that.message)
                && Objects.equals(turn, that.turn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, message, turn);
    }
}
