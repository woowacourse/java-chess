package chess.dto;

import chess.domain.board.Tile;
import chess.domain.piece.Piece;

import java.util.Map;

public class ChessBoardDto {
    private Map<Tile, Piece> board;

    public ChessBoardDto(Map<Tile, Piece> board) {
        this.board = board;
    }

    public Map<Tile, Piece> getBoard() {
        return board;
    }

    public void setBoard(Map<Tile, Piece> board) {
        this.board = board;
    }
}
