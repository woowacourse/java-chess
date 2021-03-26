package chess.domain.game.state;

import chess.domain.board.Board;
import chess.domain.piece.Color;

public class BlackWin extends Finished {

    public BlackWin(Board board) {
        super(board);
    }

    @Override
    public String finishReason() {
        return "흑색의 승리로 게임이 종료되었습니다.";
    }

    @Override
    public Color winner() {
        return Color.BLACK;
    }
}
