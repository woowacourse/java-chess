package chess.model.board;

import chess.model.Color;
import chess.model.Type;
import chess.model.piece.Piece;
import chess.model.piece.PieceColor;
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
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean isSameTeam(final PieceColor pieceColor) {
        return piece.isSameTeam(pieceColor);
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
