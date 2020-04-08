package chess.dto;

import java.util.List;
import java.util.stream.Collectors;

import chess.domain.game.Board;

public class BoardDto {
    private List<String> board;

    public BoardDto(Board board) {
        this.board = board.getBoard()
            .values()
            .stream()
            .map(piece -> piece.isBlack() ? piece.symbol().toUpperCase() : piece.symbol())
            .collect(Collectors.toList());
    }

    public List<String> getBoard() {
        return board;
    }

    public void setBoard(List<String> board) {
        this.board = board;
    }
}
