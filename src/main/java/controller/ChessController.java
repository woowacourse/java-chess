package controller;

import domain.board.Board;
import domain.board.Position;
import domain.piece.Piece;
import java.util.Map;
import view.InputView;
import view.OutputView;

public final class ChessController {

    private static final String INVALID_GAME_OPTION = "start 또는 end만 입력해야 합니다.";
    private static final String START = "start";
    private static final String END = "end";

    public void run() {
        try {
            final Board board = Board.create();
            play(board.getPieces());
        } catch (IllegalArgumentException e) {
            OutputView.printError(e.getMessage());
        }
    }

    private void play(final Map<Position, Piece> pieces) {
        final String gameOption = InputView.readGameOption();
        validateGameOption(gameOption);

        if (END.equals(gameOption)) {
            return;
        }

        OutputView.printBoard(pieces);
        play(pieces);
    }

    private void validateGameOption(final String gameOption) {
        if (START.equals(gameOption) || END.equals(gameOption)) {
            return;
        }

        throw new IllegalArgumentException(INVALID_GAME_OPTION);
    }
}
