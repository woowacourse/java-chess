package chess.domain.piece;

import chess.domain.*;
import chess.domain.coordinate.ChessCoordinate;

import java.util.*;
import java.util.stream.Collectors;

public class Knight extends ChessPiece {
    private static Map<Team, Knight> knights = new HashMap<>();

    public static Knight getInstance(Team team) {
        if (!knights.containsKey(team)) {
            knights.put(team, new Knight(team));
        }
        return knights.get(team);
    }

    private Knight(Team team) {
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
    Set<ChessCoordinate> getMovableCoordinates(PieceTeamProvider pieceTeamProvider, ChessCoordinate from) {
        List<ChessCoordinate> candidates = new ArrayList<>();

        from.getX().move(INCREASE_TWO).ifPresent(proveYSide(from, candidates));
        from.getX().move(DECREASE_TWO).ifPresent(proveYSide(from, candidates));

        from.getY().move(INCREASE_TWO).ifPresent(proveXSide(from, candidates));
        from.getY().move(DECREASE_TWO).ifPresent(proveXSide(from, candidates));

        return candidates.stream()
                .filter((coord) -> pieceTeamProvider.getTeamAt(coord) != getType().getTeam())
                .collect(Collectors.toSet());
    }

}
