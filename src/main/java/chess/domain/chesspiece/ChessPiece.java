package chess.domain.chesspiece;

import chess.domain.util.Counter;

public interface ChessPiece {
    Counter<Integer> countPiecesOnSameColumn(Counter<Integer> pawnCounter, int column);

    double getScore(Counter<Integer> pawnCounter, int column);

    String getName();

    boolean hasName(String name);

    boolean isPawn();
}