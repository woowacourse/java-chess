package chess.domain.chesspiece;

import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static chess.domain.chesspiece.Team.BLACK;
import static chess.domain.chesspiece.Team.WHITE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BishopTest {
    @Test
    @DisplayName("목적지 제외 갈 수 있는 위치들을 반환한다.")
    void Bishop_Check_route() {
        Piece piece = new Bishop(WHITE);
        List<Position> route = piece.getRoute(Position.of("a1"), Position.of("e5"));
        List<Position> positions = List.of(Position.of("b2"), Position.of("c3"),
                Position.of("d4"));
        assertThat(route).isEqualTo(positions);
    }

    @ParameterizedTest
    @CsvSource(value = {"a1, a3", "b1, a1", "b2, h7"})
    @DisplayName("목적지 제외 갈 수 있는 위치들이 아니면 예외를 발생한다.")
    void Bishop_Validate_route(Position source, Position target) {
        Piece piece = new Bishop(WHITE);
        assertThatThrownBy(() -> piece.getRoute(source, target))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이동할 수 없습니다.");
    }

    @Test
    @DisplayName("같은 팀인지 확인한다.")
    void Bishop_Validate_team() {
        Piece piece = new Bishop(WHITE);
        assertThat(piece.isTeam(new King(WHITE))).isTrue();
        assertThat(piece.isTeam(new King(BLACK))).isFalse();
    }

}
