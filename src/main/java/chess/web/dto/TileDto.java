package chess.web.dto;

import chess.board.Tile;
import chess.coordinate.Coordinate;
import chess.web.render.WebPieceImageNames;

public class TileDto {
    private final String coordinate;
    private final String piece;

    public TileDto(Tile tile) {
        Coordinate coordinate = tile.getCoordinate();
        this.coordinate = coordinate.getRawKey();
        this.piece = WebPieceImageNames.findTokenByPiece(tile.getPiece());
    }

    public String getCoordinate() {
        return coordinate;
    }

    public String getPiece() {
        return piece;
    }
}
