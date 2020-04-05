package chess.controller.dto;

import chess.domain.chesspiece.Piece;
import chess.domain.position.Position;

public class Tile {
    private final String position;
    private final String piece;

    Tile(Position position, Piece piece) {
        this.position = position.getRow().getValue().toString() + position.getColumn().getValue();
        this.piece = (piece.getPieceInfo().name() + "_" + piece.getPlayer()).toLowerCase();
    }

    public String getPosition() {
        return position;
    }

    public String getPiece() {
        return piece;
    }
}
