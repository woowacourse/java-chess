package chess.domain.piece;

import chess.domain.ChessBoard;
import chess.domain.ChessBoardPosition;
import chess.domain.Team;
import java.util.List;

public class Pawn extends ChessPiece {
    private static final double SCORE = 1;

    private Pawn(Team team) {
        super(team, SCORE);
    }

    public static Pawn create(Team team) {
        return new Pawn(team);
    }

    @Override
    public List<ChessBoard> makePath(ChessBoardPosition sourcePosition, ChessBoardPosition targetPosition) {
        return null;
    }
}
