package chess.domain.chesspiece;

import chess.domain.ChessPoint;

public interface ChessPiece {
    boolean checkRule(ChessPoint source, ChessPoint target);
}