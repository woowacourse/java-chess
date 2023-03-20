package chessgame;

import chessgame.domain.Board;
import chessgame.domain.Team;
import chessgame.domain.piece.Knight;
import chessgame.domain.piece.Pawn;
import chessgame.domain.piece.Rook;
import chessgame.factory.ChessBoardFactory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static chessgame.point.PointFixture.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class ChessBoardFactoryTest {
    @Test
    @DisplayName("모든 좌표값을 가진 보드를 생성한다")
    void Should_NotThrowException_When_ConstructBoard() {
        assertDoesNotThrow(() -> new Board(ChessBoardFactory.create()));
    }

    @Nested
    @DisplayName("흰팀 기물이 잘 생성되었는지 확인한다.")
    class checkWhitePieceTest {
        @Test
        @DisplayName("A1에 흰팀 룩이 잘 생성되었는지 확인한다")
        void Should_Same_When_ConstructBoardA1() {
            Board board = new Board(ChessBoardFactory.create());

            Assertions.assertThat(board.getBoard().get(A1)).usingRecursiveComparison().isEqualTo(Rook.from(Team.WHITE));
        }

        @Test
        @DisplayName("A2에 흰팀 폰이 잘 생성되었는지 확인한다")
        void Should_Same_When_ConstructBoardA2() {
            Board board = new Board(ChessBoardFactory.create());

            Assertions.assertThat(board.getBoard().get(A2)).usingRecursiveComparison().isEqualTo(Pawn.from(Team.WHITE));
        }

        @Test
        @DisplayName("B1에 흰팀 나이트가 잘 생성되었는지 확인한다")
        void Should_Same_When_ConstructBoardB1() {
            Board board = new Board(ChessBoardFactory.create());
            Assertions.assertThat(board.getBoard().get(B1)).usingRecursiveComparison().isEqualTo(Knight.from(Team.WHITE));
        }
    }

    @Nested
    @DisplayName("검은팀 기물이 잘 생성되었는지 확인한다.")
    class checkBlackPieceTest {
        @Test
        @DisplayName("A8에 검은팀 룩이 잘 생성되었는지 확인한다")
        void Should_Same_When_ConstructBoardA8() {
            Board board = new Board(ChessBoardFactory.create());

            Assertions.assertThat(board.getBoard().get(A8)).usingRecursiveComparison().isEqualTo(Rook.from(Team.BLACK));
        }

        @Test
        @DisplayName("A7에 검은팀 폰이 잘 생성되었는지 확인한다")
        void Should_Same_When_ConstructBoardA7() {
            Board board = new Board(ChessBoardFactory.create());

            Assertions.assertThat(board.getBoard().get(A7)).usingRecursiveComparison().isEqualTo(Pawn.from(Team.BLACK));
        }

        @Test
        @DisplayName("B8에 검은팀 나이트가 잘 생성되었는지 확인한다")
        void Should_Same_When_ConstructBoardB8() {
            Board board = new Board(ChessBoardFactory.create());
            Assertions.assertThat(board.getBoard().get(B8)).usingRecursiveComparison().isEqualTo(Knight.from(Team.BLACK));
        }
    }
}
