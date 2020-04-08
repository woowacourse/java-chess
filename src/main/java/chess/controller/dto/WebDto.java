package chess.controller.dto;

public class WebDto {
    private String key;
    private Object value;

    public WebDto(final String key, final Object value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public Object getValue() {
        return value;
    }
}