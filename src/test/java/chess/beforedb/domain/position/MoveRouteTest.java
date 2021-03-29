package chess.beforedb.domain.position;

import static chess.beforedb.domain.position.type.File.A;
import static chess.beforedb.domain.position.type.File.C;
import static chess.beforedb.domain.position.type.Rank.FIVE;
import static chess.beforedb.domain.position.type.Rank.THREE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.db.domain.position.MoveRouteForDB;
import chess.db.domain.position.PositionEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MoveRouteTest {
    @DisplayName("유효한 출발위치와 도착위치")
    @Test
    void validMoveRoute() {
        MoveRouteForDB moveRoute = new MoveRouteForDB("a3", "c5");

        assertThat(moveRoute.getStartPosition()).isEqualTo(new PositionEntity(A, THREE));
        assertThat(moveRoute.getDestination()).isEqualTo(new PositionEntity(C, FIVE));
    }

    @DisplayName("유효하지 않은 출발위치와 도착위치")
    @Test
    void invalidMoveRoute() {
        assertThatThrownBy(() -> new MoveRouteForDB("i3", "c9"))
            .isInstanceOf(IllegalArgumentException.class);
    }
}