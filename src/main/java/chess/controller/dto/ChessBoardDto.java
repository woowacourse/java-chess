package chess.controller.dto;

import chess.domain.chesspiece.Piece;
import chess.domain.position.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ChessBoardDto {
    private final List<Tile> tiles = new ArrayList<>();

    public ChessBoardDto(Map<Position, Piece> chessBoard) {
        for (Map.Entry<Position, Piece> entry :chessBoard.entrySet()) {
            tiles.add(new Tile(entry.getKey(), entry.getValue()));
        }
    }

    class Tile {
        private final String position;
        private final String piece;

        Tile (Position position, Piece piece) {
            this.position = position.getRow().getValue().toString() + position.getColumn().getValue();
            this.piece = (piece.getPieceInfo().name() + "_" +piece.getPlayer()).toLowerCase();
        }

        public String getPosition() {
            return position;
        }

        public String getPiece() {
            return piece;
        }
    }
}
