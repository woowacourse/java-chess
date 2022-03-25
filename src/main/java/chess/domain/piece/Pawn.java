package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.board.Point;
import chess.domain.piece.move.pawn.PawnMoveChain;
import chess.domain.piece.move.pawn.PawnMoveForwardChain;
import chess.domain.piece.move.pawn.PawnSupport;

public class Pawn extends Piece {

    public Pawn(Color color) {
        super(color);
    }


    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public PieceType getType() {
        return PieceType.PAWN;
    }

    @Override
    public void move(Board board, Point from, Point to) {
        PawnSupport support = new PawnSupport(color);
        PawnMoveChain chain = new PawnMoveForwardChain(support);
        chain.move(board, from, to);
    }


}
