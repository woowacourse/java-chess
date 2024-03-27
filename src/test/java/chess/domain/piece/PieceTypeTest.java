package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.square.File;
import chess.domain.square.Rank;
import chess.domain.square.Square;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class PieceTypeTest {

    @Nested
    class KingStrategyTest {

        @DisplayName("킹이 이동할 수 있으면 True를 리턴한다.")
        @ParameterizedTest
        @CsvSource({"d, FIVE", "e, FIVE", "f, FIVE", "f, FOUR", "f, THREE", "e, THREE", "d, THREE", "d, FOUR"})
        void returnTrueIfKingCanMove(final File file, final Rank rank) {
            final PieceType pieceType = PieceType.KING;
            final Square source = new Square(File.e, Rank.FOUR);
            final Square target = new Square(file, rank);

            boolean actual = pieceType.canMove(source, target);

            assertThat(actual).isTrue();
        }

        @DisplayName("킹이 이동할 수 없으면 False를 리턴한다.")
        @Test
        void returnFalseIfKingCannotMove() {
            final PieceType pieceType = PieceType.KING;
            final Square source = new Square(File.e, Rank.FOUR);
            final Square target = new Square(File.c, Rank.SIX);

            boolean actual = pieceType.canMove(source, target);

            assertThat(actual).isFalse();
        }
    }

    @Nested
    class QueenStrategyTest {

        @DisplayName("퀸이 이동할 수 있으면 True를 리턴한다.")
        @ParameterizedTest
        @CsvSource({"e, SIX", "e, ONE", "h, FOUR", "a, FOUR", "a, EIGHT", "h, SEVEN", "h, ONE", "b, ONE"})
        void returnTrueIfQueenCanMove(final File file, final Rank rank) {
            final PieceType pieceType = PieceType.QUEEN;
            final Square source = new Square(File.e, Rank.FOUR);
            final Square target = new Square(file, rank);

            boolean actual = pieceType.canMove(source, target);

            assertThat(actual).isTrue();
        }

        @DisplayName("퀸이 이동할 수 없으면 False를 리턴한다.")
        @Test
        void returnFalseIfQueenCannotMove() {
            final PieceType pieceType = PieceType.QUEEN;
            final Square source = new Square(File.e, Rank.FOUR);
            final Square target = new Square(File.g, Rank.FIVE);

            boolean actual = pieceType.canMove(source, target);

            assertThat(actual).isFalse();
        }
    }

    @Nested
    class RookStrategyTest {

        @DisplayName("룩이 이동할 수 있으면 True를 리턴한다.")
        @ParameterizedTest
        @CsvSource({"e, EIGHT", "e, ONE", "h, FOUR", "a, FOUR"})
        void returnTrueIfRookCanMove(final File file, final Rank rank) {
            final PieceType pieceType = PieceType.ROOK;
            final Square source = new Square(File.e, Rank.FOUR);
            final Square target = new Square(file, rank);

            boolean actual = pieceType.canMove(source, target);

            assertThat(actual).isTrue();
        }

        @DisplayName("룩이 이동할 수 없으면 False를 리턴한다.")
        @Test
        void returnFalseIfRookCannotMove() {
            final PieceType pieceType = PieceType.ROOK;
            final Square source = new Square(File.e, Rank.FOUR);
            final Square target = new Square(File.h, Rank.FIVE);

            boolean actual = pieceType.canMove(source, target);

            assertThat(actual).isFalse();
        }
    }

    @Nested
    class BishopStrategyTest {

        @DisplayName("비숍이 이동할 수 있으면 True를 리턴한다.")
        @ParameterizedTest
        @CsvSource({"h, SEVEN", "b, ONE", "h, ONE", "a, EIGHT"})
        void returnTrueIfBishopCanMove(final File file, final Rank rank) {
            final PieceType pieceType = PieceType.BISHOP;
            final Square source = new Square(File.e, Rank.FOUR);
            final Square target = new Square(file, rank);

            boolean actual = pieceType.canMove(source, target);

            assertThat(actual).isTrue();
        }

        @DisplayName("비숍이 이동할 수 없으면 False를 리턴한다.")
        @Test
        void returnFalseIfBishopCannotMove() {
            final PieceType pieceType = PieceType.BISHOP;
            final Square source = new Square(File.e, Rank.FOUR);
            final Square target = new Square(File.e, Rank.ONE);

            boolean actual = pieceType.canMove(source, target);

            assertThat(actual).isFalse();
        }
    }

    @Nested
    class KnightStrategyTest {

        @DisplayName("나이트가 이동할 수 있으면 True를 리턴한다.")
        @ParameterizedTest
        @CsvSource({"f, SIX", "d, SIX", "c, FIVE", "c, THREE", "d, TWO", "f, TWO", "g, THREE", "g, FIVE"})
        void returnTrueIfKnightCanMove(final File file, final Rank rank) {
            final PieceType pieceType = PieceType.KNIGHT;
            final Square source = new Square(File.e, Rank.FOUR);
            final Square target = new Square(file, rank);

            boolean actual = pieceType.canMove(source, target);

            assertThat(actual).isTrue();
        }

        @DisplayName("나이트가 이동할 수 없으면 False를 리턴한다.")
        @Test
        void returnFalseIfKnightCannotMove() {
            final PieceType pieceType = PieceType.KNIGHT;
            final Square source = new Square(File.e, Rank.FOUR);
            final Square target = new Square(File.e, Rank.SIX);

            boolean actual = pieceType.canMove(source, target);

            assertThat(actual).isFalse();
        }
    }

    @Nested
    class PawnStrategyTest {

        @DisplayName("폰이 이동할 수 있으면 True를 리턴한다.")
        @ParameterizedTest
        @CsvSource({"e, THREE", "e, FOUR", "f, THREE"})
        void returnTrueIfPawnCanMove(final File file, final Rank rank) {
            final PieceType pieceType = PieceType.PAWN;
            final Square source = new Square(File.e, Rank.TWO);
            final Square target = new Square(file, rank);

            boolean actual = pieceType.canMove(source, target);

            assertThat(actual).isTrue();
        }

        @DisplayName("폰이 이동할 수 없으면 False를 리턴한다.")
        @Test
        void returnFalseIfPawnCannotMove() {
            final PieceType pieceType = PieceType.PAWN;
            final Square source = new Square(File.e, Rank.TWO);
            final Square target = new Square(File.e, Rank.FIVE);

            boolean actual = pieceType.canMove(source, target);

            assertThat(actual).isFalse();
        }
    }
}
