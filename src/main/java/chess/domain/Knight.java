package chess.domain;

import java.util.*;
import java.util.stream.Collectors;

public class Knight extends ChessPiece {
    private static Map<Team, Knight> knights = new HashMap<>();

    static Knight getInstance(Team team) {
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
    public Set<CoordinatePair> getMovableCoordinates(PieceTeamProvider pieceTeamProvider, CoordinatePair from) {
        List<CoordinatePair> candidates = new ArrayList<>();
        candidates.addAll(checkTop(from));
        candidates.addAll(checkRight(from));
        candidates.addAll(checkBottom(from));
        candidates.addAll(checkLeft(from));
        return candidates.stream()
            .filter((coord) -> pieceTeamProvider.getTeamAt(coord) != getType().getTeam())
            .collect(Collectors.toSet());
    }

    private Set<CoordinatePair> checkTop(CoordinatePair from) {
        Optional<CoordinatePair> maybeUp = Direction.UP.move(from);
        if (!maybeUp.isPresent()) {
            return Collections.emptySet();
        }
        Set<CoordinatePair> movableCoordinates = new HashSet<>();
        Direction.LEFT_TOP.move(maybeUp.get())
            .ifPresent(movableCoordinates::add);
        Direction.RIGHT_TOP.move(maybeUp.get())
            .ifPresent(movableCoordinates::add);
        return movableCoordinates;
    }

    private Set<CoordinatePair> checkRight(CoordinatePair from) {
        Optional<CoordinatePair> maybeUp = Direction.RIGHT.move(from);
        if (!maybeUp.isPresent()) {
            return Collections.emptySet();
        }
        Set<CoordinatePair> movableCoordinates = new HashSet<>();
        Direction.RIGHT_TOP.move(maybeUp.get())
            .ifPresent(movableCoordinates::add);
        Direction.RIGHT_BOTTOM.move(maybeUp.get())
            .ifPresent(movableCoordinates::add);
        return movableCoordinates;
    }

    private Set<CoordinatePair> checkBottom(CoordinatePair from) {
        Optional<CoordinatePair> maybeUp = Direction.DOWN.move(from);
        if (!maybeUp.isPresent()) {
            return Collections.emptySet();
        }
        Set<CoordinatePair> movableCoordinates = new HashSet<>();
        Direction.LEFT_BOTTOM.move(maybeUp.get())
            .ifPresent(movableCoordinates::add);
        Direction.RIGHT_BOTTOM.move(maybeUp.get())
            .ifPresent(movableCoordinates::add);
        return movableCoordinates;
    }

    private Set<CoordinatePair> checkLeft(CoordinatePair from) {
        Optional<CoordinatePair> maybeUp = Direction.LEFT.move(from);
        if (!maybeUp.isPresent()) {
            return Collections.emptySet();
        }
        Set<CoordinatePair> movableCoordinates = new HashSet<>();
        Direction.LEFT_TOP.move(maybeUp.get())
            .ifPresent(movableCoordinates::add);
        Direction.LEFT_BOTTOM.move(maybeUp.get())
            .ifPresent(movableCoordinates::add);
        return movableCoordinates;
    }
}
