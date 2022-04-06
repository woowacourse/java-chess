package chess.domain.board;

import java.util.List;

import chess.domain.piece.move.Direction;
import chess.dto.Arguments;

public class Route {

    private static final int ARGUMENT_SIZE = 2;
    private static final int ARGUMENT_FROM_INDEX = 0;
    private static final int ARGUMENT_TO_INDEX = 1;

    private final Point source;
    private final Point destination;

    public Route(Point source, Point destination) {
        this.source = source;
        this.destination = destination;
    }

    public static Route of(List<String> arguments) {
        validateSize(arguments);
        return new Route(Point.of(arguments.get(ARGUMENT_FROM_INDEX)),
            Point.of(arguments.get(ARGUMENT_TO_INDEX)));
    }

    public static Route of(Arguments argumentsObject) {
        List<String> arguments = argumentsObject.getArguments();
        return of(arguments);
    }

    private static void validateSize(List<String> arguments) {
        if (arguments.size() != ARGUMENT_SIZE) {
            throw new IllegalArgumentException("[ERROR] 출발지와 도착자가 올바르지 않습니다.(move a1 a2)");
        }
    }

    public int subtractVertical() {
        return destination.subtractVertical(source);
    }

    public int subtractHorizontal() {
        return destination.subtractHorizontal(source);
    }

    public boolean isArrived() {
        return source.equals(destination);
    }

    public Point getSource() {
        return source;
    }

    public Point getDestination() {
        return destination;
    }

    public Route moveSource(Direction direction) {
        Point sourceNext = this.source.next(direction);
        return new Route(sourceNext, destination);
    }
}
