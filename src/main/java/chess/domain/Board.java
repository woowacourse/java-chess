package chess.domain;

import chess.domain.piece.Empty;
import chess.domain.piece.Path;
import chess.domain.piece.Paths;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceColor;
import chess.domain.position.Position;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class Board {

    private static final Piece EMPTY_PIECE = new Empty();
    private final Map<Piece, Position> coordinates;

    public Board(Map<Piece, Position> coordinates) {
        this.coordinates = coordinates;
    }

    public Board() {
        this.coordinates = new LinkedHashMap<>();
    }

    public void putPiece(Piece piece, Position position) {
        coordinates.put(piece, position);
    }

    public Piece findPieceBy(Position position) {
        return coordinates.entrySet()
                .stream()
                .filter(entry -> Objects.equals(entry.getValue(), position))
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse(EMPTY_PIECE)
                ;
    }

    public void move(Piece piece, Position target) {
        Path path = generatePath(piece);
        path.positions().forEach(position -> {
            System.out.println(position);
        });
        if (path.isAble(target)) {
            coordinates.remove(findPieceBy(target));
            putPiece(piece, target);
        }
    }

    public Path generatePath(Piece piece) {
        Paths paths = new Paths();
        paths = paths.findAllPath(piece, coordinates.get(piece));
        return paths.removeObstacles(piece, this);
    }

    public Map<Piece, Position> getCoordinates() {
        return coordinates;
    }

    public List<Piece> remainPieces(PieceColor color){
        return coordinates.keySet()
            .stream()
            .filter(piece -> piece.hasColor(color))
            .collect(Collectors.toList());
    }
}
