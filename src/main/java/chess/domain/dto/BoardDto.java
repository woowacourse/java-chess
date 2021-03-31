package chess.domain.dto;

import chess.domain.board.Board;
import chess.domain.piece.Team;

import java.util.Map;

import static java.util.stream.Collectors.toMap;

public class BoardDto {
    private Map<String, PieceDto> board;
    private String turn;

    public BoardDto(Board board) {
        this.board = board.getBoard()
                .entrySet()
                .stream()
                .collect(toMap(entry -> entry.getKey().convertToString(), entry -> new PieceDto(entry.getValue())));
    }

    public BoardDto(Team turn) {
        this.turn = turn.name();
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
}
