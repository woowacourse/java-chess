package model;

public enum Command {

    START("start"),
    END("end")
    ;

    private final String value;

    Command(String value) {
        this.value = value;
    }

    public static Command from(String value) {
        for (Command command : values()) {
            if (command.value.equals(value)) {
                return command;
            }
        }
        throw new IllegalArgumentException("값 없음");
        //TODO : 메세지 변경
    }
}
