package chess.domain.order;

import chess.domain.board.Board;
import chess.domain.piece.Piece;
import chess.domain.piece.RealPiece;
import chess.domain.position.Direction;
import chess.domain.position.Position;

import java.util.List;

public class MoveOrder {
    private final Board board;
    private final Position from;
    private final Position to;

    public MoveOrder(Board board, Position from, Position to) {
        this.board = board;
        this.from = from;
        this.to = to;
    }

    public Direction getDirection() {
        return Direction.of(this.from, this.to);
    }

    public List<Piece> getRoute() {
        return this.board.getRoute(from, to);
    }

    public boolean hasPieceAtToPosition() {
        return this.board.hasPiece(to);
    }

    public Position getFromPosition() {
        return this.from;
    }

    public Position getToPosition() {
        return this.to;
    }

    public RealPiece getPieceAtFromPosition() {
        return this.board.getRealPieceByPosition(from);
    }

    public RealPiece getPieceAtToPosition() {
        return this.board.getRealPieceByPosition(to);
    }
}
