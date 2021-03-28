package chess.domain.piece;

import chess.domain.piece.info.Color;
import chess.domain.piece.info.Name;
import chess.domain.position.Direction;
import chess.domain.position.Position;

public class Empty extends Piece {
    public Empty(Position position) {
        super(Name.NONE, Color.NONE, position);
    }

    @Override
    public void move(Position target) {
        throw new IllegalArgumentException("[ERROR] 존재하지 않는 기물입니다.");
    }

    @Override
    public void checkMovable(Piece targetPiece, Direction direction) {
        throw new IllegalArgumentException("[ERROR] 존재하지 않는 기물입니다.");
    }

    @Override
    public boolean isEmpty() {
        return true;
    }
}
