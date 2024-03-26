package chess.domain.chesspiece;

import chess.domain.chesspiece.slidingPiece.King;
import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static chess.domain.chesspiece.Team.BLACK;
import static chess.domain.chesspiece.Team.WHITE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class KnightTest {
    @Test
    @DisplayName("목적지 제외 갈 수 있는 위치들을 반환한다.")
    void Knight_Check_route() {
        Piece piece = new Knight(WHITE);
        List<Position> route = piece.getRoute(Position.of("a", "1"), Position.of("b", "3"));
        List<Position> positions = List.of();
        assertThat(route).isEqualTo(positions);
    }

    @Test
    @DisplayName("목적지 제외 갈 수 있는 위치들이 아니면 예외를 발생한다.")
    void Knight_Validate_route() {
        Piece piece = new Knight(WHITE);
        assertThatThrownBy(() -> {
            piece.getRoute(Position.of("a", "1"), Position.of("b", "4"));
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("같은 팀인지 확인한다.")
    void Knight_Validate_team() {
        Piece piece=new Knight(WHITE);
        assertThat(piece.isTeam(new King(WHITE))).isTrue();
        assertThat(piece.isTeam(new King(BLACK))).isFalse();
    }

    @Test
    @DisplayName("자신이 폰인지 확인한다.")
    void Knight_Check_pawn() {
        Piece piece = new Knight(WHITE);
        assertThat(piece.isPawn()).isFalse();
    }
}
