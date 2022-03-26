package chess.domain.game.gamestate;

import chess.domain.position.Position;

public class Ready extends BeforeRunning {

    @Override
    public State startGame() {
        return new RunningWhite();
    }

    @Override
    public State endGame() {
        return new Finished();
    }

    @Override
    public State move(Position from, Position to) {
        throw new IllegalStateException("게임 시작 전 말을 움직일 수 없습니다.");
    }

    @Override
    public State showStatus() {
        throw new IllegalStateException("게임 시작 전 점수를 보여줄 수 없습니다.");
    }
}
