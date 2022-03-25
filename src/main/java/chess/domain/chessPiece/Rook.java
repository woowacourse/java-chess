package chess.domain.chessPiece;

import chess.domain.position.Position;
import java.util.List;

public class Rook extends ChessPiece {

    private static final String NAME = "R";
    private static final Double VALUE = 5.0;

    public Rook(final Color color) {
        super(color, NAME, VALUE);
    }

    @Override
    public List<Position> getInitWhitePosition() {
        return List.of(Position.from("a1"), Position.from("h1"));
    }

    @Override
    public List<Position> getInitBlackPosition() {
        return List.of(Position.from("a8"), Position.from("h8"));
    }

    @Override
    public void canMove(final Position from, final Position to) {
        final boolean sameFile = from.isSameFile(to);
        final boolean sameRank = from.isSameRank(to);

        if (!sameFile && !sameRank) {
            throw new IllegalArgumentException("해당 기물이 갈 수 없는 위치입니다.");
        }
    }
}
