package chess.domain.piece;

import chess.domain.ChessBoard;
import chess.domain.ChessBoardPosition;
import chess.domain.Team;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Queen extends ChessPiece {
    private static final double SCORE = 9.0;
    private static final Map<ChessBoardPosition, ChessPiece> blackTeamInitialPosition = new HashMap<>();
    private static final Map<ChessBoardPosition, ChessPiece> whiteTeamInitialPosition = new HashMap<>();

    static {
        blackTeamInitialPosition.put(ChessBoardPosition.of(4, 8), new Queen(Team.BLACK));
        whiteTeamInitialPosition.put(ChessBoardPosition.of(4, 1), new Queen(Team.WHITE));
    }

    private Queen(Team team) {
        super(team, SCORE);
    }

    public static Map<ChessBoardPosition, ChessPiece> create(Team team) {
        if (team.isSame(Team.BLACK)) {
            return blackTeamInitialPosition;
        }
        return whiteTeamInitialPosition;
    }

    @Override
    public List<ChessBoard> makePath(ChessBoardPosition sourcePosition, ChessBoardPosition targetPosition) {
        return null;
    }

    @Override
    public ChessPiece hardCopy() {
        return new Queen(this.team);
    }
}
