package chess.domain.piece;

import chess.domain.ChessBoardPosition;
import chess.domain.Team;
import chess.domain.strategy.ContinuousMovingStrategy;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Queen extends ChessPiece {
    private static final int BLACK_TEAM_ROW = 8;
    private static final int WHITE_TEAM_ROW = 1;
    private static final int COLUMN = 4;
    private static final int EAST = 1;
    private static final int NORTH = 1;
    private static final int SOUTH = -1;
    private static final int WEST = -1;
    private static final int STAY = 0;
    private static final double SCORE = 9.0;
    private static final Map<ChessBoardPosition, ChessPiece> blackTeamInitialPosition = new HashMap<>();
    private static final Map<ChessBoardPosition, ChessPiece> whiteTeamInitialPosition = new HashMap<>();

    static {
        blackTeamInitialPosition.put(ChessBoardPosition.of(COLUMN, BLACK_TEAM_ROW), new Queen(Team.BLACK));
        whiteTeamInitialPosition.put(ChessBoardPosition.of(COLUMN, WHITE_TEAM_ROW), new Queen(Team.WHITE));
    }

    Queen(Team team) {
        super(team, SCORE, new ContinuousMovingStrategy(
                List.of(ChessBoardPosition.ofDirection(EAST, NORTH),
                        ChessBoardPosition.ofDirection(EAST, SOUTH),
                        ChessBoardPosition.ofDirection(WEST, NORTH),
                        ChessBoardPosition.ofDirection(WEST, SOUTH),
                        ChessBoardPosition.ofDirection(EAST, STAY),
                        ChessBoardPosition.ofDirection(WEST, STAY),
                        ChessBoardPosition.ofDirection(STAY, NORTH),
                        ChessBoardPosition.ofDirection(STAY, SOUTH))));
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
