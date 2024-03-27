package model.state;

import model.piece.Piece;
import model.position.Position;

import java.util.Map;

public final class BlackFactionCheck extends BlackFaction {

    @Override
    public boolean isCheck(final Map<Position, Piece> chessBoard) {
        if (super.isCheck(chessBoard)) {
            throw new IllegalArgumentException("해당 방향으로의 이동은 Check를 해소할 수 없습니다.");
        }
        return false;
    }

    @Override
    public FactionState check() {
        return this;
    }
}
