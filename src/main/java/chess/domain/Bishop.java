package chess.domain;

import java.util.*;

public class Bishop extends ChessPiece {
    private static Map<Team, Bishop> bishops = new HashMap<>();

    static Bishop getInstance(Team team) {
        if (!bishops.containsKey(team)) {
            bishops.put(team, new Bishop(team));
        }
        return bishops.get(team);
    }

    private Bishop(Team team) {
        super(getPieceTypeByTeam(team));
    }

    private static PieceType getPieceTypeByTeam(Team team) {
        if (team == Team.BLACK) {
            return PieceType.BISHOP_BLACK;
        }
        if (team == Team.WHITE) {
            return PieceType.BISHOP_WHITE;
        }
        throw new IllegalArgumentException();
    }

    @Override
    Set<ChessCoordinate> getMovableCoordinates(PieceTeamProvider pieceTeamProvider, ChessCoordinate from) {

        return probeAllDiagonal(pieceTeamProvider, from);
    }

}
