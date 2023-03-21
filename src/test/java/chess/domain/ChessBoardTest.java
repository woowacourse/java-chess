package chess.domain;

import chess.domain.chesspiece.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("ChessBoard 클래스")
class ChessBoardTest {

    @Nested
    @DisplayName("create 메서드로 생성된 chessBoard에서")
    class create {
        ChessBoard chessBoard = ChessBoardFactory.generate();
        Map<Square, Piece> pieces = chessBoard.getPieces();

        @Nested
        @DisplayName("Rank가 1이고")
        class rank_is_one {
            Rank rank = Rank.ONE;

            @ParameterizedTest(name = "File이 {0}이면 {1}이 위치한다.")
            @CsvSource(value = {"A,ROOK", "B,KNIGHT", "C,BISHOP", "D,QUEEN", "E,KING", "F,BISHOP", "G,KNIGHT", "H,ROOK"})
            void it_returns_piece(final File file, final PieceFixture piece) {
                assertThat(pieces.get(Square.of(rank, file))).isInstanceOf(piece.getPieceClass());
            }
        }

        @Nested
        @DisplayName("Rank가 2이고")
        class rank_is_two {
            Rank rank = Rank.TWO;

            @ParameterizedTest(name = "File이 {0}이면 PAWN이 위치한다.")
            @CsvSource(value = {"A", "B", "C", "D", "E", "F", "G", "H"})
            void it_returns_piece(final File file) {
                assertThat(pieces.get(Square.of(rank, file))).isInstanceOf(Pawn.class);
            }
        }

        @Nested
        @DisplayName("Rank가 3이고")
        class rank_is_three {
            Rank rank = Rank.THREE;

            @ParameterizedTest(name = "File이 {0}이면 기물이 존재하지 않는다.")
            @CsvSource(value = {"A", "B", "C", "D", "E", "F", "G", "H"})
            void it_returns_piece(final File file) {
                assertThat(pieces.get(Square.of(rank, file))).isInstanceOf(EmptyPiece.class);
            }
        }

        @Nested
        @DisplayName("Rank가 4이고")
        class rank_is_four {
            Rank rank = Rank.FOUR;

            @ParameterizedTest(name = "File이 {0}이면 기물이 존재하지 않는다.")
            @CsvSource(value = {"A", "B", "C", "D", "E", "F", "G", "H"})
            void it_returns_piece(final File file) {
                assertThat(pieces.get(Square.of(rank, file))).isInstanceOf(EmptyPiece.class);
            }
        }

        @Nested
        @DisplayName("Rank가 5이고")
        class rank_is_five {
            Rank rank = Rank.FIVE;

            @ParameterizedTest(name = "File이 {0}이면 기물이 존재하지 않는다.")
            @CsvSource(value = {"A", "B", "C", "D", "E", "F", "G", "H"})
            void it_returns_piece(final File file) {
                assertThat(pieces.get(Square.of(rank, file))).isInstanceOf(EmptyPiece.class);
            }
        }

        @Nested
        @DisplayName("Rank가 6이고")
        class rank_is_six {
            Rank rank = Rank.SIX;

            @ParameterizedTest(name = "File이 {0}이면 기물이 존재하지 않는다.")
            @CsvSource(value = {"A", "B", "C", "D", "E", "F", "G", "H"})
            void it_returns_piece(final File file) {
                assertThat(pieces.get(Square.of(rank, file))).isInstanceOf(EmptyPiece.class);
            }
        }

        @Nested
        @DisplayName("Rank가 7이고")
        class rank_is_seven {
            Rank rank = Rank.SEVEN;

            @ParameterizedTest(name = "File이 {0}이면 PAWN이 위치한다.")
            @CsvSource(value = {"A", "B", "C", "D", "E", "F", "G", "H"})
            void it_returns_piece(final File file) {
                assertThat(pieces.get(Square.of(rank, file))).isInstanceOf(Pawn.class);
            }
        }

        @Nested
        @DisplayName("Rank가 8이고")
        class rank_is_eight {
            Rank rank = Rank.EIGHT;

            @ParameterizedTest(name = "File이 {0}이면 {1}이 위치한다.")
            @CsvSource(value = {"A,ROOK", "B,KNIGHT", "C,BISHOP", "D,QUEEN", "E,KING", "F,BISHOP", "G,KNIGHT", "H,ROOK"})
            void it_returns_piece(final File file, final PieceFixture piece) {
                assertThat(pieces.get(Square.of(rank, file))).isInstanceOf(piece.getPieceClass());
            }
        }
    }

