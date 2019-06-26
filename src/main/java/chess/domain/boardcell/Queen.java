package chess.domain.boardcell;

import chess.domain.CoordinatePair;
import chess.domain.Direction;
import chess.domain.PieceTeamProvider;
import chess.domain.Team;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Queen extends ChessPiece {
    private static Map<Team, Queen> queens = new HashMap<>();

    static Queen getInstance(Team team) {
        if (!queens.containsKey(team)) {
            queens.put(team, new Queen(team));
        }
        return queens.get(team);
    }

    private Queen(Team team) {
        super(team);
    }

    @Override
    PieceType getPieceTypeByTeam(Team team) {
        if (team == Team.BLACK) {
            return PieceType.QUEEN_BLACK;
        }
        if (team == Team.WHITE) {
            return PieceType.QUEEN_WHITE;
        }
        throw new IllegalArgumentException();
    }

    @Override
    public Set<CoordinatePair> getMovableCoordinates(PieceTeamProvider pieceTeamProvider, CoordinatePair from) {
        return Arrays.stream(Direction.values())
            .flatMap(direction -> probeStraight(pieceTeamProvider, from, direction).stream())
            .collect(Collectors.toSet());
    }
}
