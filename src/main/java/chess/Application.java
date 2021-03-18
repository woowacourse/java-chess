package chess;

import chess.controller.ChessController;
import chess.domain.board.Board;
import chess.domain.command.Commands;
import chess.domain.command.EndCommand;
import chess.domain.command.MoveCommand;
import chess.domain.command.StartCommand;
import chess.domain.game.ChessGame;
import chess.domain.piece.PieceFactory;

import java.util.Arrays;

public class Application {

    public static void main(String[] args) {
        Board board = new Board(PieceFactory.createPieces());
        ChessGame chessGame = new ChessGame(board);

        Commands commands = new Commands(
                Arrays.asList(
                        new StartCommand(chessGame),
                        new MoveCommand(chessGame),
                        new EndCommand(chessGame))
        );

        ChessController chessController = new ChessController(board, chessGame, commands);
        chessController.run();
    }
}
