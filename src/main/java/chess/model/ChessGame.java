package chess.model;

import chess.model.board.Board;
import chess.model.gameCreator.BoardCreatingStrategy;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class ChessGame {
    public static final int COUNT_OF_TEAM = 2;

    private Board board;
    private int turn;

    public ChessGame(BoardCreatingStrategy strategy, int turn) {
        if (Objects.isNull(strategy)) {
            throw new NullPointerException();
        }
        if (turn < 0) {
            throw new IllegalArgumentException("잘못된 턴이 입력되었습니다.");
        }

        this.turn = turn;
        board = new Board(strategy);
    }

    public void movePiece(String sourceCoordinate, String targetCoordinate) {
        if (Objects.isNull(sourceCoordinate) || Objects.isNull(targetCoordinate)) {
            throw new NullPointerException();
        }
        if (isRightTurn(sourceCoordinate)) {
            board.movePiece(Arrays.asList(sourceCoordinate, targetCoordinate));
            turn++;

            return;
        }

        throw new IllegalArgumentException("다음턴에 움직여야합니다.");
    }

    private boolean isRightTurn(String sourceCoordinate) {
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
        if (turn % COUNT_OF_TEAM == 0) {
            return "black";
        }

        return "white";
    }
}
