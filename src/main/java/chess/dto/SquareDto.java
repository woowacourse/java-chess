package chess.dto;

public class SquareDto {

    private final String value;

    public SquareDto(String value) {
        this.value = dotToEmpty(value);
    }

    private String dotToEmpty(String value) {
        if (value.equals(".")) {
            return "";
        }
        return value;
    }

    public String getValue() {
        return value;
    }
}
