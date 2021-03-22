package chess.domain.game.state;

import chess.domain.board.Board;

public class Draw extends Finished {
    public Draw(Board board) {
        super(board);
    }

    @Override
    public String finishReason() {
        return "게임 진행 중 end 명령어 입력으로 무승부 종료되었습니다.";
    }
}
