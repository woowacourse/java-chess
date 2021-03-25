package chess.domain.game.state;

import chess.domain.board.Board;
import chess.domain.piece.Color;

public class WhiteWin extends Finished {

    public WhiteWin(Board board) {
        super(board);
    }

    @Override
    public String finishReason() {
        return "흰색의 승리로 종료되었습니다.";
    }

    @Override
    public Color winner() {
        return Color.WHITE;
    }
}
