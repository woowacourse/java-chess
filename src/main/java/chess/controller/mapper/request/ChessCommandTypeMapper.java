package chess.controller.mapper.request;

import chess.domain.game.ChessCommandType;
import java.util.Arrays;

public enum ChessCommandTypeMapper {
    START(ChessCommandType.START,"start"),
    MOVE(ChessCommandType.MOVE,"move"),
    END(ChessCommandType.END,"end"),
    ;

    private final ChessCommandType command;
    private final String commandInput;


    ChessCommandTypeMapper(ChessCommandType command, String commandInput) {
        this.command = command;
        this.commandInput = commandInput;
    }

    public static ChessCommandType toChessCommandType(String inputCommand) {
        return Arrays.stream(ChessCommandTypeMapper.values())
                .filter(it -> it.commandInput.equals(inputCommand))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 명령어 입니다."))
                .command;
    }
}
