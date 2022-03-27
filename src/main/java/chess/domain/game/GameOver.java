package chess.domain.game;

import chess.domain.piece.ActivePieces;
import chess.dto.MoveCommandDto;

final class GameOver extends Started {

    GameOver(ActivePieces chessmen) {
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
