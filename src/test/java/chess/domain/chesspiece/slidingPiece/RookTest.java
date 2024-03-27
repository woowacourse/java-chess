package chess.domain.chesspiece.slidingPiece;

import static chess.domain.chesspiece.Team.BLACK;
import static chess.domain.chesspiece.Team.WHITE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.chesspiece.Empty;
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
        List<Position> route = piece.findRoute(new Position("a", "1"), new Position("e", "1"), new Empty());
        List<Position> positions = List.of(new Position("b", "1"), new Position("c", "1"),
                new Position("d", "1"));
        assertThat(route).isEqualTo(positions);
    }

    @Test
    @DisplayName("목적지 제외 가로로 갈 수 있는 위치들을 반환한다.")
    void Rook_Check_horizontal_route() {
        Piece piece = new Rook(WHITE);
        List<Position> route = piece.findRoute(new Position("a", "1"), new Position("a", "5"), new Empty());
        List<Position> positions = List.of(new Position("a", "2"), new Position("a", "3"),
                new Position("a", "4"));
        assertThat(route).isEqualTo(positions);
    }

    @Test
    @DisplayName("목적지 제외 갈 수 있는 위치들이 아니면 예외를 발생한다.")
    void Rook_Validate_route() {
        Piece piece = new Rook(WHITE);
        assertThatThrownBy(() -> {
            piece.findRoute(new Position("a", "1"), new Position("c", "4"), new Empty());
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
