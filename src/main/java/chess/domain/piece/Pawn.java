package chess.domain.piece;

import chess.domain.ChessBoardPosition;
import chess.domain.Team;
import chess.domain.strategy.PawnMovingStrategy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Pawn extends ChessPiece {
    private static final double SCORE = 1;
    private static final Map<ChessBoardPosition, ChessPiece> blackTeamInitialPawn = new HashMap<>();
    private static final Map<ChessBoardPosition, ChessPiece> whiteTeamInitialPawn = new HashMap<>();

    private static final int EAST = 1;
    private static final int NORTH = 1;
    private static final int SOUTH = -1;
    private static final int WEST = -1;
    private static final int STAY = 0;
    private static final int BLACK_TEAM_ROW = 7;
    private static final int WHITE_TEAM_ROW = 2;
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

        for (int column : initialColumn) {
            blackTeamInitialPawn.put(ChessBoardPosition.of(column, BLACK_TEAM_ROW)
                    , new Pawn(Team.BLACK, blackTeamAbleMovement, Collections.emptyList(), blackTeamKillMovement, blackTeamInitialPosition));
            whiteTeamInitialPawn.put(ChessBoardPosition.of(column, WHITE_TEAM_ROW)
                    , new Pawn(Team.WHITE, Collections.emptyList(), whiteTeamAbleMovement, whiteTeamKillMovement, whiteTeamInitialPosition));
        }
    }

    Pawn(Team team,
         List<ChessBoardPosition> blackTeamAbleMovement,
         List<ChessBoardPosition> whiteTeamAbleMovement,
         List<ChessBoardPosition> killMovement,
         List<ChessBoardPosition> initialPosition) {
        super(team, SCORE, new PawnMovingStrategy(blackTeamAbleMovement, whiteTeamAbleMovement, killMovement, initialPosition));
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
