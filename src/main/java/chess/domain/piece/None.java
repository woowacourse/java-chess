package chess.domain.piece;

import chess.domain.Position;

public class None implements ChessPiece {

    @Override
    public boolean canMove(Position origin, Position destination) {
        throw new IllegalStateException("움직일 말이 존재하지 않습니다.");
    }

    @Override
    public void move() {
        throw new IllegalStateException("움직일 말이 존재하지 않습니다.");
    }

    @Override
    public String name() {
        return "-";
    }
}
