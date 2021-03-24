package chess;

import chess.controller.ChessController;
import chess.domain.board.Board;
import chess.domain.command.*;
import chess.domain.game.ChessGame;
import chess.domain.piece.PieceFactory;

import java.util.HashMap;
import java.util.Map;

public class Application {

    public static void main(String[] args) {
        Board board = new Board(PieceFactory.createPieces());
        ChessGame chessGame = new ChessGame(board);
        Commands commands = initCommands(chessGame);

        ChessController chessController = new ChessController(board, chessGame, commands);
        chessController.run();
    }

    private static Commands initCommands(final ChessGame chessGame) {
        Map<String, Command> commandGroup = new HashMap<>();
        commandGroup.put("start", new StartCommand(chessGame));
        commandGroup.put("move", new MoveCommand(chessGame));
        commandGroup.put("end", new EndCommand(chessGame));
        commandGroup.put("status", new StatusCommand(chessGame));
        Commands commands = new Commands(commandGroup);

        return commands;
    }

}
