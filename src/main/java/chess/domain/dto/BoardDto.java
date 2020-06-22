package chess.domain.dto;

import chess.domain.board.Board;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BoardDto {

    List<PieceDto> pieces;

    public BoardDto(Board board) {
        List<PieceDto> pieces = new ArrayList<>();
        Map<String, String> serializedBoard = board.serialize();
        for (Map.Entry<String, String> element : serializedBoard.entrySet()) {
            PieceDto pieceDto = new PieceDto(element.getKey(), element.getValue());
            pieces.add(pieceDto);
        }

        this.pieces = pieces;
    }

    public List<PieceDto> getPieces() {
        return pieces;
    }
}
