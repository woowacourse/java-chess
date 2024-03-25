package dto;

import domain.chess.Point;

public record RouteDto(String sourceText, String destinationText ) {

    public Point getStartPoint() {
        return Point.from(sourceText);
    }

    public Point getEndPoint() {
        return Point.from(destinationText);
    }
}
