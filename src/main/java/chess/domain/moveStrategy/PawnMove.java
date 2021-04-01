package chess.domain.moveStrategy;

import chess.domain.location.Position;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;

import java.util.List;
import java.util.Map;

public class PawnMove implements MoveStrategy{
    private Color color;

    public PawnMove(Color color) {
        this.color = color;
    }

    @Override
    public List<Position> movablePositions(Position from, Map<Position, Piece> pieceByPosition) {
        return null;
    }
}
