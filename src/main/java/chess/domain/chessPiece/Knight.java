package chess.domain.chessPiece;

import chess.domain.position.Position;
import java.util.List;
import java.util.Stack;

public class Knight extends ChessPiece {

    private static final String NAME = "N";
    private static final Double VALUE = 2.5;

    public Knight(final Color color) {
        super(color, NAME, VALUE);
    }

    @Override
    public List<Position> getInitWhitePosition() {
        return List.of(Position.from("b1"), Position.from("g1"));
    }

    @Override
    public List<Position> getInitBlackPosition() {
        return List.of(Position.from("b8"), Position.from("g8"));
    }

    @Override
    public void canMove(final Position from, final Position to) {
        final int fileDistance = Math.abs(from.fileDistance(to));
        final int rankDistance = Math.abs(from.rankDistance(to));

        if (rankDistance + fileDistance != 3) {
            throw new IllegalArgumentException("해당 기물이 갈 수 없는 위치입니다.");
        }

        if (from.isSameFile(to) || from.isSameRank(to)) {
            throw new IllegalArgumentException("해당 기물이 갈 수 없는 위치입니다.");
        }
    }

    @Override
    public Stack<Position> findRoute(final Position from, final Position to) {
        return new Stack<>();
    }
}
