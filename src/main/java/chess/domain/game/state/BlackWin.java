package chess.domain.game.state;

import chess.domain.board.Board;
import chess.domain.piece.team.Color;

public class BlackWin extends Finished {
    public static final String BLACK_WINS = "black wins";

    public BlackWin(Board board) {
        super(board);
    }

    @Override
    public String finishReason() {
        return Color.BLACK.name() + "의 승리로 종료되었습니다.";
    }

    @Override
    public Color winner() {
        return Color.BLACK;
    }

    @Override
    public String toString() {
        return BLACK_WINS;
    }
}
