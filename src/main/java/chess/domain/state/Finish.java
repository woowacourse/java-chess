package chess.domain.state;

import chess.domain.Status;
import chess.domain.board.Board;
import chess.domain.board.BoardInitializer;
import chess.domain.piece.Color;
import chess.domain.position.Position;

public final class Finish extends State {

    private final Color winnerColor;

    public Finish(final Board board, final Color color) {
        this.board = board;
        this.winnerColor = color;
    }

    @Override
    public State start() {
        return new WhiteTurn(new Board(new BoardInitializer()));
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
        return new Status(winnerColor, board);
    }
}
