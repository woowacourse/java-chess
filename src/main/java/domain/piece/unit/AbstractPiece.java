package domain.piece.unit;

import domain.piece.property.PieceInfo;
import domain.piece.property.Team;
import domain.position.Position;
import domain.position.XPosition;
import domain.position.YPosition;
import domain.piece.property.Direction;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public abstract class AbstractPiece implements Piece {

    private final PieceInfo pieceInfo;

    protected Map<Direction, List<Position>> directionalPositions;

    public AbstractPiece(final PieceInfo pieceInfo) {
        this.pieceInfo = pieceInfo;
    }

    protected boolean checkOverRange(final int x, final int y) {
        return XPosition.checkRange(x) && YPosition.checkRange(y);
    }

    public boolean checkSameTeam(final Team team) {
        return pieceInfo.checkSameTeam(team);
    }

    public boolean availableMove(final Position source, final Position target) {
        this.directionalPositions = calculateAvailableDirectionalPositions(source);
        return containsPosition(target);
    }

    protected Map<Direction, List<Position>> calculateAvailableDirectionalPositions(final Position source) {
        Map<Direction, List<Position>> directionalPositions = getDirections().stream()
                .collect(
                        Collectors.toMap(Function.identity(),
                                direction -> calculateAvailableDirectionByPosition(source, direction)
                        )
                );
        return directionalPositions;
    }

    protected abstract List<Position> calculateAvailableDirectionByPosition(final Position source,
                                                                            final Direction direction);

    protected boolean containsPosition(final Position position) {
        return getDirections().stream()
                .map(direction -> directionalPositions.get(direction))
                .anyMatch(positions -> positions.contains(position));
    }

    public List<Position> calculateRoute(final Position target) {
        return getDirections().stream()
                .map(direction -> directionalPositions.get(direction))
                .filter(positions -> positions.contains(target))
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean checkOneAndTwoSouthNorthDirections(Position target) {
        return false;
    }

    @Override
    public Direction getDirection(Position target) {
        return getDirections().stream()
                .filter(direction -> directionalPositions.get(direction).contains(target))
                .filter(direction -> directionalPositions.get(direction) != null)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 잘못된 타켓 입력입니다."));

    }

    public String getSymbolByTeam() {
        return pieceInfo.getSymbolByTeam();
    }

    public String symbol() {
        return pieceInfo.symbol();
    }

    @Override
    public String toString() {
        return "AbstractPiece{" +
                "pieceInfo=" + pieceInfo +
                ", directionalPositions=" + directionalPositions +
                '}';
    }
}
