package chess.domain.piece.pawn;

import chess.domain.ChessBoard;
import chess.domain.Position;
import chess.domain.piece.Piece;
import chess.domain.piece.strategy.WhitePawnFirstMovableStrategy;

public final class WhiteFirstPawn extends AbstractWhitePawn {

    public WhiteFirstPawn() {
        super(new WhitePawnFirstMovableStrategy());
    }

    @Override
    public Piece move(Position start, Position target, ChessBoard chessBoard) {
        super.move(start, target, chessBoard);
        return new WhitePawn();
    }
}
