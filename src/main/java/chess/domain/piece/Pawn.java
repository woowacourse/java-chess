package chess.domain.piece;

import chess.domain.ChessBoardPosition;
import chess.domain.Team;
import chess.domain.strategy.MovingStrategy;
import chess.domain.strategy.PawnMovingStrategy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Pawn extends ChessPiece {
    private static final int EAST = 1;
    private static final int NORTH = 1;
    private static final int SOUTH = -1;
    private static final int WEST = -1;
    private static final int STAY = 0;
    private static final int BLACK_TEAM_ROW = 7;
    private static final int WHITE_TEAM_ROW = 2;
    private static final double SCORE = 1;
    private static final Map<ChessBoardPosition, ChessPiece> blackTeamInitialPawn = new HashMap<>();
    private static final Map<ChessBoardPosition, ChessPiece> whiteTeamInitialPawn = new HashMap<>();
    private static final MovingStrategy blackPawnMovingStrategy;
    private static final MovingStrategy whitePawnMovingStrategy;
    private static final List<Integer> initialColumn = List.of(1, 2, 3, 4, 5, 6, 7, 8);

    static {
        List<ChessBoardPosition> blackTeamAbleMovement = List.of(ChessBoardPosition.ofDirection(STAY, SOUTH));
        List<ChessBoardPosition> whiteTeamAbleMovement = List.of(ChessBoardPosition.ofDirection(STAY, NORTH));
        List<ChessBoardPosition> blackTeamKillMovement = List.of(
                ChessBoardPosition.ofDirection(EAST, SOUTH),
                ChessBoardPosition.ofDirection(WEST, SOUTH));
        List<ChessBoardPosition> whiteTeamKillMovement = List.of(
                ChessBoardPosition.ofDirection(EAST, NORTH),
                ChessBoardPosition.ofDirection(WEST, NORTH));
        List<ChessBoardPosition> blackTeamInitialPosition = new ArrayList<>();
        List<ChessBoardPosition> whiteTeamInitialPosition = new ArrayList<>();
        for (int column : initialColumn) {
            blackTeamInitialPosition.add(ChessBoardPosition.of(column, BLACK_TEAM_ROW));
            whiteTeamInitialPosition.add(ChessBoardPosition.of(column, WHITE_TEAM_ROW));
        }
        blackPawnMovingStrategy = new PawnMovingStrategy(blackTeamAbleMovement, blackTeamKillMovement, blackTeamInitialPosition);
        whitePawnMovingStrategy = new PawnMovingStrategy(whiteTeamAbleMovement, whiteTeamKillMovement, whiteTeamInitialPosition);

        for (int column : initialColumn) {
            blackTeamInitialPawn.put(ChessBoardPosition.of(column, BLACK_TEAM_ROW)
                    , new Pawn(Team.BLACK, blackPawnMovingStrategy));
            whiteTeamInitialPawn.put(ChessBoardPosition.of(column, WHITE_TEAM_ROW)
                    , new Pawn(Team.WHITE, whitePawnMovingStrategy));
        }
    }

    Pawn(Team team, MovingStrategy movingStrategy) {
        super(team, SCORE, movingStrategy);
    }

    public static Pawn of(Team team) {
        if (Team.BLACK.isSame(team)) {
            return new Pawn(team, blackPawnMovingStrategy);
        }
        return new Pawn(team, whitePawnMovingStrategy);
    }

    public static Map<ChessBoardPosition, ChessPiece> create(Team team) {
        if (team.isSame(Team.BLACK)) {
            return blackTeamInitialPawn;
        }
        return whiteTeamInitialPawn;
    }

    @Override
    public List<ChessBoardPosition> getPath(ChessBoardPosition sourcePosition, ChessBoardPosition targetPosition) {
        if (isKillMovement(sourcePosition, targetPosition)) {
            return Collections.emptyList();
        }
        return movingStrategy.makePath(sourcePosition, targetPosition);
    }

    @Override
    public boolean isKillMovement(ChessBoardPosition sourcePosition, ChessBoardPosition targetPosition) {
        return movingStrategy.isKillMovement(sourcePosition, targetPosition);
    }
}
