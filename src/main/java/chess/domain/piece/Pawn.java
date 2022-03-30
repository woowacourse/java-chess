package chess.domain.piece;

import chess.domain.board.Point;
import chess.domain.piece.move.pawn.PawnMoveChain;
import chess.domain.piece.move.pawn.PawnMoveForwardChain;
import chess.domain.piece.move.pawn.PawnSupport;

import java.util.Map;

public class Pawn extends Piece {

    public Pawn(Color color) {
        super(color, PieceType.PAWN);
    }

    @Override
    public void move(Map<Point, Piece> pointPieces, Point from, Point to) {
        PawnSupport support = new PawnSupport(color);
        PawnMoveChain chain = new PawnMoveForwardChain(support);
        chain.move(pointPieces, from, to);
    }
}
