
package chess.dto;

import chess.domain.piece.Piece;
import chess.domain.position.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BoardDto {
    private final List<GridDto> grids = new ArrayList<>();

    public BoardDto(Map<Position, Piece> chessBoard) {
        chessBoard.forEach((key, value) -> grids.add(new GridDto(key, value)));
    }

    public List<GridDto> getBoard() {
        return grids;
    }
}