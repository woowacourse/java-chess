package domain;

import domain.piece.Pawn;
import domain.piece.Piece;

import java.util.stream.IntStream;

public class ScoreMachine {
    private Piece[][] board;

    public ScoreMachine(Piece[][] board) {
        this.board = board;
    }

    public Score blackPiecesScore() {
        Score score = new Score();

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j] != null && board[i][j].isBlack()) {
                    if (board[i][j] instanceof Pawn) {
                        score = addPawnScore(i, j, score);
                        continue;
                    }
                    score = score.sum(board[i][j].getScore());
                }
            }
        }

        return score;
    }

    private Score addPawnScore(int row, int column, Score score) {
        if (isExistSamePawn(row, column)) {
            return score.sum(board[row][column].getScore().half());
        }
        return score.sum(board[row][column].getScore());
    }

    private boolean isExistSamePawn(int row, int column) {
        return IntStream.range(0, Board.SIZE)
                .filter(i -> board[i][column] instanceof Pawn && i != row && board[i][column].isBlack() == board[row][column].isBlack())
                .findAny()
                .isPresent();
    }

    public Score whitePiecesScore() {
        Score score = new Score();

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j] != null && !board[i][j].isBlack()) {
                    if (board[i][j] instanceof Pawn) {
                        score = addPawnScore(i, j, score);
                        continue;
                    }
                    score = score.sum(board[i][j].getScore());
                }
            }
        }
        return score;
    }
}
