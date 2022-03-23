package chess.domain.chessPiece;

import chess.domain.position.Position;

import java.util.List;

public class Rook extends ChessPiece {

    private static final String NAME = "R";

    public Rook(Color color) {
        super(color, NAME);
    }

    @Override
    public List<Position> getInitWhitePosition() {
        return List.of(new Position("a1"), new Position("h1"));
    }

    @Override
    public List<Position> getInitBlackPosition() {
        return List.of(new Position("a8"), new Position("h8"));
    }

    @Override
    public void canMove(Position from, Position to) {
        boolean sameFile = from.isSameFile(to);
        boolean sameRank = from.isSameRank(to);

        if (!sameFile && !sameRank) {
            throw new IllegalArgumentException("해당 기물이 갈 수 없는 위치입니다.");
        }
    }
}
