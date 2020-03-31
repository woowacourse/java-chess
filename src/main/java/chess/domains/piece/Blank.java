package chess.domains.piece;

import chess.domains.position.Position;

import java.util.Collections;
import java.util.List;

public class Blank extends Piece {
    public Blank() {
        super(PieceColor.BLANK, PieceType.BLANK.name, PieceType.BLANK.score);
    }

    @Override
    public boolean isValidMove(Position currentPosition, Position targetPosition) {
        return false;
    }

    @Override
    public List<Position> findRoute(Position source, Position target) {
        return Collections.emptyList();
    }
}
