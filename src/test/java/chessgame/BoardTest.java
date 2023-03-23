package chessgame;

import chessgame.domain.Board;
import chessgame.domain.ChessBoardFactory;
import chessgame.domain.Team;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static chessgame.point.PointFixture.*;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class BoardTest {
    @Test
    @DisplayName("모든 좌표값을 가진 보드를 생성한다")
    void Should_NotThrowException_When_ConstructBord() {
        assertDoesNotThrow(() -> new Board(ChessBoardFactory.create()));
    }

    @Test
    @DisplayName("소스에 기물이 있는지 확인하다.")
    void Should_ThrowException_When_SourcePointHasPiece() {
        Board board = new Board(ChessBoardFactory.create());

        assertThatThrownBy(() -> board.move(A3, A4, Team.WHITE))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 좌표에 기물이 존재하지 않습니다.");
    }

    @Nested
    @DisplayName("블랙팀, 화이트팀 의 기물을 움직인다.")
    class MovePieceTeamTest {
        @Nested
        @DisplayName("해당 순서가 맞지 않을 때를 확인한다.")
        class MovePieceNoRightTeamTest {
            @Test
            @DisplayName("화이트팀 차례에 검은팀 기물이 움직이지 못한다.")
            void Should_ThrowException_When_WhiteCanMove() {
                Board board = new Board(ChessBoardFactory.create());

                assertThatThrownBy(() -> board.move(A7, A5, Team.WHITE))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("흰팀 기물만 움직일 수 있습니다.");
            }

            @Test
            @DisplayName("화이트팀 차례에 검은팀 기물이 움직이지 못한다.")
            void Should_ThrowException_When_BlackCanMove() {
                Board board = new Board(ChessBoardFactory.create());

                assertThatThrownBy(() -> board.move(A7, A5, Team.WHITE))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("흰팀 기물만 움직일 수 있습니다.");
            }
        }

        @Nested
        @DisplayName("해당 순서가 맞을 때를 확인한다.")
        class MovePieceRightTeamTest {
            @Test
            @DisplayName("블랙팀 차례에 검은팀 기물이 움직인다.")
            void Should_NoException_When_BlackCanMove() {
                Board board = new Board(ChessBoardFactory.create());

                assertThatNoException().isThrownBy(() -> board.move(A7, A5, Team.BLACK));
            }

            @Test
            @DisplayName("흰팀 차례에 흰색팀 기물이 움직인다.")
            void Should_NoException_When_WhiteCanMove() {
                Board board = new Board(ChessBoardFactory.create());

                assertThatNoException().isThrownBy(() -> board.move(A2, A4, Team.WHITE));
            }
        }
    }

    @Test
    @DisplayName("해당 팀에 해당하는 기물은 잡을 수 없습니다.")
    void Should_ThrowException_When_SameTarget() {
        Board board = new Board(ChessBoardFactory.create());

        assertThatThrownBy(() -> board.move(A1, A2, Team.WHITE))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("자기팀 기물을 잡을 수 없습니다.");
    }

    @Test
    @DisplayName("기물의 이동 경로가 다른 기물에 의해 막혀있는지 확인합니다.")
    void Should_False_When_RouteBlockedByPiece() {
        Board board = new Board(ChessBoardFactory.create());

        assertThatThrownBy(() -> board.move(A1, A2, Team.WHITE))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Nested
    @DisplayName("폰의 움직임을 테스트 한다.")
    class MovePawnTest {
        @Test
        @DisplayName("폰이 공격 대상이 없는 좌표로 대각선 이동 시 실패 테스트")
        void Should_ThrowException_When_ImpossibleMove() {
            Board board = new Board(ChessBoardFactory.create());

            assertThatThrownBy(() -> board.move(B2, C3, Team.WHITE))
                    .isExactlyInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("움직일 수 없습니다.");
        }

        @Test
        @DisplayName("폰이 전진 시 상대 기물이 있을 경우 실패 테스트")
        void Should_ThrowException_When_PawnAttackStraight() {
            Board board = new Board(ChessBoardFactory.create());

            board.move(A2, A4, Team.WHITE);
            board.move(A7, A5, Team.BLACK);

            assertThatThrownBy(() -> board.move(A4, A5, Team.WHITE))
                    .isExactlyInstanceOf(IllegalArgumentException.class)
                    .hasMessage("움직일 수 없습니다.");
        }
    }

    @Test
    @DisplayName("king이 존재하는지 확인한다.")
    void Should_ExistKing_When_Move() {
        Board board = new Board(ChessBoardFactory.create());

        assertThat(board.isExistKing(Team.BLACK)).isTrue();
        assertThat(board.isExistKing(Team.WHITE)).isTrue();
    }

    @Test
    @DisplayName("king이 없을 경우")
    void Should_NoExistKing_When_Move() {
        Board board = new Board(ChessBoardFactory.create());

        board.move(B1, A3, Team.WHITE);
        board.move(H7, H6, Team.BLACK);
        board.move(A3, B5, Team.WHITE);
        board.move(H8, H7, Team.BLACK);
        board.move(B5, D6, Team.WHITE);
        board.move(A7, A5, Team.BLACK);
        board.move(D6, E8, Team.WHITE);

        assertThat(board.isExistKing(Team.BLACK)).isFalse();
    }
}
