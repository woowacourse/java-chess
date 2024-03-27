package model.state;

import model.piece.Color;
import model.piece.Piece;
import model.position.Position;

import java.util.Map;

public sealed class WhiteFaction implements FactionState permits WhiteFactionCheck {
    @Override
    public void checkSameFaction(final Piece piece) {
        if (piece.color() != Color.WHITE) {
            throw new IllegalArgumentException("현재 해당 진영의 차례가 아닙니다.");
        }
    }

    @Override
    public FactionState pass() {
        return new BlackFaction();
    }

    @Override
    public boolean isCheck(final Map<Position, Piece> chessBoard) {
        Position kingPosition = positionOfKing(chessBoard, Color.WHITE);
        Map<Position, Piece> enemyFaction = factionOf(chessBoard, Color.BLACK);
        return enemyFaction.entrySet()
                           .stream()
                           .anyMatch(entry -> possibleAttacked(chessBoard, kingPosition, entry));
    }

    @Override
    public FactionState check() {
        return new WhiteFactionCheck();
    }
}
