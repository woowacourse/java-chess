package chess.domain.game.gamestate;

import chess.domain.board.Board;
import chess.domain.position.Position;

class FinishedKing extends BeforeRunning {

    public FinishedKing(Board board) {
        super(board);
    }

    @Override
    public State move(Position from, Position to) {
        throw new IllegalStateException("게임 중이 아니어서 말을 움직일 수 없습니다.");
    }

    @Override
    public State showStatus() {
        throw new IllegalStateException("킹이 죽었을 경우 점수를 표시할 수 없습니다.");
    }

    @Override
    public State endGame() {
        throw new IllegalStateException("이미 게임이 종료되었습니다.");
    }
}
