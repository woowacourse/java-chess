package chess.service.dto;

import chess.board.Tile;
import chess.coordinate.Coordinate;
import chess.service.PieceNameConverter;

public class TileDto {
    private final String coordinate;
    private final String piece;

    public TileDto(Tile tile) {
        Coordinate coordinate = tile.getCoordinate();
        this.coordinate = coordinate.getRawKey();
        this.piece = PieceNameConverter.findTokenByPiece(tile.getPiece());
    }

    public String getCoordinate() {
        return coordinate;
    }

    public String getPiece() {
        return piece;
    }
}
