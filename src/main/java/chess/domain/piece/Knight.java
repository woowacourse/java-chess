package chess.domain.piece;

import chess.domain.ChessBoard;
import chess.domain.ChessBoardPosition;
import chess.domain.Team;
import chess.domain.strategy.NoPathMovingStrategy;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Knight extends ChessPiece {
    private static final double SCORE = 2.5;
    private static final Map<ChessBoardPosition, ChessPiece> blackTeamInitialPosition = new HashMap<>();
    private static final Map<ChessBoardPosition, ChessPiece> whiteTeamInitialPosition = new HashMap<>();
    private static final int LEFT_KNIGHT_COLUMN = 2;
    private static final int RIGHT_KNIGHT_COLUMN = 7;
    private static final int BLACK_TEAM_ROW = 8;
    private static final int WHITE_TEAM_ROW = 1;
    private static final int EAST = 1;
    private static final int NORTH = 1;
    private static final int SOUTH = -1;
    private static final int WEST = -1;

    static {
        blackTeamInitialPosition.put(ChessBoardPosition.of(LEFT_KNIGHT_COLUMN, BLACK_TEAM_ROW), new Knight(Team.BLACK));
        blackTeamInitialPosition.put(ChessBoardPosition.of(RIGHT_KNIGHT_COLUMN, BLACK_TEAM_ROW), new Knight(Team.BLACK));
        whiteTeamInitialPosition.put(ChessBoardPosition.of(LEFT_KNIGHT_COLUMN, WHITE_TEAM_ROW), new Knight(Team.WHITE));
        whiteTeamInitialPosition.put(ChessBoardPosition.of(RIGHT_KNIGHT_COLUMN, WHITE_TEAM_ROW), new Knight(Team.WHITE));
    }

    Knight(Team team) {
        super(team, SCORE, new NoPathMovingStrategy(
                List.of(ChessBoardPosition.of(2*EAST, NORTH)
                , ChessBoardPosition.ofDirection(2*EAST, SOUTH)
                , ChessBoardPosition.ofDirection(2*WEST, NORTH)
                , ChessBoardPosition.ofDirection(2*WEST, SOUTH)
                , ChessBoardPosition.ofDirection(EAST, 2*NORTH)
                , ChessBoardPosition.ofDirection(EAST, 2*SOUTH)
                , ChessBoardPosition.ofDirection(WEST, 2*NORTH)
                , ChessBoardPosition.ofDirection(WEST, 2*SOUTH))
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
