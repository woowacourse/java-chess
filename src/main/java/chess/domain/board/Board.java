package chess.domain.board;

import static java.util.stream.Collectors.*;

import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

import chess.domain.piece.Piece;

public class Board {
    private final Map<Position, Optional<Piece>> board;

    private Board(final Map<Position, Optional<Piece>> board) {
        this.board = board;
    }

    public static Board init() {
        return new Board(initialPlacing());
    }

    private static Map<Position, Optional<Piece>> initialPlacing() {
        return Position.getAllPositions()
            .stream()
            .collect(toMap(Function.identity(), Board::findInitialPieceOn));
    }

    private static Optional<Piece> findInitialPieceOn(Position position) {
        return Piece.getPieces()
            .stream()
            .filter(piece -> piece.canBePlacedOn(position))
            .findAny();
    }

    public Optional<Piece> findPieceBy(Position position) {
        return board.get(position);
    }

    // TODO: 경로 구하는 알고리즘 (직선, 대각선, 박스 형태)
    // TODO: 위 알고리즘을 통해 Sub-Map 구현하여 전달
    // TODO: SortedMap 등을 통해 시작과 끝 지점을 암시적으로 알려줄수 있는지? (ex. map.keySet().toArray()[0])
}
