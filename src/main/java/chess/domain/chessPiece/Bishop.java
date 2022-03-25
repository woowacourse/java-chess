package chess.domain.chessPiece;

import chess.domain.position.Position;
import java.util.List;

public class Bishop extends ChessPiece {

    private static final String NAME = "B";
    private static final Double VALUE = 3.0;

    public Bishop(final Color color) {
        super(color, NAME, VALUE);
    }

    @Override
    public List<Position> getInitWhitePosition() {
        return List.of(Position.from("c1"), Position.from("f1"));
    }

    @Override
    public List<Position> getInitBlackPosition() {
        return List.of(Position.from("c8"), Position.from("f8"));
    }

    @Override
    public void canMove(final Position from, final Position to) {
        final int rankDistance = Math.abs(from.rankDistance(to));
        final int fileDistance = Math.abs(from.fileDistance(to));

        if (fileDistance != rankDistance) {
            throw new IllegalArgumentException("해당 기물이 갈 수 없는 위치입니다.");
        }
    }
}
