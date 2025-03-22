package chess;

import chess.piece.Piece;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Board {

    private final Map<Position, Piece> pieces;

    public Board(Map<Position, Piece> pieces) {
        this.pieces = pieces;
    }

    public void move(Position departure, Position arrival) {
        Piece piece = pieces.get(departure);

        List<Position> canMovePositions = piece.calculateCanMovePosition(departure, arrival);
        Optional<Position> isAlreadyExistAnotherPiece = canMovePositions.stream()
            .filter(position -> !position.equals(arrival)) // 도착지 탐색 제외
            .filter(position -> pieces.get(position) != null)
            .findAny();

        if (isAlreadyExistAnotherPiece.isPresent()) {
            throw new IllegalArgumentException("해당 위치로는 이동할 수 없습니다.");
        }

        // 해당 위치에 아무것도 존재하지 않는다면 이동한다
        if (pieces.get(arrival) == null) {
            pieces.put(departure, piece);
            pieces.remove(departure);
            return;
        }
        Piece currentPieceOfArrivalPosition = pieces.get(arrival);

        // 같은 팀 일 경우 해당 위치로 이동할 수 없다.
        if (piece.getColor() == currentPieceOfArrivalPosition.getColor()) {
            throw new IllegalArgumentException("해당 위치로는 이동할 수 없습니다.");
        }
        // 해당 위치의 기물을 제거 후 이동한다.
        pieces.remove(arrival);
        pieces.put(arrival, piece);
        pieces.remove(departure);
    }

    public Optional<Piece> getPiece(Column column, Row row) {
        return Optional.ofNullable(pieces.get(new Position(column, row)));
    }
}
