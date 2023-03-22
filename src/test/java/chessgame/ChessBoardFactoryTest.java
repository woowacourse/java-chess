package chessgame;

import chessgame.domain.Board;
import chessgame.domain.Team;
import chessgame.domain.piece.*;
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
        void Should_SameRook_When_ConstructBoardA1() {
            Board board = new Board(ChessBoardFactory.create());

            Assertions.assertThat(board.getBoard().get(A1)).usingRecursiveComparison().isEqualTo(Rook.from(Team.WHITE));
        }

        @Test
        @DisplayName("A2에 흰팀 폰이 잘 생성되었는지 확인한다")
        void Should_SamePawn_When_ConstructBoardA2() {
            Board board = new Board(ChessBoardFactory.create());

            Assertions.assertThat(board.getBoard().get(A2)).usingRecursiveComparison().isEqualTo(Pawn.from(Team.WHITE));
        }

        @Test
        @DisplayName("B1에 흰팀 나이트가 잘 생성되었는지 확인한다")
        void Should_SameKnight_When_ConstructBoardB1() {
            Board board = new Board(ChessBoardFactory.create());
            Assertions.assertThat(board.getBoard().get(B1)).usingRecursiveComparison().isEqualTo(Knight.from(Team.WHITE));
        }

        @Test
        @DisplayName("C1에 흰팀 비숍이 잘 생성되었는지 확인한다")
        void Should_SameBishop_When_ConstructBoardC1() {
            Board board = new Board(ChessBoardFactory.create());
            Assertions.assertThat(board.getBoard().get(C1)).usingRecursiveComparison().isEqualTo(Bishop.from(Team.WHITE));
        }

        @Test
        @DisplayName("D1에 흰팀 퀸이 잘 생성되었는지 확인한다")
        void Should_SameQueen_When_ConstructBoardD1() {
            Board board = new Board(ChessBoardFactory.create());
            Assertions.assertThat(board.getBoard().get(D1)).usingRecursiveComparison().isEqualTo(Queen.from(Team.WHITE));
        }

        @Test
        @DisplayName("E1에 흰팀 킹이 잘 생성되었는지 확인한다")
        void Should_SameKing_When_ConstructBoardDE1() {
            Board board = new Board(ChessBoardFactory.create());
            Assertions.assertThat(board.getBoard().get(E1)).usingRecursiveComparison().isEqualTo(King.from(Team.WHITE));
        }

        @Test
        @DisplayName("F1에 흰팀 비숍이 잘 생성되었는지 확인한다")
        void Should_SameBishop_When_ConstructBoardF1() {
            Board board = new Board(ChessBoardFactory.create());
            Assertions.assertThat(board.getBoard().get(F1)).usingRecursiveComparison().isEqualTo(Bishop.from(Team.WHITE));
        }

        @Test
        @DisplayName("G1에 흰팀 나이트가 잘 생성되었는지 확인한다")
        void Should_SameKnight_When_ConstructBoardG1() {
            Board board = new Board(ChessBoardFactory.create());
            Assertions.assertThat(board.getBoard().get(G1)).usingRecursiveComparison().isEqualTo(Knight.from(Team.WHITE));
        }

        @Test
        @DisplayName("H1에 흰팀 룩이 잘 생성되었는지 확인한다")
        void Should_SameRook_When_ConstructBoardH1() {
            Board board = new Board(ChessBoardFactory.create());

            Assertions.assertThat(board.getBoard().get(H1)).usingRecursiveComparison().isEqualTo(Rook.from(Team.WHITE));
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


        @Test
        @DisplayName("C8에 흰팀 비숍이 잘 생성되었는지 확인한다")
        void Should_SameBishop_When_ConstructBoardC8() {
            Board board = new Board(ChessBoardFactory.create());
            Assertions.assertThat(board.getBoard().get(C8)).usingRecursiveComparison().isEqualTo(Bishop.from(Team.BLACK));
        }

        @Test
        @DisplayName("D8에 흰팀 퀸이 잘 생성되었는지 확인한다")
        void Should_SameQueen_When_ConstructBoardD8() {
            Board board = new Board(ChessBoardFactory.create());
            Assertions.assertThat(board.getBoard().get(D8)).usingRecursiveComparison().isEqualTo(Queen.from(Team.BLACK));
        }

        @Test
        @DisplayName("E8에 흰팀 킹이 잘 생성되었는지 확인한다")
        void Should_SameKing_When_ConstructBoardDE8() {
            Board board = new Board(ChessBoardFactory.create());
            Assertions.assertThat(board.getBoard().get(E8)).usingRecursiveComparison().isEqualTo(King.from(Team.BLACK));
        }

        @Test
        @DisplayName("F8에 흰팀 비숍이 잘 생성되었는지 확인한다")
        void Should_SameBishop_When_ConstructBoardF8() {
            Board board = new Board(ChessBoardFactory.create());
            Assertions.assertThat(board.getBoard().get(F8)).usingRecursiveComparison().isEqualTo(Bishop.from(Team.BLACK));
        }

        @Test
        @DisplayName("G8에 흰팀 나이트가 잘 생성되었는지 확인한다")
        void Should_SameKnight_When_ConstructBoardG8() {
            Board board = new Board(ChessBoardFactory.create());
            Assertions.assertThat(board.getBoard().get(G8)).usingRecursiveComparison().isEqualTo(Knight.from(Team.BLACK));
        }

        @Test
        @DisplayName("H8에 흰팀 룩이 잘 생성되었는지 확인한다")
        void Should_SameRook_When_ConstructBoardH8() {
            Board board = new Board(ChessBoardFactory.create());

            Assertions.assertThat(board.getBoard().get(H8)).usingRecursiveComparison().isEqualTo(Rook.from(Team.BLACK));
        }
    }
}
