package chess.domain.piece;

import chess.domain.ChessBoard;
import chess.domain.ChessBoardPosition;
import chess.domain.Team;
import java.util.List;

public class Rook extends ChessPiece {
    private static final double SCORE = 5;

    private Rook(Team team) {
        super(team, SCORE);
    }

    public static Rook create(Team team) {
        return new Rook(team);
    }

    @Override
    public List<ChessBoard> makePath(ChessBoardPosition sourcePosition, ChessBoardPosition targetPosition) {
        return null;
    }
}
