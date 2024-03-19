package model;

import java.util.regex.Pattern;

public enum Command {

    START("start"),
    MOVE("move [a-h][1-8] [a-h][1-8]"),
    END("end")
    ;

    private final String value;

    Command(String value) {
        this.value = value;
    }

    public static Command from(String value) {
        for (Command command : values()) {
            if (Pattern.compile(command.value).matcher(value).matches()) {
                return command;
            }
        }
        throw new IllegalArgumentException("값 없음");
        //TODO : 메세지 변경
    }
}
