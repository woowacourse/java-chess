package chess.domain.chessBoard;

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
            for (Space space : spaces) {
                validateRouteHasPiece(route, space);
            }
        }
    }

    private void validateRouteHasPiece(Position route, Space space) {
        if (space.isSamePosition(route) && space.hasPiece()) {
            throw new IllegalArgumentException("루트에 피스가 있습니다.");
        }
    }

    private Space findSpace(Position position) {
        for (Space space : spaces) {
            if (space.isSamePosition(position)) {
                return space;
            }
        }
        throw new IllegalArgumentException("해당하는 Space가 없습니다");
    }

    public List<Space> getSpaces() {
        return spaces;
    }
}
