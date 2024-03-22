package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.position.File;
import chess.domain.position.Rank;
import chess.domain.position.Square;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


@DisplayName("말 종류")
class PieceTypeTest {

    private PieceType type;
    private Square source;
    private Board board;

    @DisplayName("폰")
    @Nested
    class Pawn {

        @BeforeEach
        void setUp() {
            type = PieceType.PAWN;
            source = Square.of(File.a, Rank.SEVEN);
            board = new Board();
        }

        @DisplayName("폰은 한 칸 전진할 수 있다.")
        @Test
        void pawnCanMove() {
            // given
            Square destination = Square.of(File.a, Rank.SIX);
            Piece destinationPiece = board.findPieceBySquare(destination);

            // when
            boolean actual = type.canMove(source, destination, board);

            // then
            assertThat(actual).isTrue();
        }

        @DisplayName("폰은 최초 이동이면 두 칸 전진할 수 있다.")
        @Test
        void pawnCanFirstMove() {
            // given
            Square destination = Square.of(File.a, Rank.FIVE);
            Piece destinationPiece = board.findPieceBySquare(destination);

            // when
            boolean actual = type.canMove(source, destination, board);

            // then
            assertThat(actual).isTrue();
        }

        @DisplayName("폰은 최초 이동이 아니면 두 칸 전진할 수 없다.")
        @Test
        void pawnCannotFirstMove() {
            // given
            source = Square.of(File.a, Rank.SIX);
            Square destination = Square.of(File.a, Rank.FOUR);
            Piece destinationPiece = board.findPieceBySquare(destination);

            // when
            boolean actual = type.canMove(source, destination, board);

            // then
            assertThat(actual).isFalse();
        }
    }

    @DisplayName("나이트")
    @Nested
    class Knight {

        @BeforeEach
        void setUp() {
            type = PieceType.KNIGHT;
            source = Square.of(File.b, Rank.EIGHT);
            board = new Board();
        }

        @DisplayName("나이트는 주어진 규칙에 따라 움직일 수 있다.")
        @Test
        void knightWhiteCanMove() {
            // given
            Square destination = Square.of(File.c, Rank.SIX);
            Piece destinationPiece = board.findPieceBySquare(destination);

            // when
            boolean actual = type.canMove(source, destination, board);

            // then
            assertThat(actual).isTrue();
        }
    }

    @DisplayName("비숍")
    @Nested
    class Bishop {

        @BeforeEach
        void setUp() {
            type = PieceType.BISHOP;
            board = new Board();
        }

        @DisplayName("비숍은 대각선 방향으로 움직일 수 있다.")
        @Test
        void knightWhiteCanMove() {
            // given
            source = Square.of(File.e, Rank.THREE);
            Square destination = Square.of(File.b, Rank.SIX);
            Piece destinationPiece = board.findPieceBySquare(destination);

            // when
            boolean actual = type.canMove(source, destination, board);

            // then
            assertThat(actual).isTrue();
        }
    }

    @DisplayName("룩")
    @Nested
    class Rook {

        @BeforeEach
        void setUp() {
            type = PieceType.ROOK;
            board = new Board();
        }

        @DisplayName("룩은 수직 방향으로 움직일 수 있다.")
        @Test
        void rookVerticalCanMove() {
            // given
            source = Square.of(File.a, Rank.SIX);
            Square destination = Square.of(File.a, Rank.TWO);
            Piece destinationPiece = board.findPieceBySquare(destination);

            // when
            boolean actual = type.canMove(source, destination, board);

            // then
            assertThat(actual).isTrue();
        }

        @DisplayName("룩은 수평 방향으로 움직일 수 있다.")
        @Test
        void rookHorizontalCanMove() {
            // given
            source = Square.of(File.a, Rank.SIX);
            Square destination = Square.of(File.h, Rank.SIX);
            Piece destinationPiece = board.findPieceBySquare(destination);

            // when
            boolean actual = type.canMove(source, destination, board);

            // then
            assertThat(actual).isTrue();
        }
    }

    @DisplayName("퀸")
    @Nested
    class Queen {

        @BeforeEach
        void setUp() {
            type = PieceType.QUEEN;
            board = new Board();
        }

        @DisplayName("퀸은 수직 방향으로 움직일 수 있다.")
        @Test
        void queenVerticalCanMove() {
            // given
            source = Square.of(File.d, Rank.SIX);
            Square destination = Square.of(File.d, Rank.THREE);
            Piece destinationPiece = board.findPieceBySquare(destination);

            // when
            boolean actual = type.canMove(source, destination, board);

            // then
            assertThat(actual).isTrue();
        }

        @DisplayName("퀸은 수평 방향으로 움직일 수 있다.")
        @Test
        void queenHorizontalCanMove() {
            // given
            source = Square.of(File.d, Rank.SIX);
            Square destination = Square.of(File.a, Rank.SIX);
            Piece destinationPiece = board.findPieceBySquare(destination);

            // when
            boolean actual = type.canMove(source, destination, board);

            // then
            assertThat(actual).isTrue();
        }

        @DisplayName("퀸은 대각선 방향으로 움직일 수 있다.")
        @Test
        void queenWhiteCanMove() {
            // given
            source = Square.of(File.d, Rank.SIX);
            Square destination = Square.of(File.g, Rank.THREE);
            Piece destinationPiece = board.findPieceBySquare(destination);

            // when
            boolean actual = type.canMove(source, destination, board);

            // then
            assertThat(actual).isTrue();
        }
    }

    @DisplayName("킹")
    @Nested
    class King {

        @BeforeEach
        void setUp() {
            type = PieceType.KING;
            source = Square.of(File.e, Rank.EIGHT);
            board = new Board();
        }

        @DisplayName("킹은 수직 1칸 이동할 수 있다.")
        @Test
        void kingVerticalMove() {
            // given
            Square destination = Square.of(File.e, Rank.SEVEN);
            Piece destinationPiece = board.findPieceBySquare(destination);

            // when
            boolean actual = type.canMove(source, destination, board);

            // then
            assertThat(actual).isTrue();
        }

        @DisplayName("킹은 수평 1칸 이동할 수 있다.")
        @Test
        void kingHorizontalMove() {
            // given
            Square destination = Square.of(File.d, Rank.EIGHT);
            Piece destinationPiece = board.findPieceBySquare(destination);

            // when
            boolean actual = type.canMove(source, destination, board);

            // then
            assertThat(actual).isTrue();
        }

        @DisplayName("킹은 대각선 방향으로 1칸 이동할 수 있다.")
        @Test
        void queenWhiteCanMove() {
            // given
            Square destination = Square.of(File.f, Rank.SEVEN);
            Piece destinationPiece = board.findPieceBySquare(destination);
            // when
            boolean actual = type.canMove(source, destination, board);

            // then
            assertThat(actual).isTrue();
        }
    }
}
