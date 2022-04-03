package chess.domain.game.state;

import chess.domain.Board;
import chess.domain.piece.Color;

public class Terminate extends End {

    private static final String NOT_EXIST_WINNER = "[ERROR] 게임이 종료되어 승자가 존재하지 않습니다.";

    public Terminate(Board board) {
        super(board);
    }

    @Override
    public Color judgeWinner() {
        throw new IllegalStateException(NOT_EXIST_WINNER);
    }

    @Override
    public boolean isTerminate() {
        return true;
    }
}
