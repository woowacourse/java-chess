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

import static chess.PieceFixture.BLACK_KING;
import static chess.PieceFixture.WHITE_KING;
import static chess.PositionFixture.THREE_B;
import static chess.PositionFixture.TWO_B;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("ChessBoard 클래스")
class ChessBoardTest {

    @Nested
    @DisplayName("create 메서드로 생성된 chessBoard에서")
    class create {
        ChessBoard chessBoard = new ChessBoardFactory().generate();
        Map<Position, Piece> pieces = chessBoard.getPieces();

        @Nested
        @DisplayName("Rank가 1이고")
        class rank_is_one {
            Rank rank = Rank.ONE;

            @ParameterizedTest(name = "File이 {0}이면 {1}의 기물이 위치한다.")
            @CsvSource(value = {"A,ROOK", "B,KNIGHT", "C,BISHOP", "D,QUEEN", "E,KING", "F,BISHOP", "G,KNIGHT", "H,ROOK"})
            void it_returns_piece(final File file, final PieceFixture piece) {
                assertThat(pieces.get(Position.of(rank, file))).isInstanceOf(piece.getPieceClass());
            }
        }

        @Nested
        @DisplayName("Rank가 2이고")
        class rank_is_two {
            Rank rank = Rank.TWO;

            @ParameterizedTest(name = "File이 {0}이면 {1}의 기물이 위치한다.")
            @CsvSource(value = {"A", "B", "C", "D", "E", "F", "G", "H"})
            void it_returns_piece(final File file) {
                assertThat(pieces.get(Position.of(rank, file))).isInstanceOf(Pawn.class);
            }
        }

        @Nested
        @DisplayName("Rank가 3이고")
        class rank_is_three {
            Rank rank = Rank.THREE;

            @ParameterizedTest(name = "File이 {0}이면 {1}의 기물이 위치한다.")
            @CsvSource(value = {"A", "B", "C", "D", "E", "F", "G", "H"})
            void it_returns_piece(final File file) {
                assertThat(pieces.get(Position.of(rank, file))).isInstanceOf(EmptyPiece.class);
            }
        }

        @Nested
        @DisplayName("Rank가 4이고")
        class rank_is_four {
            Rank rank = Rank.FOUR;

            @ParameterizedTest(name = "File이 {0}이면 {1}의 기물이 위치한다.")
            @CsvSource(value = {"A", "B", "C", "D", "E", "F", "G", "H"})
            void it_returns_piece(final File file) {
                assertThat(pieces.get(Position.of(rank, file))).isInstanceOf(EmptyPiece.class);
            }
        }

        @Nested
        @DisplayName("Rank가 5이고")
        class rank_is_five {
            Rank rank = Rank.FIVE;

            @ParameterizedTest(name = "File이 {0}이면 {1}의 기물이 위치한다.")
            @CsvSource(value = {"A", "B", "C", "D", "E", "F", "G", "H"})
            void it_returns_piece(final File file) {
                assertThat(pieces.get(Position.of(rank, file))).isInstanceOf(EmptyPiece.class);
            }
        }

        @Nested
        @DisplayName("Rank가 6이고")
        class rank_is_six {
            Rank rank = Rank.SIX;

            @ParameterizedTest(name = "File이 {0}이면 {1}의 기물이 위치한다.")
            @CsvSource(value = {"A", "B", "C", "D", "E", "F", "G", "H"})
            void it_returns_piece(final File file) {
                assertThat(pieces.get(Position.of(rank, file))).isInstanceOf(EmptyPiece.class);
            }
        }

        @Nested
        @DisplayName("Rank가 7이고")
        class rank_is_seven {
            Rank rank = Rank.SEVEN;

            @ParameterizedTest(name = "File이 {0}이면 {1}의 기물이 위치한다.")
            @CsvSource(value = {"A", "B", "C", "D", "E", "F", "G", "H"})
            void it_returns_piece(final File file) {
                assertThat(pieces.get(Position.of(rank, file))).isInstanceOf(Pawn.class);
            }
        }

        @Nested
        @DisplayName("Rank가 8이고")
        class rank_is_eight {
            Rank rank = Rank.EIGHT;

