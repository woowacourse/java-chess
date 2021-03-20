package chess.domain;

import chess.domain.piece.Team;

public class PrintBoardDto {
    private final Board board;
    private final Team turn;

    public PrintBoardDto(Board board, Team turn) {
        this.board = board;
        this.turn = turn;
    }

    public Board getBoard() {
        return board;
    }

    public Team getTurn() {
        return turn;
    }
}