    @Nested
    @DisplayName("move 메서드는")
    class move {

        Map<Square, Piece> pieces;

        @BeforeEach
        void empty_chessboard() {
            pieces = new HashMap<>();
            for (Rank rank : Rank.values()) {
                for (File file : File.values()) {
                    pieces.put(Square.of(rank, file), EmptyPiece.getInstance());
                }
            }
        }

        @Nested
        @DisplayName("폰의 경우에")
        class given_pawn {

            @DisplayName("앞의 두 칸에 기물이 존재하지 않는다면 true를 반환한다")
            @Test
            void it_returns_true1() {
                Square from = Square.of(Rank.SEVEN, File.A);
                Square to = Square.of(Rank.FIVE, File.A);
                pieces.put(from, Pawn.of(Side.BLACK)
                        .get(0));
                ChessBoard chessBoard = new ChessBoard(pieces, Turn.BLACK_TURN);

                assertThat(chessBoard.canMove(from, to)).isTrue();
            }

            @DisplayName("앞의 칸에 기물이 존재한다면 false를 반환한다")
            @Test
            void it_returns_false1() {
                Square from = Square.of(Rank.SEVEN, File.A);
                Square middle = Square.of(Rank.SIX, File.A);
                Square to = Square.of(Rank.FIVE, File.A);
                pieces.put(from, Pawn.of(Side.BLACK)
                        .get(0));
                pieces.put(middle, Queen.of(Side.WHITE));
                ChessBoard chessBoard = new ChessBoard(pieces, Turn.BLACK_TURN);

                assertThat(chessBoard.canMove(from, to)).isFalse();
            }

            @DisplayName("대각선 앞 칸에 적의 기물이 존재한다면 true를 반환한다")
            @Test
            void it_returns_true2() {
                Square from = Square.of(Rank.SEVEN, File.B);
                Square to = Square.of(Rank.SIX, File.A);
                pieces.put(from, Pawn.of(Side.BLACK)
                        .get(0));
                pieces.put(to, Queen.of(Side.WHITE));
                ChessBoard chessBoard = new ChessBoard(pieces, Turn.BLACK_TURN);

                assertThat(chessBoard.canMove(from, to)).isTrue();
            }

            @DisplayName("대각선 앞 칸에 적의 기물이 존재하지 않는다면 false를 반환한다")
            @Test
            void it_returns_false2() {
                Square from = Square.of(Rank.SEVEN, File.B);
                Square to = Square.of(Rank.SIX, File.A);
                pieces.put(from, Pawn.of(Side.BLACK)
                        .get(0));
                pieces.put(to, Queen.of(Side.BLACK));
                ChessBoard chessBoard = new ChessBoard(pieces, Turn.BLACK_TURN);

                assertThat(chessBoard.canMove(from, to)).isFalse();
                assertThat(chessBoard.canMove(from, Square.of(Rank.SIX, File.C))).isFalse();
            }
        }

        @Nested
        @DisplayName("비숍의 경우에")
        class given_bishop {

            @DisplayName("대각선 내에 기물이 존재하지 않는다면 true를 반환한다")
            @Test
            void it_returns_true() {
                Square from = Square.of(Rank.THREE, File.E);
                Square to = Square.of(Rank.SEVEN, File.A);
                pieces.put(from, Bishop.of(Side.BLACK));
                ChessBoard chessBoard = new ChessBoard(pieces, Turn.BLACK_TURN);

                assertThat(chessBoard.canMove(from, to)).isTrue();
            }

            @DisplayName("대각선 내에 기물이 존재한다면 false를 반환한다")
            @Test
            void it_returns_false1() {
                Square from = Square.of(Rank.THREE, File.E);
                Square middle = Square.of(Rank.FIVE, File.C);
                Square to = Square.of(Rank.SEVEN, File.A);
                pieces.put(from, Bishop.of(Side.BLACK));
                pieces.put(middle, Queen.of(Side.WHITE));
                ChessBoard chessBoard = new ChessBoard(pieces, Turn.BLACK_TURN);

                assertThat(chessBoard.canMove(from, to)).isFalse();
            }

