package chess.domain.controller;

import chess.domain.ChessGame;
import chess.domain.board.Board;
import chess.view.Command;
import chess.view.InputView;
import chess.view.OutputView;

public class MainController {

    private final ChessGame chessGame;

    public MainController(final ChessGame chessGame) {
        this.chessGame = chessGame;
    }

    public void run() {
        Command command = InputView.readStart();

        if (command == Command.START) {
            Board board = chessGame.createBoard();
            OutputView.printBoard(board);
        }

        OutputView.printFinishMessage();
    }
}
