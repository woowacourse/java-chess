package chess.domain.state;

import chess.domain.board.Board;
import chess.domain.board.BoardCalculator;
import chess.domain.board.BoardInitializer;
import chess.domain.piece.Color;
import chess.domain.position.Position;

public final class Finish extends State {

    private final Color winnerColor;

    public Finish(final Color winnerColor, final Board board) {
        this.winnerColor = winnerColor;
        this.board = board;
    }

    @Override
    public State start() {
        return new Running(Color.WHITE, new Board(new BoardInitializer()));
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public State move(Position from, Position to) {
        throw new IllegalStateException("이번 게임은 끝났습니다.");
    }

    @Override
    public Status status() {
        final var boardCalculator = new BoardCalculator(board.getValue());
        return new Status(Result.from(winnerColor), boardCalculator.sumScore(Color.WHITE), boardCalculator.sumScore(Color.BLACK));
    }
}
