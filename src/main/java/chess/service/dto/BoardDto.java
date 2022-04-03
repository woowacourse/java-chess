package chess.service.dto;

import chess.controller.console.dto.PieceDto;
import chess.piece.Piece;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BoardDto {

    private final String turn;
    private final Map<String, String> board;
    //private final boolean isFinished;

    public BoardDto(String turn, Map<String, String> board) {
        this.turn = turn;
        this.board = board;
    }

    public Map<String, String> getBoard() {
        return board;
    }

    public String getTurn() {
        return turn;
    }

}
