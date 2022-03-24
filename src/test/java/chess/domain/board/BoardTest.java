package chess.domain.board;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.piece.AbstractPiece;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
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
        Optional<AbstractPiece> actual = board.find(Position.from(XAxis.A, YAxis.TWO));
        Class<Pawn> expected = Pawn.class;

        // when & then
        assertThat(actual.get()).isExactlyInstanceOf(expected);
    }

    @DisplayName("전달된 위치에 말이 없는 경우 빈 옵셔널을 반환한다.")
    @Test
    void findPieceByPosition_returnsEmptyOptionalOnEmpty() {
        // given
        Board board = Board.createInitializedBoard();

        // when
        Optional<AbstractPiece> actual = board.find(Position.from(XAxis.A, YAxis.THREE));
        Optional<AbstractPiece> expected = Optional.empty();

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
                board.executeCommand(Position.from(XAxis.A, YAxis.TWO), Position.from(XAxis.A, YAxis.THREE))).isTrue();
    }

    @DisplayName("이동할 위치에 적이 있는 경우 말을 이동시킨다.")
    @Test
    void movePiece_toEnemyPlace() {
        // given
        Board board = Board.createInitializedBoard();

        // when & then
        board.executeCommand(Position.from(XAxis.B, YAxis.ONE), Position.from(XAxis.C, YAxis.THREE));
        board.executeCommand(Position.from(XAxis.C, YAxis.THREE), Position.from(XAxis.D, YAxis.FIVE));

        assertThat(
                board.executeCommand(Position.from(XAxis.D, YAxis.FIVE), Position.from(XAxis.E, YAxis.SEVEN))).isTrue();
    }

    @DisplayName("이동할 위치에 적이 있는 경우 적을 제거한다.")
    @Test
    void movePiece_toEnemyPlaceAndRemoveEnemy() {
        // given
        Board board = Board.createInitializedBoard();

        // when & then
        board.executeCommand(Position.from(XAxis.B, YAxis.ONE), Position.from(XAxis.C, YAxis.THREE));
        board.executeCommand(Position.from(XAxis.C, YAxis.THREE), Position.from(XAxis.D, YAxis.FIVE));
        board.executeCommand(Position.from(XAxis.D, YAxis.FIVE), Position.from(XAxis.E, YAxis.SEVEN));

        assertThat(board.find(Position.from(XAxis.E, YAxis.SEVEN)).get()).isInstanceOf(Knight.class);
    }

    @DisplayName("이동할 위치에 아군이 있는 경우 이동이 불가능하다.")
    @Test
    void movePiece_toSameTeamPlaceIsNotAbleToMove() {
        // given
        Board board = Board.createInitializedBoard();

        // when
        boolean actual = board.executeCommand(Position.from(XAxis.A, YAxis.ONE), Position.from(XAxis.A, YAxis.TWO));

        // then
        assertThat(actual).isFalse();
    }

    @DisplayName("흰색 진영 폰의 대각선 전진방향에 적이 있다면, 해당 위치로 이동하고 적을 제거한다.")
    @Test
    void move_returnsTruePawnDiagonalForwardInWhite() {
        // given
        Board board = Board.createInitializedBoard();

        // when
        board.executeCommand(Position.from(XAxis.A, YAxis.TWO), Position.from(XAxis.A, YAxis.FOUR));
        board.executeCommand(Position.from(XAxis.A, YAxis.FOUR), Position.from(XAxis.A, YAxis.FIVE));
        board.executeCommand(Position.from(XAxis.A, YAxis.FIVE), Position.from(XAxis.A, YAxis.SIX));
        boolean actual = board.executeCommand(Position.from(XAxis.A, YAxis.SIX), Position.from(XAxis.B, YAxis.SEVEN));

        // then
        assertThat(actual).isTrue();
    }

    @DisplayName("흰색 진영 폰의 대각선 전진방향에 적이 없다면, 해당 위치로 이동할 수 없다.")
    @Test
    void move_returnsFalsePawnDiagonalForwardInWhite() {
        // given
        Board board = Board.createInitializedBoard();

        // when
        board.executeCommand(Position.from(XAxis.A, YAxis.TWO), Position.from(XAxis.A, YAxis.FOUR));
        board.executeCommand(Position.from(XAxis.A, YAxis.FOUR), Position.from(XAxis.A, YAxis.FIVE));
        boolean actual = board.executeCommand(Position.from(XAxis.A, YAxis.FIVE), Position.from(XAxis.B, YAxis.SIX));

        // then
        assertThat(actual).isFalse();
    }

    @DisplayName("검정색 진영 폰의 대각선 전진방향에 적이 있다면, 해당 위치로 이동하고 적을 제거한다.")
    @Test
    void move_returnsTruePawnDiagonalForwardInBlack() {
        // given
        Board board = Board.createInitializedBoard();

        // when
        board.executeCommand(Position.from(XAxis.A, YAxis.SEVEN), Position.from(XAxis.A, YAxis.FIVE));
        board.executeCommand(Position.from(XAxis.A, YAxis.FIVE), Position.from(XAxis.A, YAxis.FOUR));
        board.executeCommand(Position.from(XAxis.A, YAxis.FOUR), Position.from(XAxis.A, YAxis.THREE));
        boolean actual = board.executeCommand(Position.from(XAxis.A, YAxis.THREE), Position.from(XAxis.B, YAxis.TWO));

        // then
        assertThat(actual).isTrue();
    }

    @DisplayName("검정색 진영 폰의 대각선 전진방향에 적이 없다면, 해당 위치로 이동할 수 없다.")
    @Test
    void move_returnsFalsePawnDiagonalForwardInBlack() {
        // given
        Board board = Board.createInitializedBoard();

        // when
        board.executeCommand(Position.from(XAxis.A, YAxis.SEVEN), Position.from(XAxis.A, YAxis.FIVE));
        board.executeCommand(Position.from(XAxis.A, YAxis.FIVE), Position.from(XAxis.A, YAxis.FOUR));
        boolean actual = board.executeCommand(Position.from(XAxis.A, YAxis.FOUR), Position.from(XAxis.B, YAxis.THREE));

        // then
        assertThat(actual).isFalse();
    }
}
