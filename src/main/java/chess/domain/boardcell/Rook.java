package chess.domain.boardcell;

import chess.domain.CoordinatePair;
import chess.domain.Direction;
import chess.domain.PieceTeamProvider;
import chess.domain.Team;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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
    public Set<CoordinatePair> getMovableCoordinates(PieceTeamProvider pieceTeamProvider, CoordinatePair from) {
        Set<CoordinatePair> movableCoordinates = new HashSet<>();
        movableCoordinates.addAll(probeStraight(pieceTeamProvider, from, Direction.UP));
        movableCoordinates.addAll(probeStraight(pieceTeamProvider, from, Direction.DOWN));
        movableCoordinates.addAll(probeStraight(pieceTeamProvider, from, Direction.LEFT));
        movableCoordinates.addAll(probeStraight(pieceTeamProvider, from, Direction.RIGHT));
        return movableCoordinates;
    }
}
