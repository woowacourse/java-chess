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
}
