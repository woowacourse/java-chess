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
        Optional<Piece> actual = board.find(Position.from(XAxis.A, YAxis.TWO));

        // when & then
        assertThat(actual.get().getPieceType()).isEqualTo(PieceType.PAWN);
    }

    @DisplayName("전달된 위치에 말이 없는 경우 빈 옵셔널을 반환한다.")
    @Test
    void findPieceByPosition_returnsEmptyOptionalOnEmpty() {
        // given
        Board board = Board.createInitializedBoard();

        // when
        Optional<Piece> actual = board.find(Position.from(XAxis.A, YAxis.THREE));
        Optional<Piece> expected = Optional.empty();

        // when & then
        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("이동할 위치가 빈 칸인 경우 말을 이동시킨다.")
    @Test
    void movePiece_toEmptyPlace() {
        // given
        Board board = Board.createInitializedBoard();

        // when & then
        assertThat(
                board.executeCommand(Position.from(XAxis.A, YAxis.TWO), Position.from(XAxis.A, YAxis.THREE),
                        PieceColor.WHITE).isMoveSuccess()).isTrue();
    }

    @DisplayName("이동할 위치에 적이 있는 경우 말을 이동시킨다.")
    @Test
    void movePiece_toEnemyPlace() {
        // given
        Board board = Board.createInitializedBoard();

        // when & then
        board.executeCommand(Position.from(XAxis.B, YAxis.ONE), Position.from(XAxis.C, YAxis.THREE), PieceColor.WHITE);
        board.executeCommand(Position.from(XAxis.C, YAxis.THREE), Position.from(XAxis.D, YAxis.FIVE), PieceColor.WHITE);

        assertThat(
                board.executeCommand(Position.from(XAxis.D, YAxis.FIVE), Position.from(XAxis.E, YAxis.SEVEN),
                        PieceColor.WHITE).isMoveSuccess()).isTrue();
    }

    @DisplayName("이동할 위치에 적이 있는 경우 적을 제거한다.")
    @Test
    void movePiece_toEnemyPlaceAndRemoveEnemy() {
        // given
        Board board = Board.createInitializedBoard();

        // when & then
        board.executeCommand(Position.from(XAxis.B, YAxis.ONE), Position.from(XAxis.C, YAxis.THREE), PieceColor.WHITE);
        board.executeCommand(Position.from(XAxis.C, YAxis.THREE), Position.from(XAxis.D, YAxis.FIVE), PieceColor.WHITE);
        board.executeCommand(Position.from(XAxis.D, YAxis.FIVE), Position.from(XAxis.E, YAxis.SEVEN), PieceColor.WHITE);

        assertThat(board.find(Position.from(XAxis.E, YAxis.SEVEN)).get().getPieceType()).isEqualTo(PieceType.KNIGHT);
    }

    @DisplayName("이동할 위치에 아군이 있는 경우 이동이 불가능하다.")
    @Test
    void movePiece_toSameTeamPlaceIsNotAbleToMove() {
        // given
        Board board = Board.createInitializedBoard();

        // when
        boolean actual = board.executeCommand(Position.from(XAxis.A, YAxis.ONE), Position.from(XAxis.A, YAxis.TWO),
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
        board.executeCommand(Position.from(XAxis.A, YAxis.TWO), Position.from(XAxis.A, YAxis.FOUR), PieceColor.WHITE);
        board.executeCommand(Position.from(XAxis.A, YAxis.FOUR), Position.from(XAxis.A, YAxis.FIVE), PieceColor.WHITE);
        board.executeCommand(Position.from(XAxis.A, YAxis.FIVE), Position.from(XAxis.A, YAxis.SIX), PieceColor.WHITE);
        boolean actual = board.executeCommand(Position.from(XAxis.A, YAxis.SIX), Position.from(XAxis.B, YAxis.SEVEN),
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
        board.executeCommand(Position.from(XAxis.A, YAxis.TWO), Position.from(XAxis.A, YAxis.FOUR), PieceColor.WHITE);
        board.executeCommand(Position.from(XAxis.A, YAxis.FOUR), Position.from(XAxis.A, YAxis.FIVE), PieceColor.WHITE);
        boolean actual = board.executeCommand(Position.from(XAxis.A, YAxis.FIVE), Position.from(XAxis.B, YAxis.SIX),
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
        board.executeCommand(Position.from(XAxis.A, YAxis.SEVEN), Position.from(XAxis.A, YAxis.FIVE), PieceColor.BLACK);
        board.executeCommand(Position.from(XAxis.A, YAxis.FIVE), Position.from(XAxis.A, YAxis.FOUR), PieceColor.BLACK);
        board.executeCommand(Position.from(XAxis.A, YAxis.FOUR), Position.from(XAxis.A, YAxis.THREE), PieceColor.BLACK);
        boolean actual = board.executeCommand(Position.from(XAxis.A, YAxis.THREE), Position.from(XAxis.B, YAxis.TWO),
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
        board.executeCommand(Position.from(XAxis.A, YAxis.SEVEN), Position.from(XAxis.A, YAxis.FIVE), PieceColor.WHITE);
        board.executeCommand(Position.from(XAxis.A, YAxis.FIVE), Position.from(XAxis.A, YAxis.FOUR), PieceColor.WHITE);
        boolean actual = board.executeCommand(Position.from(XAxis.A, YAxis.FOUR), Position.from(XAxis.B, YAxis.THREE),
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
        boolean actual = board.executeCommand(Position.from(XAxis.A, YAxis.ONE), Position.from(XAxis.A, YAxis.THREE),
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
        boolean actual = board.executeCommand(Position.from(XAxis.A, YAxis.TWO), Position.from(XAxis.A, YAxis.FOUR),
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
        board.executeCommand(Position.from(XAxis.A, YAxis.TWO), Position.from(XAxis.A, YAxis.FOUR), PieceColor.WHITE);
        board.executeCommand(Position.from(XAxis.A, YAxis.ONE), Position.from(XAxis.A, YAxis.THREE), PieceColor.WHITE);
        board.executeCommand(Position.from(XAxis.B, YAxis.TWO), Position.from(XAxis.B, YAxis.THREE), PieceColor.WHITE);
        boolean actual = board.executeCommand(Position.from(XAxis.A, YAxis.THREE), Position.from(XAxis.C, YAxis.THREE),
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
        board.executeCommand(Position.from(XAxis.A, YAxis.TWO), Position.from(XAxis.A, YAxis.FOUR), PieceColor.WHITE);
        board.executeCommand(Position.from(XAxis.A, YAxis.ONE), Position.from(XAxis.A, YAxis.THREE), PieceColor.WHITE);
        boolean actual = board.executeCommand(Position.from(XAxis.A, YAxis.THREE), Position.from(XAxis.C, YAxis.THREE),
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
        boolean actual = board.executeCommand(Position.from(XAxis.C, YAxis.ONE), Position.from(XAxis.E, YAxis.THREE),
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
        board.executeCommand(Position.from(XAxis.D, YAxis.TWO), Position.from(XAxis.D, YAxis.THREE), PieceColor.WHITE);
        boolean actual = board.executeCommand(Position.from(XAxis.C, YAxis.ONE), Position.from(XAxis.E, YAxis.THREE),
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
        boolean actual = board.executeCommand(Position.from(XAxis.C, YAxis.ONE), Position.from(XAxis.A, YAxis.THREE),
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
        board.executeCommand(Position.from(XAxis.B, YAxis.TWO), Position.from(XAxis.B, YAxis.THREE), PieceColor.WHITE);
        boolean actual = board.executeCommand(Position.from(XAxis.C, YAxis.ONE), Position.from(XAxis.A, YAxis.THREE),
                PieceColor.WHITE).isMoveSuccess();

        // then
        assertThat(actual).isTrue();
    }

    @DisplayName("초기화된 상태의 보드에서 흰색팀의 총 점수를 계산한다.")
    @Test
    void calculateScore_withWhite() {
        // given
        Board board = Board.createInitializedBoard();

        // when
        double actual = board.calculateScore(PieceColor.WHITE);

        // then
        assertThat(actual).isEqualTo(38);
    }

    @DisplayName("초기화된 상태의 보드에서 검정색팀의 총 점수를 계산한다.")
    @Test
    void calculateScore_withBlack() {
        // given
        Board board = Board.createInitializedBoard();

        // when
        double actual = board.calculateScore(PieceColor.BLACK);

        // then
        assertThat(actual).isEqualTo(38);
    }

    @DisplayName("폰이 같은 열에 있을 경우 해당 열의 모든 폰은 0.5점으로 계산된다.")
    @Test
    void calculateScore_withWhitePawnSameColumn() {
        // given
        Board board = Board.createInitializedBoard();

        // when
        board.executeCommand(Position.from(XAxis.B, YAxis.SEVEN), Position.from(XAxis.B, YAxis.FIVE), PieceColor.BLACK);
        board.executeCommand(Position.from(XAxis.B, YAxis.FIVE), Position.from(XAxis.B, YAxis.FOUR), PieceColor.BLACK);
        board.executeCommand(Position.from(XAxis.B, YAxis.FOUR), Position.from(XAxis.B, YAxis.THREE), PieceColor.BLACK);
        board.executeCommand(Position.from(XAxis.A, YAxis.TWO), Position.from(XAxis.B, YAxis.THREE), PieceColor.WHITE);

        double actual = board.calculateScore(PieceColor.WHITE);

        // then
        assertThat(actual).isEqualTo(37);
    }
}
