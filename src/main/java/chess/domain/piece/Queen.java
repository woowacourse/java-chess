package chess.domain.piece;

import chess.domain.ChessBoard;
import chess.domain.ChessBoardPosition;
import chess.domain.Team;
import java.util.List;

public class Queen extends ChessPiece {
    private static final double SCORE = 9.0;

    private Queen(Team team) {
        super(team, SCORE);
    }

    public static Queen create(Team team) {
        return new Queen(team);
    }

    @Override
    public List<ChessBoard> makePath(ChessBoardPosition sourcePosition, ChessBoardPosition targetPosition) {
        return null;
    }
}
