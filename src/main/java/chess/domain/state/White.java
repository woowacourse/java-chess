package chess.domain.state;

import chess.domain.Board;
import chess.domain.Color;
import chess.domain.PieceType;
import chess.domain.Position;
import chess.domain.piece.Piece;

public class White extends Running {
    protected White(final Board board) {
        super(board);
    }

    @Override
    public State move(final Position source, final Position target) {
        Piece capturePiece = board.move(source, target, Color.WHITE);
        if (capturePiece.isSamePieceType(PieceType.KING)){
            return new WhiteVictory(board);
        }
        return new Black(board);
    }
}
