package chessgame.piece;

import chessgame.domain.Board;
import chessgame.domain.ChessBoardFactory;
import chessgame.domain.Team;
import chessgame.domain.piece.Pawn;
import chessgame.domain.piece.PieceType;
import chessgame.domain.piece.Rook;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static chessgame.point.PointFixture.*;

class PawnTest {

    @Test
    @DisplayName("A1에 흰팀 룩이 잘 생성되었는지 확인한다")
    void Should_SameRook_When_ConstructBoardA1() {
        Board board = new Board(ChessBoardFactory.create());

        Assertions.assertThat(board.getBoard().get(A1)).usingRecursiveComparison().isEqualTo(Rook.from(Team.WHITE));
    }

    @Nested
    @DisplayName("흑팀 Pawn이 이동한다.")
    class moveBlackPawn {
        Pawn pawn = Pawn.from(Team.BLACK);

        @Test
        @DisplayName("흑팀 Pawn은 시작 시 아래로 2칸 움직일 수 있다.")
        void Should_MoveVerticalTwoDistance_When_BlackPawnStart() {
            boolean result = pawn.isMovable(F7, F5, false);
            Assertions.assertThat(result).isTrue();
        }

        @Test
        @DisplayName("흑팀 Pawn은 아래로 1칸 움직일 수 있다.")
        void Should_MoveVerticalOneDistance_When_BlackPawn() {
            boolean result = pawn.isMovable(A4, A3, false);
            Assertions.assertThat(result).isTrue();
        }

        @Test
        @DisplayName("흑팀 Pawn은 공격시 대각선 밑으로 1칸 움직일 수 있다.")
        void Should_MoveDiagonalOneDistance_When_BlackPawnAttack() {
            Pawn pawn = Pawn.from(Team.BLACK);
            boolean result = pawn.isMovable(A4, B3, true);
            Assertions.assertThat(result).isTrue();
        }
    }

    @Nested
    @DisplayName("백팀 Pawn이 이동한다.")
    class moveWhitePawn {
        Pawn pawn = Pawn.from(Team.WHITE);

        @Test
        @DisplayName("백팀 Pawn은 시작 시 위로 2칸 움직일 수 있다.")
        void Should_MoveVerticalTwoDistance_When_WhitePawnStart() {
            boolean result = pawn.isMovable(A2, A4, false);
            Assertions.assertThat(result).isTrue();
        }

        @Test
        @DisplayName("백팀 Pawn은 위로 1칸 움직일 수 있다.")
        void Should_MoveVerticalOneDistance_When_WhitePawn() {
            boolean result = pawn.isMovable(A3, A4, false);
            Assertions.assertThat(result).isTrue();
        }

        @Test
        @DisplayName("백팀 Pawn은 공격 시 대각선 위로 1칸 움직일 수 있다.")
        void Should_MoveDiagonalOneDistance_When_WhitePawnAttack() {
            boolean result = pawn.isMovable(A2, B3, true);
            Assertions.assertThat(result).isTrue();
        }
    }

    @Nested
    @DisplayName("기물을 확인한다.")
    class CheckPiece {
        Pawn pawn = Pawn.from(Team.WHITE);

        @Test
        @DisplayName("Pawn인지 여부를 물어본다.")
        void Should_False_When_IsPawn() {
            Assertions.assertThat(pawn.isPiece(PieceType.PAWN)).isTrue();
        }

        @Test
        @DisplayName("Knight인지 여부를 물어본다.")
        void Should_False_When_IsKnight() {
            Assertions.assertThat(pawn.isPiece(PieceType.KNIGHT)).isFalse();
        }

        @Test
        @DisplayName("King인지 여부를 물어본다.")
        void Should_False_When_IsKing() {
            Assertions.assertThat(pawn.isPiece(PieceType.KING)).isFalse();
        }
    }
}
