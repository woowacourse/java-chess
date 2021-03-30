package chess.domain.piece;

import chess.domain.piece.direction.MoveStrategies;
import chess.domain.position.Position;

import java.util.List;

public class Blank extends Piece{
    private static final String SYMBOL = "blank";

    public Blank() {
        super(SYMBOL, Color.BLANK, new MoveStrategies(), Position.emptyPosition());
    }

    @Override
    public double score(List<Piece> pieces) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isKing() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isPawn() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isKnight() {
        throw new UnsupportedOperationException();
    }
}
