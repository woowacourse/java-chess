package chess.domain.piece.type;

import chess.domain.piece.Piece;
import chess.domain.piece.PieceColor;
import chess.domain.PieceRelation;
import chess.domain.position.Movement;
import chess.domain.position.PathStatus;
import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class KingTest {

    static Stream<Arguments> canKingMoveAllDirectionOneStepArguments() {
        return Stream.of(Arguments.arguments(Position.of("d4"), Position.of("d3")), Arguments.arguments(Position.of("d4"), Position.of("e4")), Arguments.arguments(Position.of("d4"), Position.of("d5")), Arguments.arguments(Position.of("d4"), Position.of("c4")), Arguments.arguments(Position.of("d4"), Position.of("e5")), Arguments.arguments(Position.of("d4"), Position.of("c5")), Arguments.arguments(Position.of("d4"), Position.of("e3")), Arguments.arguments(Position.of("d4"), Position.of("c3")));
    }

    static Stream<Arguments> cannotKingMoveAllDirectionMoreThanTwoStepArguments() {
        return Stream.of(Arguments.arguments(Position.of("d4"), Position.of("d2")), Arguments.arguments(Position.of("d4"), Position.of("f4")), Arguments.arguments(Position.of("d4"), Position.of("d6")), Arguments.arguments(Position.of("d4"), Position.of("b4")), Arguments.arguments(Position.of("d4"), Position.of("f6")), Arguments.arguments(Position.of("d4"), Position.of("b6")), Arguments.arguments(Position.of("d4"), Position.of("f2")), Arguments.arguments(Position.of("d4"), Position.of("b2")));
    }

    @DisplayName("킹은 모든 방향으로 한 칸 움직일 수 있다.")
    @ParameterizedTest
    @MethodSource("canKingMoveAllDirectionOneStepArguments")
    void canKingMoveAllDirectionOneStep(Position source, Position target) {
        // given
        Piece king = new King(PieceColor.BLACK);
        Movement movement = new Movement(source, target);
        PieceRelation targetStatus = PieceRelation.NONE;
        PathStatus pathStatus = PathStatus.OPEN;

        // when
        boolean result = king.isMovable(movement, targetStatus, pathStatus);

        // then
        assertThat(result).isTrue();
    }

    @DisplayName("킹은 모든 방향으로 두 칸 이상 움직일 수 없다.")
    @ParameterizedTest
    @MethodSource("cannotKingMoveAllDirectionMoreThanTwoStepArguments")
    void cannotKingMoveAllDirectionMoreThanTwoStep(Position source, Position target) {
        // given
        Piece king = new King(PieceColor.BLACK);
        Movement movement = new Movement(source, target);
        PieceRelation targetStatus = PieceRelation.NONE;
        PathStatus pathStatus = PathStatus.OPEN;

        // when
        boolean result = king.isMovable(movement, targetStatus, pathStatus);

        // then
        assertThat(result).isFalse();
    }

    @DisplayName("킹은 Source와 Target 사이에 다른 기물이 존재하면 이동할 수 없다.")
    @Test
    void cannotKingMove() {
        // given
        Piece king = new King(PieceColor.BLACK);
        Movement movement = new Movement(Position.of("d4"), Position.of("d3"));
        PieceRelation targetStatus = PieceRelation.NONE;
        PathStatus pathStatus = PathStatus.BLOCKED;

        // when & then
        assertThat(king.isMovable(movement, targetStatus, pathStatus)).isFalse();
    }
}
