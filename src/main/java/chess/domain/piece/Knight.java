package chess.domain.piece;

import chess.domain.piece.info.Color;
import chess.domain.piece.info.Name;
import chess.domain.piece.info.Score;
import chess.domain.position.Direction;
import chess.domain.position.Position;

public class Knight extends Piece {
    public Knight(Position position, Color color) {
        super(position, Name.KNIGHT, color, new Score(2.5));
    }

    @Override
    public void checkMovable(Piece targetPiece, Direction direction) {
        if (!Direction.knightDirection().contains(direction)) {
            throw new IllegalArgumentException("[ERROR] 올바른 방향이 아닙니다.");
        }
    }
}
