package chess.domain.piece;

import chess.domain.position.Position;

public class Blank extends Piece {
    private static final String INITIAL_NAME = ".";

    public Blank() {
        super(false, INITIAL_NAME);
    }

    @Override
    public boolean canMove(Position source, Position target, Piece piece) {
        throw new IllegalStateException("비어 있는 칸입니다.");
    }

    @Override
    public double getScore() {
        return 0;
    }
}
