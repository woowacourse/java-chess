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

    static {
        blackTeamInitialPosition.put(ChessBoardPosition.of(1, 8), new Rook(Team.BLACK));
        blackTeamInitialPosition.put(ChessBoardPosition.of(8, 8), new Rook(Team.BLACK));
        whiteTeamInitialPosition.put(ChessBoardPosition.of(1, 1), new Rook(Team.WHITE));
        whiteTeamInitialPosition.put(ChessBoardPosition.of(8, 1), new Rook(Team.WHITE));
    }

    private Rook(Team team) {
        super(team, SCORE, new ContinuousMovingStrategy(
                List.of(ChessBoardPosition.of(1, 0),
                        ChessBoardPosition.of(-1, 0),
                        ChessBoardPosition.of(0, 1),
                        ChessBoardPosition.of(0, -1))));
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
}
