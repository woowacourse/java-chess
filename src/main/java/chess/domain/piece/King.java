package chess.domain.piece;

import chess.domain.ChessBoard;
import chess.domain.ChessBoardPosition;
import chess.domain.Team;
import java.util.List;

public class King extends ChessPiece {
    private static final double SCORE = 0.0;

    private King(Team team) {
        super(team, SCORE);
    }

    public static King create(Team team) {
        return new King(team);
    }

    @Override
    public List<ChessBoard> makePath(ChessBoardPosition sourcePosition, ChessBoardPosition targetPosition) {
        return null;
    }
}