            @ParameterizedTest(name = "File이 {0}이면 {1}의 기물이 위치한다.")
            @CsvSource(value = {"A,ROOK", "B,KNIGHT", "C,BISHOP", "D,QUEEN", "E,KING", "F,BISHOP", "G,KNIGHT", "H,ROOK"})
            void it_returns_piece(final File file, final PieceFixture piece) {
                assertThat(pieces.get(Position.of(rank, file))).isInstanceOf(piece.getPieceClass());
            }
        }
    }

    @Nested
    @DisplayName("move 메서드는")
    class move {

        Map<Position, Piece> pieces;

        @BeforeEach
        void empty_chessboard() {
            pieces = new HashMap<>();
            for (Rank rank : Rank.values()) {
                for (File file : File.values()) {
                    pieces.put(Position.of(rank, file), EmptyPiece.getInstance());
                }
            }
        }

        @Nested
        @DisplayName("폰의 경우에")
        class given_pawn {

            @DisplayName("앞의 두 칸에 기물이 존재하지 않는다면 true를 반환한다")
            @Test
            void it_returns_true1() {
                Position from = Position.of(Rank.SEVEN, File.A);
                Position to = Position.of(Rank.FIVE, File.A);
                pieces.put(from, Pawn.getPawnsOf(Side.BLACK)
                                     .get(0));
                ChessBoard chessBoard = new ChessBoard(pieces);

                assertThat(chessBoard.moveWithCapture(from, to)).isEqualTo(EmptyPiece.getInstance());
            }

            @DisplayName("앞의 칸에 기물이 존재한다면 false를 반환한다")
            @Test
            void it_returns_false1() {
                Position from = Position.of(Rank.SEVEN, File.A);
                Position middle = Position.of(Rank.SIX, File.A);
                Position to = Position.of(Rank.FIVE, File.A);
                pieces.put(from, Pawn.getPawnsOf(Side.BLACK)
                                     .get(0));
                pieces.put(middle, Queen.getQueenOf(Side.WHITE));
                ChessBoard chessBoard = new ChessBoard(pieces);

                assertThatThrownBy(() -> chessBoard.moveWithCapture(from, to))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("이동 불가능한 경로입니다");
            }

            @DisplayName("대각선 앞 칸에 적의 기물이 존재한다면 true를 반환한다")
            @Test
            void it_returns_true2() {
                Position from = Position.of(Rank.SEVEN, File.B);
                Position to = Position.of(Rank.SIX, File.A);
                final Queen expected = Queen.getQueenOf(Side.WHITE);
                pieces.put(from, Pawn.getPawnsOf(Side.BLACK)
                                     .get(0));
                pieces.put(to, expected);
                ChessBoard chessBoard = new ChessBoard(pieces);

                assertThat(chessBoard.moveWithCapture(from, to)).isEqualTo(expected);
            }

            @DisplayName("대각선 앞 칸에 적의 기물이 존재하지 않는다면 false를 반환한다")
            @Test
            void it_returns_false2() {
                Position from = Position.of(Rank.SEVEN, File.B);
                Position to = Position.of(Rank.SIX, File.A);
                pieces.put(from, Pawn.getPawnsOf(Side.BLACK)
                                     .get(0));
                pieces.put(to, Queen.getQueenOf(Side.BLACK));
                ChessBoard chessBoard = new ChessBoard(pieces);

                assertThatThrownBy(() -> chessBoard.moveWithCapture(from, to))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("이동 불가능한 경로입니다");
                assertThatThrownBy(() -> chessBoard.moveWithCapture(from, Position.of(Rank.SIX, File.C)))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("이동 불가능한 경로입니다");
            }
        }

        @Nested
        @DisplayName("비숍의 경우에")
        class given_bishop {

            @DisplayName("대각선 내에 기물이 존재하지 않는다면 true를 반환한다")
            @Test
            void it_returns_true() {
                Position from = Position.of(Rank.THREE, File.E);
                Position to = Position.of(Rank.SEVEN, File.A);
                pieces.put(from, Bishop.getBishopsOf(Side.BLACK)
                                       .get(0));
                ChessBoard chessBoard = new ChessBoard(pieces);

                assertThat(chessBoard.moveWithCapture(from, to)).isEqualTo(EmptyPiece.getInstance());
            }

