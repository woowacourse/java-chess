package model.piece.role;

import model.direction.Direction;
import model.direction.WayPoints;
import model.piece.Color;
import model.piece.Piece;
import model.position.File;
import model.position.Position;
import model.position.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

class RoleTest {

    @DisplayName("해당 Role이 Square인지 알 수 있다")
    @ParameterizedTest
    @EnumSource(Color.class)
    void isOccupied(Color color) {
        assertAll(
                () -> assertThat(new Pawn(color).isOccupied()).isTrue(),
                () -> assertThat(new Square().isOccupied()).isFalse()
        );
    }

    @DisplayName("같은 진영의 기물이 목적 지점에 위치한 경우 예외가 발생한다.")
    @ParameterizedTest
    @EnumSource(Color.class)
    void validateMoveTo(Color color) {
        WayPoints wayPoints = new WayPoints(Direction.N, Map.of());
        assertThatThrownBy(() -> new Pawn(color).moveTo(wayPoints, new Bishop(color)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("같은 진영의 기물이 목적 지점에 위치합니다.");

    }

    @DisplayName("특정 기물이 이동할 때 목적 지점까지의 경로에 기물이 위치하여 이동할 수 없는 경우 예외가 발생한다.")
    @Test
    void validateNotExistWayPoints() {
        WayPoints wayPoints = new WayPoints(Direction.N, Map.of(Position.of(File.E, Rank.FOUR), new Piece(new Bishop(Color.WHITE))));
        assertThatThrownBy(() -> new Rook(Color.BLACK).moveTo(wayPoints, new Bishop(Color.WHITE)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("목적 지점까지의 경로에 기물이 위치하여 이동할 수 없습니다.");
    }

    @DisplayName("특정 기물이 이동할 수 있는 반경 외의 목적 지점이 주어진 경우 예외가 발생한다.")
    @Test
    void findRoute() {
        assertThatThrownBy(() -> new Pawn(Color.BLACK).findRoute(Position.of(File.A, Rank.TWO), Position.of(File.E, Rank.FIVE)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 기물이 이동할 수 없는 좌표입니다");

    }
}
