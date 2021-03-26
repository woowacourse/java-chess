package chess.domain.piece;

import chess.domain.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;

public class QueenTest {
    @Test
    @DisplayName("퀸 정상 생성되는지 확인한다")
    void init() {
        assertThatCode(() -> new Queen())
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("퀸이 직선으로 움직일 수 있는 경로가 주어지면, 참을 반환한다.")
    void check_position_rule_straight_valid_test() {
        final Queen queen = new Queen();
        final Position start = Position.of("c1");
        final Position validDestination = Position.of("c3");
        assertThat(queen.checkPositionRule(start, validDestination)).isTrue();
    }

    @Test
    @DisplayName("퀸이 대각선으로 움직일 수 있는 경로가 주어지면, 참을 반환한다.")
    void check_position_rule_diagonal_valid_test() {
        final Queen queen = new Queen();
        final Position start = Position.of("c1");
        final Position validDestination = Position.of("e3");
        assertThat(queen.checkPositionRule(start, validDestination)).isTrue();
    }

    @Test
    @DisplayName("퀸이 규칙상 정상적으로 움직일 수 없는 경로가 주어지면, 거짓을 반환한다.")
    void check_position_rule_invalid_test() {
        final Queen queen = new Queen();
        final Position start = Position.of("c1");
        final Position invalidDestination = Position.of("d3");
        assertThat(queen.checkPositionRule(start, invalidDestination)).isFalse();
    }


    @Test
    @DisplayName("퀸이 가려는 직선 경로에 기물이 끼어있다면, 거짓을 반환한다.")
    void check_is_movable_invalid_straight_test() {
        final Queen queen = new Queen();
        final Position start = Position.of("d1");
        final Position dest = Position.of("d6");

        final Pawn pawn = new Pawn(1);
        final Map<Position, Piece> chessBoard = new HashMap<>();
        chessBoard.put(Position.of("d2"), pawn);

        assertThat(queen.isMovable(start, dest, chessBoard)).isFalse();
    }

    @Test
    @DisplayName("퀸이이 가려는 대각선 경로에 기물이 끼어있다면, 거짓을 반환한다.")
    void check_is_movable_invalid_diagonal_test() {
        final Queen queen = new Queen();
        final Position start = Position.of("d1");
        final Position dest = Position.of("h5");

        final Pawn pawn = new Pawn(1);
        final Map<Position, Piece> chessBoard = new HashMap<>();
        chessBoard.put(Position.of("e2"), pawn);

        assertThat(queen.isMovable(start, dest, chessBoard)).isFalse();
    }
}
