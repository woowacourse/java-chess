package chess.game.status;

import chess.domain.board.BoardFactory;
import chess.domain.board.TurnTrackerBoard;
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
        TurnTrackerBoard turnTrackerBoard = new TurnTrackerBoard(BoardFactory.create(), FIRST_TURN_COLOR);
        outputView.printBoard(turnTrackerBoard);
        return new PlayingGame(turnTrackerBoard);
    }
}
