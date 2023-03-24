package domain;

import domain.exception.BlockedPathException;
import domain.exception.InvalidDestinationPointException;
import domain.exception.TargetPieceNotFoundException;
import domain.piece.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertAll;

class BoardTest {
    @Test
    @DisplayName("체스판의 현재 상태를 조회할 수 있다.")
    void initialize() {
        Board board = Board.initialize();
        List<List<Piece>> status = board.findCurrentStatus();

        assertThat(status).hasSize(8);
        assertStatusOfPieces(status);
    }

    private void assertStatusOfPieces(List<List<Piece>> status) {
        assertFirstWhiteRank(status.get(0));
        assertSecondWhiteRank(status.get(1));
        assertEmptyRanks(status);
        assertSecondBlackRank(status.get(6));
        assertFirstBlackRank(status.get(7));
    }

    private void assertEmptyRanks(List<List<Piece>> status) {
        for (int rankIndex = 2; rankIndex < 5; rankIndex++) {
            assertEmptyRank(status.get(rankIndex));
        }
    }

    private void assertEmptyRank(List<Piece> pieces) {
        assertAll(
                () -> assertThat(pieces).hasSize(8),
                () -> assertThat(pieces).containsOnly(new Empty())
        );
    }

    private void assertFirstWhiteRank(List<Piece> pieces) {
        assertAll(
                () -> assertThat(pieces).hasSize(8),
                () -> assertThat(pieces.get(0)).isEqualTo(new WhiteRook()),
                () -> assertThat(pieces.get(1)).isEqualTo(new WhiteKnight()),
                () -> assertThat(pieces.get(2)).isEqualTo(new WhiteBishop()),
                () -> assertThat(pieces.get(3)).isEqualTo(new WhiteQueen()),
                () -> assertThat(pieces.get(4)).isEqualTo(new WhiteKing()),
                () -> assertThat(pieces.get(5)).isEqualTo(new WhiteBishop()),
                () -> assertThat(pieces.get(6)).isEqualTo(new WhiteKnight()),
                () -> assertThat(pieces.get(7)).isEqualTo(new WhiteRook())
        );
    }

    private void assertSecondWhiteRank(List<Piece> pieces) {
        assertAll(
                () -> assertThat(pieces).hasSize(8),
                () -> assertThat(pieces).containsOnly(new WhitePawn())
        );
    }

    private void assertFirstBlackRank(List<Piece> pieces) {
        assertAll(
                () -> assertThat(pieces).hasSize(8),
                () -> assertThat(pieces.get(0)).isEqualTo(new BlackRook()),
                () -> assertThat(pieces.get(1)).isEqualTo(new BlackKnight()),
                () -> assertThat(pieces.get(2)).isEqualTo(new BlackBishop()),
                () -> assertThat(pieces.get(3)).isEqualTo(new BlackQueen()),
                () -> assertThat(pieces.get(4)).isEqualTo(new BlackKing()),
                () -> assertThat(pieces.get(5)).isEqualTo(new BlackBishop()),
                () -> assertThat(pieces.get(6)).isEqualTo(new BlackKnight()),
                () -> assertThat(pieces.get(7)).isEqualTo(new BlackRook())
        );
    }

    private void assertSecondBlackRank(List<Piece> pieces) {
        assertThat(pieces).containsOnly(new BlackPawn());
    }

    @Nested
    @DisplayName("장기말 이동과 관련한 메서드 테스트")
    class moveTest {
        @Test
        @DisplayName("출발 좌표, 도착 좌표가 주어지면 출발 좌표에 있는 말이 이동한다.")
        void move() {
            List<List<Piece>> boardStatus = Arrays.asList(
                    Arrays.asList(new Empty(), new Empty(), new Empty()), // a3, b3, c3
                    Arrays.asList(new BlackPawn(), new Empty(), new Empty()), // a2, b2, c2
                    Arrays.asList(new Empty(), new Empty(), new Empty()) // a1, b2, c3
            );
            Board board = new Board(boardStatus);

            board.move("a2", "a3");

            assertThat(boardStatus.get(1).get(0)).isEqualTo(new Empty());
            assertThat(boardStatus.get(2).get(0)).isEqualTo(new OnceMovedBlackPawn());
        }

