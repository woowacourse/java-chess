package chess.domain.piece;

import chess.domain.piece.info.Color;
import chess.domain.piece.info.Name;
import chess.domain.position.Direction;
import chess.domain.position.Position;

public class Empty extends Piece {
    public Empty(Position position) {
        super(position, Name.NONE, Color.NONE);
    }

    public Empty() {
        this(Position.EMPTY, Name.NONE, Color.NONE);
    }

    public Empty(Position position, Name name, Color color) {
        super(position, name, color);
    }

    @Override
    public void move(Position target) {
        throw new IllegalArgumentException("[ERROR] 존재하지 않는 기물입니다.");
    }

    @Override
    public void checkMovable(Piece targetPiece, Direction direction) {
        throw new IllegalArgumentException("[ERROR] 존재하지 않는 기물입니다.");
    }
}
