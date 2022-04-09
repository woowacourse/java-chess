package chess.dto;

import chess.domain.piece.Piece;
import chess.domain.piece.PieceFactory;
import chess.domain.position.Position;

public class SquareDto {

    private final Position position;
    private Piece piece;
    private String pieceName;
    private String pieceColor;

    public SquareDto(final Position position) {
        this.position = position;
    }

    public SquareDto(final String position) {
        this(Position.valueOf(position));
    }

    public SquareDto(final Position position, final Piece piece) {
        this.position = position;
        this.piece = piece;
        this.pieceName = piece.representative().toUpperCase();
        this.pieceColor = piece.getColorName();
    }

    public SquareDto(final String position, final String piece, final String color) {
        this.position = Position.valueOf(position);
        this.piece = PieceFactory.create(piece, color);
        this.pieceName = this.piece.representative().toUpperCase();
        this.pieceColor = color;
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