        @Test
        @DisplayName("출발 좌표에 아무 장기말이 없으면 예외가 발생한다.")
        void moveFromEmptyPoint() {
            List<List<Piece>> boardStatus = Arrays.asList(
                    Arrays.asList(new Empty(), new Empty(), new Empty()), // a3, b3, c3
                    Arrays.asList(new BlackPawn(), new Empty(), new Empty()), // a2, b2, c2
                    Arrays.asList(new Empty(), new Empty(), new Empty()) // a1, b2, c3
            );

            Board board = new Board(boardStatus);

            assertThatThrownBy(() -> board.move("a1", "a3"))
                    .isInstanceOf(TargetPieceNotFoundException.class);
        }

        @Nested
        @DisplayName("검정색 폰을 움직이는 경우")
        class BlackPawnCase {
            @Test
            @DisplayName("폰을 처음 움직이는 경우, 한 번에 두 칸씩 이동할 수 있다.")
            void pawnFirstMove() {
                List<List<Piece>> boardStatus = Arrays.asList(
                        Arrays.asList(new BlackPawn(), new Empty(), new Empty()), // a1, b1, c1
                        Arrays.asList(new Empty(), new Empty(), new Empty()), // a2, b2, c2
                        Arrays.asList(new Empty(), new Empty(), new Empty()) // a3, b3, c3
                );
                Board board = new Board(boardStatus);

                board.move("a1", "a3");

                assertThat(boardStatus.get(2).get(0)).isEqualTo(new OnceMovedBlackPawn());
            }

            @Test
            @DisplayName("폰을 처음 움직인 이후에는, 한 번에 한 칸씩 전진할 수 있다.")
            void pawnMoveAfterFirst() {
                List<List<Piece>> boardStatus = Arrays.asList(
                        Arrays.asList(new BlackPawn(), new Empty(), new Empty()), // a1, a2, a3
                        Arrays.asList(new Empty(), new Empty(), new Empty()), // a2, b2, c2
                        Arrays.asList(new Empty(), new Empty(), new Empty()), // a3, b3, c3
                        Arrays.asList(new Empty(), new Empty(), new Empty()), // a4, b4, c4
                        Arrays.asList(new Empty(), new Empty(), new Empty()) // a5, b5, c5
                );
                Board board = new Board(boardStatus);
                board.move("a1", "a3");

                assertThatThrownBy(() -> board.move("a3", "a5"))
                        .as("최초의 이동이 아닌데 두 칸을 한번에 전진하려는 경우 예외가 발생한다.")
                        .isInstanceOf(InvalidDestinationPointException.class);
                assertDoesNotThrow(() -> board.move("a3", "a4"));
            }

            @Test
            @DisplayName("주위에 장기말이 없을 때, 폰을 위쪽 방향 외의 다른 방향으로 이동하려는 경우 예외가 발생한다.")
            void pawnMoveToInvalidDirection() {
                List<List<Piece>> boardStatus = Arrays.asList(
                        Arrays.asList(new Empty(), new Empty(), new Empty()), // a1, b1, c1
                        Arrays.asList(new Empty(), new BlackPawn(), new Empty()), // a2, b2, c2
                        Arrays.asList(new Empty(), new Empty(), new Empty()) // a3, b3, c3
                );
                Board board = new Board(boardStatus);

                assertAll(
                        () -> assertThatThrownBy(() -> board.move("b2", "a1"))
                                .as("왼쪽 아래 이동 불가").isInstanceOf(InvalidDestinationPointException.class),
                        () -> assertThatThrownBy(() -> board.move("b2", "c1"))
                                .as("오른쪽 아래 이동 불가").isInstanceOf(InvalidDestinationPointException.class),
                        () -> assertThatThrownBy(() -> board.move("b2", "c2"))
                                .as("오른쪽 이동 불가").isInstanceOf(InvalidDestinationPointException.class),
                        () -> assertThatThrownBy(() -> board.move("b2", "c3"))
                                .as("오른쪽 위 이동 불가").isInstanceOf(InvalidDestinationPointException.class),
                        () -> assertThatThrownBy(() -> board.move("b2", "b1"))
                                .as("아래 이동 불가").isInstanceOf(InvalidDestinationPointException.class),
                        () -> assertThatThrownBy(() -> board.move("b2", "a3"))
                                .as("왼쪽 위 이동 불가").isInstanceOf(InvalidDestinationPointException.class),
                        () -> assertThatThrownBy(() -> board.move("b2", "a2"))
                                .as("왼쪽 이동 불가").isInstanceOf(InvalidDestinationPointException.class)
                );
            }

