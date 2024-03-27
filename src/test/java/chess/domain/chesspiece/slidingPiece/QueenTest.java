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

class QueenTest {
    @Test
    @DisplayName("여왕은 앞뒤로 움직일 수 있다.")
    void Queen_Move_forward_and_backward() {
        Piece piece = new Queen(WHITE);
        List<Position> route = piece.getMovingRoute(new Position("a", "1"), new Position("a", "4"));
        List<Position> positions = List.of(new Position("a", "2"), new Position("a", "3"));
        assertThat(route).isEqualTo(positions);
    }

    @Test
    @DisplayName("여왕은 좌우로 움직일 수 있다.")
    void Queen_Move_side() {
        Piece piece = new Queen(WHITE);
        List<Position> route = piece.getMovingRoute(new Position("b", "4"), new Position("e", "4"));
        List<Position> positions = List.of(new Position("c", "4"), new Position("d", "4"));
        assertThat(route).isEqualTo(positions);
    }

    @Test
    @DisplayName("여왕은 대각선으로 움직일 수 있다.")
    void Queen_Move_diagonal() {
        Piece piece = new Queen(WHITE);
        List<Position> route = piece.getMovingRoute(new Position("b", "2"), new Position("e", "5"));
        List<Position> positions = List.of(new Position("c", "3"), new Position("d", "4"));
        assertThat(route).isEqualTo(positions);
    }

    @Test
    @DisplayName("목적지 제외 갈 수 있는 위치들이 아니면 예외를 발생한다.")
    void Bishop_Validate_route() {
        Piece piece = new Queen(WHITE);
        assertThatThrownBy(() -> {
            piece.getMovingRoute(new Position("a", "1"), new Position("b", "4'"));
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("같은 팀인지 확인한다.")
    void Queen_Validate_team() {
        Piece piece = new Queen(WHITE);
        assertThat(piece.isTeam(new King(WHITE))).isTrue();
        assertThat(piece.isTeam(new King(BLACK))).isFalse();
    }

    @Test
    @DisplayName("자신이 폰인지 확인한다.")
    void Queen_Check_pawn() {
        Piece piece = new Queen(WHITE);
        assertThat(piece.isPawn()).isFalse();
    }
}
