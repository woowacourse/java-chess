package chess.model.board;

import chess.model.Color;
import chess.model.Type;
import chess.model.piece.Piece;
import chess.model.position.Position;

public class PieceSquare extends AbstractSquare {

    private final Piece piece;

    public PieceSquare(final Position position, final Piece piece) {
        super(position);
        this.piece = piece;
    }

    @Override
    public Square movePiece(final Position position) {
        return new PieceSquare(position, this.piece);
    }

    @Override
    public Type getType() {
        return piece.getType();
    }

    @Override
    public Color getColor() {
        return piece.getColor();
    }
}
