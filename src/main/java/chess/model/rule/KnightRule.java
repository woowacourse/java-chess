package chess.model.rule;

import chess.model.board.Board;
import chess.model.board.Square;
import chess.model.unit.Piece;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

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

        private Function<Square, Square> square1;
        private Function<Square, Square> square2;

        Diagonal(final Function<Square, Square> square1, final Function<Square, Square> square2) {
            this.square1 = square1;
            this.square2 = square2;
        }

        List<Square> getSquares(final Square square) {
            final List<Square> squares = new ArrayList<>();
            final Square diagonal;
            try {
                diagonal = square2.apply(square1.apply(square));
            } catch (NullPointerException e) {
                return squares;
            }
            squares.add(square1.apply(diagonal));
            squares.add(square2.apply(diagonal));
            return squares.stream()
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
        }
    }
}
