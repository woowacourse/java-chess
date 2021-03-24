package chess.domain;

import chess.domain.board.*;
import chess.domain.piece.Team;

public class ChessGame {
    private static final String END = "end";

    private Board board;
    private boolean isStart;

    public void settingBoard() {
        board = BoardFactory.create();
        isStart = false;
    }

    public void start() {
        board.calculateScore();
        isStart = true;
    }

    public boolean move(String target, String destination) {
        if (board.movePiece(convertStringToPosition(target), convertStringToPosition(destination))) {
            return true;
        }
        board.calculateScore();
        return false;
    }

    public double status(Team team) {
        return board.score(team);
    }

    public String end() {
        return END;
    }

    private Position convertStringToPosition(String input) {
        return Position.of(Horizontal.find(input.substring(0, 1)),
                Vertical.find(input.substring(1, 2)));
    }

    public boolean isBeforeStart() {
        return !isStart;
    }

    public Board getBoard() {
        return board;
    }
}
