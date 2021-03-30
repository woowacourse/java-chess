package chess.domain.piece.strategy;

import chess.domain.board.ChessBoard;
import chess.domain.board.Direction;
import chess.domain.board.Position;
import chess.domain.piece.Piece;

public interface MoveStrategy {

    boolean movable(
        ChessBoard chessBoard,
        Position sourcePosition,
        Position targetPosition,
        Piece sourcePiece
    );
}
