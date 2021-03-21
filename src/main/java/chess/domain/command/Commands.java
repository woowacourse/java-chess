package chess.domain.command;

import chess.domain.board.Board;
import chess.domain.board.InitBoardGenerator;
import chess.domain.game.ChessGame;
import java.util.Arrays;
import java.util.List;

public class Commands {

    private final List<Command> commands;

    private Commands(List<Command> commands) {
        this.commands = commands;
    }

    public static Commands initCommands(ChessGame chessGame) {
        return new Commands(Arrays.asList(
            new Start(chessGame),
            new Move(chessGame),
            new End(chessGame)
        ));
    }

    public Command matchedCommand(String text) {
        return commands.stream()
            .filter(command -> command.isMatchedCommand(text))
            .findFirst()
            .orElseThrow(IllegalArgumentException::new);
    }
}
