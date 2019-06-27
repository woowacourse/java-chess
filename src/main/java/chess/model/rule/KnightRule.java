package chess.model.rule;

import chess.model.board.Board;
import chess.model.board.Square;
import chess.model.unit.Piece;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

class KnightRule extends PieceRule {
    KnightRule() {
        super();
        pieceScore = 2.5;
    }

    @Override
    List<Square> getMovableSquares(Board board, final Square square, Piece piece) {
        final List<Square> candidate = new ArrayList<>();
        candidate.addAll(Diagonal.UPPER_LEFT.getSquares(square));
        candidate.addAll(Diagonal.UPPER_RIGHT.getSquares(square));
        candidate.addAll(Diagonal.LOWER_LEFT.getSquares(square));
        candidate.addAll(Diagonal.LOWER_RIGHT.getSquares(square));
        return candidate;
    }

    enum Diagonal {
        UPPER_LEFT(Square::getLeftOneNeighbor, Square::getUpOneNeighbor),
        UPPER_RIGHT(Square::getRightOneNeighbor, Square::getUpOneNeighbor),
        LOWER_LEFT(Square::getLeftOneNeighbor, Square::getDownOneNeighbor),
        LOWER_RIGHT(Square::getRightOneNeighbor, Square::getDownOneNeighbor);

        private Function<Square, Optional<Square>> square1;
        private Function<Square, Optional<Square>> square2;

        Diagonal(
                final Function<Square, Optional<Square>> square1,
                final Function<Square, Optional<Square>> square2
        ) {
            this.square1 = square1;
            this.square2 = square2;
        }

        List<Square> getSquares(final Square square) {
            final List<Square> squares = new ArrayList<>();
            Optional<Square> diagonal = square1.apply(square).flatMap(square2);
            if (diagonal.isPresent()) {
                square1.apply(diagonal.get()).ifPresent(squares::add);
                square2.apply(diagonal.get()).ifPresent(squares::add);
            }
            return squares;
        }
    }
}
