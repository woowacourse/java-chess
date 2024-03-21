package dto;

import domain.piece.attribute.point.Point;

public record RouteDto(String sourceText, String destinationText ) {

    public Point getStartPoint() {
        return Point.from(sourceText);
    }

    public Point getEndPoint() {
        return Point.from(destinationText);
    }
}
