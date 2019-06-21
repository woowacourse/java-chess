package chess.domain;

import java.util.*;

public class Rook extends ChessPiece {
    private static Map<Team, Rook> rooks = new HashMap<>();

    static Rook getInstance(Team team) {
        if (!rooks.containsKey(team)) {
            rooks.put(team, new Rook(team));
        }
        return rooks.get(team);
    }

    private Rook(Team team) {
        super(getPieceTypeByTeam(team));
    }

    private static PieceType getPieceTypeByTeam(Team team) {
        if (team == Team.BLACK) {
            return PieceType.ROOK_BLACK;
        }
        if (team == Team.WHITE) {
            return PieceType.ROOK_WHITE;
        }
        throw new IllegalArgumentException();
    }

    @Override
    public Set<ChessCoordinate> getMovableCoordinates(PieceTeamProvider pieceTeamProvider, ChessCoordinate from) {

        return probCross(pieceTeamProvider, from);
    }

}