            @DisplayName("대각선 내에 기물이 존재한다면 false를 반환한다")
            @Test
            void it_returns_false1() {
                Position from = Position.of(Rank.THREE, File.E);
                Position middle = Position.of(Rank.FIVE, File.C);
                Position to = Position.of(Rank.SEVEN, File.A);
                pieces.put(from, Bishop.getBishopsOf(Side.BLACK)
                                       .get(0));
                pieces.put(middle, Queen.getQueenOf(Side.WHITE));
                ChessBoard chessBoard = new ChessBoard(pieces);

                assertThatThrownBy(() -> chessBoard.moveWithCapture(from, to))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("이동 불가능한 경로입니다");
            }

            @DisplayName("도착 위치에 적의 기물이 존재한거나 비어있다면 true를 반환한다")
            @Test
            void it_returns_true2() {
                Position from = Position.of(Rank.SEVEN, File.B);
                Position to = Position.of(Rank.SIX, File.A);
                pieces.put(from, Bishop.getBishopsOf(Side.BLACK)
                                       .get(0));
                final Queen expected = Queen.getQueenOf(Side.WHITE);
                pieces.put(to, expected);
                ChessBoard chessBoard = new ChessBoard(pieces);

                assertThat(chessBoard.moveWithCapture(from, to)).isEqualTo(expected);
                chessBoard.moveWithCapture(to, from);
                assertThat(chessBoard.moveWithCapture(from, Position.of(Rank.FIVE, File.D))).isEqualTo(EmptyPiece.getInstance());
            }

            @DisplayName("도착 위치에 아군의 기물이 존재한다면 false를 반환한다")
            @Test
            void it_returns_false2() {
                Position from = Position.of(Rank.SEVEN, File.B);
                Position to = Position.of(Rank.SIX, File.A);
                pieces.put(from, Bishop.getBishopsOf(Side.BLACK)
                                       .get(0));
                pieces.put(to, Queen.getQueenOf(Side.BLACK));
                ChessBoard chessBoard = new ChessBoard(pieces);

                assertThatThrownBy(() -> chessBoard.moveWithCapture(from, to))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("이동 불가능한 경로입니다");
            }
        }

        @Nested
        @DisplayName("룩의 경우에")
        class given_rook {

            @DisplayName("직선 내에 기물이 존재하지 않는다면 true를 반환한다")
            @Test
            void it_returns_true() {
                Position from = Position.of(Rank.THREE, File.E);
                Position to = Position.of(Rank.THREE, File.A);
                pieces.put(from, Rook.getRooksOf(Side.BLACK)
                                     .get(0));
                ChessBoard chessBoard = new ChessBoard(pieces);

                assertThat(chessBoard.moveWithCapture(from, to)).isEqualTo(EmptyPiece.getInstance());
            }

            @DisplayName("작선 내에 기물이 존재한다면 false를 반환한다")
            @Test
            void it_returns_false1() {
                Position from = Position.of(Rank.THREE, File.E);
                Position middle = Position.of(Rank.THREE, File.B);
                Position to = Position.of(Rank.THREE, File.A);
                pieces.put(from, Rook.getRooksOf(Side.BLACK)
                                     .get(0));
                pieces.put(middle, Queen.getQueenOf(Side.WHITE));
                ChessBoard chessBoard = new ChessBoard(pieces);

                assertThatThrownBy(() -> chessBoard.moveWithCapture(from, to))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("이동 불가능한 경로입니다");
            }

            @DisplayName("도착 위치에 적의 기물이 존재한거나 비어 있다면 true를 반환한다")
            @Test
            void it_returns_true2() {
                Position from = Position.of(Rank.SEVEN, File.B);
                Position to = Position.of(Rank.SIX, File.B);
                pieces.put(from, Rook.getRooksOf(Side.BLACK)
                                     .get(0));
                final Queen expected = Queen.getQueenOf(Side.WHITE);
                pieces.put(to, expected);
                ChessBoard chessBoard = new ChessBoard(pieces);

                assertThat(chessBoard.moveWithCapture(from, to)).isEqualTo(expected);
                chessBoard.moveWithCapture(to, from);
                assertThat(chessBoard.moveWithCapture(from, Position.of(Rank.SEVEN, File.D))).isEqualTo(EmptyPiece.getInstance());
            }

