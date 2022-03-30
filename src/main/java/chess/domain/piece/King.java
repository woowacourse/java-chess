package chess.domain.piece;

import chess.domain.ChessBoard;
import chess.domain.ChessBoardPosition;
import chess.domain.Team;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class King extends ChessPiece {
    private static final double SCORE = 0.0;
    private static final Map<ChessBoardPosition, ChessPiece> blackTeamInitialPosition = new HashMap<>();
    private static final Map<ChessBoardPosition, ChessPiece> whiteTeamInitialPosition = new HashMap<>();

    static {
        blackTeamInitialPosition.put(ChessBoardPosition.of(5, 8), new King(Team.BLACK));
        whiteTeamInitialPosition.put(ChessBoardPosition.of(5, 1), new King(Team.WHITE));
    }

    private King(Team team) {
        super(team, SCORE);
    }

    public static Map<ChessBoardPosition, ChessPiece> create(Team team) {
        if (team.isSame(Team.BLACK)) {
            return blackTeamInitialPosition;
        }
        return whiteTeamInitialPosition;
    }

    @Override
    public List<ChessBoard> makePath(ChessBoardPosition sourcePosition, ChessBoardPosition targetPosition) {
        return null;
    }

    @Override
    public ChessPiece hardCopy() {
        return new King(this.team);
    }
}
