package chess.domain.piece;

import chess.domain.ChessBoard;
import chess.domain.ChessBoardPosition;
import chess.domain.Team;
import java.util.List;

public class Knight extends ChessPiece {
    private static final double SCORE = 2.5;

    private Knight(Team team) {
        super(team, SCORE);
    }

    public static Knight create(Team team) {
        return new Knight(team);
    }

    @Override
    public List<ChessBoard> makePath(ChessBoardPosition sourcePosition, ChessBoardPosition targetPosition) {
        return null;
    }
}
