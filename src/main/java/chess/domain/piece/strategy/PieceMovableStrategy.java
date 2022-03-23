package chess.domain.piece.strategy;

import chess.domain.ChessBoard;
import chess.domain.Position;

public interface PieceMovableStrategy {

    boolean isMovable(Position start, Position target, ChessBoard chessBoard);
}
