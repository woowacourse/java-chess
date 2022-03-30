package chess.domain.piece;

import chess.domain.game.Color;
import chess.domain.position.Position;

import java.util.List;

public class King extends ChessPiece {

    private static final String NAME = "K";
    private static final Double VALUE = 0.0;

    public King(Color color) {
        super(color, NAME, VALUE);
    }

    @Override
    public List<Position> getInitWhitePosition() {
        return List.of(new Position("e1"));
    }

    @Override
    public List<Position> getInitBlackPosition() {
        return List.of(new Position("e8"));
    }

    @Override
    public void canMove(Position from, Position to) {
        int fileDistance = Math.abs(from.fileDistance(to));
        int rankDistance = Math.abs(from.rankDistance(to));

        if (Math.abs(fileDistance) > 1 || Math.abs(rankDistance) > 1) {
            throw new IllegalArgumentException("해당 기물이 갈 수 없는 위치입니다.");
        }
    }

    @Override
    public boolean isKing() {
        return true;
    }
}
