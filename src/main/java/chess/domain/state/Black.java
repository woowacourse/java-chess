package chess.domain.state;

import chess.domain.Board;
import chess.domain.Color;
import chess.domain.PieceType;
import chess.domain.Position;
import chess.domain.piece.Piece;

public class Black extends Running {
    protected Black(final Board Board) {
        super(Board);
    }

    @Override
    public State move(final Position source, final Position target) {
        Piece capturePiece = board.move(source, target, Color.BLACK);
        if (capturePiece.isSamePieceType(PieceType.KING)) {
            return new BlackVictory(board);
        }
        return new White(board);
    }
}
