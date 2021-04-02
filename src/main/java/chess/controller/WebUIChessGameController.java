package chess.controller;

import chess.domain.board.Board;
import chess.domain.command.Command;
import chess.domain.command.Commands;
import chess.domain.game.ChessGame;
import java.util.List;

public class WebUIChessGameController {

    public ChessGame chessGame() {
        return new ChessGame(new Board());
    }

    public ChessGame chessGame(List<String> commandsStrings) {
        ChessGame chessGame = new ChessGame(new Board());
        Commands commands = Commands.initCommands(chessGame);

        for (String commandString : commandsStrings) {
            Command command = commands.matchedCommand(commandString);
            command.execution(commandString);
        }

        return chessGame;
    }
}
