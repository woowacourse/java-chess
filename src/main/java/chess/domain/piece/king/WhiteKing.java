package chess.domain.piece.king;

import chess.domain.direction.Direction;
import chess.domain.piece.Owner;
import chess.domain.piece.Score;

public class WhiteKing extends King{

    private static final WhiteKing WHITE_KING = new WhiteKing();

    public WhiteKing() {
        super(Owner.WHITE, new Score(0.0d), Direction.allDirections());
    }

    public static WhiteKing getInstance() {
        return WHITE_KING;
    }

    @Override
    public String getSymbol() {
        return "k";
    }
}
