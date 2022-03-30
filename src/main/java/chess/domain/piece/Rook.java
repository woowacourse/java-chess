package chess.domain.piece;

import chess.domain.ChessBoard;
import chess.domain.ChessBoardPosition;
import chess.domain.Team;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Rook extends ChessPiece {
    private static final double SCORE = 5;
    private static final Map<ChessBoardPosition, ChessPiece> blackTeamInitialPosition = new HashMap<>();
    private static final Map<ChessBoardPosition, ChessPiece> whiteTeamInitialPosition = new HashMap<>();

    static {
        blackTeamInitialPosition.put(ChessBoardPosition.of("a8"), new Rook(Team.BLACK));
        blackTeamInitialPosition.put(ChessBoardPosition.of("h8"), new Rook(Team.BLACK));
        whiteTeamInitialPosition.put(ChessBoardPosition.of("a1"), new Rook(Team.WHITE));
        whiteTeamInitialPosition.put(ChessBoardPosition.of("h1"), new Rook(Team.WHITE));
    }

    private Rook(Team team) {
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
        return new Rook(this.team);
    }
}
