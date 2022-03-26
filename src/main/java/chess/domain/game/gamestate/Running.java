package chess.domain.game.gamestate;

abstract class Running extends Started {
    @Override
    public State startGame() {
        throw new IllegalStateException("이미 게임 중 입니다.");
    }

    @Override
    public State endGame() {
        return new Finished();
    }

    @Override
    public State showStatus() {
        return new Finished();
    }

}
