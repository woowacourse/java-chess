package chess.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

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
    public Set<CoordinatePair> getMovableCoordinates(PieceTeamProvider pieceTeamProvider, CoordinatePair from) {
        Set<CoordinatePair> movableCoordinates = probeStraight(pieceTeamProvider, from, Direction.LEFT_TOP);
        movableCoordinates.addAll(probeStraight(pieceTeamProvider, from, Direction.RIGHT_TOP));
        movableCoordinates.addAll(probeStraight(pieceTeamProvider, from, Direction.LEFT_BOTTOM));
        movableCoordinates.addAll(probeStraight(pieceTeamProvider, from, Direction.RIGHT_BOTTOM));
        return movableCoordinates;
    }
}
