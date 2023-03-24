package chess.chessboard;

import chess.piece.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

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

                assertThat(chessBoard.movePiece(source, destination)).isTrue();
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

                assertThat(chessBoard.movePiece(source, destination)).isFalse();
            }

            @DisplayName("대각선 앞 칸에 적의 기물이 존재한다면 true를 반환한다")
            @Test
            void it_returns_true2() {
                Square source = Square.of(Rank.SEVEN, File.B);
                Square destination = Square.of(Rank.SIX, File.A);
                pieces.put(source, Pawn.getPawnsOf(Side.BLACK)
                                       .get(0));
                pieces.put(destination, Queen.getQueenOf(Side.WHITE));
                ChessBoard chessBoard = new ChessBoard(pieces);

                assertThat(chessBoard.movePiece(source, destination)).isTrue();
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

                assertThat(chessBoard.movePiece(source, destination)).isFalse();
                assertThat(chessBoard.movePiece(source, Square.of(Rank.SIX, File.C))).isFalse();
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

                assertThat(chessBoard.movePiece(source, destination)).isTrue();
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

                assertThat(chessBoard.movePiece(source, destination)).isFalse();
            }

            @DisplayName("도착 위치에 적의 기물이 존재한거나 비어있다면 true를 반환한다")
            @Test
            void it_returns_true2() {
                Square source = Square.of(Rank.SEVEN, File.B);
                Square destination = Square.of(Rank.SIX, File.A);
                pieces.put(source, Bishop.getBishopsOf(Side.BLACK)
                                         .get(0));
                pieces.put(destination, Queen.getQueenOf(Side.WHITE));
                ChessBoard chessBoard = new ChessBoard(pieces);

                assertThat(chessBoard.movePiece(source, destination)).isTrue();
                chessBoard.movePiece(destination, source);
                assertThat(chessBoard.movePiece(source, Square.of(Rank.FIVE, File.D))).isTrue();
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

                assertThat(chessBoard.movePiece(source, destination)).isFalse();
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

                assertThat(chessBoard.movePiece(source, destination)).isTrue();
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

                assertThat(chessBoard.movePiece(source, destination)).isFalse();
            }

            @DisplayName("도착 위치에 적의 기물이 존재한거나 비어 있다면 true를 반환한다")
            @Test
            void it_returns_true2() {
                Square source = Square.of(Rank.SEVEN, File.B);
                Square destination = Square.of(Rank.SIX, File.B);
                pieces.put(source, Rook.getRooksOf(Side.BLACK)
                                       .get(0));
                pieces.put(destination, Queen.getQueenOf(Side.WHITE));
                ChessBoard chessBoard = new ChessBoard(pieces);

                assertThat(chessBoard.movePiece(source, destination)).isTrue();
                chessBoard.movePiece(destination, source);
                assertThat(chessBoard.movePiece(source, Square.of(Rank.SEVEN, File.D))).isTrue();
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

                assertThat(chessBoard.movePiece(source, destination)).isFalse();
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
                pieces.put(source, Knight.getKnightsOf(Side.BLACK)
                                         .get(0));
                ChessBoard chessBoard = new ChessBoard(pieces);

                assertThat(chessBoard.movePiece(source, destination)).isTrue();
                chessBoard.movePiece(destination, source);
                assertThat(chessBoard.movePiece(source, Square.of(Rank.FIVE, File.D))).isTrue();
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

                assertThat(chessBoard.movePiece(source, destination)).isFalse();
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

                assertThat(chessBoard.movePiece(source, destination1)).isTrue();
                chessBoard.movePiece(destination1, source);
                assertThat(chessBoard.movePiece(source, destination2)).isTrue();
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

                assertThat(chessBoard.movePiece(source, destination1)).isFalse();
                assertThat(chessBoard.movePiece(source, destination2)).isFalse();
            }

            @DisplayName("도착 위치에 적의 기물이 존재한거나 비어 있다면 true를 반환한다")
            @Test
            void it_returns_true2() {
                Square source = Square.of(Rank.SEVEN, File.B);
                Square destination1 = Square.of(Rank.THREE, File.F);
                Square destination2 = Square.of(Rank.SEVEN, File.A);
                pieces.put(source, Queen.getQueenOf(Side.BLACK));
                pieces.put(destination1, Queen.getQueenOf(Side.WHITE));
                ChessBoard chessBoard = new ChessBoard(pieces);

                assertThat(chessBoard.movePiece(source, destination1)).isTrue();
                chessBoard.movePiece(destination1, source);
                assertThat(chessBoard.movePiece(source, destination2)).isTrue();
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

                assertThat(chessBoard.movePiece(source, destination1)).isFalse();
                assertThat(chessBoard.movePiece(source, destination2)).isFalse();
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
                pieces.put(source, King.getKingOf(Side.BLACK));
                pieces.put(destination1, Queen.getQueenOf(Side.WHITE));
                ChessBoard chessBoard = new ChessBoard(pieces);

                assertThat(chessBoard.movePiece(source, destination1)).isTrue();
                chessBoard.movePiece(destination1, source);
                assertThat(chessBoard.movePiece(source, destination2)).isTrue();
            }

            @DisplayName("도착 위치에 아군의 기물이 존재한다면 false를 반환한다")
            @Test
            void it_returns_false() {
                Square source = Square.of(Rank.SEVEN, File.B);
                Square destination = Square.of(Rank.SIX, File.A);
                pieces.put(source, King.getKingOf(Side.BLACK));
                pieces.put(destination, Queen.getQueenOf(Side.BLACK));
                ChessBoard chessBoard = new ChessBoard(pieces);

                assertThat(chessBoard.movePiece(source, destination)).isFalse();
            }
        }


        @Nested
        @DisplayName("이동 순서가 있는데")
        class sequence {
            Square source1 = Square.of(Rank.FIVE, File.B);
            Square source2 = Square.of(Rank.FIVE, File.C);
            Square destination1 = Square.of(Rank.FOUR, File.B);
            Square destination2 = Square.of(Rank.SEVEN, File.C);

            @Test
            @DisplayName("처음에 백이 먼저 공격해야 true를 반환한다")
            void it_returns_true() {
                List<Rook> whiteRooks = Rook.getRooksOf(Side.WHITE);
                List<Rook> blackRooks = Rook.getRooksOf(Side.BLACK);

                pieces.put(source1, whiteRooks.get(0));
                pieces.put(source2, blackRooks.get(0));

                final ChessBoard chessBoard = new ChessBoard(pieces);
                assertThat(chessBoard.executeTurnMove(source1, destination1)).isTrue();
            }

            @Test
            @DisplayName("처음에 흑이 먼저 공격하면 false를 반환한다.")
            void it_returns_false() {
                List<Rook> whiteRooks = Rook.getRooksOf(Side.WHITE);
                List<Rook> blackRooks = Rook.getRooksOf(Side.BLACK);

                pieces.put(source1, whiteRooks.get(0));
                pieces.put(source2, blackRooks.get(0));

                final ChessBoard chessBoard = new ChessBoard(pieces);
                assertThat(chessBoard.executeTurnMove(source2, destination2)).isFalse();
            }

            @Test
            @DisplayName("백이 공격하고 나서 흑이 공격해야 true를 반환한다")
            void it_returns_true1() {
                List<Rook> whiteRooks = Rook.getRooksOf(Side.WHITE);
                List<Rook> blackRooks = Rook.getRooksOf(Side.BLACK);

                pieces.put(source1, whiteRooks.get(0));
                pieces.put(source2, blackRooks.get(0));

                final ChessBoard chessBoard = new ChessBoard(pieces);
                chessBoard.executeTurnMove(source1, destination1);

                assertThat(chessBoard.executeTurnMove(source2, destination2)).isTrue();
            }

            @Test
            @DisplayName("흑이 공격하고 나서 백이 공격해야 true를 반환한다")
            void it_returns_true2() {
                List<Rook> whiteRooks = Rook.getRooksOf(Side.WHITE);
                List<Rook> blackRooks = Rook.getRooksOf(Side.BLACK);

                pieces.put(source1, whiteRooks.get(0));
                pieces.put(source2, blackRooks.get(0));

                final ChessBoard chessBoard = new ChessBoard(pieces);
                chessBoard.executeTurnMove(source1, destination1);
                chessBoard.executeTurnMove(source2, destination2);

                assertThat(chessBoard.executeTurnMove(destination1, source1)).isTrue();
            }

            @Test
            @DisplayName("백이 공격하고 나서 백이 공격하면 false를 반환한다")
            void it_returns_false1() {
                List<Rook> whiteRooks = Rook.getRooksOf(Side.WHITE);
                List<Rook> blackRooks = Rook.getRooksOf(Side.BLACK);

                pieces.put(source1, whiteRooks.get(0));
                pieces.put(source2, blackRooks.get(0));

                final ChessBoard chessBoard = new ChessBoard(pieces);
                chessBoard.executeTurnMove(source1, destination1);

                assertThat(chessBoard.executeTurnMove(destination1, source1)).isFalse();
            }
        }
    }
}
