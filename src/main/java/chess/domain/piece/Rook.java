package chess.domain.piece;

import chess.domain.ChessBoard;
import chess.domain.ChessBoardPosition;
import chess.domain.Team;
import chess.domain.strategy.ContinuousMovingStrategy;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Rook extends ChessPiece {
    private static final double SCORE = 5;
    private static final Map<ChessBoardPosition, ChessPiece> blackTeamInitialPosition = new HashMap<>();
    private static final Map<ChessBoardPosition, ChessPiece> whiteTeamInitialPosition = new HashMap<>();

    private static final int BLACK_TEAM_ROW = 8;
    private static final int WHITE_TEAM_ROW = 1;
    private static final int LEFT_ROOK_COLUMN = 1;
    private static final int RIGHT_ROOK_COLUMN = 8;
    private static final int EAST = 1;
    private static final int NORTH = 1;
    private static final int SOUTH = -1;
    private static final int WEST = -1;
    private static final int STAY = 0;

    static {
        blackTeamInitialPosition.put(ChessBoardPosition.of(LEFT_ROOK_COLUMN, BLACK_TEAM_ROW), new Rook(Team.BLACK));
        blackTeamInitialPosition.put(ChessBoardPosition.of(RIGHT_ROOK_COLUMN, BLACK_TEAM_ROW), new Rook(Team.BLACK));
        whiteTeamInitialPosition.put(ChessBoardPosition.of(LEFT_ROOK_COLUMN, WHITE_TEAM_ROW), new Rook(Team.WHITE));
        whiteTeamInitialPosition.put(ChessBoardPosition.of(RIGHT_ROOK_COLUMN, WHITE_TEAM_ROW), new Rook(Team.WHITE));
    }

    Rook(Team team) {
        super(team, SCORE, new ContinuousMovingStrategy(
                List.of(ChessBoardPosition.ofDirection(EAST, STAY),
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
