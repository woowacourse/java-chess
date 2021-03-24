package chess.domain.piece;

import chess.domain.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;

public class RookTest {
    @Test
    @DisplayName("룩이 정상 생성되는지 확인한다")
    void init() {
        assertThatCode(() -> new Rook()).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("룩이 규칙상 정상적으로 움직일 수 있는 경로가 주어지면, 참을 반환한다.")
    void check_position_rule_valid_test() {
        final Rook rook = new Rook();
        final Position start = Position.of("a1");
        final Position validDestination = Position.of("a6");
        assertThat(rook.checkPositionRule(start, validDestination)).isTrue();
    }

    @Test
    @DisplayName("룩이 가려는 경로에 기물이 끼어있다면, 거짓을 반환한다.")
    void check_is_movable_invalid_test() {
        final Rook rook = new Rook();
        final Position start = Position.of("a1");
        final Position dest = Position.of("a6");

        final Pawn pawn = new Pawn(1);
        final Map<Position, Piece> chessBoard = new HashMap<>();
        chessBoard.put(Position.of("a2"), pawn);

        assertThat(rook.isMovable(start, dest, chessBoard)).isFalse();
    }
}
