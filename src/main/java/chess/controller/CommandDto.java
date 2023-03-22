package chess.controller;

import java.util.List;

public interface CommandDto {
    static CommandDto from(List<String> commandWithOptions) {
        validateLengthNotZero(commandWithOptions);

        if (commandWithOptions.size() == 1) {
            return new OptionLessCommandDto(commandWithOptions);
        }

        return MoveCommandDto.from(commandWithOptions);
    }

    private static void validateLengthNotZero(List<String> commandWithOptions) {
        if (commandWithOptions.size() == 0) {
            throw new IllegalArgumentException("커맨드가 존재하지 않습니다.");
        }
    }

    String getCommand();
}