            @DisplayName("도착 위치에 아군의 기물이 존재한다면 false를 반환한다")
            @Test
            void it_returns_false2() {
                Position from = Position.of(Rank.SEVEN, File.B);
                Position to = Position.of(Rank.FIVE, File.B);
                pieces.put(from, Rook.getRooksOf(Side.BLACK)
                                     .get(0));
                pieces.put(to, Queen.getQueenOf(Side.BLACK));
                ChessBoard chessBoard = new ChessBoard(pieces);

                assertThatThrownBy(() -> chessBoard.moveWithCapture(from, to))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("이동 불가능한 경로입니다");
            }
        }

        @Nested
        @DisplayName("나이트의 경우에")
        class given_knight {

            @DisplayName("도착 위치에 적의 기물이 존재하거나 비어있다면 true를 반환한다")
            @Test
            void it_returns_true() {
                Position from = Position.of(Rank.THREE, File.E);
                Position to = Position.of(Rank.TWO, File.G);
                final Queen expected = Queen.getQueenOf(Side.WHITE);
                pieces.put(from, Knight.getKnightsOf(Side.BLACK)
                                       .get(0));
                pieces.put(to, expected);
                ChessBoard chessBoard = new ChessBoard(pieces);

                assertThat(chessBoard.moveWithCapture(from, to)).isEqualTo(expected);
                chessBoard.moveWithCapture(to, from);
                assertThat(chessBoard.moveWithCapture(from, Position.of(Rank.FIVE, File.D))).isEqualTo(EmptyPiece.getInstance());
            }

            @DisplayName("도착 위치에 아군의 기물이 존재한다면 false를 반환한다")
            @Test
            void it_returns_false() {
                Position from = Position.of(Rank.SEVEN, File.B);
                Position to = Position.of(Rank.FIVE, File.C);
                pieces.put(from, Knight.getKnightsOf(Side.BLACK)
                                       .get(0));
                pieces.put(to, Queen.getQueenOf(Side.BLACK));
                ChessBoard chessBoard = new ChessBoard(pieces);

                assertThatThrownBy(() -> chessBoard.moveWithCapture(from, to))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("이동 불가능한 경로입니다");
            }
        }

        @Nested
        @DisplayName("퀸의 경우에")
        class given_queen {

            @DisplayName("이동 경로상에 기물이 존재하지 않는다면 true를 반환한다")
            @Test
            void it_returns_true() {
                Position from = Position.of(Rank.THREE, File.E);
                Position to1 = Position.of(Rank.THREE, File.A);
                Position to2 = Position.of(Rank.SEVEN, File.A);

                pieces.put(from, Queen.getQueenOf(Side.BLACK));

                ChessBoard chessBoard = new ChessBoard(pieces);

                assertThat(chessBoard.moveWithCapture(from, to1)).isEqualTo(EmptyPiece.getInstance());
                chessBoard.moveWithCapture(to1, from);
                assertThat(chessBoard.moveWithCapture(from, to2)).isEqualTo(EmptyPiece.getInstance());
            }

            @DisplayName("이동 경로 상에 기물이 존재한다면 false를 반환한다")
            @Test
            void it_returns_false1() {
                Position from = Position.of(Rank.THREE, File.E);
                Position middle1 = Position.of(Rank.THREE, File.B);
                Position middle2 = Position.of(Rank.FIVE, File.C);
                Position to1 = Position.of(Rank.THREE, File.A);
                Position to2 = Position.of(Rank.SEVEN, File.A);
                pieces.put(from, Queen.getQueenOf(Side.BLACK));
                pieces.put(middle1, Queen.getQueenOf(Side.WHITE));
                pieces.put(middle2, Queen.getQueenOf(Side.WHITE));
                ChessBoard chessBoard = new ChessBoard(pieces);

                assertThatThrownBy(() -> chessBoard.moveWithCapture(from, to1))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("이동 불가능한 경로입니다");
                assertThatThrownBy(() -> chessBoard.moveWithCapture(from, to2))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("이동 불가능한 경로입니다");
            }