            @DisplayName("도착 위치에 적의 기물이 존재한다면 true를 반환한다")
            @Test
            void it_returns_true2() {
                Square from = Square.of(Rank.SEVEN, File.B);
                Square to = Square.of(Rank.SIX, File.A);
                pieces.put(from, Bishop.of(Side.BLACK));
                pieces.put(to, Queen.of(Side.WHITE));
                ChessBoard chessBoard = new ChessBoard(pieces, Turn.BLACK_TURN);

                assertThat(chessBoard.canMove(from, to)).isTrue();
            }

            @DisplayName("도착 위치가 비어있다면 true를 반환한다")
            @Test
            void it_returns_true3() {
                Square from = Square.of(Rank.SEVEN, File.B);
                Square to = Square.of(Rank.SIX, File.A);
                pieces.put(from, Bishop.of(Side.BLACK));
                ChessBoard chessBoard = new ChessBoard(pieces, Turn.BLACK_TURN);

                assertThat(chessBoard.canMove(from, to)).isTrue();
            }

            @DisplayName("도착 위치에 아군의 기물이 존재한다면 false를 반환한다")
            @Test
            void it_returns_false2() {
                Square from = Square.of(Rank.SEVEN, File.B);
                Square to = Square.of(Rank.SIX, File.A);
                pieces.put(from, Bishop.of(Side.BLACK));
                pieces.put(to, Queen.of(Side.BLACK));
                ChessBoard chessBoard = new ChessBoard(pieces, Turn.BLACK_TURN);

                assertThat(chessBoard.canMove(from, to)).isFalse();
            }
        }

        @Nested
        @DisplayName("룩의 경우에")
        class given_rook {

            @DisplayName("직선 내에 기물이 존재하지 않는다면 true를 반환한다")
            @Test
            void it_returns_true() {
                Square from = Square.of(Rank.THREE, File.E);
                Square to = Square.of(Rank.THREE, File.A);
                pieces.put(from, Rook.of(Side.BLACK));
                ChessBoard chessBoard = new ChessBoard(pieces, Turn.BLACK_TURN);

                assertThat(chessBoard.canMove(from, to)).isTrue();
            }

            @DisplayName("작선 내에 기물이 존재한다면 false를 반환한다")
            @Test
            void it_returns_false1() {
                Square from = Square.of(Rank.THREE, File.E);
                Square middle = Square.of(Rank.THREE, File.B);
                Square to = Square.of(Rank.THREE, File.A);
                pieces.put(from, Rook.of(Side.BLACK));
                pieces.put(middle, Queen.of(Side.WHITE));
                ChessBoard chessBoard = new ChessBoard(pieces, Turn.BLACK_TURN);

                assertThat(chessBoard.canMove(from, to)).isFalse();
            }

            @DisplayName("도착 위치에 적의 기물이 존재한다면 true를 반환한다")
            @Test
            void it_returns_true2() {
                Square from = Square.of(Rank.SEVEN, File.B);
                Square to = Square.of(Rank.SIX, File.B);
                pieces.put(from, Rook.of(Side.BLACK));
                pieces.put(to, Queen.of(Side.WHITE));
                ChessBoard chessBoard = new ChessBoard(pieces, Turn.BLACK_TURN);

                assertThat(chessBoard.canMove(from, to)).isTrue();
            }

            @DisplayName("도착 위치가 비어 있다면 true를 반환한다")
            @Test
            void it_returns_true3() {
                Square from = Square.of(Rank.SEVEN, File.B);
                Square to = Square.of(Rank.SIX, File.B);
                pieces.put(from, Rook.of(Side.BLACK));
                ChessBoard chessBoard = new ChessBoard(pieces, Turn.BLACK_TURN);

                assertThat(chessBoard.canMove(from, to)).isTrue();
            }

            @DisplayName("도착 위치에 아군의 기물이 존재한다면 false를 반환한다")
            @Test
            void it_returns_false2() {
                Square from = Square.of(Rank.SEVEN, File.B);
                Square to = Square.of(Rank.FIVE, File.B);
                pieces.put(from, Rook.of(Side.BLACK));
                pieces.put(to, Queen.of(Side.BLACK));
                ChessBoard chessBoard = new ChessBoard(pieces, Turn.BLACK_TURN);

                assertThat(chessBoard.canMove(from, to)).isFalse();
            }
        }

        @Nested
        @DisplayName("나이트의 경우에")
        class given_knight {

