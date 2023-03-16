package controller;

import domain.board.Board;
import domain.board.InitialChessAlignment;
import domain.piece.Piece;
import domain.position.Position;
import java.util.Map;
import view.InputView;
import view.OutputView;

public final class ChessController {

    public void run() {
        try {
            final Board board = Board.create(new InitialChessAlignment());
            play(board.getPieces());
        } catch (IllegalArgumentException e) {
            OutputView.printError(e.getMessage());
        }
    }

    private void play(final Map<Position, Piece> pieces) {
        final Command gameOption = Command.from(InputView.readGameOption());

        if (Command.END.equals(gameOption)) {
            return;
        }

        OutputView.printBoard(pieces);
        play(pieces);
    }
}
