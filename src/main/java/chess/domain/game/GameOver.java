package chess.domain.game;

import chess.dto.MovePositionCommandDto;

public class GameOver extends Started {

    public GameOver(ActivePieces chessmen) {
        super(chessmen);
    }

    @Override
    public Game moveChessmen(MovePositionCommandDto dto) {
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
