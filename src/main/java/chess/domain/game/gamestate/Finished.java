package chess.domain.game.gamestate;

import chess.domain.position.Position;

class Finished extends AfterRunning {
    @Override
    public State startGame() {
        return new RunningWhite();
    }

    @Override
    public State endGame() {
        throw new IllegalStateException("이미 게임이 끝난 상태입니다.");
    }

    @Override
    public State move(Position from, Position to) {
        throw new IllegalStateException("게임 중이 아니어서 말을 움직일 수 없습니다.");
    }

    @Override
    public State showStatus() {
        throw new IllegalStateException("이미 게임이 끝난 상태입니다.");
    }
}
