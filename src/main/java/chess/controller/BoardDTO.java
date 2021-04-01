package chess.controller;

import chess.domain.location.Position;
import chess.domain.piece.Piece;

import java.util.Map;

public class BoardDTO {
    Map<Position, Piece> board;

    public BoardDTO(Map<Position, Piece> board) {
        this.board = board;
    }

    public void setBoard(Map<Position, Piece> board) {
        this.board = board;
    }

    public Map<Position, Piece> getBoard() {
        return board;
    }
}
