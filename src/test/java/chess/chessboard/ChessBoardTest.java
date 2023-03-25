package chess.chessboard;

import chess.piece.*;
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
        ChessBoard chessBoard = new ChessBoardFactory().generate();
        Map<Square, Piece> pieces = chessBoard.getPieces();

        @Nested
        @DisplayName("Rank가 1이고")
        class rank_is_one {
            Rank rank = Rank.ONE;

            @ParameterizedTest(name = "File이 {0}이면 {1}의 기물이 위치한다.")
            @CsvSource(value = {"A,ROOK", "B,KNIGHT", "C,BISHOP", "D,QUEEN", "E,KING", "F,BISHOP", "G,KNIGHT", "H,ROOK"})
            void it_returns_piece(final File file, final PieceFixture piece) {
                assertThat(pieces.get(Square.of(rank, file))).isInstanceOf(piece.getPieceClass());
            }
        }

        @Nested
        @DisplayName("Rank가 2이고")
        class rank_is_two {
            Rank rank = Rank.TWO;

            @ParameterizedTest(name = "File이 {0}이면 {1}의 기물이 위치한다.")
            @CsvSource(value = {"A", "B", "C", "D", "E", "F", "G", "H"})
            void it_returns_piece(final File file) {
                assertThat(pieces.get(Square.of(rank, file))).isInstanceOf(Pawn.class);
            }
        }

        @Nested
        @DisplayName("Rank가 3이고")
        class rank_is_three {
            Rank rank = Rank.THREE;

            @ParameterizedTest(name = "File이 {0}이면 {1}의 기물이 위치한다.")
            @CsvSource(value = {"A", "B", "C", "D", "E", "F", "G", "H"})
            void it_returns_piece(final File file) {
                assertThat(pieces.get(Square.of(rank, file))).isInstanceOf(EmptyPiece.class);
            }
        }

        @Nested
        @DisplayName("Rank가 4이고")
        class rank_is_four {
            Rank rank = Rank.FOUR;

            @ParameterizedTest(name = "File이 {0}이면 {1}의 기물이 위치한다.")
            @CsvSource(value = {"A", "B", "C", "D", "E", "F", "G", "H"})
            void it_returns_piece(final File file) {
                assertThat(pieces.get(Square.of(rank, file))).isInstanceOf(EmptyPiece.class);
            }
        }

        @Nested
        @DisplayName("Rank가 5이고")
        class rank_is_five {
            Rank rank = Rank.FIVE;

            @ParameterizedTest(name = "File이 {0}이면 {1}의 기물이 위치한다.")
            @CsvSource(value = {"A", "B", "C", "D", "E", "F", "G", "H"})
            void it_returns_piece(final File file) {
                assertThat(pieces.get(Square.of(rank, file))).isInstanceOf(EmptyPiece.class);
            }
        }

        @Nested
        @DisplayName("Rank가 6이고")
        class rank_is_six {
            Rank rank = Rank.SIX;

            @ParameterizedTest(name = "File이 {0}이면 {1}의 기물이 위치한다.")
            @CsvSource(value = {"A", "B", "C", "D", "E", "F", "G", "H"})
            void it_returns_piece(final File file) {
                assertThat(pieces.get(Square.of(rank, file))).isInstanceOf(EmptyPiece.class);
            }
        }

        @Nested
        @DisplayName("Rank가 7이고")
        class rank_is_seven {
            Rank rank = Rank.SEVEN;

            @ParameterizedTest(name = "File이 {0}이면 {1}의 기물이 위치한다.")
            @CsvSource(value = {"A", "B", "C", "D", "E", "F", "G", "H"})
            void it_returns_piece(final File file) {
                assertThat(pieces.get(Square.of(rank, file))).isInstanceOf(Pawn.class);
            }
        }

        @Nested
        @DisplayName("Rank가 8이고")
        class rank_is_eight {
            Rank rank = Rank.EIGHT;

            @ParameterizedTest(name = "File이 {0}이면 {1}의 기물이 위치한다.")
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
                Square source = Square.of(Rank.SEVEN, File.A);
                Square destination = Square.of(Rank.FIVE, File.A);
                pieces.put(source, Pawn.getPawnsOf(Side.BLACK)
                                       .get(0));
                ChessBoard chessBoard = new ChessBoard(pieces);

                assertThat(chessBoard.move(source, destination)).isEqualTo(EmptyPiece.getInstance());
            }

            @DisplayName("앞의 칸에 기물이 존재한다면 false를 반환한다")
            @Test
            void it_returns_false1() {
                Square source = Square.of(Rank.SEVEN, File.A);
                Square middle = Square.of(Rank.SIX, File.A);
                Square destination = Square.of(Rank.FIVE, File.A);
                pieces.put(source, Pawn.getPawnsOf(Side.BLACK)
                                       .get(0));
                pieces.put(middle, Queen.getQueenOf(Side.WHITE));
                ChessBoard chessBoard = new ChessBoard(pieces);

                assertThatThrownBy(() -> chessBoard.move(source, destination))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("갈 수 없는 경로입니다");
            }

            @DisplayName("대각선 앞 칸에 적의 기물이 존재한다면 true를 반환한다")
            @Test
            void it_returns_true2() {
                Square source = Square.of(Rank.SEVEN, File.B);
                Square destination = Square.of(Rank.SIX, File.A);
                final Queen expected = Queen.getQueenOf(Side.WHITE);
                pieces.put(source, Pawn.getPawnsOf(Side.BLACK)
                                       .get(0));
                pieces.put(destination, expected);
                ChessBoard chessBoard = new ChessBoard(pieces);

                assertThat(chessBoard.move(source, destination)).isEqualTo(expected);
            }

            @DisplayName("대각선 앞 칸에 적의 기물이 존재하지 않는다면 false를 반환한다")
            @Test
            void it_returns_false2() {
                Square source = Square.of(Rank.SEVEN, File.B);
                Square destination = Square.of(Rank.SIX, File.A);
                pieces.put(source, Pawn.getPawnsOf(Side.BLACK)
                                       .get(0));
                pieces.put(destination, Queen.getQueenOf(Side.BLACK));
                ChessBoard chessBoard = new ChessBoard(pieces);

                assertThatThrownBy(() -> chessBoard.move(source, destination))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("갈 수 없는 경로입니다");
                assertThatThrownBy(() -> chessBoard.move(source, Square.of(Rank.SIX, File.C)))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("갈 수 없는 경로입니다");
            }
        }

        @Nested
        @DisplayName("비숍의 경우에")
        class given_bishop {

            @DisplayName("대각선 내에 기물이 존재하지 않는다면 true를 반환한다")
            @Test
            void it_returns_true() {
                Square source = Square.of(Rank.THREE, File.E);
                Square destination = Square.of(Rank.SEVEN, File.A);
                pieces.put(source, Bishop.getBishopsOf(Side.BLACK)
                                         .get(0));
                ChessBoard chessBoard = new ChessBoard(pieces);

                assertThat(chessBoard.move(source, destination)).isEqualTo(EmptyPiece.getInstance());
            }

            @DisplayName("대각선 내에 기물이 존재한다면 false를 반환한다")
            @Test
            void it_returns_false1() {
                Square source = Square.of(Rank.THREE, File.E);
                Square middle = Square.of(Rank.FIVE, File.C);
                Square destination = Square.of(Rank.SEVEN, File.A);
                pieces.put(source, Bishop.getBishopsOf(Side.BLACK)
                                         .get(0));
                pieces.put(middle, Queen.getQueenOf(Side.WHITE));
                ChessBoard chessBoard = new ChessBoard(pieces);

                assertThatThrownBy(() -> chessBoard.move(source, destination))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("갈 수 없는 경로입니다");
            }

            @DisplayName("도착 위치에 적의 기물이 존재한거나 비어있다면 true를 반환한다")
            @Test
            void it_returns_true2() {
                Square source = Square.of(Rank.SEVEN, File.B);
                Square destination = Square.of(Rank.SIX, File.A);
                pieces.put(source, Bishop.getBishopsOf(Side.BLACK)
                                         .get(0));
                final Queen expected = Queen.getQueenOf(Side.WHITE);
                pieces.put(destination, expected);
                ChessBoard chessBoard = new ChessBoard(pieces);

                assertThat(chessBoard.move(source, destination)).isEqualTo(expected);
                chessBoard.move(destination, source);
                assertThat(chessBoard.move(source, Square.of(Rank.FIVE, File.D))).isEqualTo(EmptyPiece.getInstance());
            }

            @DisplayName("도착 위치에 아군의 기물이 존재한다면 false를 반환한다")
            @Test
            void it_returns_false2() {
                Square source = Square.of(Rank.SEVEN, File.B);
                Square destination = Square.of(Rank.SIX, File.A);
                pieces.put(source, Bishop.getBishopsOf(Side.BLACK)
                                         .get(0));
                pieces.put(destination, Queen.getQueenOf(Side.BLACK));
                ChessBoard chessBoard = new ChessBoard(pieces);

                assertThatThrownBy(() -> chessBoard.move(source, destination))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("갈 수 없는 경로입니다");
            }
        }

        @Nested
        @DisplayName("룩의 경우에")
        class given_rook {

            @DisplayName("직선 내에 기물이 존재하지 않는다면 true를 반환한다")
            @Test
            void it_returns_true() {
                Square source = Square.of(Rank.THREE, File.E);
                Square destination = Square.of(Rank.THREE, File.A);
                pieces.put(source, Rook.getRooksOf(Side.BLACK)
                                       .get(0));
                ChessBoard chessBoard = new ChessBoard(pieces);

                assertThat(chessBoard.move(source, destination)).isEqualTo(EmptyPiece.getInstance());
            }

            @DisplayName("작선 내에 기물이 존재한다면 false를 반환한다")
            @Test
            void it_returns_false1() {
                Square source = Square.of(Rank.THREE, File.E);
                Square middle = Square.of(Rank.THREE, File.B);
                Square destination = Square.of(Rank.THREE, File.A);
                pieces.put(source, Rook.getRooksOf(Side.BLACK)
                                       .get(0));
                pieces.put(middle, Queen.getQueenOf(Side.WHITE));
                ChessBoard chessBoard = new ChessBoard(pieces);

                assertThatThrownBy(() -> chessBoard.move(source, destination))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("갈 수 없는 경로입니다");
            }

            @DisplayName("도착 위치에 적의 기물이 존재한거나 비어 있다면 true를 반환한다")
            @Test
            void it_returns_true2() {
                Square source = Square.of(Rank.SEVEN, File.B);
                Square destination = Square.of(Rank.SIX, File.B);
                pieces.put(source, Rook.getRooksOf(Side.BLACK)
                                       .get(0));
                final Queen expected = Queen.getQueenOf(Side.WHITE);
                pieces.put(destination, expected);
                ChessBoard chessBoard = new ChessBoard(pieces);

                assertThat(chessBoard.move(source, destination)).isEqualTo(expected);
                chessBoard.move(destination, source);
                assertThat(chessBoard.move(source, Square.of(Rank.SEVEN, File.D))).isEqualTo(EmptyPiece.getInstance());
            }

            @DisplayName("도착 위치에 아군의 기물이 존재한다면 false를 반환한다")
            @Test
            void it_returns_false2() {
                Square source = Square.of(Rank.SEVEN, File.B);
                Square destination = Square.of(Rank.FIVE, File.B);
                pieces.put(source, Rook.getRooksOf(Side.BLACK)
                                       .get(0));
                pieces.put(destination, Queen.getQueenOf(Side.BLACK));
                ChessBoard chessBoard = new ChessBoard(pieces);

                assertThatThrownBy(() -> chessBoard.move(source, destination))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("갈 수 없는 경로입니다");
            }
        }

        @Nested
        @DisplayName("나이트의 경우에")
        class given_knight {

            @DisplayName("도착 위치에 적의 기물이 존재하거나 비어있다면 true를 반환한다")
            @Test
            void it_returns_true() {
                Square source = Square.of(Rank.THREE, File.E);
                Square destination = Square.of(Rank.TWO, File.G);
                final Queen expected = Queen.getQueenOf(Side.WHITE);
                pieces.put(source, Knight.getKnightsOf(Side.BLACK)
                                         .get(0));
                pieces.put(destination, expected);
                ChessBoard chessBoard = new ChessBoard(pieces);

                assertThat(chessBoard.move(source, destination)).isEqualTo(expected);
                chessBoard.move(destination, source);
                assertThat(chessBoard.move(source, Square.of(Rank.FIVE, File.D))).isEqualTo(EmptyPiece.getInstance());
            }

            @DisplayName("도착 위치에 아군의 기물이 존재한다면 false를 반환한다")
            @Test
            void it_returns_false() {
                Square source = Square.of(Rank.SEVEN, File.B);
                Square destination = Square.of(Rank.FIVE, File.C);
                pieces.put(source, Knight.getKnightsOf(Side.BLACK)
                                         .get(0));
                pieces.put(destination, Queen.getQueenOf(Side.BLACK));
                ChessBoard chessBoard = new ChessBoard(pieces);

                assertThatThrownBy(() -> chessBoard.move(source, destination))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("갈 수 없는 경로입니다");
            }
        }

        @Nested
        @DisplayName("퀸의 경우에")
        class given_queen {

            @DisplayName("이동 경로상에 기물이 존재하지 않는다면 true를 반환한다")
            @Test
            void it_returns_true() {
                Square source = Square.of(Rank.THREE, File.E);
                Square destination1 = Square.of(Rank.THREE, File.A);
                Square destination2 = Square.of(Rank.SEVEN, File.A);

                pieces.put(source, Queen.getQueenOf(Side.BLACK));

                ChessBoard chessBoard = new ChessBoard(pieces);

                assertThat(chessBoard.move(source, destination1)).isEqualTo(EmptyPiece.getInstance());
                chessBoard.move(destination1, source);
                assertThat(chessBoard.move(source, destination2)).isEqualTo(EmptyPiece.getInstance());
            }

            @DisplayName("이동 경로 상에 기물이 존재한다면 false를 반환한다")
            @Test
            void it_returns_false1() {
                Square source = Square.of(Rank.THREE, File.E);
                Square middle1 = Square.of(Rank.THREE, File.B);
                Square middle2 = Square.of(Rank.FIVE, File.C);
                Square destination1 = Square.of(Rank.THREE, File.A);
                Square destination2 = Square.of(Rank.SEVEN, File.A);
                pieces.put(source, Queen.getQueenOf(Side.BLACK));
                pieces.put(middle1, Queen.getQueenOf(Side.WHITE));
                pieces.put(middle2, Queen.getQueenOf(Side.WHITE));
                ChessBoard chessBoard = new ChessBoard(pieces);

                assertThatThrownBy(() -> chessBoard.move(source, destination1))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("갈 수 없는 경로입니다");
                assertThatThrownBy(() -> chessBoard.move(source, destination2))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("갈 수 없는 경로입니다");
            }

            @DisplayName("도착 위치에 적의 기물이 존재한거나 비어 있다면 true를 반환한다")
            @Test
            void it_returns_true2() {
                Square source = Square.of(Rank.SEVEN, File.B);
                Square destination1 = Square.of(Rank.THREE, File.F);
                Square destination2 = Square.of(Rank.SEVEN, File.A);
                final Queen expected = Queen.getQueenOf(Side.WHITE);
                pieces.put(source, Queen.getQueenOf(Side.BLACK));
                pieces.put(destination1, expected);
                ChessBoard chessBoard = new ChessBoard(pieces);

                assertThat(chessBoard.move(source, destination1)).isEqualTo(expected);
                chessBoard.move(destination1, source);
                assertThat(chessBoard.move(source, destination2)).isEqualTo(EmptyPiece.getInstance());
            }

            @DisplayName("도착 위치에 아군의 기물이 존재한다면 false를 반환한다")
            @Test
            void it_returns_false2() {
                Square source = Square.of(Rank.SEVEN, File.B);
                Square destination1 = Square.of(Rank.THREE, File.A);
                Square destination2 = Square.of(Rank.SEVEN, File.A);
                pieces.put(source, Queen.getQueenOf(Side.BLACK));
                pieces.put(destination1, King.getKingOf(Side.BLACK));
                pieces.put(destination2, King.getKingOf(Side.BLACK));
                ChessBoard chessBoard = new ChessBoard(pieces);

                assertThatThrownBy(() -> chessBoard.move(source, destination1))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("갈 수 없는 경로입니다");
                assertThatThrownBy(() -> chessBoard.move(source, destination2))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("갈 수 없는 경로입니다");
            }
        }

        @Nested
        @DisplayName("킹의 경우에")
        class given_king {

            @DisplayName("도착 위치에 적의 기물이 존재한거나 비어 있다면 true를 반환한다")
            @Test
            void it_returns_true() {
                Square source = Square.of(Rank.THREE, File.E);
                Square destination1 = Square.of(Rank.THREE, File.F);
                Square destination2 = Square.of(Rank.FOUR, File.D);
                final Queen expected = Queen.getQueenOf(Side.WHITE);
                pieces.put(source, King.getKingOf(Side.BLACK));
                pieces.put(destination1, expected);
                ChessBoard chessBoard = new ChessBoard(pieces);

                assertThat(chessBoard.move(source, destination1)).isEqualTo(expected);
                chessBoard.move(destination1, source);
                assertThat(chessBoard.move(source, destination2)).isEqualTo(EmptyPiece.getInstance());
            }

            @DisplayName("도착 위치에 아군의 기물이 존재한다면 false를 반환한다")
            @Test
            void it_returns_false() {
                Square source = Square.of(Rank.SEVEN, File.B);
                Square destination = Square.of(Rank.SIX, File.A);
                pieces.put(source, King.getKingOf(Side.BLACK));
                pieces.put(destination, Queen.getQueenOf(Side.BLACK));
                ChessBoard chessBoard = new ChessBoard(pieces);

                assertThatThrownBy(() -> chessBoard.move(source, destination))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("갈 수 없는 경로입니다");
            }
        }
    }

    @Nested
    @DisplayName("getPieces 메서드는")
    class getPieces {
        ChessBoard chessBoard;

        @BeforeEach
        void 초기보드생성() {
            chessBoard = new ChessBoardFactory().generate();
        }

        @Test
        @DisplayName("Black 진영을 입력하면 해당 진영의 모든 기물을 반환한다")
        void it_returns_black_pieces() {
            assertThat(chessBoard.getPieces(Side.BLACK)
                                 .values())
                    .allMatch(piece -> piece.isSameSide(Side.BLACK))
                    .hasSize(16);
        }

        @Test
        @DisplayName("White 진영을 입력하면 해당 진영의 모든 기물을 반환한다")
        void it_returns_white_pieces() {
            assertThat(chessBoard.getPieces(Side.WHITE)
                                 .values())
                    .allMatch(piece -> piece.isSameSide(Side.WHITE))
                    .hasSize(16);
        }
    }
}
