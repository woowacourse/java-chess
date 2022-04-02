package chess.domain.game.state;

import chess.domain.Board;
import chess.domain.piece.Color;

public final class Complete extends End {

    public Complete(Board board) {
        super(board);
    }

    @Override
    public Color judgeWinner() {
        return board.getWinnerTeamColor();
    }

    @Override
    public boolean isTerminate() {
        return false;
    }
}
