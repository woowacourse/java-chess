package chess.domain.piece.pawn;

import chess.domain.ChessBoard;
import chess.domain.Position;
import chess.domain.piece.Piece;
import chess.domain.piece.strategy.BlackPawnFirstMovableStrategy;

public final class BlackFirstPawn extends AbstractBlackPawn {

    public BlackFirstPawn() {
        super(new BlackPawnFirstMovableStrategy());
    }

    @Override
    public Piece move(Position source, Position target, ChessBoard chessBoard) {
        super.move(source, target, chessBoard);
        return new BlackPawn();
    }
}
