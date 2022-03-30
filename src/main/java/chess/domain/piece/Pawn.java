package chess.domain.piece;

import chess.domain.ChessBoard;
import chess.domain.ChessBoardPosition;
import chess.domain.Team;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Pawn extends ChessPiece {
    private static final double SCORE = 1;
    private static final Map<ChessBoardPosition, ChessPiece> blackTeamInitialPosition = new HashMap<>();
    private static final Map<ChessBoardPosition, ChessPiece> whiteTeamInitialPosition = new HashMap<>();

    static {
        blackTeamInitialPosition.put(ChessBoardPosition.of("a7"), new Pawn(Team.BLACK));
        blackTeamInitialPosition.put(ChessBoardPosition.of("b7"), new Pawn(Team.BLACK));
        blackTeamInitialPosition.put(ChessBoardPosition.of("c7"), new Pawn(Team.BLACK));
        blackTeamInitialPosition.put(ChessBoardPosition.of("d7"), new Pawn(Team.BLACK));
        blackTeamInitialPosition.put(ChessBoardPosition.of("e7"), new Pawn(Team.BLACK));
        blackTeamInitialPosition.put(ChessBoardPosition.of("f7"), new Pawn(Team.BLACK));
        blackTeamInitialPosition.put(ChessBoardPosition.of("g7"), new Pawn(Team.BLACK));
        blackTeamInitialPosition.put(ChessBoardPosition.of("h7"), new Pawn(Team.BLACK));

        whiteTeamInitialPosition.put(ChessBoardPosition.of("a2"), new Pawn(Team.WHITE));
        whiteTeamInitialPosition.put(ChessBoardPosition.of("b2"), new Pawn(Team.WHITE));
        whiteTeamInitialPosition.put(ChessBoardPosition.of("c2"), new Pawn(Team.WHITE));
        whiteTeamInitialPosition.put(ChessBoardPosition.of("d2"), new Pawn(Team.WHITE));
        whiteTeamInitialPosition.put(ChessBoardPosition.of("e2"), new Pawn(Team.WHITE));
        whiteTeamInitialPosition.put(ChessBoardPosition.of("f2"), new Pawn(Team.WHITE));
        whiteTeamInitialPosition.put(ChessBoardPosition.of("g2"), new Pawn(Team.WHITE));
        whiteTeamInitialPosition.put(ChessBoardPosition.of("h2"), new Pawn(Team.WHITE));
    }

    private Pawn(Team team) {
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
        return new Pawn(this.team);
    }
}
