package chess.domain.piece;

import chess.domain.ChessBoard;
import chess.domain.ChessBoardPosition;
import chess.domain.Team;
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

    private Knight(Team team) {
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
        return new Knight(this.team);
    }
}
