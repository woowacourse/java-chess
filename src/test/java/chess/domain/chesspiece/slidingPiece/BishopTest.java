package chess.domain.chesspiece.slidingPiece;

import static chess.domain.chesspiece.Team.BLACK;
import static chess.domain.chesspiece.Team.WHITE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.chesspiece.Piece;
import chess.domain.position.Position;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class BishopTest {
    @Test
    @DisplayName("목적지 제외 갈 수 있는 위치들을 반환한다.")
    void Bishop_Check_route() {
        Piece piece = new Bishop(WHITE);
        List<Position> route = piece.getMovingRoute(Position.of("a", "1"), Position.of("e", "5"));
        List<Position> positions = List.of(Position.of("b", "2"), Position.of("c", "3"),
                Position.of("d", "4"));
        assertThat(route).isEqualTo(positions);
    }

    @ParameterizedTest
    @CsvSource(value = {"a, 1, a, 3", "b, 1, a, 1", "b, 2, h, 7"})
    @DisplayName("목적지 제외 갈 수 있는 위치들이 아니면 예외를 발생한다.")
    void Bishop_Validate_route(String file1,
                               String rank1,
                               String file2,
                               String rank2) {
        Position source = Position.of(file1, rank1);
        Position target = Position.of(file2, rank2);
        Piece piece = new Bishop(WHITE);
        assertThatThrownBy(() -> {
            piece.getMovingRoute(source, target);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("같은 팀인지 확인한다.")
    void Bishop_Validate_team() {
        Piece piece = new Bishop(WHITE);
        assertThat(piece.isTeam(new King(WHITE))).isTrue();
        assertThat(piece.isTeam(new King(BLACK))).isFalse();
    }

    @Test
    @DisplayName("자신이 폰인지 확인한다.")
    void Bishop_Check_pawn() {
        Piece piece = new Bishop(WHITE);
        assertThat(piece.isPawn()).isFalse();
    }
}
