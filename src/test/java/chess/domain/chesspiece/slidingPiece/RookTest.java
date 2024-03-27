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

class RookTest {
    @Test
    @DisplayName("목적지 제외 세로로 갈 수 있는 위치들을 반환한다.")
    void Rook_Check_vertical_route() {
        Piece piece = new Rook(WHITE);
        List<Position> route = piece.getMovingRoute(Position.of("a", "1"), Position.of("e", "1"));
        List<Position> positions = List.of(Position.of("b", "1"), Position.of("c", "1"),
                Position.of("d", "1"));
        assertThat(route).isEqualTo(positions);
    }

    @Test
    @DisplayName("목적지 제외 가로로 갈 수 있는 위치들을 반환한다.")
    void Rook_Check_horizontal_route() {
        Piece piece = new Rook(WHITE);
        List<Position> route = piece.getMovingRoute(Position.of("a", "1"), Position.of("a", "5"));
        List<Position> positions = List.of(Position.of("a", "2"), Position.of("a", "3"),
                Position.of("a", "4"));
        assertThat(route).isEqualTo(positions);
    }

    @Test
    @DisplayName("목적지 제외 갈 수 있는 위치들이 아니면 예외를 발생한다.")
    void Rook_Validate_route() {
        Piece piece = new Rook(WHITE);
        assertThatThrownBy(() -> {
            piece.getMovingRoute(Position.of("a", "1"), Position.of("c", "4"));
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("같은 팀인지 확인한다.")
    void Rook_Validate_team() {
        Piece piece = new Rook(WHITE);
        assertThat(piece.isTeam(new King(WHITE))).isTrue();
        assertThat(piece.isTeam(new King(BLACK))).isFalse();
    }

    @Test
    @DisplayName("자신이 폰인지 확인한다.")
    void Rook_Check_pawn() {
        Piece piece = new Rook(WHITE);
        assertThat(piece.isPawn()).isFalse();
    }
}
