package chess.dto;

import chess.domain.chesspiece.ChessPiece;
import chess.domain.position.Position;

public class ChessPieceDto {

    private final String position;
    private final String pieceType;
    private final String color;

    private ChessPieceDto(final String position, final String pieceType, final String color) {
        this.position = position;
        this.pieceType = pieceType;
        this.color = color;
    }

    public static ChessPieceDto of(String position, String pieceType, String color) {
        return new ChessPieceDto(position, pieceType, color);
    }

    public Position getPosition() {
        return Position.from(position);
    }

    public ChessPiece getChessPiece() {
        return ChessPieceMapper.toChessPiece(pieceType, color);
    }
}
