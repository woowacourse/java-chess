package domain.board;

import domain.piece.*;
import domain.position.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static domain.board.ChessAlignmentMock.testStrategy;
import static domain.position.PositionFixture.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class BoardTest {
    @DisplayName("Board를 초기화해 생성할 수 있다.")
    @Test
    void createTest() {
        //given
        Board board = Board.create(new InitialChessAlignment());
        final List<Position> rooksPosition = List.of(A1, A8, H1, H8);
        final List<Position> knightsPosition = List.of(B1, B8, G1, G8);
        final List<Position> bishopsPosition = List.of(C1, C8, F1, F8);
        final List<Position> pawnsPosition = List.of(A2, B2, C2, D2, E2, F2, G2, H2,
                A7, B7, C7, D7, E7, F7, G7, H7);
        final List<Position> kingsPosition = List.of(E1, E8);
        final List<Position> queensPosition = List.of(D1, D8);

        //when
        final Map<Position, Piece> pieces = board.getPieces();

        //then
        assertAll(
                () -> assertThat(rooksPosition).map(pieces::get).allSatisfy(piece -> assertThat(piece).isInstanceOf(Rook.class)),
                () -> assertThat(knightsPosition).map(pieces::get).allSatisfy(piece -> assertThat(piece).isInstanceOf(Knight.class)),
                () -> assertThat(bishopsPosition).map(pieces::get).allSatisfy(piece -> assertThat(piece).isInstanceOf(Bishop.class)),
                () -> assertThat(pawnsPosition).map(pieces::get).allSatisfy(piece -> assertThat(piece).isInstanceOf(Pawn.class)),
                () -> assertThat(kingsPosition).map(pieces::get).allSatisfy(piece -> assertThat(piece).isInstanceOf(King.class)),
                () -> assertThat(queensPosition).map(pieces::get).allSatisfy(piece -> assertThat(piece).isInstanceOf(Queen.class)));
    }

    @Nested
    class move {
        @Nested
        @DisplayName("킹은 ")
        class moveKing {
            @DisplayName("움직일 수 있다.")
            @Test
            void kingMove() {
                //given
                final King king = new King(Team.WHITE);
                final Board board = Board.create(testStrategy(Map.of(D4, king)));

                //when
                board.move(D4, D3);

                //then
                assertAll(
                        () -> assertThat(board.getPieces().containsKey(D4)).isFalse(),
                        () -> assertThat(board.getPieces().get(D3)).isEqualTo(king));
            }

            @DisplayName("적이 있을 때 먹을 수 있다.")
            @Test
            void kingEat() {
                //given
                final King king = new King(Team.WHITE);
                final King feed = new King(Team.BLACK);
                final Board board = Board.create(testStrategy(Map.of(D4, king,
                        E5, feed)));

                //when
                board.move(D4, E5);

                //then
                assertAll(
                        () -> assertThat(board.getPieces().containsKey(D4)).isFalse(),
                        () -> assertThat(board.getPieces().get(E5)).isEqualTo(king));
            }

            @DisplayName("자신의 가동 범위 밖으로 움직일 수 없다.")
            @Test
            void kingNotMove() {
                //given
                final King king = new King(Team.WHITE);
                final Board board = Board.create(testStrategy(Map.of(D4, king)));

                //when

                //then
                assertThatThrownBy(() -> board.move(D4, A3))
                        .isInstanceOf(IllegalArgumentException.class);
            }
        }

        @Nested
        @DisplayName("퀸은 ")
        class moveQueen {
            @DisplayName("움직일 수 있다.")
            @Test
            void queenMove() {
                //given
                final Queen queen = new Queen(Team.WHITE);
                final Board board = Board.create(testStrategy(Map.of(D4, queen)));

                //when
                board.move(D4, D8);

                //then
                assertAll(
                        () -> assertThat(board.getPieces().containsKey(D4)).isFalse(),
                        () -> assertThat(board.getPieces().get(D8)).isEqualTo(queen));
            }

            @DisplayName("적이 있을 때 먹을 수 있다.")
            @Test
            void queenEat() {
                //given
                final Queen queen = new Queen(Team.WHITE);
                final King feed = new King(Team.BLACK);
                final Board board = Board.create(testStrategy(Map.of(D4, queen,
                        H8, feed)));

                //when
                board.move(D4, H8);

                //then
                assertAll(
                        () -> assertThat(board.getPieces().containsKey(D4)).isFalse(),
                        () -> assertThat(board.getPieces().get(H8)).isEqualTo(queen));
            }

            @DisplayName("자신의 가동 범위 밖으로 움직일 수 없다.")
            @Test
            void queenNotMove() {
                //given
                final Queen queen = new Queen(Team.WHITE);
                final Board board = Board.create(testStrategy(Map.of(D4, queen)));

                //when

                //then
                assertThatThrownBy(() -> board.move(D4, E2))
                        .isInstanceOf(IllegalArgumentException.class);
            }

            @DisplayName("이동 경로 사이에 다른 기물이 있으면 움직일 수 없다.")
            @Test
            void queenNotMove2() {
                //given
                final Queen queen = new Queen(Team.WHITE);
                final King another = new King(Team.WHITE);
                final Board board = Board.create(testStrategy(Map.of(D4, queen,
                        D6, another)));

                //when

                //then
                assertThatThrownBy(() -> board.move(D4, D8))
                        .isInstanceOf(IllegalArgumentException.class);
            }
        }

        @Nested
        @DisplayName("나이트는 ")
        class moveKnight {
            @DisplayName("움직일 수 있다.")
            @Test
            void knightMove() {
                //given
                final Knight knight = new Knight(Team.WHITE);
                final Board board = Board.create(testStrategy(Map.of(D4, knight)));

                //when
                board.move(D4, F3);

                //then
                assertAll(
                        () -> assertThat(board.getPieces().containsKey(D4)).isFalse(),
                        () -> assertThat(board.getPieces().get(F3)).isEqualTo(knight));
            }

            @DisplayName("적이 있을 때 먹을 수 있다.")
            @Test
            void knightEat() {
                //given
                final Knight knight = new Knight(Team.WHITE);
                final King feed = new King(Team.BLACK);
                final Board board = Board.create(testStrategy(Map.of(D4, knight,
                        B3, feed)));

                //when
                board.move(D4, B3);

                //then
                assertAll(
                        () -> assertThat(board.getPieces().containsKey(D4)).isFalse(),
                        () -> assertThat(board.getPieces().get(B3)).isEqualTo(knight));
            }

            @DisplayName("사이에 다른 기물이 있어도 움직일 수 있다.")
            @Test
            void knightMove2() {
                //given
                final Knight knight = new Knight(Team.WHITE);
                final King another1 = new King(Team.WHITE);
                final King another2 = new King(Team.WHITE);
                final Board board = Board.create(testStrategy(Map.of(D4, knight,
                        D3, another1,
                        E3, another2)));

                //when
                board.move(D4, F3);

                //then
                assertAll(
                        () -> assertThat(board.getPieces().containsKey(D4)).isFalse(),
                        () -> assertThat(board.getPieces().get(F3)).isEqualTo(knight));
            }

            @DisplayName("자신의 가동 범위 밖으로 움직일 수 없다.")
            @Test
            void knightNotMove() {
                //given
                final Knight knight = new Knight(Team.WHITE);
                final Board board = Board.create(testStrategy(Map.of(D4, knight)));

                //when

                //then
                assertThatThrownBy(() -> board.move(D4, D2))
                        .isInstanceOf(IllegalArgumentException.class);
            }
        }

        @Nested
        @DisplayName("폰은")
        class movePawn {
            @DisplayName("처음에 두 칸 움직일 수 있다.")
            @Test
            void pawnTwoStepMove() {
                //given
                final Pawn pawn = new Pawn(Team.WHITE);
                final Board board = Board.create(testStrategy(Map.of(D2, pawn)));

                //when
                board.move(D2, D4);

                //then
                assertAll(
                        () -> assertThat(board.getPieces().containsKey(D2)).isFalse(),
                        () -> assertThat(board.getPieces().get(D4)).isEqualTo(pawn));
            }

            @DisplayName("한 칸 움직일 수 있다.")
            @Test
            void pawnOneStepMove() {
                //given
                final Pawn pawn = new Pawn(Team.WHITE);
                final Board board = Board.create(testStrategy(Map.of(D6, pawn)));

                //when
                board.move(D6, D7);

                //then
                assertAll(
                        () -> assertThat(board.getPieces().containsKey(D6)).isFalse(),
                        () -> assertThat(board.getPieces().get(D7)).isEqualTo(pawn));
            }

            @DisplayName("자신의 가동 범위 밖으로 움직일 수 없다.")
            @Test
            void pawnNotMove() {
                //given
                final Pawn pawn = new Pawn(Team.WHITE);
                final Board board = Board.create(testStrategy(Map.of(D4, pawn)));

                //when

                //then
                assertThatThrownBy(() -> board.move(D4, A2))
                        .isInstanceOf(IllegalArgumentException.class);
            }

            @DisplayName("앞에 적이 있으면 앞으로 갈 수 없다.")
            @Test
            void pawnNotMove2() {
                //given
                final Pawn pawn = new Pawn(Team.WHITE);
                final King king = new King(Team.BLACK);
                final Board board = Board.create(testStrategy(Map.of(D4, pawn,
                        D3, king)));

                //when

                //then
                assertThatThrownBy(() -> board.move(D4, D3))
                        .isInstanceOf(IllegalArgumentException.class);
            }

            @DisplayName("앞에 적이 있으면 처음에 앞으로 두 칸 갈 수 없다.")
            @Test
            void pawnNotMove3() {
                //given
                final Pawn pawn = new Pawn(Team.WHITE);
                final King king = new King(Team.BLACK);
                final Board board = Board.create(testStrategy(Map.of(D7, pawn,
                        D6, king)));

                //when

                //then
                assertThatThrownBy(() -> board.move(D7, D5))
                        .isInstanceOf(IllegalArgumentException.class);
            }

            @DisplayName("대각선에 적이 없으면 대각선으로 전진할 수 없다.")
            @Test
            void pawnNotMove4() {
                //given
                final Pawn pawn = new Pawn(Team.WHITE);
                final Board board = Board.create(testStrategy(Map.of(D7, pawn)));

                //when

                //then
                assertThatThrownBy(() -> board.move(D7, E8))
                        .isInstanceOf(IllegalArgumentException.class);
            }

            @DisplayName("대각선에 적이 있으면 대각선으로 적을 먹을 수 있다.")
            @Test
            void pawnMove1() {
                //given
                final Pawn pawn = new Pawn(Team.WHITE);
                final King king = new King(Team.BLACK);
                final Board board = Board.create(testStrategy(Map.of(D7, pawn,
                        E6, king)));

                //when
                board.move(D7, E6);

                //then
                assertAll(
                        () -> assertThat(board.getPieces().containsKey(D7)).isFalse(),
                        () -> assertThat(board.getPieces().get(E6)).isEqualTo(pawn));
            }
        }

        @Nested
        @DisplayName("비숍은")
        class moveBishop {
            @DisplayName("움직일 수 있다.")
            @Test
            void bishopMove() {
                //given
                final Bishop bishop = new Bishop(Team.WHITE);
                final Board board = Board.create(testStrategy(Map.of(D4, bishop)));

                //when
                board.move(D4, H8);

                //then
                assertAll(
                        () -> assertThat(board.getPieces().containsKey(D4)).isFalse(),
                        () -> assertThat(board.getPieces().get(H8)).isEqualTo(bishop));
            }

            @DisplayName("자신의 가동 범위 밖으로 움직일 수 없다.")
            @Test
            void bishopNotMove() {
                //given
                final Bishop bishop = new Bishop(Team.WHITE);
                final Board board = Board.create(testStrategy(Map.of(D4, bishop)));

                //when

                //then
                assertThatThrownBy(() -> board.move(D4, H7))
                        .isInstanceOf(IllegalArgumentException.class);
            }

            @DisplayName("이동 경로 사이에 다른 기물이 있으면 움직일 수 없다.")
            @Test
            void bishopNotMove2() {
                //given
                final Bishop bishop = new Bishop(Team.WHITE);
                final King another = new King(Team.WHITE);
                final Board board = Board.create(testStrategy(Map.of(D4, bishop,
                        D6, another)));

                //when

                //then
                assertThatThrownBy(() -> board.move(D4, D8))
                        .isInstanceOf(IllegalArgumentException.class);
            }

            @DisplayName("적이 있을 때 먹을 수 있다.")
            @Test
            void bishopEat() {
                //given
                final Bishop bishop = new Bishop(Team.WHITE);
                final King feed = new King(Team.BLACK);
                final Board board = Board.create(testStrategy(Map.of(D4, bishop,
                        H8, feed)));

                //when
                board.move(D4, H8);

                //then
                assertAll(
                        () -> assertThat(board.getPieces().containsKey(D4)).isFalse(),
                        () -> assertThat(board.getPieces().get(H8)).isEqualTo(bishop));
            }
        }

        @Nested
        @DisplayName("룩은 ")
        class moveRook {
            @DisplayName("움직일 수 있다.")
            @Test
            void rookMove() {
                //given
                final Rook rook = new Rook(Team.WHITE);
                final Board board = Board.create(testStrategy(Map.of(D4, rook)));

                //when
                board.move(D4, D8);

                //then
                assertAll(
                        () -> assertThat(board.getPieces().containsKey(D4)).isFalse(),
                        () -> assertThat(board.getPieces().get(D8)).isEqualTo(rook));
            }

            @DisplayName("자신의 가동 범위 밖으로 움직일 수 없다.")
            @Test
            void rookNotMove() {
                //given
                final Rook rook = new Rook(Team.WHITE);
                final Board board = Board.create(testStrategy(Map.of(D4, rook)));

                //when

                //then
                assertThatThrownBy(() -> board.move(D4, H8))
                        .isInstanceOf(IllegalArgumentException.class);
            }

            @DisplayName("이동 경로 사이에 다른 기물이 있으면 움직일 수 없다.")
            @Test
            void rookNotMove2() {
                //given
                final Rook rook = new Rook(Team.WHITE);
                final King another = new King(Team.WHITE);
                final Board board = Board.create(testStrategy(Map.of(D4, rook,
                        D6, another)));

                //when

                //then
                assertThatThrownBy(() -> board.move(D4, D8))
                        .isInstanceOf(IllegalArgumentException.class);
            }

            @DisplayName("적이 있을 때 먹을 수 있다.")
            @Test
            void rookEat() {
                //given
                final Rook rook = new Rook(Team.WHITE);
                final King feed = new King(Team.BLACK);
                final Board board = Board.create(testStrategy(Map.of(D4, rook,
                        D8, feed)));

                //when
                board.move(D4, D8);

                //then
                assertAll(
                        () -> assertThat(board.getPieces().containsKey(D4)).isFalse(),
                        () -> assertThat(board.getPieces().get(D8)).isEqualTo(rook));
            }
        }

        @DisplayName("목적지에 같은 팀이 있으면 갈 수 없다.")
        @Test
        void existOnDestination() {
            //given
            final King king = new King(Team.WHITE);
            final Bishop team = new Bishop(Team.WHITE);
            final Board board = Board.create(testStrategy(Map.of(D4, king,
                    E5, team)));

            //when

            //then
            assertThatThrownBy(() -> board.move(D4, E5))
                    .isInstanceOf(IllegalArgumentException.class);
        }

        @DisplayName("출발지에 말이 없으면 예외가 발생한다.")
        @Test
        void existOnSource() {
            //given
            final Board board = Board.create(testStrategy(Collections.emptyMap()));

            //when

            //then
            assertThatThrownBy(() -> board.move(D4, E5))
                    .isInstanceOf(IllegalArgumentException.class);
        }
    }

    @DisplayName("킹이 존재하지 않을 때")
    @Test
    void notKingExist() {
        //given
        final Board board = Board.create(testStrategy(Map.of(D4, new Rook(Team.BLACK))));

        //when

        //then
        Assertions.assertAll(
                () -> assertThat(board.isBlackKingExist()).isFalse(),
                () -> assertThat(board.isWhiteKingExist()).isFalse());
    }

    @DisplayName("킹이 존재할 때")
    @Test
    void kingExist() {
        //given
        final Board board = Board.create(new InitialChessAlignment());

        //when

        //then
        Assertions.assertAll(
                () -> assertThat(board.isBlackKingExist()).isTrue(),
                () -> assertThat(board.isWhiteKingExist()).isTrue());
    }

    @DisplayName("점수를 구할 수 있다(초기 상태)")
    @Test
    void calculate1() {
        //given
        final Board board = Board.create(new InitialChessAlignment());

        //when

        //then
        Assertions.assertAll(
                () -> assertThat(board.getCurrentBlackScore()).isEqualTo(38),
                () -> assertThat(board.getCurrentWhiteScore()).isEqualTo(38));
    }

    @DisplayName("점수를 구할 수 있다(폰이 2개가 겹쳤을 때)")
    @Test
    void calculate2() {
        //given
        final Board board = Board.create(testStrategy(Map.of(
                D4, new Pawn(Team.BLACK),
                D5, new Pawn(Team.BLACK))));

        //when

        //then
        assertThat(board.getCurrentBlackScore()).isEqualTo(1);
    }

    @DisplayName("점수를 구할 수 있다(폰이 4개가 겹쳤을 때)")
    @Test
    void calculate3() {
        //given
        final Board board = Board.create(testStrategy(Map.of(
                D4, new Pawn(Team.BLACK),
                D5, new Pawn(Team.BLACK),
                D6, new Pawn(Team.BLACK),
                D8, new Pawn(Team.BLACK))));

        //when

        //then
        assertThat(board.getCurrentBlackScore()).isEqualTo(2);
    }

    @DisplayName("한 번 이동할 때마다 차례가 바뀐다")
    @Test
    void changeTurn() {
        //given
        final Board board = Board.create(new InitialChessAlignment());

        //when


        //then
        assertAll(
                () -> assertDoesNotThrow(() -> board.move(B2, B3)),
                () -> assertDoesNotThrow(() -> board.move(A7, A5)),
                () -> assertDoesNotThrow(() -> board.move(B3, B4)));
    }

    @DisplayName("잘못된 차례로 이동하면 예외가 발생한다")
    @Test
    void changeTurn2() {
        //given
        final Board board = Board.create(new InitialChessAlignment());

        //when


        //then
        assertThatThrownBy(() -> board.move(A7, A5))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("승자를 구할 수 있다.")
    @Test
    void blackWin() {
        //given
        final Board board = Board.create(ChessAlignmentMock.testStrategy(Map.of(D4, new King(Team.BLACK))));

        //when

        //then
        org.assertj.core.api.Assertions.assertThat(board.getWinner()).isEqualTo(Team.BLACK);
    }

    @DisplayName("승자를 구할 수 있다.")
    @Test
    void whiteWin() {
        //given
        final Board board = Board.create(ChessAlignmentMock.testStrategy(Map.of(D4, new King(Team.WHITE))));

        //when

        //then
        org.assertj.core.api.Assertions.assertThat(board.getWinner()).isEqualTo(Team.WHITE);
    }
}