            @DisplayName("도착 위치에 적의 기물이 존재한다면 true를 반환한다")
            @Test
            void it_returns_true() {
                Square from = Square.of(Rank.THREE, File.E);
                Square to = Square.of(Rank.TWO, File.G);
                pieces.put(from, Knight.of(Side.BLACK));
                pieces.put(to, Queen.of(Side.WHITE));
                ChessBoard chessBoard = new ChessBoard(pieces, Turn.BLACK_TURN);

                assertThat(chessBoard.canMove(from, to)).isTrue();
            }

            @DisplayName("도착 위치가 비어있다면 true를 반환한다")
            @Test
            void it_returns_true2() {
                Square from = Square.of(Rank.THREE, File.E);
                Square to = Square.of(Rank.TWO, File.G);
                pieces.put(from, Knight.of(Side.BLACK));
                ChessBoard chessBoard = new ChessBoard(pieces, Turn.BLACK_TURN);

                assertThat(chessBoard.canMove(from, to)).isTrue();
            }

            @DisplayName("도착 위치에 아군의 기물이 존재한다면 false를 반환한다")
            @Test
            void it_returns_false() {
                Square from = Square.of(Rank.SEVEN, File.B);
                Square to = Square.of(Rank.FIVE, File.C);
                pieces.put(from, Knight.of(Side.BLACK));
                pieces.put(to, Queen.of(Side.BLACK));
                ChessBoard chessBoard = new ChessBoard(pieces, Turn.BLACK_TURN);

                assertThat(chessBoard.canMove(from, to)).isFalse();
            }
        }

        @Nested
        @DisplayName("퀸의 경우에")
        class given_queen {

            @DisplayName("이동 경로상에 기물이 존재하지 않는다면 true를 반환한다")
            @Test
            void it_returns_true() {
                Square from1 = Square.of(Rank.THREE, File.E);
                Square from2 = Square.of(Rank.FOUR, File.D);
                Square to1 = Square.of(Rank.THREE, File.A);
                Square to2 = Square.of(Rank.SEVEN, File.A);
                pieces.put(from1, Queen.of(Side.BLACK));
                pieces.put(from2, Queen.of(Side.WHITE));

                ChessBoard chessBoard = new ChessBoard(pieces, Turn.BLACK_TURN);

                assertThat(chessBoard.canMove(from1, to1)).isTrue();
                assertThat(chessBoard.canMove(from2, to2)).isTrue();
            }

            @DisplayName("이동 경로 상에 기물이 존재한다면 false를 반환한다")
            @Test
            void it_returns_false1() {
                Square from = Square.of(Rank.THREE, File.E);
                Square middle1 = Square.of(Rank.THREE, File.B);
                Square middle2 = Square.of(Rank.FIVE, File.C);
                Square to1 = Square.of(Rank.THREE, File.A);
                Square to2 = Square.of(Rank.SEVEN, File.A);
                pieces.put(from, Queen.of(Side.BLACK));
                pieces.put(middle1, Queen.of(Side.WHITE));
                pieces.put(middle2, Queen.of(Side.WHITE));
                ChessBoard chessBoard = new ChessBoard(pieces, Turn.BLACK_TURN);

                assertThat(chessBoard.canMove(from, to1)).isFalse();
                assertThat(chessBoard.canMove(from, to2)).isFalse();
            }

            @DisplayName("도착 위치에 적의 기물이 존재한다면 true를 반환한다")
            @Test
            void it_returns_true2() {
                Square from = Square.of(Rank.SEVEN, File.B);
                Square to = Square.of(Rank.THREE, File.F);
                pieces.put(from, Queen.of(Side.BLACK));
                pieces.put(to, Queen.of(Side.WHITE));
                ChessBoard chessBoard = new ChessBoard(pieces, Turn.BLACK_TURN);

                assertThat(chessBoard.canMove(from, to)).isTrue();
            }

            @DisplayName("도착 위치가 비어 있다면 true를 반환한다")
            @Test
            void it_returns_true3() {
                Square from = Square.of(Rank.SEVEN, File.B);
                Square to = Square.of(Rank.THREE, File.F);
                pieces.put(from, Queen.of(Side.BLACK));
                ChessBoard chessBoard = new ChessBoard(pieces, Turn.BLACK_TURN);

                assertThat(chessBoard.canMove(from, to)).isTrue();
            }

