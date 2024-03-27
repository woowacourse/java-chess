package chess.game.status;

import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.domain.piece.Color;
import chess.view.input.InputView;
import chess.view.output.OutputView;

public class RestartGame implements GameStatus {
    private static final Color FIRST_TURN_COLOR = Color.WHITE;

    @Override
    public boolean isPlayable() {
        return true;
    }

    @Override
    public GameStatus play(final InputView inputView, final OutputView outputView) {
        Board board = BoardFactory.create();
        outputView.printBoard(board);
        return new PlayingGame(board, FIRST_TURN_COLOR);
    }
}
