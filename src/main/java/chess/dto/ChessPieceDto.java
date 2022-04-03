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

    public static ChessPieceDto of(String position, String pieceType, String color) {
        return new ChessPieceDto(Position.from(position), ChessPieceMapper.toChessPiece(pieceType, color));
    }

    public Position getPosition() {
        return position;
    }

    public ChessPiece getChessPiece() {
        return chessPiece;
    }
}
