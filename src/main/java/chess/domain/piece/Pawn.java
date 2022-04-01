package chess.domain.piece;

import chess.domain.ChessBoardPosition;
import chess.domain.Team;
import chess.domain.strategy.PawnMovingStrategy;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Pawn extends ChessPiece {
    private static final double SCORE = 1;
    private static final Map<ChessBoardPosition, ChessPiece> blackTeamInitialPawn = new HashMap<>();
    private static final Map<ChessBoardPosition, ChessPiece> whiteTeamInitialPawn = new HashMap<>();

    static {
        List<ChessBoardPosition> blackTeamAbleMovement = List.of(ChessBoardPosition.ofDirection(0, -1));
        List<ChessBoardPosition> whiteTeamAbleMovement = List.of(ChessBoardPosition.ofDirection(0, 1));
        List<ChessBoardPosition> blackTeamKillMovement = List.of(
                ChessBoardPosition.ofDirection(1, -1),
                ChessBoardPosition.ofDirection(-1, -1));
        List<ChessBoardPosition> whiteTeamKillMovement = List.of(
                ChessBoardPosition.ofDirection(1, 1),
                ChessBoardPosition.ofDirection(-1, 1));
        List<ChessBoardPosition> blackTeamInitialPosition = List.of(
                ChessBoardPosition.of(1, 7),
                ChessBoardPosition.of(2, 7),
                ChessBoardPosition.of(3, 7),
                ChessBoardPosition.of(4, 7),
                ChessBoardPosition.of(5, 7),
                ChessBoardPosition.of(6, 7),
                ChessBoardPosition.of(7, 7),
                ChessBoardPosition.of(8, 7)
        );
        List<ChessBoardPosition> whiteTeamInitialPosition = List.of(
                ChessBoardPosition.of(1, 2),
                ChessBoardPosition.of(2, 2),
                ChessBoardPosition.of(3, 2),
                ChessBoardPosition.of(4, 2),
                ChessBoardPosition.of(5, 2),
                ChessBoardPosition.of(6, 2),
                ChessBoardPosition.of(7, 2),
                ChessBoardPosition.of(8, 2)
        );
        whiteTeamInitialPawn.put(ChessBoardPosition.of(1, 7)
                , new Pawn(Team.BLACK, blackTeamAbleMovement, Collections.emptyList(), blackTeamKillMovement, blackTeamInitialPosition));
        whiteTeamInitialPawn.put(ChessBoardPosition.of(2, 7)
                , new Pawn(Team.BLACK, blackTeamAbleMovement, Collections.emptyList(), blackTeamKillMovement, blackTeamInitialPosition));
        whiteTeamInitialPawn.put(ChessBoardPosition.of(3, 7)
                , new Pawn(Team.BLACK, blackTeamAbleMovement, Collections.emptyList(), blackTeamKillMovement, blackTeamInitialPosition));
        whiteTeamInitialPawn.put(ChessBoardPosition.of(4, 7)
                , new Pawn(Team.BLACK, blackTeamAbleMovement, Collections.emptyList(), blackTeamKillMovement, blackTeamInitialPosition));
        whiteTeamInitialPawn.put(ChessBoardPosition.of(5, 7)
                , new Pawn(Team.BLACK, blackTeamAbleMovement, Collections.emptyList(), blackTeamKillMovement, blackTeamInitialPosition));
        whiteTeamInitialPawn.put(ChessBoardPosition.of(6, 7)
                , new Pawn(Team.BLACK, blackTeamAbleMovement, Collections.emptyList(), blackTeamKillMovement, blackTeamInitialPosition));
        whiteTeamInitialPawn.put(ChessBoardPosition.of(7, 7)
                , new Pawn(Team.BLACK, blackTeamAbleMovement, Collections.emptyList(), blackTeamKillMovement, blackTeamInitialPosition));
        whiteTeamInitialPawn.put(ChessBoardPosition.of(8, 7)
                , new Pawn(Team.BLACK, blackTeamAbleMovement, Collections.emptyList(), blackTeamKillMovement, blackTeamInitialPosition));

        whiteTeamInitialPawn.put(ChessBoardPosition.of(1, 2)
                , new Pawn(Team.WHITE, Collections.emptyList(), whiteTeamAbleMovement, whiteTeamKillMovement, whiteTeamInitialPosition));
        whiteTeamInitialPawn.put(ChessBoardPosition.of(2, 2)
                , new Pawn(Team.WHITE, Collections.emptyList(), whiteTeamAbleMovement, whiteTeamKillMovement, whiteTeamInitialPosition));
        whiteTeamInitialPawn.put(ChessBoardPosition.of(3, 2)
                , new Pawn(Team.WHITE, Collections.emptyList(), whiteTeamAbleMovement, whiteTeamKillMovement, whiteTeamInitialPosition));
        whiteTeamInitialPawn.put(ChessBoardPosition.of(4, 2)
                , new Pawn(Team.WHITE, Collections.emptyList(), whiteTeamAbleMovement, whiteTeamKillMovement, whiteTeamInitialPosition));
        whiteTeamInitialPawn.put(ChessBoardPosition.of(5, 2)
                , new Pawn(Team.WHITE, Collections.emptyList(), whiteTeamAbleMovement, whiteTeamKillMovement, whiteTeamInitialPosition));
        whiteTeamInitialPawn.put(ChessBoardPosition.of(6, 2)
                , new Pawn(Team.WHITE, Collections.emptyList(), whiteTeamAbleMovement, whiteTeamKillMovement, whiteTeamInitialPosition));
        whiteTeamInitialPawn.put(ChessBoardPosition.of(7, 2)
                , new Pawn(Team.WHITE, Collections.emptyList(), whiteTeamAbleMovement, whiteTeamKillMovement, whiteTeamInitialPosition));
        whiteTeamInitialPawn.put(ChessBoardPosition.of(8, 2)
                , new Pawn(Team.WHITE, Collections.emptyList(), whiteTeamAbleMovement, whiteTeamKillMovement, whiteTeamInitialPosition));
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
        return movingStrategy.makePath(sourcePosition, targetPosition);
    }

    @Override
    public boolean isKillMovement(ChessBoardPosition sourcePosition, ChessBoardPosition targetPosition) {
        return movingStrategy.isKillMovement(sourcePosition, targetPosition);
    }
}
