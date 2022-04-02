package chess.domain.piece;

import chess.domain.ChessBoard;
import chess.domain.ChessBoardPosition;
import chess.domain.Team;
import chess.domain.strategy.NoPathMovingStrategy;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class King extends ChessPiece {
    private static final double SCORE = 0.0;
    private static final Map<ChessBoardPosition, ChessPiece> blackTeamInitialPosition = new HashMap<>();
    private static final Map<ChessBoardPosition, ChessPiece> whiteTeamInitialPosition = new HashMap<>();
    private static int COLUMN = 5;
    private static int BLACK_TEAM_ROW = 8;
    private static int WHITE_TEAM_ROW = 1;
    private static final int EAST = 1;
    private static final int NORTH = 1;
    private static final int SOUTH = -1;
    private static final int WEST = -1;
    private static final int STAY = 0;

    static {
        blackTeamInitialPosition.put(ChessBoardPosition.of(COLUMN, BLACK_TEAM_ROW), new King(Team.BLACK));
        whiteTeamInitialPosition.put(ChessBoardPosition.of(COLUMN, WHITE_TEAM_ROW), new King(Team.WHITE));
    }

    King(Team team) {
        super(team, SCORE, new NoPathMovingStrategy(
                List.of(ChessBoardPosition.ofDirection(EAST, STAY)
                , ChessBoardPosition.ofDirection(EAST, NORTH)
                , ChessBoardPosition.ofDirection(STAY, NORTH)
                , ChessBoardPosition.ofDirection(WEST, NORTH)
                , ChessBoardPosition.ofDirection(WEST, STAY)
                , ChessBoardPosition.ofDirection(WEST, SOUTH)
                , ChessBoardPosition.ofDirection(STAY, SOUTH)
                , ChessBoardPosition.ofDirection(EAST, SOUTH))
        ));
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
