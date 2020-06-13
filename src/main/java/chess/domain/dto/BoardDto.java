package chess.domain.dto;

import chess.domain.board.Board;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BoardDto {
    List<PieceDto> pieces;

    public BoardDto(Board board) {
        Map<String, PieceDto> serializedBoard = board.serialize();
        this.pieces = new ArrayList<>(serializedBoard.values());
    }

    public List<PieceDto> getPieces() {
        return pieces;
    }
}
