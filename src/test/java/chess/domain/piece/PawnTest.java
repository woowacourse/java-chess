package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.Movement;
import chess.domain.Position;
import chess.domain.piece.character.Character;
import chess.domain.piece.character.Kind;
import chess.domain.piece.character.Team;
import chess.exception.ImpossibleMoveException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

class PawnTest {
    @DisplayName("자신의 특징을 반환한다.")
    @ParameterizedTest
    @EnumSource
    void findCharacter(Team team) {
        assertThat(new Pawn(team).character())
                .isEqualTo(new Character(team, Kind.PAWN));
    }

    @DisplayName("흰색 폰은 시작 지점에 있는 경우, 2칸 초과시 예외가 발생한다.")
    @Test
    void startWhitePawnMoveOverTwo() {
        assertThatThrownBy(() -> new Pawn(Team.WHITE)
                .findBetweenPositions(new Movement(
                        Position.of(2, 1),
                        Position.of(5, 1))))
                .isInstanceOf(ImpossibleMoveException.class)
                .hasMessage("해당 위치로 움직일 수 없습니다.");
    }

    @DisplayName("검은색 폰은 시작 지점에 있는 경우, 2칸 초과시 예외가 발생한다.")
    @Test
    void startBlackPawnMoveOverTwo() {
        assertThatThrownBy(() -> new Pawn(Team.BLACK)
                .findBetweenPositions(new Movement(
                        Position.of(7, 1),
                        Position.of(4, 1))))
                .isInstanceOf(ImpossibleMoveException.class)
                .hasMessage("해당 위치로 움직일 수 없습니다.");
    }

    @DisplayName("흰색 폰은 1칸 초과시 예외가 발생한다.")
    @Test
    void whitePawnMoveOverTwo() {
        assertThatThrownBy(() -> new Pawn(Team.WHITE).move()
                .findBetweenPositions(new Movement(
                        Position.of(3, 1),
                        Position.of(5, 1))))
                .isInstanceOf(ImpossibleMoveException.class)
                .hasMessage("해당 위치로 움직일 수 없습니다.");
    }

    @DisplayName("검은색 폰은 1칸 초과시 예외가 발생한다.")
    @Test
    void blackPawnMoveOverTwo() {
        assertThatThrownBy(() -> new Pawn(Team.BLACK).move()
                .findBetweenPositions(new Movement(
                        Position.of(6, 1),
                        Position.of(4, 1))))
                .isInstanceOf(ImpossibleMoveException.class)
                .hasMessage("해당 위치로 움직일 수 없습니다.");
    }

    @DisplayName("흰색 폰은 옆으로 이동할 수 없다.")
    @ParameterizedTest
    @EnumSource
    void whitePawnMoveColumn(Team team) {
        assertThatThrownBy(() -> new Pawn(team)
                .findBetweenPositions(new Movement(
                        Position.of(7, 1),
                        Position.of(7, 2))))
                .isInstanceOf(ImpossibleMoveException.class)
                .hasMessage("해당 위치로 움직일 수 없습니다.");
    }

    @DisplayName("두 위치 사이의 폰이 갈 수 있는 위치들을 반환한다.")
    @Test
    void betweenPosition() {
        assertThat(new Pawn(Team.WHITE)
                .findBetweenPositions(new Movement(
                        Position.of(2, 3),
                        Position.of(4, 3))))
                .containsExactly(Position.of(3, 3));
    }

    @DisplayName("두 위치 사이의 폰이 갈 수 있는 위치들을 반환한다.")
    @Test
    void betweenPositionHasMoved() {
        assertThat(new Pawn(Team.WHITE).move()
                .findBetweenPositions(new Movement(
                        Position.of(3, 3),
                        Position.of(4, 3))))
                .isEmpty();
    }

    @DisplayName("두 위치 사이의 폰이 갈 수 있는 위치들을 반환한다.")
    @Test
    void betweenPositionOneWhenHasNotMoved() {
        assertThat(new Pawn(Team.WHITE)
                .findBetweenPositions(new Movement(
                        Position.of(2, 3),
                        Position.of(3, 3))))
                .isEmpty();
    }

    @DisplayName("공격 가능할 때, 대각으로 움직일 수 있다.")
    @Test
    void movableDiagonalWhenAttack() {
        assertThatCode(() -> new Pawn(Team.WHITE)
                .findBetweenPositionsWhenAttack(new Movement(
                        Position.of(2, 2),
                        Position.of(3, 3))))
                .doesNotThrowAnyException();
    }

    @DisplayName("공격 가능할 때, 두 위치 사이의 폰이 갈 수 있는 위치는 없다.")
    @Test
    void noneBetweenPositionWhenAttack() {
        assertThat(new Pawn(Team.WHITE)
                .findBetweenPositionsWhenAttack(new Movement(
                        Position.of(2, 2),
                        Position.of(3, 3))))
                .isEmpty();
    }

    @DisplayName("공격 가능할 때, 직선으로 움직이면 예외가 발생한다.")
    @Test
    void cannotMoveStraightWhenAttack() {
        assertThatThrownBy(() -> new Pawn(Team.WHITE)
                .findBetweenPositionsWhenAttack(new Movement(
                        Position.of(2, 2),
                        Position.of(3, 2))))
                .isInstanceOf(ImpossibleMoveException.class)
                .hasMessage("해당 위치로 움직일 수 없습니다.");
    }
}
