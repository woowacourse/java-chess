package chess.domain.game.state;

import chess.domain.Board;
import chess.domain.piece.Color;

public class Complete extends End {

    public Complete(Board board) {
        super(board);
    }

    @Override
    public Color judgeWinner() {
        return board.getWinnerTeamColor();
    }
}
