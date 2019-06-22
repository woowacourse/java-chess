package chess.domain.piece;

import chess.domain.board.Position;
import chess.exception.NotFoundPositionException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Rook implements Piece {
    private static final Rook BLACK_ROOK = new Rook(TeamType.BLACK);
    private static final Rook WHITE_ROOK = new Rook(TeamType.WHITE);
    private static final List<DirectionType> directions;

    private final TeamType teamType;

    static {
        directions = Arrays.asList(
                DirectionType.UP,
                DirectionType.DOWN,
                DirectionType.LEFT,
                DirectionType.RIGHT
        );
    }

    private Rook(final TeamType teamType) {
        this.teamType = teamType;
    }

    public static Rook of(TeamType teamType) {
        return (teamType == TeamType.BLACK) ? BLACK_ROOK : WHITE_ROOK;
    }

    @Override
    public boolean isSameTeam(Piece piece) {
        return this.teamType == ((Rook) piece).teamType;
    }

    @Override
    public List<Position> makePossiblePositions(Position source, PositionChecker positionChecker) {
        List<Position> positions = new ArrayList<>();

        for (DirectionType direction : directions) {
            Position nextPosition = source.hopNextPosition(direction);
            positions.addAll(makePossiblePositionsByDirection(positionChecker, direction, nextPosition));
        }
        return positions;
    }

    // TODO 메소드 길이 리펙토링 필요
    private List<Position> makePossiblePositionsByDirection(
            PositionChecker positionChecker, DirectionType direction, Position nextPosition) {
        List<Position> positions = new ArrayList<>();

        try {
            while (Objects.isNull(positionChecker.getPiece(nextPosition))) {
                positions.add(nextPosition);
                nextPosition = nextPosition.hopNextPosition(direction);
            }
        } catch (NotFoundPositionException e) {
            return positions;
        }

        if (!positionChecker.getPiece(nextPosition).isSameTeam(this)) {
            positions.add(nextPosition);
        }

        return positions;
    }
}
