package chess.web.dto;

import chess.domain.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.position.Position;

public class PieceDto {

    private final PieceType pieceType;
    private final Position position;
    private final Color color;

    public PieceDto(String pieceType, String position, String color) {
        this(PieceType.from(pieceType).newPiece(Color.from(color)), new Position(position));
    }

    public PieceDto(Piece piece, Position position) {
        this(piece, position, piece.getColor());
    }

    private PieceDto(Piece piece, Position position, Color color) {
        this.pieceType = piece.getPieceType();
        this.position = position;
        this.color = color;
    }

    public String getPieceType() {
        return pieceType.getNotation(Color.BLACK);
    }

    public String getPosition() {
        return position.getNotation();
    }

    public String getColor() {
        return color.getName();
    }
}
