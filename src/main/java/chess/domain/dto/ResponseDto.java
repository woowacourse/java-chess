package chess.domain.dto;

public class ResponseDto<T> {

    private final T value;
    private final ResponseType type;

    private ResponseDto(T value, ResponseType type) {
        this.value = value;
        this.type = type;
    }

    public static <T extends BoardDto> ResponseDto<T> withBoard(T value) {
        return new ResponseDto<>(value, ResponseType.BOARD);
    }

    public static <T extends ScoreDto> ResponseDto<T> withScore(T value) {
        return new ResponseDto<>(value, ResponseType.SCORE);
    }

    public T getValue() {
        return value;
    }

    public ResponseType getType() {
        return type;
    }
}
