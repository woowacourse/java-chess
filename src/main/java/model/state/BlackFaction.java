package model.state;

import model.piece.Color;
import model.piece.Piece;
import model.position.Position;

import java.util.Map;

public sealed class BlackFaction implements FactionState permits BlackFactionCheck {
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
        return enemyFaction.entrySet()
                           .stream()
                           .anyMatch(entry -> possibleAttacked(chessBoard, kingPosition, entry));
    }

    @Override
    public FactionState check() {
        return new BlackFactionCheck();
    }
}
