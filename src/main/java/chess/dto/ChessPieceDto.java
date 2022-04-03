package chess.dto;

import chess.domain.chesspiece.ChessPiece;
import chess.domain.position.Position;

public class ChessPieceDto {

    private final Position position;
    private final ChessPiece chessPiece;

    private ChessPieceDto(final Position position, final ChessPiece chessPiece) {
        this.position = position;
        this.chessPiece = chessPiece;
    }

    public static ChessPieceDto of(Position from, String pieceType, String color) {
        return new ChessPieceDto(from, ChessPieceMapper.findBy(pieceType, color));
    }

    public Position getPosition() {
        return position;
    }

    public ChessPiece getChessPiece() {
        return chessPiece;
    }
}
