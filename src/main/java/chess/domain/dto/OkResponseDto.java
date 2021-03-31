package chess.domain.dto;

public class OkResponseDto<T> {

    private final ResponseStatus status;
    private final T payload;

    private OkResponseDto(ResponseStatus status, T payload) {
        this.status = status;
        this.payload = payload;
    }

    public static <T> OkResponseDto<T> payload(T payload) {
        return new OkResponseDto<>(ResponseStatus.OK, payload);
    }

    public String getStatus() {
        return status.getStatus();
    }

    public T getPayload() {
        return payload;
    }
}
