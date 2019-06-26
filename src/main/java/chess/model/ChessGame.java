package chess.model;

import java.util.Arrays;
import java.util.List;

public class ChessGame {
    private Board board;
    private int turn;

    public ChessGame(CreateStrategy strategy, int turn) {
        this.turn = turn;
        board = new Board(strategy);
    }

    public void movePiece(String sourceCoordinate, String targetCoordinate) {
        if (isRightTurn(sourceCoordinate)) {
            board.movePiece(Arrays.asList(sourceCoordinate, targetCoordinate));
            turn++;

            return;
        }

        throw new IllegalArgumentException("다음턴에 움직여야합니다.");
    }

    public boolean isRightTurn(String sourceCoordinate) {
        return board.isRightTurn(sourceCoordinate, turn);
    }

    public List<String> convertToList() {
        return board.convertToList();
    }

    public ScoreResult calculateScore() {
        return board.makeScoreResult();
    }

    public boolean checkKingDead() {
        return !board.checkKingAlive();
    }

    public String getCurrentTeam() {
        if (turn % 2 == 0) {
            return "black";
        }

        return "white";
    }
}
