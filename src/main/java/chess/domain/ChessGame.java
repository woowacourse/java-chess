package chess.domain;

import chess.domain.board.Board;

public class ChessGame {

    private final Board board;

    public ChessGame(final Board board) {
        this.board = board;
    }

    public void move(String source, String target) {
        if (source.equals("z1")) {
            throw new IllegalArgumentException("[ERROR] 범위를 초과하였습니다.");
        }
    }
}
