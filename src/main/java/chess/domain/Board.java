package chess.domain;

import chess.domain.piece.Empty;
import chess.domain.piece.Path;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class Board {

    private static final Piece EMPTY_PIECE = new Empty();
    private final Map<Piece, Position> coordinates;

    public Board(Map<Piece, Position> coordinates) {
        this.coordinates = coordinates;
    }

    public Board() {
        this.coordinates = new LinkedHashMap<>();
    }

    private void putPiece(Piece piece, Position position) {
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
//        Position source = Position.of(values.get(1));
//        Position target = Position.of(values.get(2));
//        Piece piece = findPieceBy(source)
//                .orElseThrow(() -> new IllegalArgumentException("source 위치에 말이 없습니다."));
//        moveCurrentPiece(player, target, piece);
    }

    public void move2(Position source, Position target) {
        Piece piece = findPieceBy(source);
        putPiece(piece, target);
    }

    public void move3(Piece piece, Position position) {
        putPiece(piece, position);
    }

    private void moveCurrentPiece(Player player, Position target, Piece piece) {
//        if (player.isOwnerOf(piece)) {
//            Path path = Path.of(piece, this);
//            if (piece.canMove(this) && path.isAble(target)) {
//                putPiece(piece, target);
//            }
//        }
    }

    public boolean isEmpty(Position position) {
        return coordinates.containsValue(position);
    }
}
