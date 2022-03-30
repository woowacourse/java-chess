package chess.domain.piece;

import chess.domain.ChessBoard;
import chess.domain.ChessBoardPosition;
import chess.domain.Team;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Bishop extends ChessPiece {
    private static final double SCORE = 3.0;
    private static final Map<ChessBoardPosition, ChessPiece> blackTeamInitialPosition = new HashMap<>();
    private static final Map<ChessBoardPosition, ChessPiece> whiteTeamInitialPosition = new HashMap<>();

    static {
        blackTeamInitialPosition.put(ChessBoardPosition.of("c8"), new Bishop(Team.BLACK));
        blackTeamInitialPosition.put(ChessBoardPosition.of("f8"), new Bishop(Team.BLACK));
        whiteTeamInitialPosition.put(ChessBoardPosition.of("c1"), new Bishop(Team.WHITE));
        whiteTeamInitialPosition.put(ChessBoardPosition.of("f1"), new Bishop(Team.WHITE));
    }

    private Bishop(Team team) {
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
        return new Bishop(this.team);
    }
}
