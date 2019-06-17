package chess.domain.chesspiece;

import chess.domain.ChessPoint;
import chess.domain.RelativeChessPoint;

public interface ChessPiece {
    boolean checkRule(ChessPoint source, ChessPoint target);

    RelativeChessPoint calculateUnitDirection(ChessPoint source, ChessPoint target);
}