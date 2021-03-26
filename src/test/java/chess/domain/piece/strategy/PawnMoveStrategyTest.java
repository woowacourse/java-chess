package chess.domain.piece.strategy;

import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.domain.order.MoveRoute;
import chess.domain.piece.Color;
import chess.domain.piece.Pawn;
import chess.domain.position.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PawnMoveStrategyTest {
    private Board board;

    @BeforeEach
    void setUp() {
        board = BoardFactory.createBoard();
    }

    @DisplayName("행마에 대한 검증 - 직선")
    @ParameterizedTest
    @CsvSource({"a2, a3, WHITE", "b7, b6, BLACK"})
    void canMove_StraightDirection(String from, String to, Color color) {
        Pawn pawn = new Pawn(color);
        MoveRoute moveRoute = board.createMoveRoute(Position.of(from), Position.of(to));
        assertThat(pawn.canMove(moveRoute)).isEqualTo(true);
    }

    @DisplayName("직선으로 3칸 이상 이동할 경우 예외")
    @ParameterizedTest
    @CsvSource({"c2, c5, WHITE", "g7, g4, BLACK"})
    void throwExceptionWhenMoveOverThreeSquares(String from, String to, Color color) {
        Pawn pawn = new Pawn(color);
        MoveRoute moveRoute = board.createMoveRoute(Position.of(from), Position.of(to));
        assertThatThrownBy(() -> pawn.canMove(moveRoute))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("폰이 움직일 수 있는 범위를 벗어났습니다.");
    }

    // TODO 대각선 이동 테스트 구현

    @DisplayName("폰은 첫 행마에 2칸을 움직일 수 있다.")
    @ParameterizedTest
    @CsvSource({"a2, a4, WHITE", "b7, b5, BLACK"})
    void canMove_2RankOnFirstMove(String from, String to, Color color) {
        Pawn pawn = new Pawn(color);
        MoveRoute moveRoute = board.createMoveRoute(Position.of(from), Position.of(to));
        assertThat(pawn.canMove(moveRoute)).isEqualTo(true);
    }

    // TODO 2번째 행마 시 2칸을 전진시 예외 발생 테스트
}