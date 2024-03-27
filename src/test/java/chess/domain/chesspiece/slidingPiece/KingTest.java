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

class KingTest {
    @Test
    @DisplayName("왕은 앞뒤로 움직일 수 있다.")
    void King_Move_forward_and_backward() {
        Piece piece = new King(WHITE);
        List<Position> route = piece.findRoute(new Position("a", "1"), new Position("a", "2"), new Empty());
        List<Position> positions = List.of();
        assertThat(route).isEqualTo(positions);
    }

    @Test
    @DisplayName("왕은 좌우로 움직일 수 있다.")
    void King_Move_side() {
        Piece piece = new King(WHITE);
        List<Position> route = piece.findRoute(new Position("b", "2"), new Position("a", "2"), new Empty());
        List<Position> positions = List.of();
        assertThat(route).isEqualTo(positions);
    }

    @Test
    @DisplayName("왕은 대각선으로 움직일 수 있다.")
    void King_Move_diagonal() {
        Piece piece = new King(WHITE);
        List<Position> route = piece.findRoute(new Position("b", "2"), new Position("a", "1"), new Empty());
        List<Position> positions = List.of();
        assertThat(route).isEqualTo(positions);
    }

    @Test
    @DisplayName("목적지 제외 갈 수 있는 위치들이 아니면 예외를 발생한다.")
    void King_Validate_route() {
        Piece piece = new King(WHITE);
        assertThatThrownBy(() -> {
            piece.findRoute(new Position("a", "1"), new Position("a", "3"), new Empty());
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("같은 팀인지 확인한다.")
    void King_Validate_team() {
        Piece piece = new King(WHITE);
        assertThat(piece.isTeam(new King(WHITE))).isTrue();
        assertThat(piece.isTeam(new King(BLACK))).isFalse();
    }

    @Test
    @DisplayName("자신이 폰인지 확인한다.")
    void King_Check_pawn() {
        Piece piece = new King(WHITE);
        assertThat(piece.isPawn()).isFalse();
    }
}
