package chess.domain.chessBoard;

import chess.domain.piece.Color;
import chess.domain.position.Position;
import java.util.List;

public class ChessBoard {

    private final List<Space> spaces;

    public ChessBoard(SpaceGenerator spaceGenerator) {
        this.spaces = spaceGenerator.generateSpaces();
    }

    public void move(Position from, Position to) {
        Space fromSpace = findSpace(from);
        Space toSpace = findSpace(to);

        List<Position> routeToTarget = fromSpace.findRouteToTarget(toSpace);
        validateClearRoute(routeToTarget);

        fromSpace.movePiece(toSpace);
    }

    private void validateClearRoute(List<Position> routes) {
        for (Position route : routes) {
            validateRouteHasPiece(route);
        }
    }

    private void validateRouteHasPiece(Position route) {
        if (findSpace(route).hasPiece()) {
            throw new IllegalArgumentException("루트에 피스가 있습니다.");
        }
    }

    private Space findSpace(Position position) {
        return spaces.stream()
                .filter(space -> space.isSamePosition(position))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("해당하는 Space가 없습니다"));
    }

    public boolean isSameColor(Position position, Color color) {
        Space space = findSpace(position);
        return space.isSameColor(color);
    }

    public List<Space> getSpaces() {
        return spaces;
    }
}
