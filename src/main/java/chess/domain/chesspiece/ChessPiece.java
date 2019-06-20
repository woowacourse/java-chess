package chess.domain.chesspiece;

import chess.domain.Counter;
import chess.domain.chesspoint.ChessPoint;

public interface ChessPiece {
    boolean checkRule(ChessPoint source, ChessPoint target, boolean opponentPieceOnTarget);

    Counter<Integer> countPiecesOnSameColumn(Counter<Integer> pawnCounter, int column);

    double getScore(Counter<Integer> pawnCounter, int column);
}