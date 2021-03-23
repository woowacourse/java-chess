package chess.domain.dto;

import chess.domain.team.Team;
import chess.domain.team.Winner;

public class ResponseDto {

    private final char[][] board;
    private final double blackScore;
    private final double whiteScore;
    private final Winner winner;

    public static class Builder {
        private char[][] board;
        private double blackScore;
        private double whiteScore;
        private Winner winner;

        public Builder(char[][] board) {
            this.board = board;
        }

        public Builder blackScore(double blackScore) {
            this.blackScore = blackScore;
            return this;
        }

        public Builder whiteScore(double whiteScore) {
            this.whiteScore = whiteScore;
            return this;
        }

        public Builder winner(Winner winner) {
            this.winner = winner;
            return this;
        }

        public ResponseDto build() {
            return new ResponseDto(this);
        }
    }

    private ResponseDto(Builder builder) {
        this.board = builder.board;
        this.blackScore = builder.blackScore;
        this.whiteScore = builder.whiteScore;
        this.winner = builder.winner;
    }

    public char[][] getBoard() {
        return board;
    }

    public double getBlackScore() {
        return blackScore;
    }

    public double getWhiteScore() {
        return whiteScore;
    }

    public Winner getWinner() {
        return winner;
    }
}
