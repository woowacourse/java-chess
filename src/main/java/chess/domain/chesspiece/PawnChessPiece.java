package chess.domain.chesspiece;

import chess.domain.chesspoint.ChessPoint;

public interface PawnChessPiece extends ChessPiece {
    boolean checkRule(ChessPoint source, ChessPoint target, boolean opponentPieceOnTarget);
}
