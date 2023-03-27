package domain.board;

import domain.Board;
import domain.exception.BlockedPathException;
import domain.exception.InvalidDestinationPointException;
import domain.piece.Empty;
import domain.piece.Piece;
import domain.piece.bishop.BlackBishop;
import domain.piece.bishop.WhiteBishop;
import domain.piece.pawn.WhitePawn;
import domain.piece.rook.BlackRook;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class BishopTest {
    @Nested
    @DisplayName("비숍이 이동하는 경우")
    class moveCase {
        @ParameterizedTest(name = "{displayName} - {0}")
        @ValueSource(strings = {"b4", "a5", "b2", "a1", "d4", "e5", "d2", "e1"})
        @DisplayName("주위에 어떤 장기말도 없을 때, 룩은 양 대각선 방향으로 무한히 이동할 수 있다.")
        void rookFirstMove(String toPoint) {
            List<List<Piece>> boardStatus = Arrays.asList(
                    Arrays.asList(new Empty(), new Empty(), new Empty(), new Empty(), new Empty()), // a5, b5, c5, d5, e5
                    Arrays.asList(new Empty(), new Empty(), new Empty(), new Empty(), new Empty()), // a4, b4, c4, d4, e4
                    Arrays.asList(new Empty(), new Empty(), new WhiteBishop(), new Empty(), new Empty()), // a3, b3, c3, d3, e3
                    Arrays.asList(new Empty(), new Empty(), new Empty(), new Empty(), new Empty()), // a2, b2, c2, d2, e2
                    Arrays.asList(new Empty(), new Empty(), new Empty(), new Empty(), new Empty()) // a1, b1, c1, d1, e1
            );
            Board board = new Board(boardStatus);

            assertDoesNotThrow(() -> board.move("c3", toPoint));
        }

        @Test
        @DisplayName("룩을 가로세로 방향으로 이동하려는 경우 예외가 발생한다.")
        void pawnMoveToInvalidDirection() {
            List<List<Piece>> boardStatus = Arrays.asList(
                    Arrays.asList(new Empty(), new Empty(), new Empty()), // a1, b1, c1
                    Arrays.asList(new Empty(), new WhiteBishop(), new Empty()), // a2, b2, c2
                    Arrays.asList(new Empty(), new Empty(), new Empty()) // a3, b3, c3
            );
            Board board = new Board(boardStatus);

            assertAll(
                    () -> assertThatThrownBy(() -> board.move("b2", "b1"))
                            .as("아래 이동 불가").isInstanceOf(InvalidDestinationPointException.class),
                    () -> assertThatThrownBy(() -> board.move("b2", "c2"))
                            .as("오른쪽 이동 불가").isInstanceOf(InvalidDestinationPointException.class),
                    () -> assertThatThrownBy(() -> board.move("b2", "b3"))
                            .as("위 이동 불가").isInstanceOf(InvalidDestinationPointException.class),
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
                    Arrays.asList(new BlackBishop(), new BlackRook(), new Empty()) // a3, b3, c3
            );
            Board board = new Board(boardStatus);

            assertThatThrownBy(() -> board.move("a3", "c1"))
                    .isInstanceOf(BlockedPathException.class);
        }

        @Test
        @DisplayName("이동하려는 위치에 우리 편의 기물이 있다면 이동이 불가능하다.")
        void givenTeamOnPoint_whenPawnMoveToPoint() {
            List<List<Piece>> boardStatus = Arrays.asList(
                    Arrays.asList(new Empty(), new Empty(), new BlackRook()), // a1, b1, c1
                    Arrays.asList(new Empty(), new Empty(), new Empty()), // a2, b2, c2
                    Arrays.asList(new BlackBishop(), new Empty(), new Empty()) // a3, b3, c3
            );
            Board board = new Board(boardStatus);

            assertThatThrownBy(() -> board.move("a3", "c1"))
                    .isInstanceOf(BlockedPathException.class);
        }
    }
}