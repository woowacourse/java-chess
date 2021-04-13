package chess.dto;

public class ErrorDto {
    private final String msg;

    public ErrorDto(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return "alert(\"" + msg + "\")";
    }
}
