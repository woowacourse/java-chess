package chess2.dto2;

public class MoveCommandDto {

    private final String from;
    private final String to;

    public MoveCommandDto(String from, String to) {
        this.from = from;
        this.to = to;
    }

    public String source() {
        return from;
    }

    public String target() {
        return to;
    }
}
