package chess.game;

import chess.board.Board;

public class Finished extends Started {

    Finished(Board board) {
        super(board);
    }

    @Override
    public GameState start() {
        throw new UnsupportedOperationException("[ERROR] 이미 게임이 시작되었습니다.");
    }

    @Override
    public GameState finish() {
        throw new UnsupportedOperationException("[ERROR] 이미 게임이 끝났습니다.");
    }
}
