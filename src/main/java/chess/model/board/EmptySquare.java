package chess.model.board;

import chess.model.Color;
import chess.model.Type;
import chess.model.position.Position;

public class EmptySquare extends AbstractSquare {

    public EmptySquare(final Position position) {
        super(position);
    }

    @Override
    public Square movePiece(final Position position) {
        throw new UnsupportedOperationException("지원하지 않는 기능입니다.");
    }

    @Override
    public Type getType() {
        return DefaultType.EMPTY;
    }

    @Override
    public Color getColor() {
        return DefaultColor.EMPTY;
    }
}
