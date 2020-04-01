package chess.domain;

import chess.domain.board.Board;
import chess.domain.judge.Judge;

public class ChessContext {
    private final Board board;
    private final Judge judge;

    public ChessContext(final Board board, final Judge judge) {
        this.board = board;
        this.judge = judge;
    }

    public Board getBoard() {
        return board;
    }

    public Judge getJudge() {
        return judge;
    }
}
