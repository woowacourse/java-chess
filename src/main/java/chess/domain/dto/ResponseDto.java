package chess.domain.dto;

public class ResponseDto {

    private final char[][] board;
    private final double blackScore;
    private final double whiteScore;

    public static class Builder {
        private char[][] board;
        private double blackScore;
        private double whiteScore;

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

        public ResponseDto build() {
            return new ResponseDto(this);
        }
    }

    private ResponseDto(Builder builder) {
        this.board = builder.board;
        this.blackScore = builder.blackScore;
        this.whiteScore = builder.whiteScore;
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
}
