package chess.domain.board;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.piece.Piece;
import chess.domain.piece.PieceColor;
import chess.domain.piece.PieceType;
import chess.domain.position.Position;
import chess.domain.position.XAxis;
import chess.domain.position.YAxis;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BoardTest {

    @DisplayName("createInitializedBoard 메소드를 호출하여 말이 초기화된 Board 를 생성할 수 있다.")
    @Test
    void constructor() {
        // given
        Board board = Board.createInitializedBoard();

        // when & then
        assertThat(board).isNotNull();
    }

    @DisplayName("말의 위치로 보드의 말을 받아올 수 있다.")
    @Test
    void findPieceByPosition() {
        // given
        Board board = Board.createInitializedBoard();

        // when
        Optional<Piece> actual = board.find(Position.of(XAxis.A, YAxis.TWO));

        // when & then
        assertThat(actual.get().getPieceType()).isEqualTo(PieceType.PAWN);
    }

    @DisplayName("전달된 위치에 말이 없는 경우 빈 옵셔널을 반환한다.")
    @Test
    void findPieceByPosition_returnsEmptyOptionalOnEmpty() {
        // given
        Board board = Board.createInitializedBoard();

        // when
        Optional<Piece> actual = board.find(Position.of(XAxis.A, YAxis.THREE));

        // when & then
        assertThat(actual.isEmpty()).isEqualTo(true);
    }

    @DisplayName("이동할 위치가 빈 칸인 경우 말을 이동시킨다.")
    @Test
    void movePiece_toEmptyPlace() {
        // given
        Board board = Board.createInitializedBoard();

        // when & then
        assertThat(
                board.executeCommand(Position.of(XAxis.A, YAxis.TWO), Position.of(XAxis.A, YAxis.THREE),
                        PieceColor.WHITE).isMoveSuccess()).isTrue();
    }

    @DisplayName("이동할 위치에 적이 있는 경우 말을 이동시킨다.")
    @Test
    void movePiece_toEnemyPlace() {
        // given
        Board board = Board.createInitializedBoard();

        // when & then
        board.executeCommand(Position.of(XAxis.B, YAxis.ONE), Position.of(XAxis.C, YAxis.THREE), PieceColor.WHITE);
        board.executeCommand(Position.of(XAxis.C, YAxis.THREE), Position.of(XAxis.D, YAxis.FIVE), PieceColor.WHITE);

        assertThat(
                board.executeCommand(Position.of(XAxis.D, YAxis.FIVE), Position.of(XAxis.E, YAxis.SEVEN),
                        PieceColor.WHITE).isMoveSuccess()).isTrue();
    }

    @DisplayName("이동할 위치에 적이 있는 경우 적을 제거한다.")
    @Test
    void movePiece_toEnemyPlaceAndRemoveEnemy() {
        // given
        Board board = Board.createInitializedBoard();

        // when & then
        board.executeCommand(Position.of(XAxis.B, YAxis.ONE), Position.of(XAxis.C, YAxis.THREE), PieceColor.WHITE);
        board.executeCommand(Position.of(XAxis.C, YAxis.THREE), Position.of(XAxis.D, YAxis.FIVE), PieceColor.WHITE);
        board.executeCommand(Position.of(XAxis.D, YAxis.FIVE), Position.of(XAxis.E, YAxis.SEVEN), PieceColor.WHITE);

        assertThat(board.find(Position.of(XAxis.E, YAxis.SEVEN)).get().getPieceType()).isEqualTo(PieceType.KNIGHT);
    }

    @DisplayName("이동할 위치에 아군이 있는 경우 이동이 불가능하다.")
    @Test
    void movePiece_toSameTeamPlaceIsNotAbleToMove() {
        // given
        Board board = Board.createInitializedBoard();

        // when
        boolean actual = board.executeCommand(Position.of(XAxis.A, YAxis.ONE), Position.of(XAxis.A, YAxis.TWO),
                PieceColor.WHITE).isMoveSuccess();

        // then
        assertThat(actual).isFalse();
    }

    @DisplayName("흰색 진영 폰의 대각선 전진방향에 적이 있다면, 해당 위치로 이동하고 적을 제거한다.")
    @Test
    void move_returnsTruePawnDiagonalForwardInWhite() {
        // given
        Board board = Board.createInitializedBoard();

        // when
        board.executeCommand(Position.of(XAxis.A, YAxis.TWO), Position.of(XAxis.A, YAxis.FOUR), PieceColor.WHITE);
        board.executeCommand(Position.of(XAxis.A, YAxis.FOUR), Position.of(XAxis.A, YAxis.FIVE), PieceColor.WHITE);
        board.executeCommand(Position.of(XAxis.A, YAxis.FIVE), Position.of(XAxis.A, YAxis.SIX), PieceColor.WHITE);
        boolean actual = board.executeCommand(Position.of(XAxis.A, YAxis.SIX), Position.of(XAxis.B, YAxis.SEVEN),
                PieceColor.WHITE).isMoveSuccess();

        // then
        assertThat(actual).isTrue();
    }

    @DisplayName("흰색 진영 폰의 대각선 전진방향에 적이 없다면, 해당 위치로 이동할 수 없다.")
    @Test
    void move_returnsFalsePawnDiagonalForwardInWhite() {
        // given
        Board board = Board.createInitializedBoard();

        // when
        board.executeCommand(Position.of(XAxis.A, YAxis.TWO), Position.of(XAxis.A, YAxis.FOUR), PieceColor.WHITE);
        board.executeCommand(Position.of(XAxis.A, YAxis.FOUR), Position.of(XAxis.A, YAxis.FIVE), PieceColor.WHITE);
        boolean actual = board.executeCommand(Position.of(XAxis.A, YAxis.FIVE), Position.of(XAxis.B, YAxis.SIX),
                PieceColor.WHITE).isMoveSuccess();

        // then
        assertThat(actual).isFalse();
    }

    @DisplayName("검정색 진영 폰의 대각선 전진방향에 적이 있다면, 해당 위치로 이동하고 적을 제거한다.")
    @Test
    void move_returnsTruePawnDiagonalForwardInBlack() {
        // given
        Board board = Board.createInitializedBoard();

        // when
        board.executeCommand(Position.of(XAxis.A, YAxis.SEVEN), Position.of(XAxis.A, YAxis.FIVE), PieceColor.BLACK);
        board.executeCommand(Position.of(XAxis.A, YAxis.FIVE), Position.of(XAxis.A, YAxis.FOUR), PieceColor.BLACK);
        board.executeCommand(Position.of(XAxis.A, YAxis.FOUR), Position.of(XAxis.A, YAxis.THREE), PieceColor.BLACK);
        boolean actual = board.executeCommand(Position.of(XAxis.A, YAxis.THREE), Position.of(XAxis.B, YAxis.TWO),
                PieceColor.BLACK).isMoveSuccess();

        // then
        assertThat(actual).isTrue();
    }

    @DisplayName("검정색 진영 폰의 대각선 전진방향에 적이 없다면, 해당 위치로 이동할 수 없다.")
    @Test
    void move_returnsFalsePawnDiagonalForwardInBlack() {
        // given
        Board board = Board.createInitializedBoard();

        // when
        board.executeCommand(Position.of(XAxis.A, YAxis.SEVEN), Position.of(XAxis.A, YAxis.FIVE), PieceColor.WHITE);
        board.executeCommand(Position.of(XAxis.A, YAxis.FIVE), Position.of(XAxis.A, YAxis.FOUR), PieceColor.WHITE);
        boolean actual = board.executeCommand(Position.of(XAxis.A, YAxis.FOUR), Position.of(XAxis.B, YAxis.THREE),
                PieceColor.WHITE).isMoveSuccess();

        // then
        assertThat(actual).isFalse();
    }

    @DisplayName("나이트가 아닌 말의 수직방향 진로에 다른 말이 있다면, 이동할 수 없다.")
    @Test
    void moveVertical_returnsFalseRouteHasObstacle() {
        // given
        Board board = Board.createInitializedBoard();

        // when
        boolean actual = board.executeCommand(Position.of(XAxis.A, YAxis.ONE), Position.of(XAxis.A, YAxis.THREE),
                PieceColor.WHITE).isMoveSuccess();

        // then
        assertThat(actual).isFalse();
    }

    @DisplayName("나이트가 아닌 말의 수직방향 진로에 다른 말이 없다면, 이동할 수 있다.")
    @Test
    void moveVertical_returnsTrueRouteHasNotObstacle() {
        // given
        Board board = Board.createInitializedBoard();

        // when
        boolean actual = board.executeCommand(Position.of(XAxis.A, YAxis.TWO), Position.of(XAxis.A, YAxis.FOUR),
                PieceColor.WHITE).isMoveSuccess();

        // then
        assertThat(actual).isTrue();
    }

    @DisplayName("나이트가 아닌 말의 수평방향 진로에 다른 말이 있다면, 이동할 수 없다.")
    @Test
    void moveHorizontal_returnsFalseRouteHasObstacle() {
        // given
        Board board = Board.createInitializedBoard();

        // when
        board.executeCommand(Position.of(XAxis.A, YAxis.TWO), Position.of(XAxis.A, YAxis.FOUR), PieceColor.WHITE);
        board.executeCommand(Position.of(XAxis.A, YAxis.ONE), Position.of(XAxis.A, YAxis.THREE), PieceColor.WHITE);
        board.executeCommand(Position.of(XAxis.B, YAxis.TWO), Position.of(XAxis.B, YAxis.THREE), PieceColor.WHITE);
        boolean actual = board.executeCommand(Position.of(XAxis.A, YAxis.THREE), Position.of(XAxis.C, YAxis.THREE),
                PieceColor.WHITE).isMoveSuccess();

        // then
        assertThat(actual).isFalse();
    }

    @DisplayName("나이트가 아닌 말의 수평방향 진로에 다른 말이 없다면, 이동할 수 있다.")
    @Test
    void moveHorizontal_returnsTrueRouteHasNotObstacle() {
        // given
        Board board = Board.createInitializedBoard();

        // when
        board.executeCommand(Position.of(XAxis.A, YAxis.TWO), Position.of(XAxis.A, YAxis.FOUR), PieceColor.WHITE);
        board.executeCommand(Position.of(XAxis.A, YAxis.ONE), Position.of(XAxis.A, YAxis.THREE), PieceColor.WHITE);
        boolean actual = board.executeCommand(Position.of(XAxis.A, YAxis.THREE), Position.of(XAxis.C, YAxis.THREE),
                PieceColor.WHITE).isMoveSuccess();

        // then
        assertThat(actual).isTrue();
    }

    @DisplayName("나이트가 아닌 말의 오른쪽 위 방향 대각선 진로에 다른 말이 있다면, 이동할 수 없다.")
    @Test
    void moveRightUpDiagonalDirection_returnsFalseRouteHasObstacle() {
        // given
        Board board = Board.createInitializedBoard();

        // when
        boolean actual = board.executeCommand(Position.of(XAxis.C, YAxis.ONE), Position.of(XAxis.E, YAxis.THREE),
                PieceColor.WHITE).isMoveSuccess();

        // then
        assertThat(actual).isFalse();
    }

    @DisplayName("나이트가 아닌 말의 오른쪽 위 방향 대각선 진로에 다른 말이 없다면, 이동할 수 있다.")
    @Test
    void moveRightUpDiagonalDirection_returnsTrueRouteHasNotObstacle() {
        // given
        Board board = Board.createInitializedBoard();

        // when
        board.executeCommand(Position.of(XAxis.D, YAxis.TWO), Position.of(XAxis.D, YAxis.THREE), PieceColor.WHITE);
        boolean actual = board.executeCommand(Position.of(XAxis.C, YAxis.ONE), Position.of(XAxis.E, YAxis.THREE),
                PieceColor.WHITE).isMoveSuccess();

        // then
        assertThat(actual).isTrue();
    }

    @DisplayName("나이트가 아닌 말의 왼쪽 위 방향 대각선 진로에 다른 말이 있다면, 이동할 수 없다.")
    @Test
    void moveLeftUpDiagonalDirection_returnsFalseRouteHasObstacle() {
        // given
        Board board = Board.createInitializedBoard();

        // when
        boolean actual = board.executeCommand(Position.of(XAxis.C, YAxis.ONE), Position.of(XAxis.A, YAxis.THREE),
                PieceColor.WHITE).isMoveSuccess();

        // then
        assertThat(actual).isFalse();
    }

    @DisplayName("나이트가 아닌 말의 왼쪽 위 방향 대각선 진로에 다른 말이 없다면, 이동할 수 있다.")
    @Test
    void moveLeftUpDiagonalDirection_returnsTrueRouteHasNotObstacle() {
        // given
        Board board = Board.createInitializedBoard();

        // when
        board.executeCommand(Position.of(XAxis.B, YAxis.TWO), Position.of(XAxis.B, YAxis.THREE), PieceColor.WHITE);
        boolean actual = board.executeCommand(Position.of(XAxis.C, YAxis.ONE), Position.of(XAxis.A, YAxis.THREE),
                PieceColor.WHITE).isMoveSuccess();

        // then
        assertThat(actual).isTrue();
    }
}
