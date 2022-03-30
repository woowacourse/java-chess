package chess.domain.piece;

import chess.domain.ChessBoard;
import chess.domain.ChessBoardPosition;
import chess.domain.Team;
import java.util.List;

public class Bishop extends ChessPiece {
    private static final double SCORE = 3.0;

    private Bishop(Team team) {
        super(team, SCORE);
    }

    public static Bishop create(Team team) {
        return new Bishop(team);
    }

    @Override
    public List<ChessBoard> makePath(ChessBoardPosition sourcePosition, ChessBoardPosition targetPosition) {
        return null;
    }
}
