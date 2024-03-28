package view.format.command;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public enum PlayCommandFormatType {
    MOVE(inputs -> new MoveCommandFormat(inputs.get(1), inputs.get(2))),
    STATUS(inputs -> new StatusCommandFormat()),
    END(inputs -> new EndCommandFormat()),
    QUIT(inputs -> new QuitCommandFormat());

    private static final String MOVE_COMMAND_DELIMITER = " ";
    private static final int COMMAND_INDEX = 0;

    private final Function<List<String>, PlayCommandFormat> playCommandFormat;

    PlayCommandFormatType(final Function<List<String>, PlayCommandFormat> playCommandFormat) {
        this.playCommandFormat = playCommandFormat;
    }

    public static PlayCommandFormat from(final String input) {
        final List<String> inputs = Arrays.asList(input.split(MOVE_COMMAND_DELIMITER));

        return Arrays.stream(values())
                .filter(value -> value.name().toLowerCase().equals(inputs.get(COMMAND_INDEX)))
                .map(format -> format.playCommandFormat.apply(inputs))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 커맨드입니다."));
    }
}
