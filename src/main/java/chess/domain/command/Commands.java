package chess.domain.command;

import chess.domain.game.ChessGame;
import java.util.Arrays;
import java.util.List;

public class Commands {

    private final List<Command3> commands;

    private Commands(List<Command3> commands) {
        this.commands = commands;
    }

    public static Commands initCommands(ChessGame chessGame) {
        return new Commands(Arrays.asList(
            new Start(chessGame),
            new Move(chessGame),
            new End(chessGame),
            new Status(chessGame)
        ));
    }

    public Command3 matchedCommand(String text) {
        return this.commands.stream()
            .filter(command -> command.isMatchedCommand(text))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("수행할 수 없는 명령어 입니다."));
    }
}
