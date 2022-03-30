package chess.domain.game;

import java.util.Map;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.position.Position;

public class Winner {

    private static final int TOTAL_KING_COUNT = 2;

    private Winner() {
    }

    public static Color from(Map<Position, Piece> boardPieces) {
        if (isAllKingExist(boardPieces)) {
            return Color.NONE;
        }
        return boardPieces.values().stream()
            .filter(Piece::isKing)
            .map(Piece::getColor)
            .findAny()
            .get();
    }

    private static boolean isAllKingExist(Map<Position, Piece> boardPieces) {
        return boardPieces.values().stream()
            .filter(Piece::isKing)
            .count() == TOTAL_KING_COUNT;
    }
}
