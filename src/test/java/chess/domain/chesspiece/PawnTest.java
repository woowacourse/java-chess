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
        List<Position> route = piece.getRoute(Position.of("a", "2"), Position.of("a", "4"));
        List<Position> positions = List.of(Position.of("a", "3"));
        assertThat(route).isEqualTo(positions);
    }

    @Test
    @DisplayName("폰은 앞으로 한 칸 이동할 수 있다.")
    void Pawn_Move_forward_once() {
        Piece piece = new Pawn(WHITE);
        List<Position> route = piece.getRoute(Position.of("a", "2"), Position.of("a", "3"));
        List<Position> positions = List.of();
        assertThat(route).isEqualTo(positions);
    }

    @Test
    @DisplayName("폰은 대각선으로 이동할 수 있다.")
    void Pawn_Move_diagonal() {
        Piece piece = new Pawn(WHITE);
        List<Position> route = piece.getRoute(Position.of("a", "2"), Position.of("b", "3"));
        List<Position> positions = List.of();
        assertThat(route).isEqualTo(positions);
    }

    @ParameterizedTest
    @CsvSource(value = {"a, 1, a, 3", "a, 1, b, 3", "b, 1, a, 1", "b, 2, b, 1"})
    @DisplayName("목적지 제외 갈 수 있는 위치들이 아니면 예외를 발생한다.")
    void Pawn_Validate_route(String file1, String rank1, String file2, String rank2) {
        Piece piece = new Pawn(WHITE);
        Position source = Position.of(file1, rank1);
        Position target = Position.of(file2, rank2);
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
