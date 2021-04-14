package chess.dto;

import chess.domain.board.Board;
import chess.domain.piece.Team;

import java.util.Map;

import static java.util.stream.Collectors.toMap;

public class BoardDto {
    private static final String END_FALSE = "false";

    private Map<String, PieceDto> board;
    private String turn;
    private String end;

    public BoardDto(Board board, Team turn) {
        this(board, turn.name(), END_FALSE);
    }

    public BoardDto(Board board, String turn, String end) {
        this.board = board.getBoard()
                .entrySet()
                .stream()
                .collect(toMap(entry -> entry.getKey().convertToString(), entry -> new PieceDto(entry.getValue())));
        this.turn = turn;
        this.end = end;
    }

    public Map<String, PieceDto> getBoard() {
        return board;
    }

    public void setBoard(Map<String, PieceDto> board) {
        this.board = board;
    }

    public String getTurn() {
        return turn;
    }

    public void setTurn(String turn) {
        this.turn = turn;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }
}
