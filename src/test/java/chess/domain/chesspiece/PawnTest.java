package chess.domain.chesspiece;

import static chess.domain.chesspiece.Team.BLACK;
import static chess.domain.chesspiece.Team.WHITE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.chesspiece.pawn.BlackPawn;
import chess.domain.chesspiece.pawn.WhitePawn;
import chess.domain.chesspiece.slidingPiece.King;
import chess.domain.position.Position;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class PawnTest {
    @Test
    @DisplayName("폰은 시작 지점에 있을 때 앞으로 2칸 이동할 수 있다.")
    void Pawn_Move_forward_twice_on_start_position() {
        Piece piece = new WhitePawn();
        List<Position> route = piece.findRoute(new Position("a", "2"), new Position("a", "4"), new Empty());
        List<Position> positions = List.of(new Position("a", "3"));
        assertThat(route).isEqualTo(positions);
    }

    @Test
    @DisplayName("폰은 앞으로 한 칸 이동할 수 있다.")
    void Pawn_Move_forward_once() {
        Piece piece = new WhitePawn();
        List<Position> route = piece.findRoute(new Position("a", "2"), new Position("a", "3"), new Empty());
        List<Position> positions = List.of();
        assertThat(route).isEqualTo(positions);
    }

    @Test
    @DisplayName("폰은 공격이 아닐 때는 대각선으로 이동할 수 없다.")
    void Pawn_Can_not_move_diagonal() {
        assertThatThrownBy(() -> {
            Piece piece = new WhitePawn();
            piece.findRoute(new Position("a", "2"), new Position("b", "3"), new Empty());
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("폰은 공격할 때만 대각선으로 이동할 수 있다.")
    void Pawn_Attack_diagonal() {
        Piece piece = new WhitePawn();
        List<Position> route = piece.findRoute(new Position("a", "2"), new Position("b", "3"), new BlackPawn());
        List<Position> positions = List.of();
        assertThat(route).isEqualTo(positions);
    }

    @Test
    @DisplayName("폰은 앞으로는 공격할 수 없다.")
    void Pawn_Can_not_attack_forward() {
        assertThatThrownBy(() -> {
            Piece piece = new WhitePawn();
            piece.findRoute(new Position("a", "2"), new Position("a", "3"), new BlackPawn());
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @CsvSource(value = {"a, 1, a, 3", "a, 1, b, 3", "b, 1, a, 1", "b, 2, b, 1"})
    @DisplayName("목적지 제외 갈 수 있는 위치들이 아니면 예외를 발생한다.")
    void Pawn_Validate_route(String file1, String rank1, String file2, String rank2) {
        Piece piece = new WhitePawn();
        Position source = new Position(file1, rank1);
        Position target = new Position(file2, rank2);
        assertThatThrownBy(() -> {
            piece.findRoute(source, target, new Empty());
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("같은 팀인지 확인한다.")
    void Pawn_Validate_team() {
        Piece piece = new WhitePawn();
        assertThat(piece.isTeam(new King(WHITE))).isTrue();
        assertThat(piece.isTeam(new King(BLACK))).isFalse();
    }

    @Test
    @DisplayName("자신이 폰인지 확인한다.")
    void Pawn_Check_pawn() {
        Piece piece = new WhitePawn();
        assertThat(piece.isPawn()).isTrue();
    }
}
