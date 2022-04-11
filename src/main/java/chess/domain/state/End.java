package chess.domain.state;

import chess.domain.board.Board;
import chess.domain.board.BoardInitializer;
import chess.domain.piece.Color;
import chess.domain.position.Position;

public final class End extends CalculableState {

    private static final String END_MESSAGE = "게임이 끝났습니다";
    private final Color winnerColor;

    public End(final Color winnerColor, final Board board) {
        this.winnerColor = winnerColor;
        this.board = board;
    }

    @Override
    public State start() {
        return new Running(Color.WHITE, new Board(new BoardInitializer()));
    }

    @Override
    public boolean isEnd() {
        return true;
    }

    @Override
    public State move(Position from, Position to) {
        throw new IllegalStateException(END_MESSAGE);
    }

    @Override
    public Result getWinner() {
        return Result.from(winnerColor);
    }

    @Override
    public State end() {
        throw new IllegalStateException(END_MESSAGE);
    }
}
