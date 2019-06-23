package chess.domain;

import java.util.*;
import java.util.function.Consumer;
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

        from.getX().move(-1).ifPresent(proveYSide(from, candidates));
        from.getX().move(1).ifPresent(proveYSide(from, candidates));

        proveXSide(from, candidates).accept(from.getY());
        proveYSide(from, candidates).accept(from.getX());

        return candidates.stream()
                .filter((coord) -> pieceTeamProvider.getTeamAt(coord) != getType().getTeam())
                .collect(Collectors.toSet());
    }

}
