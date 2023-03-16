package chess.controller;

import chess.domain.board.ChessBoard;
import chess.domain.state.ChessState;
import chess.domain.state.Initialize;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.List;

public class ChessController {

    public ChessController() {
    }

    public void start() {
        OutputView.printStartMessage();
        final ChessBoard chessBoard = ChessBoard.create();
        ChessState state = new Initialize(chessBoard);

        while (state.runnable()) {
            try {
                List<String> command = InputView.readCommand();

                state = state.command(new ChessState.Command(command));
                OutputView.showBoard(chessBoard.pieces());
            } catch (Exception e) {
                System.out.println("[ERROR] " + e.getMessage());
            }
        }
    }
}
