package chess.controller;

import chess.domain.board.ChessBoard;
import chess.domain.board.ChessBoardFactory;
import chess.domain.state.ChessState;
import chess.domain.state.Initialize;
import chess.domain.state.command.Command;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.List;

public class ChessController {

    public void run() {
        OutputView.printStartMessage();
        final ChessBoard chessBoard = ChessBoardFactory.create();
        final ChessState state = new Initialize(chessBoard);
        run(chessBoard, state);
    }

    private void run(final ChessBoard chessBoard, final ChessState state) {
        ChessState current = state;
        while (current.executable()) {
            current = execute(chessBoard, current);
        }
    }

    private ChessState execute(final ChessBoard chessBoard, final ChessState state) {
        ChessState current = state;
        try {
            final List<String> command = InputView.readCommand();
            current = state.execute(Command.parse(command));
            OutputView.showBoard(chessBoard.pieces());
        } catch (final Exception e) {
            OutputView.error(e.getMessage());
        }
        return current;
    }
}
