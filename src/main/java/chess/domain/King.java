package chess.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class King extends ChessPiece {

    protected King(Team team) {
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
    List<ChessCoordinate> getMovableCoordinates(PieceTeamProvider pieceTeamProvider, ChessCoordinate from) {
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
                .filter((coord) -> pieceTeamProvider.getTeamAt(coord.getX(), coord.getY()) != getType().getTeam())
                .collect(Collectors.toList());
    }
}
