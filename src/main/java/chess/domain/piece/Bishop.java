package chess.domain.piece;

import chess.domain.ChessBoardPosition;
import chess.domain.Team;
import chess.domain.strategy.ContinuousMovingStrategy;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Bishop extends ChessPiece {
    private static final double SCORE = 3.0;
    private static final Map<ChessBoardPosition, ChessPiece> blackTeamInitialPosition = new HashMap<>();
    private static final Map<ChessBoardPosition, ChessPiece> whiteTeamInitialPosition = new HashMap<>();

    static {
        blackTeamInitialPosition.put(ChessBoardPosition.of(3, 8), new Bishop(Team.BLACK));
        blackTeamInitialPosition.put(ChessBoardPosition.of(6, 8), new Bishop(Team.BLACK));
        whiteTeamInitialPosition.put(ChessBoardPosition.of(3, 1), new Bishop(Team.WHITE));
        whiteTeamInitialPosition.put(ChessBoardPosition.of(6, 1), new Bishop(Team.WHITE));
    }

    private Bishop(Team team) {
        super(team, SCORE, new ContinuousMovingStrategy(
                List.of(ChessBoardPosition.ofDirection(1, 1),
                        ChessBoardPosition.ofDirection(1, -1),
                        ChessBoardPosition.ofDirection(-1, 1),
                        ChessBoardPosition.ofDirection(-1, -1))));
    }

    public static Map<ChessBoardPosition, ChessPiece> create(Team team) {
        if (team.isSame(Team.BLACK)) {
            return blackTeamInitialPosition;
        }
        return whiteTeamInitialPosition;
    }

    @Override
    public List<ChessBoardPosition> getPath(ChessBoardPosition sourcePosition, ChessBoardPosition targetPosition) {
        return movingStrategy.makePath(sourcePosition, targetPosition);
    }

    @Override
    public boolean isKillMovement(ChessBoardPosition sourcePosition, ChessBoardPosition targetPosition) {
        return movingStrategy.isKillMovement(sourcePosition, targetPosition);
    }
}
