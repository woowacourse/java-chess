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
    Set<ChessCoordinate> getMovableCoordinates(PieceTeamProvider pieceTeamProvider, ChessCoordinate from) {
        List<ChessCoordinate> candidates = new ArrayList<>();

        from.getX().move(-1)
                .ifPresent(x -> {
                    candidates.add(ChessCoordinate.valueOf(x, from.getY()).get());
                    from.getY().move(-1).ifPresent(y -> candidates.add(ChessCoordinate.valueOf(x, y).get()));
                    from.getY().move(1).ifPresent(y -> candidates.add(ChessCoordinate.valueOf(x, y).get()));
                });
        from.getX().move(1)
                .ifPresent(x -> {
                    candidates.add(ChessCoordinate.valueOf(x, from.getY()).get());
                    from.getY().move(-1).ifPresent(y -> candidates.add(ChessCoordinate.valueOf(x, y).get()));
                    from.getY().move(1).ifPresent(y -> candidates.add(ChessCoordinate.valueOf(x, y).get()));
                });

        from.getY().move(1).ifPresent(y -> candidates.add(ChessCoordinate.valueOf(from.getX(), y).get()));
        from.getY().move(-1).ifPresent(y -> candidates.add(ChessCoordinate.valueOf(from.getX(), y).get()));

        return candidates.stream()
                .filter((coord) -> pieceTeamProvider.getTeamAt(coord) != getType().getTeam())
                .collect(Collectors.toSet());
    }
}
