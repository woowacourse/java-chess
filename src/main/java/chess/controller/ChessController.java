package chess.controller;

import chess.domain.Board;
import chess.domain.BoardInitializer;
import chess.domain.ChessGame;
import chess.domain.Command;
import chess.domain.piece.Bishop;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {

    public void run() {
        ChessGame chessGame = new ChessGame();
        while(chessGame.isRunning()) {
            Command command = InputView.inputCommand();
            command.apply(chessGame, command.name());
        }
    }

    public static void start(ChessGame chessGame, String command) {
        Board board = BoardInitializer.init();
        OutputView.printBoard(board);
    }

    public static void end(ChessGame chessGame, String command){
        chessGame.endGame();
    }

    public static void status(ChessGame chessGame, String command) {
    }

    public static void move(ChessGame chessGame, String command) {
    }
}
