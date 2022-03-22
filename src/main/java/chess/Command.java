package chess;

public class Command {
    private final String value;

    public Command(String value) {
        validateMenu(value);
        this.value = value;
    }

    private void validateMenu(String value) {
        if (!value.equals("start") && !value.equals("end")) {
            throw new IllegalArgumentException("start 또는 end만 입력해주세요.");
        }
    }

    public boolean isEnd() {
        return value.equals("end");
    }
}
