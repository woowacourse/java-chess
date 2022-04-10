package chess.database.dto;

import chess.domain.board.Route;

public class RouteDto {

    private final PointDto source;
    private final PointDto destination;

    private RouteDto(PointDto source, PointDto destination) {
        this.source = source;
        this.destination = destination;
    }

    public static RouteDto of(Route route) {
        return new RouteDto(
            PointDto.of(route.getSource()),
            PointDto.of(route.getDestination())
        );
    }

    public PointDto getSource() {
        return source;
    }

    public PointDto getDestination() {
        return destination;
    }
}
