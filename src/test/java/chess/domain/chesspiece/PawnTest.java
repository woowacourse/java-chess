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

class PawnTest {
    @Test
    @DisplayName("폰은 시작 지점에 있을 때 앞으로 2칸 이동할 수 있다.")
    void Pawn_Move_forward_twice_on_start_position() {
        Piece piece = new Pawn(WHITE);
        List<Position> route = piece.getRoute(Position.from("a2"), Position.from("a4"));
        List<Position> positions = List.of(Position.from("a3"));
        assertThat(route).isEqualTo(positions);
    }

    @Test
    @DisplayName("폰은 앞으로 한 칸 이동할 수 있다.")
    void Pawn_Move_forward_once() {
        Piece piece = new Pawn(WHITE);
        List<Position> route = piece.getRoute(Position.from("a2"), Position.from("a3"));
        List<Position> positions = List.of();
        assertThat(route).isEqualTo(positions);
    }

    @Test
    @DisplayName("폰은 대각선으로 이동할 수 있다.")
    void Pawn_Move_diagonal() {
        Piece piece = new Pawn(WHITE);
        List<Position> route = piece.getRoute(Position.from("a2"), Position.from("b3"));
        List<Position> positions = List.of();
        assertThat(route).isEqualTo(positions);
    }

    @ParameterizedTest
    @CsvSource(value = {"a1, a3", "a1, b3", "b1, a1", "b2, b1"})
    @DisplayName("목적지 제외 갈 수 있는 위치들이 아니면 예외를 발생한다.")
    void Pawn_Validate_route(Position source,Position target) {
        Piece piece = new Pawn(WHITE);
        assertThatThrownBy(() -> {
            piece.getRoute(source, target);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("같은 팀인지 확인한다.")
    void Pawn_Validate_team() {
        Piece piece=new Pawn(WHITE);
        assertThat(piece.isTeam(new King(WHITE))).isTrue();
        assertThat(piece.isTeam(new King(BLACK))).isFalse();
    }

    @Test
    @DisplayName("자신이 폰인지 확인한다.")
    void Pawn_Check_pawn() {
        Piece piece = new Pawn(WHITE);
        assertThat(piece.isPawn()).isTrue();
    }
}