            @Test
            @DisplayName("이동하려는 경로 사이에 다른 기물이 막고있을 경우, 전진하지 못하고 예외가 발생한다.")
            void givenPieceBetWeenTwoPoint_whenPawnMoveToPoint() {
                List<List<Piece>> boardStatus = Arrays.asList(
                        Arrays.asList(new Empty(), new Empty(), new Empty()), // a1, b1, c1
                        Arrays.asList(new Empty(), new BlackPawn(), new Empty()), // a2, b2, c2
                        Arrays.asList(new Empty(), new BlackPawn(), new Empty()), // a3, b3, c3
                        Arrays.asList(new Empty(), new Empty(), new Empty()) // a4, b4, c4
                );
                Board board = new Board(boardStatus);

                assertThatThrownBy(() -> board.move("b2", "b4"))
                        .isInstanceOf(BlockedPathException.class);
            }

            @Test
            @DisplayName("이동하려는 위치에 우리 편의 기물이 있다면 이동이 불가능하다.")
            void givenTeamOnPoint_whenPawnMoveToPoint() {
                List<List<Piece>> boardStatus = Arrays.asList(
                        Arrays.asList(new Empty(), new Empty(), new Empty()), // a1, b1, c1
                        Arrays.asList(new Empty(), new BlackPawn(), new Empty()), // a2, b2, c2
                        Arrays.asList(new Empty(), new Empty(), new Empty()), // a3, b3, c3
                        Arrays.asList(new Empty(), new BlackPawn(), new Empty()) // a4, b4, c4
                );
                Board board = new Board(boardStatus);

                assertThatThrownBy(() -> board.move("b2", "b4"))
                        .isInstanceOf(BlockedPathException.class);
            }
        }

        @Nested
        @DisplayName("하얀색 폰을 움직이는 경우")
        class WhitePawnCase {
            @Test
            @DisplayName("폰을 처음 움직이는 경우, 한 번에 두 칸씩 이동할 수 있다.")
            void pawnFirstMove() {
                List<List<Piece>> boardStatus = Arrays.asList(
                        Arrays.asList(new Empty(), new Empty(), new Empty()), // a1, b1, c1
                        Arrays.asList(new Empty(), new Empty(), new Empty()), // a2, b2, c2
                        Arrays.asList(new Empty(), new WhitePawn(), new Empty()) // a3, b3, c3
                );
                Board board = new Board(boardStatus);

                board.move("b3", "b1");

                assertThat(boardStatus.get(0).get(1)).isEqualTo(new OneMovedWhitePawn());
            }

            @Test
            @DisplayName("폰을 처음 움직인 이후에는, 한 번에 한 칸씩 전진할 수 있다.")
            void pawnMoveAfterFirst() {
                List<List<Piece>> boardStatus = Arrays.asList(
                        Arrays.asList(new Empty(), new Empty(), new Empty()), // a1, a2, a3
                        Arrays.asList(new Empty(), new Empty(), new Empty()), // a2, b2, c2
                        Arrays.asList(new Empty(), new Empty(), new Empty()), // a3, b3, c3
                        Arrays.asList(new Empty(), new Empty(), new Empty()), // a4, b4, c4
                        Arrays.asList(new Empty(), new WhitePawn(), new Empty()) // a5, b5, c5
                );
                Board board = new Board(boardStatus);
                board.move("b5", "b3");

                assertThatThrownBy(() -> board.move("b3", "b1"))
                        .as("최초의 이동이 아닌데 두 칸을 한번에 전진하려는 경우 예외가 발생한다.")
                        .isInstanceOf(InvalidDestinationPointException.class);
                assertDoesNotThrow(() -> board.move("b3", "b2"));
            }

