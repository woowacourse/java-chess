package chess.domain.dto;

import chess.domain.board.Board;

import java.util.Map;

import static java.util.stream.Collectors.toMap;

public class BoardInitializeDto {
    private Map<String, PieceDto> board;

    public BoardInitializeDto(Board board) {
        this.board = board.getBoard()
                .entrySet()
                .stream()
                .collect(toMap(entry -> entry.getKey().convertToString(), entry -> new PieceDto(entry.getValue())));
    }

    public Map<String, PieceDto> getBoard() {
        return board;
    }

    public void setBoard(Map<String, PieceDto> board) {
        this.board = board;
    }
}
