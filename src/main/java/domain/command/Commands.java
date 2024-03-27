package domain.command;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.regex.Pattern;

public enum Commands {
    START(Pattern.compile("start"), Start.getInstance()),
    MOVE(Pattern.compile("move ([a-h][1-8]) ([a-h][1-8])"), Move.getInstance()),
    END(Pattern.compile("end"), End.getInstance()),

    ;

    private final Pattern pattern;
    private final Command command;

    Commands(final Pattern pattern, final Command command) {
        this.pattern = pattern;
        this.command = command;
    }

    public static Command from(final String command) {
        return Arrays.stream(values())
                .filter(value -> value.pattern.matcher(command).matches())
                .map(value -> value.command)
                .findAny()
                .orElseThrow(() -> new NoSuchElementException(String.format("찾으려는 커맨드: %s,가 존재하지 않습니다.", command)));
    }
}
