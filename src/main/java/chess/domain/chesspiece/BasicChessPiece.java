package chess.domain.chesspiece;

import chess.domain.chesspoint.ChessPoint;

public interface BasicChessPiece extends ChessPiece {
    boolean checkRule(ChessPoint source, ChessPoint target);
}