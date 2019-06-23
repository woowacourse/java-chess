package chess.domain;

import java.util.*;
import java.util.stream.Collectors;

public class King extends ChessPiece {
    private static Map<Team, King> kings = new HashMap<>();

    static King getInstance(Team team) {
        if (!kings.containsKey(team)) {
            kings.put(team, new King(team));
        }
        return kings.get(team);
    }

    private King(Team team) {
        super(getPieceTypeByTeam(team));
    }

    private static PieceType getPieceTypeByTeam(Team team) {
        if (team == Team.BLACK) {
            return PieceType.KING_BLACK;
        }
        if (team == Team.WHITE) {
            return PieceType.KING_WHITE;
        }
        throw new IllegalArgumentException();
    }

    @Override
    public Set<CoordinatePair> getMovableCoordinates(PieceTeamProvider pieceTeamProvider, CoordinatePair from) {
        return Arrays.stream(Direction.values())
            .map(direction -> direction.move(from))
            .filter(Optional::isPresent)
            .map(Optional::get)
            .filter((coord) -> pieceTeamProvider.getTeamAt(coord) != getType().getTeam())
            .collect(Collectors.toSet());
    }
}
