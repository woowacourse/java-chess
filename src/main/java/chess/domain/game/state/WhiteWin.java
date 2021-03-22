package chess.domain.game.state;

import chess.domain.board.Board;
import chess.domain.piece.team.Color;

public class WhiteWin extends Finished {
    public WhiteWin(Board board) {
        super(board);
    }

    @Override
    public String finishReason() {
        return Color.WHITE.name() + "의 승리로 종료되었습니다.";
    }
}
