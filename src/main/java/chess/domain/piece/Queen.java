package chess.domain.piece;

import chess.domain.*;
import chess.domain.coordinate.ChessCoordinate;

import java.util.*;

public class Queen extends ChessPiece {
    private static Map<Team, Queen> queens = new HashMap<>();

    public static Queen getInstance(Team team) {
        if (!queens.containsKey(team)) {
            queens.put(team, new Queen(team));
        }
        return queens.get(team);
    }

    private Queen(Team team) {
        super(getPieceTypeByTeam(team));
    }

    private static PieceType getPieceTypeByTeam(Team team) {
        if (team == Team.BLACK) {
            return PieceType.QUEEN_BLACK;
        }
        if (team == Team.WHITE) {
            return PieceType.QUEEN_WHITE;
        }
        throw new IllegalArgumentException();
    }

    @Override
    public Set<ChessCoordinate> getMovableCoordinates(PieceTeamProvider pieceTeamProvider, ChessCoordinate from) {
        Set<ChessCoordinate> movableCoords = probCross(pieceTeamProvider, from);

        movableCoords.addAll(probeAllDiagonal(pieceTeamProvider, from));

        return movableCoords;
    }
}
