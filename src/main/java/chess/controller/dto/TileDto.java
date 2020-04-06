package chess.controller.dto;

import chess.domain.chesspiece.Piece;
import chess.domain.position.Position;

public class TileDto {
    private final String position;
    private final PieceDto pieceDto;

    public TileDto(Position position, Piece piece) {
        this.position = position.getRow().getValue().toString() + position.getColumn().getValue();
        this.pieceDto = new PieceDto(piece.getPieceInfo().toString(), piece.getPlayer());
    }

    public String getPosition() {
        return position;
    }

    public String getPieceName() {
        return pieceDto.getName();
    }

    public String getPiecePlayer() {
        return pieceDto.getPlayer().toString();
    }
}
