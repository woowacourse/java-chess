package chess.domain.piece;

import chess.domain.Board;
import chess.domain.PieceInfo;
import chess.domain.Position;
import chess.domain.strategy.MoveStrategy;

public abstract class ChessPiece implements Piece {
    private PieceInfo pieceInfo;
    private MoveStrategy moveStrategy;

    @Override
    public boolean move(Position newPosition, Board board) {
        return false;
    }
}
