package chess.dto;

import chess.domain.piece.Piece;
import chess.domain.position.Position;

public class SquareDto {

    private final Position position;
    private Piece piece;
    private String pieceName;
    private String pieceColor;

    public SquareDto(final Position position) {
        this.position = position;
    }

    public SquareDto(final Position position, final Piece piece) {
        this.position = position;
        this.piece = piece;
        this.pieceName = piece.representative().toUpperCase();
        this.pieceColor = piece.getColorName();
    }

    public Position getPosition() {
        return position;
    }

    public Piece getPiece() {
        return piece;
    }

    public String getPieceName() {
        return pieceName;
    }

    public String getPieceColor() {
        return pieceColor;
    }
}
