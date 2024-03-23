package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.position.Square;
import chess.domain.strategy.*;

public enum PieceType {

    KING(new KingLegalMoveCheckStrategy()),
    QUEEN(new QueenLegalMoveCheckStrategy()),
    ROOK(new RookLegalMoveCheckStrategy()),
    BISHOP(new BishopLegalMoveCheckStrategy()),
    KNIGHT(new KnightLegalMoveCheckStrategy()),
    PAWN(new PawnLegalMoveCheckStrategy()),
    EMPTY(new EmptyLegalMoveCheckStrategy()),
    ;

    private final LegalMoveCheckStrategy legalMoveCheckStrategy;

    PieceType(LegalMoveCheckStrategy legalMoveCheckStrategy) {
        this.legalMoveCheckStrategy = legalMoveCheckStrategy;
    }

    public boolean canMove(Square source, Square destination, Board board) {
        return legalMoveCheckStrategy.check(source, destination, board);
    }
}
