package chess.domain.game;

import chess.dto.MoveCommandDto;

public final class GameOver extends Started {

    public GameOver(ActivePieces chessmen) {
        super(chessmen);
    }

    @Override
    public Game moveChessmen(MoveCommandDto dto) {
        throw new UnsupportedOperationException("이미 종료된 게임입니다.");
    }

    @Override
    public boolean isEnd() {
        return true;
    }

    @Override
    public String toString() {
        return "GameOver{" + "chessmen=" + chessmen + '}';
    }
}
