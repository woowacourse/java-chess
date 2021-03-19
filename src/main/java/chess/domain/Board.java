package chess.domain;

import chess.domain.piece.Empty;
import chess.domain.piece.Path;
import chess.domain.piece.Paths;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

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

    public Map<Piece, Position> getCoordinates() {
        return coordinates;
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

    public Position findPositionBy(Piece piece) {
        return coordinates.get(piece);
    }

    public void move(Player player, List<String> values) {
        Position source = Position.of(values.get(1));
        Position target = Position.of(values.get(2));
        Piece piece = findPieceBy(source);
        moveCurrentPiece(player, target, piece);
    }

    public void move2(Position source, Position target) {
        Piece piece = findPieceBy(source);
        putPiece(piece, target);
    }

    public void move3(Piece piece, Position target) {
        Path path = generatePath(piece);
        if (path.isAble(target)) {
            putPiece(piece, target);
        }
    }

    private void moveCurrentPiece(Player player, Position target, Piece piece) {
//        if (player.isOwnerOf(piece)) {
//            Path path = Path.of(piece, this);
//            if (piece.canMove(this) && path.isAble(target)) {
//                putPiece(piece, target);
//            }
//        }
    }

    public Path generatePath(Piece piece) {
        Paths paths = new Paths(piece.findAllPath(coordinates.get(piece)));
        return paths.removeObstacles(this);
    }

    public boolean isEmpty(Position position) {
        return findPieceBy(position).equals(EMPTY_PIECE);
    }
}
