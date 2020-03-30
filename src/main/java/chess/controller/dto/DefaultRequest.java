package chess.controller.dto;

public class DefaultRequest<T> {
    private T data;

    public DefaultRequest(T data) {
        this.data = data;
    }

    public static DefaultRequest<Void> empty() {
        return new DefaultRequest<>(null);
    }

    public T getData() {
        return data;
    }
}
