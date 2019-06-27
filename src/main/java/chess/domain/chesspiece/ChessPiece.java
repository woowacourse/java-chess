package chess.domain.chesspiece;

import chess.domain.chesspoint.ChessPoint;
import chess.domain.util.Counter;

public interface ChessPiece {
    boolean checkRule(ChessPoint source, ChessPoint target, boolean opponentPieceOnTarget);

    Counter<Integer> countPiecesOnSameColumn(Counter<Integer> pawnCounter, int column);

    double getScore(Counter<Integer> pawnCounter, int column);

    String getName();

    default boolean hasName(String name) {
        return getName().equals(name);
    }
}