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

        Optional<ChessXCoordinate> maybeXCandPos = createXCoordinate(from.getX(), 2);
        if (maybeXCandPos.isPresent()) {
            createYCoordinate(from.getY(), 1).ifPresent(y -> candidates.add(ChessCoordinate.valueOf(maybeXCandPos.get(), y)));
            createYCoordinate(from.getY(), -1).ifPresent(y -> candidates.add(ChessCoordinate.valueOf(maybeXCandPos.get(), y)));
        }
        Optional<ChessXCoordinate> maybeXCandNeg = createXCoordinate(from.getX(), -2);
        if (maybeXCandNeg.isPresent()) {
            createYCoordinate(from.getY(), 1).ifPresent(y -> candidates.add(ChessCoordinate.valueOf(maybeXCandNeg.get(), y)));
            createYCoordinate(from.getY(), -1).ifPresent(y -> candidates.add(ChessCoordinate.valueOf(maybeXCandNeg.get(), y)));
        }
        Optional<ChessYCoordinate> maybeYCandPos = createYCoordinate(from.getY(), 2);
        if (maybeYCandPos.isPresent()) {
            createXCoordinate(from.getX(), 1).ifPresent(x -> candidates.add(ChessCoordinate.valueOf(x, maybeYCandPos.get())));
            createXCoordinate(from.getX(), -1).ifPresent(x -> candidates.add(ChessCoordinate.valueOf(x, maybeYCandPos.get())));
        }
        Optional<ChessYCoordinate> maybeYCandNeg = createYCoordinate(from.getY(), -2);
        if (maybeYCandNeg.isPresent()) {
            createXCoordinate(from.getX(), 1).ifPresent(x -> candidates.add(ChessCoordinate.valueOf(x, maybeYCandNeg.get())));
            createXCoordinate(from.getX(), -1).ifPresent(x -> candidates.add(ChessCoordinate.valueOf(x, maybeYCandNeg.get())));
        }

        return candidates.stream()
                .filter((coord) -> pieceTeamProvider.getTeamAt(coord.getX(), coord.getY()) != getType().getTeam())
                .collect(Collectors.toList());
    }

    private Optional<ChessXCoordinate> createXCoordinate(ChessXCoordinate from, int deltaX) {
        try {
            return Optional.of(from.move(deltaX));
        } catch (IllegalArgumentException e) {
            return Optional.empty();
        }
    }

    private Optional<ChessYCoordinate> createYCoordinate(ChessYCoordinate from, int deltaY) {
        try {
            return Optional.of(from.move(deltaY));
        } catch (IllegalArgumentException e) {
            return Optional.empty();
        }
    }
}