            @DisplayName("도착 위치에 아군의 기물이 존재한다면 false를 반환한다")
            @Test
            void it_returns_false2() {
                Square from = Square.of(Rank.SEVEN, File.B);
                Square to1 = Square.of(Rank.THREE, File.A);
                Square to2 = Square.of(Rank.SEVEN, File.A);
                pieces.put(from, Queen.of(Side.BLACK));
                pieces.put(to1, King.of(Side.BLACK));
                pieces.put(to2, King.of(Side.BLACK));
                ChessBoard chessBoard = new ChessBoard(pieces, Turn.BLACK_TURN);

                assertThat(chessBoard.canMove(from, to1)).isFalse();
                assertThat(chessBoard.canMove(from, to2)).isFalse();
            }
        }

        @Nested
        @DisplayName("킹의 경우에")
        class given_king {

            @DisplayName("도착 위치에 적의 기물이 존재한다면 true를 반환한다")
            @Test
            void it_returns_true1() {
                Square from = Square.of(Rank.THREE, File.E);
                Square to = Square.of(Rank.THREE, File.F);
                pieces.put(from, King.of(Side.BLACK));
                pieces.put(to, Queen.of(Side.WHITE));
                ChessBoard chessBoard = new ChessBoard(pieces, Turn.BLACK_TURN);

                assertThat(chessBoard.canMove(from, to)).isTrue();
            }

            @DisplayName("도착 위치가 비어 있다면 true를 반환한다")
            @Test
            void it_returns_true2() {
                Square from = Square.of(Rank.THREE, File.E);
                Square to = Square.of(Rank.THREE, File.F);
                pieces.put(from, King.of(Side.BLACK));
                ChessBoard chessBoard = new ChessBoard(pieces, Turn.BLACK_TURN);

                assertThat(chessBoard.canMove(from, to)).isTrue();
            }

            @DisplayName("도착 위치에 아군의 기물이 존재한다면 false를 반환한다")
            @Test
            void it_returns_false() {
                Square from = Square.of(Rank.SEVEN, File.B);
                Square to = Square.of(Rank.SIX, File.A);
                pieces.put(from, King.of(Side.BLACK));
                pieces.put(to, Queen.of(Side.BLACK));
                ChessBoard chessBoard = new ChessBoard(pieces, Turn.BLACK_TURN);

                assertThat(chessBoard.canMove(from, to)).isFalse();
            }
        }

        @Nested
        @DisplayName("각 진영의 차례에 관해서")
        class given_turn {

            @DisplayName("최초 순서가 백 진영이 아닌 경우 예외를 던진다")
            @Test
            void it_throws_exception_when_first_turn_is_not_white() {
                Square from = Square.of(Rank.THREE, File.E);
                Square to = Square.of(Rank.TWO, File.G);
                pieces.put(from, Knight.of(Side.BLACK));
                ChessBoard chessBoard = new ChessBoard(pieces);

                assertThatThrownBy(() -> chessBoard.canMove(from, to))
                        .isInstanceOf(IllegalArgumentException.class);
            }

            @DisplayName("백 진영이 연속해서 차례를 진행할 경우 예외를 던진다")
            @Test
            void it_throws_exception_when_white_side_has_two_turns_in_a_row() {
                Square from = Square.of(Rank.SEVEN, File.B);
                Square to1 = Square.of(Rank.FIVE, File.C);
                Square to2 = Square.of(Rank.FOUR, File.E);
                pieces.put(from, Knight.of(Side.WHITE));
                ChessBoard chessBoard = new ChessBoard(pieces);

                assertThatThrownBy(() -> {
                    chessBoard.canMove(from, to1);
                    chessBoard.canMove(to1, to2);
                }).isInstanceOf(IllegalArgumentException.class);
            }

            @DisplayName("흑 진영이 연속해서 차례를 진행할 경우 예외를 던진다")
            @Test
            void it_throws_exception_when_black_side_has_two_turns_in_a_row() {
                Square blackFrom = Square.of(Rank.SEVEN, File.B);
                Square blackTo1 = Square.of(Rank.FIVE, File.C);
                Square blackTo2 = Square.of(Rank.FOUR, File.E);
                pieces.put(blackFrom, Knight.of(Side.BLACK));
                ChessBoard chessBoard = new ChessBoard(pieces, Turn.BLACK_TURN);

                assertThatThrownBy(() -> {
                    chessBoard.canMove(blackFrom, blackTo1);
                    chessBoard.canMove(blackTo1, blackTo2);
                }).isInstanceOf(IllegalArgumentException.class);
            }
        }
    }
}
