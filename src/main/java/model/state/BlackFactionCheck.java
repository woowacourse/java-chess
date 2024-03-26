package model.state;

import model.piece.Color;
import model.piece.Piece;
import model.position.Position;

import java.util.Map;

public final class BlackFactionCheck implements FactionState {
    @Override
    public void checkSameFaction(final Piece piece) {
        if (piece.color() != Color.BLACK) {
            throw new IllegalArgumentException("현재 해당 진영의 차례가 아닙니다.");
        }
    }

    @Override
    public FactionState pass() {
        return new WhiteFaction();
    }

    @Override
    public boolean isCheck(final Map<Position, Piece> chessBoard) {
        Position kingPosition = positionOfKing(chessBoard, Color.BLACK);
        Map<Position, Piece> enemyFaction = factionOf(chessBoard, Color.WHITE);
        boolean isCheck = enemyFaction.entrySet()
                                      .stream()
                                      .anyMatch(entry -> possibleAttacked(chessBoard, kingPosition, entry));
        if (isCheck) {
            throw new IllegalArgumentException("해당 방향으로의 이동은 Check를 해소할 수 없습니다.");
        }
        return false;
    }

    @Override
    public FactionState check() {
        return this;
    }
}
