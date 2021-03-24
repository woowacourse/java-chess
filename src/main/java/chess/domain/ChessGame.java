package chess.domain;

import chess.domain.board.*;
import chess.domain.piece.Team;

public class ChessGame {
    private static final String END = "end";
    private static final int OPTION_TARGET_INDEX = 0;
    private static final int OPTION_DESTINATION_INDEX = 1;
    private static final int OPTION_FINISH = 2;

    private Board board;
    private boolean isStart;

    public void settingBoard() {
        board = BoardFactory.create();
        isStart = false;
    }

    public void start() {
        board.applyStatus();
        isStart = true;
    }

    public boolean move(String target, String destination) {
        if (board.movePiece(convertStringToPosition(target), convertStringToPosition(destination))) {
            return true;
        }
        board.applyStatus();
        return false;
    }

    public double status(Team team) {
        return board.score(team);
    }

    public String end() {
        return END;
    }

    private Position convertStringToPosition(String input) {
        return Position.of(Horizontal.find(input.substring(OPTION_TARGET_INDEX, OPTION_DESTINATION_INDEX)),
                Vertical.find(input.substring(OPTION_DESTINATION_INDEX, OPTION_FINISH)));
    }

    public boolean isBeforeStart() {
        return !isStart;
    }

    public Board getBoard() {
        return board;
    }
}