            @Test
            @DisplayName("주위에 장기말이 없을 때, 폰을 위쪽 방향 외의 다른 방향으로 이동하려는 경우 예외가 발생한다.")
            void pawnMoveToInvalidDirection() {
                List<List<Piece>> boardStatus = Arrays.asList(
                        Arrays.asList(new Empty(), new Empty(), new Empty()), // a1, b1, c1
                        Arrays.asList(new Empty(), new WhitePawn(), new Empty()), // a2, b2, c2
                        Arrays.asList(new Empty(), new Empty(), new Empty()) // a3, b3, c3
                );
                Board board = new Board(boardStatus);

                assertAll(
                        () -> assertThatThrownBy(() -> board.move("b2", "b3"))
                                .as("위 이동 불가").isInstanceOf(InvalidDestinationPointException.class),
                        () -> assertThatThrownBy(() -> board.move("b2", "a1"))
                                .as("왼쪽 아래 이동 불가").isInstanceOf(InvalidDestinationPointException.class),
                        () -> assertThatThrownBy(() -> board.move("b2", "c1"))
                                .as("오른쪽 아래 이동 불가").isInstanceOf(InvalidDestinationPointException.class),
                        () -> assertThatThrownBy(() -> board.move("b2", "c2"))
                                .as("오른쪽 이동 불가").isInstanceOf(InvalidDestinationPointException.class),
                        () -> assertThatThrownBy(() -> board.move("b2", "c3"))
                                .as("오른쪽 위 이동 불가").isInstanceOf(InvalidDestinationPointException.class),
                        () -> assertThatThrownBy(() -> board.move("b2", "a3"))
                                .as("왼쪽 위 이동 불가").isInstanceOf(InvalidDestinationPointException.class),
                        () -> assertThatThrownBy(() -> board.move("b2", "a2"))
                                .as("왼쪽 이동 불가").isInstanceOf(InvalidDestinationPointException.class)
                );
            }

            @Test
            @DisplayName("이동하려는 경로 사이에 다른 기물이 막고있을 경우, 전진하지 못하고 예외가 발생한다.")
            void givenPieceBetWeenTwoPoint_whenPawnMoveToPoint() {
                List<List<Piece>> boardStatus = Arrays.asList(
                        Arrays.asList(new Empty(), new Empty(), new Empty()), // a1, b1, c1
                        Arrays.asList(new Empty(), new WhitePawn(), new Empty()), // a2, b2, c2
                        Arrays.asList(new Empty(), new WhitePawn(), new Empty()), // a3, b3, c3
                        Arrays.asList(new Empty(), new Empty(), new Empty()) // a4, b4, c4
                );
                Board board = new Board(boardStatus);

                assertThatThrownBy(() -> board.move("b3", "b1"))
                        .isInstanceOf(BlockedPathException.class);
            }

            @Test
            @DisplayName("이동하려는 위치에 우리 편의 기물이 있다면 이동이 불가능하다.")
            void givenTeamOnPoint_whenPawnMoveToPoint() {
                List<List<Piece>> boardStatus = Arrays.asList(
                        Arrays.asList(new Empty(), new Empty(), new Empty()), // a1, b1, c1
                        Arrays.asList(new Empty(), new WhitePawn(), new Empty()), // a2, b2, c2
                        Arrays.asList(new Empty(), new Empty(), new Empty()), // a3, b3, c3
                        Arrays.asList(new Empty(), new WhitePawn(), new Empty()) // a4, b4, c4
                );
                Board board = new Board(boardStatus);

                assertThatThrownBy(() -> board.move("b4", "b2"))
                        .isInstanceOf(BlockedPathException.class);
            }
        }
    }
}