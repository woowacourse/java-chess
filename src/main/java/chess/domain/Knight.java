package chess.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Knight extends ChessPiece {
    protected Knight(Team team) {
        super(getPieceTypeByTeam(team));
    }

    private static PieceType getPieceTypeByTeam(Team team) {
        if (team == Team.BLACK) {
            return PieceType.KNIGHT_BLACK;
        }
        if (team == Team.WHITE) {
            return PieceType.KNIGHT_WHITE;
        }
        throw new IllegalArgumentException();
    }

    @Override
    List<ChessCoordinate> getMovableCoordinates(PieceTeamProvider pieceTeamProvider, ChessCoordinate from) {

        List<ChessCoordinate> candidates = new ArrayList<>();

        from.getX().move(2)
                .ifPresent((x) -> {
                    from.getY().move(1).ifPresent(y -> ChessCoordinate.valueOf(x, y).ifPresent(candidates::add));
                    from.getY().move(-1).ifPresent(y -> ChessCoordinate.valueOf(x, y).ifPresent(candidates::add));
                });
        from.getX().move(-2)
                .ifPresent((x) -> {
                    from.getY().move(1).ifPresent(y -> ChessCoordinate.valueOf(x, y).ifPresent(candidates::add));
                    from.getY().move(-1).ifPresent(y -> ChessCoordinate.valueOf(x, y).ifPresent(candidates::add));
                });
        from.getY().move(2)
                .ifPresent((y) -> {
                    from.getX().move(1).ifPresent(x -> ChessCoordinate.valueOf(x, y).ifPresent(candidates::add));
                    from.getX().move(-1).ifPresent(x -> ChessCoordinate.valueOf(x, y).ifPresent(candidates::add));
                });
        from.getY().move(-2)
                .ifPresent((y) -> {
                    from.getX().move(1).ifPresent(x -> ChessCoordinate.valueOf(x, y).ifPresent(candidates::add));
                    from.getX().move(-1).ifPresent(x -> ChessCoordinate.valueOf(x, y).ifPresent(candidates::add));
                });

        return candidates.stream()
                .filter((coord) -> pieceTeamProvider.getTeamAt(coord.getX(), coord.getY()) != getType().getTeam())
                .collect(Collectors.toList());
    }
}
