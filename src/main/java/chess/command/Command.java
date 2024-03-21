package chess.command;

import chess.dto.ArgumentDto;

import java.util.Collections;
import java.util.List;

public record Command(CommandType type, List<ArgumentDto> arguments) {
    public static Command createNoArgCommand(CommandType commandType) {
        return new Command(commandType, Collections.emptyList());
    }
}
