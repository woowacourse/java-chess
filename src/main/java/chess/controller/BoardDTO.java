package chess.controller;

import chess.domain.location.Position;
import chess.domain.piece.Piece;

import java.util.HashMap;
import java.util.Map;

public class BoardDTO {
    Map<PositionDTO, PieceDTO> board;

    public BoardDTO(Map<Position, Piece> board) {
        Map<PositionDTO, PieceDTO> maps = new HashMap<>();
        for (Position position : board.keySet()) {
            maps.put(new PositionDTO(position), new PieceDTO(board.get(position)));
        }
        this.board = maps;
    }

    public Map<PositionDTO, PieceDTO> getBoard() {
        return board;
    }

    public void setBoard(Map<PositionDTO, PieceDTO> board) {
        this.board = board;
    }
}
