package chess.domain;

import java.util.List;

public interface Piece {
    List<Point> getCandidatePoints(Point start, Point end);
}