            @DisplayName("도착 위치에 적의 기물이 존재한거나 비어 있다면 true를 반환한다")
            @Test
            void it_returns_true2() {
                Position from = Position.of(Rank.SEVEN, File.B);
                Position to1 = Position.of(Rank.THREE, File.F);
                Position to2 = Position.of(Rank.SEVEN, File.A);
                final Queen expected = Queen.getQueenOf(Side.WHITE);
                pieces.put(from, Queen.getQueenOf(Side.BLACK));
                pieces.put(to1, expected);
                ChessBoard chessBoard = new ChessBoard(pieces);

                assertThat(chessBoard.moveWithCapture(from, to1)).isEqualTo(expected);
                chessBoard.moveWithCapture(to1, from);
                assertThat(chessBoard.moveWithCapture(from, to2)).isEqualTo(EmptyPiece.getInstance());
            }

            @DisplayName("도착 위치에 아군의 기물이 존재한다면 false를 반환한다")
            @Test
            void it_returns_false2() {
                Position from = Position.of(Rank.SEVEN, File.B);
                Position to1 = Position.of(Rank.THREE, File.A);
                Position to2 = Position.of(Rank.SEVEN, File.A);
                pieces.put(from, Queen.getQueenOf(Side.BLACK));
                pieces.put(to1, King.getKingOf(Side.BLACK));
                pieces.put(to2, King.getKingOf(Side.BLACK));
                ChessBoard chessBoard = new ChessBoard(pieces);

                assertThatThrownBy(() -> chessBoard.moveWithCapture(from, to1))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("이동 불가능한 경로입니다");
                assertThatThrownBy(() -> chessBoard.moveWithCapture(from, to2))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("이동 불가능한 경로입니다");
            }
        }

        @Nested
        @DisplayName("킹의 경우에")
        class given_king {

            @DisplayName("도착 위치에 적의 기물이 존재한거나 비어 있다면 true를 반환한다")
            @Test
            void it_returns_true() {
                Position from = Position.of(Rank.THREE, File.E);
                Position to1 = Position.of(Rank.THREE, File.F);
                Position to2 = Position.of(Rank.FOUR, File.D);
                final Queen expected = Queen.getQueenOf(Side.WHITE);
                pieces.put(from, King.getKingOf(Side.BLACK));
                pieces.put(to1, expected);
                ChessBoard chessBoard = new ChessBoard(pieces);

                assertThat(chessBoard.moveWithCapture(from, to1)).isEqualTo(expected);
                chessBoard.moveWithCapture(to1, from);
                assertThat(chessBoard.moveWithCapture(from, to2)).isEqualTo(EmptyPiece.getInstance());
            }

            @DisplayName("도착 위치에 아군의 기물이 존재한다면 false를 반환한다")
            @Test
            void it_returns_false() {
                Position from = Position.of(Rank.SEVEN, File.B);
                Position to = Position.of(Rank.SIX, File.A);
                pieces.put(from, King.getKingOf(Side.BLACK));
                pieces.put(to, Queen.getQueenOf(Side.BLACK));
                ChessBoard chessBoard = new ChessBoard(pieces);

                assertThatThrownBy(() -> chessBoard.moveWithCapture(from, to))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("이동 불가능한 경로입니다");
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
                    .allMatch(piece -> piece.isSideOf(Side.BLACK))
                    .hasSize(16);
        }

        @Test
        @DisplayName("White 진영을 입력하면 해당 진영의 모든 기물을 반환한다")
        void it_returns_white_pieces() {
            assertThat(chessBoard.getPieces(Side.WHITE)
                                 .values())
                    .allMatch(piece -> piece.isSideOf(Side.WHITE))
                    .hasSize(16);
        }
    }

    @Nested
    @DisplayName("isKingDead 메서드는")
    class isKingDead {
        @Test
        @DisplayName("King 개수가 두 개보다 작지 않으면 false를 반환한다")
        void test1() {
            final ChessBoard chessBoard = new ChessBoard(Map.of(TWO_B, BLACK_KING, THREE_B, WHITE_KING));

            assertThat(chessBoard.isKingDead()).isFalse();
        }

        @Test
        @DisplayName("King 개수가 두 개보다 작으면 true를 반환한다")
        void test2() {
            final ChessBoard chessBoard = new ChessBoard(Map.of(TWO_B, BLACK_KING));

            assertThat(chessBoard.isKingDead()).isTrue();
        }
    }
}
