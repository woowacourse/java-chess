package chess.domain.movement;

import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import chess.domain.position.TerminalPosition;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class MovmentsTest {
    @DisplayName("출발지와 목적지가 주어지면 이동 경로를 찾는다.")
    @Test
    void findPassPathTest() {
        // given
        TerminalPosition terminalPosition =
                new TerminalPosition(new Position(File.A, Rank.FIRST), new Position(File.A, Rank.THIRD));
        Movements movements = new Movements(Set.of(UnitMovement.UP), Set.of());

        // when & then
        assertThat(movements.findPassPathTaken(terminalPosition, 2))
                .isEqualTo(List.of(new Position(File.A, Rank.SECOND)));
    }

    @DisplayName("출발지와 목적지가 주어지면 공격 경로를 찾는다.")
    @Test
    void findAttackPathTest() {
        // given
        TerminalPosition terminalPosition =
                new TerminalPosition(new Position(File.A, Rank.FIRST), new Position(File.C, Rank.THIRD));
        Movements movements = new Movements(Set.of(), Set.of(UnitMovement.RIGHT_UP));

        // when & then
        assertThat(movements.findAttackPathTaken(terminalPosition, 2))
                .isEqualTo(List.of(new Position(File.B, Rank.SECOND)));
    }

    @DisplayName("출발지에서 목적지를 갈 수 없는 경우 예외를 발생시킨다.")
    @Test
    void canNotArriveTest() {
        // given
        TerminalPosition terminalPosition =
                new TerminalPosition(new Position(File.H, Rank.EIGHTH), new Position(File.D, Rank.THIRD));
        Movements movements = new Movements(Set.of(), Set.of(UnitMovement.RIGHT));

        // when & then
        assertThatThrownBy(() -> movements.findAttackPathTaken(terminalPosition, 8))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("도착 위치는 체스 말이 도달할 수 없는 위치입니다.");
    }

    @DisplayName("출발지에서 목적지로 가는 거리가 부족한 경우 예외를 발생시킨다.")
    @Test
    void shortMovementTest() {
        // given
        TerminalPosition terminalPosition =
                new TerminalPosition(new Position(File.A, Rank.FIRST), new Position(File.A, Rank.THIRD));
        Movements movements = new Movements(Set.of(), Set.of(UnitMovement.UP));

        // when & then
        assertThatThrownBy(() -> movements.findAttackPathTaken(terminalPosition, 1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("도착 위치는 체스 말이 도달할 수 없는 위치입니다.");
    }
}
