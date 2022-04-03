package chess.domain.game;

import java.util.HashMap;
import java.util.Map;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.position.Position;

public class Winner {

    private static final int TOTAL_KING_COUNT = 2;
    private static final String ALL_KINGS_DEAD = "[ERROR] 킹이 모두 죽어서 승자를 구할 수 없습니다.";

    private final Map<Position, Piece> boardPieces;

    public Winner(final Map<Position, Piece> boardPieces) {
        this.boardPieces = new HashMap(boardPieces);
    }

    public Color getColor() {
        if (isAllKingAlive()) {
            return Color.NONE;
        }
        return judge();
    }

    private Color judge() {
        return boardPieces.values().stream()
            .filter(Piece::isKing)
            .map(Piece::getColor)
            .findAny()
            .orElseThrow(() -> new IllegalStateException(ALL_KINGS_DEAD));
    }

    private boolean isAllKingAlive() {
        return boardPieces.values().stream()
            .filter(Piece::isKing)
            .count() == TOTAL_KING_COUNT;
    }
}
