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

    static {
        blackTeamInitialPosition.put(ChessBoardPosition.of(2, 8), new Knight(Team.BLACK));
        blackTeamInitialPosition.put(ChessBoardPosition.of(7, 8), new Knight(Team.BLACK));
        whiteTeamInitialPosition.put(ChessBoardPosition.of(2, 1), new Knight(Team.WHITE));
        whiteTeamInitialPosition.put(ChessBoardPosition.of(7, 1), new Knight(Team.WHITE));
    }

    Knight(Team team) {
        super(team, SCORE, new NoPathMovingStrategy(
                List.of(ChessBoardPosition.of(2, 1)
                , ChessBoardPosition.ofDirection(2, -1)
                , ChessBoardPosition.ofDirection(-2, 1)
                , ChessBoardPosition.ofDirection(-2, -1)
                , ChessBoardPosition.ofDirection(1, 2)
                , ChessBoardPosition.ofDirection(1, -2)
                , ChessBoardPosition.ofDirection(-1, 2)
                , ChessBoardPosition.ofDirection(-1, -2))
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
