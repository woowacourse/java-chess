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
    private static final Map<ChessBoardPosition, ChessPiece> blackTeamInitialPosition = new HashMap<>();
    private static final Map<ChessBoardPosition, ChessPiece> whiteTeamInitialPosition = new HashMap<>();

    static {
        List<ChessBoardPosition> blackTeamAbleMovement = List.of(ChessBoardPosition.ofDirection(0, -1));
        List<ChessBoardPosition> whiteTeamAbleMovement = List.of(ChessBoardPosition.ofDirection(0, 1));
        blackTeamInitialPosition.put(ChessBoardPosition.of(1, 7)
                , new Pawn(Team.BLACK, blackTeamAbleMovement, Collections.emptyList()));
        blackTeamInitialPosition.put(ChessBoardPosition.of(2, 7)
                , new Pawn(Team.BLACK, blackTeamAbleMovement, Collections.emptyList()));
        blackTeamInitialPosition.put(ChessBoardPosition.of(3, 7)
                , new Pawn(Team.BLACK, blackTeamAbleMovement, Collections.emptyList()));
        blackTeamInitialPosition.put(ChessBoardPosition.of(4, 7)
                , new Pawn(Team.BLACK, blackTeamAbleMovement, Collections.emptyList()));
        blackTeamInitialPosition.put(ChessBoardPosition.of(5, 7)
                , new Pawn(Team.BLACK, blackTeamAbleMovement, Collections.emptyList()));
        blackTeamInitialPosition.put(ChessBoardPosition.of(6, 7)
                , new Pawn(Team.BLACK, blackTeamAbleMovement, Collections.emptyList()));
        blackTeamInitialPosition.put(ChessBoardPosition.of(7, 7)
                , new Pawn(Team.BLACK, blackTeamAbleMovement, Collections.emptyList()));
        blackTeamInitialPosition.put(ChessBoardPosition.of(8, 7)
                , new Pawn(Team.BLACK, blackTeamAbleMovement, Collections.emptyList()));

        whiteTeamInitialPosition.put(ChessBoardPosition.of(1, 2)
                , new Pawn(Team.WHITE, Collections.emptyList(), whiteTeamAbleMovement));
        whiteTeamInitialPosition.put(ChessBoardPosition.of(2, 2)
                , new Pawn(Team.WHITE, Collections.emptyList(), whiteTeamAbleMovement));
        whiteTeamInitialPosition.put(ChessBoardPosition.of(3, 2)
                , new Pawn(Team.WHITE, Collections.emptyList(), whiteTeamAbleMovement));
        whiteTeamInitialPosition.put(ChessBoardPosition.of(4, 2)
                , new Pawn(Team.WHITE, Collections.emptyList(), whiteTeamAbleMovement));
        whiteTeamInitialPosition.put(ChessBoardPosition.of(5, 2)
                , new Pawn(Team.WHITE, Collections.emptyList(), whiteTeamAbleMovement));
        whiteTeamInitialPosition.put(ChessBoardPosition.of(6, 2)
                , new Pawn(Team.WHITE, Collections.emptyList(), whiteTeamAbleMovement));
        whiteTeamInitialPosition.put(ChessBoardPosition.of(7, 2)
                , new Pawn(Team.WHITE, Collections.emptyList(), whiteTeamAbleMovement));
        whiteTeamInitialPosition.put(ChessBoardPosition.of(8, 2)
                , new Pawn(Team.WHITE, Collections.emptyList(), whiteTeamAbleMovement));
    }

    Pawn(Team team
            , List<ChessBoardPosition> blackTeamAbleMovement, List<ChessBoardPosition> whiteTeamAbleMovement) {
        super(team, SCORE, new PawnMovingStrategy(blackTeamAbleMovement, whiteTeamAbleMovement));
    }

    public static Map<ChessBoardPosition, ChessPiece> create(Team team) {
        if (team.isSame(Team.BLACK)) {
            return blackTeamInitialPosition;
        }
        return whiteTeamInitialPosition;
    }

    @Override
    public List<ChessBoardPosition> getPath(ChessBoardPosition sourcePosition, ChessBoardPosition targetPosition) {
        if (isInitialPosition(sourcePosition)) {
            return movingStrategy.makePath(sourcePosition, targetPosition);
        }
        return Collections.emptyList();
    }

    private boolean isInitialPosition(ChessBoardPosition sourcePosition) {
        if (team.isSame(Team.BLACK) && blackTeamInitialPosition.containsKey(sourcePosition)) {
            return true;
        }
        return team.isSame(Team.WHITE) && whiteTeamInitialPosition.containsKey(sourcePosition);
    }
}
