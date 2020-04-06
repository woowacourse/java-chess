package chess.controller.dto;

public class WebDto {
    private String key;
    private String value;

    public WebDto(final String key, final String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
}
