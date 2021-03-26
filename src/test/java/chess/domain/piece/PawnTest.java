package chess.domain.piece;

import chess.domain.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class PawnTest {
    @Test
    @DisplayName("방향을 지정한 폰을 정상 생성한다.")
    void init_direction_fixed_pawn() {
        assertThatCode(() -> new Pawn(1))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("폰이 규칙상 움직일 수 없는 경로라면, 거짓을 반환한다.")
    void when_pawn_cannot_move_according_to_rule_return_false() {
        final Pawn blackPawn = new Pawn(-1);
        final Position start = Position.of("f7");
        final Position dest = Position.of("f4");
        final Map<Position, Piece> chessBoard = new HashMap<>();
        chessBoard.put(start, blackPawn);
        assertThat(blackPawn.isMovable(start, dest, chessBoard)).isFalse();
    }

    @Test
    @DisplayName("폰은 대각선으로 가려면 도착지에 기물이 있음을 체크한다")
    void when_piece_exist_diagonal_pawn_can_move() {
        final Pawn blackPawn = new Pawn(-1);
        final Pawn whitePawn = new Pawn(1);
        final Position start = Position.of("f6");
        final Position dest = Position.of("e5");
        final Map<Position, Piece> chessBoard = new HashMap<>();

        chessBoard.put(start, blackPawn);
        assertThat(blackPawn.isMovable(start, dest, chessBoard)).isFalse();

        chessBoard.put(dest, whitePawn);
        assertThat(blackPawn.isMovable(start, dest, chessBoard)).isTrue();
    }

    @Test
    @DisplayName("폰이 직선으로 갈 때, 직선 경로에 기물이 없다면 움직일 수 있다.")
    void when_piece_dont_exist_straight_pawn_can_move() {
        final Pawn blackPawn = new Pawn(-1);
        final Position start = Position.of("f7");
        final Position dest = Position.of("f5");
        final Map<Position, Piece> chessBoard = new HashMap<>();
        chessBoard.put(start, blackPawn);

        assertThat(blackPawn.isMovable(start, dest, chessBoard)).isTrue();
    }

    @Test
    @DisplayName("폰이 직선으로 갈 때, 직선 경로에 기물이 있다면 움직일 수 없다.")
    void when_piece_exist_straight_pawn_cannot_move() {
        final Pawn blackPawn = new Pawn(-1);
        final Position start = Position.of("f7");
        final Position dest = Position.of("f5");
        final Map<Position, Piece> chessBoard = new HashMap<>();
        chessBoard.put(start, blackPawn);

        final Pawn whitePawn = new Pawn(1);
        chessBoard.put(Position.of("f6"), whitePawn);

        assertThat(blackPawn.isMovable(start, dest, chessBoard)).isFalse();
    }

    @ParameterizedTest
    @CsvSource(value = {"a7,a6", "a7,a5", "a7,b6", "h7,h6", "h7,h5", "h7,g6"})
    @DisplayName("처음으로 이동할 수 있는 좌표가 주어졌을 때, 블랙폰이 움직일 수 있는 칸인지 체크한다.")
    void when_give_position_check_black_pawn_rule(final String start, final String dest) {
        final Pawn blackPawn = new Pawn(-1);
        final Position current = Position.of(start);
        final Position destination = Position.of(dest);
        assertThat(blackPawn.checkPositionRule(current, destination)).isTrue();
    }

    @ParameterizedTest
    @CsvSource(value = {"a2,a3", "a2,a4", "a2,b3", "h2,h3", "h2,h4", "h2,g3"})
    @DisplayName("처음으로 이동할 수 있는 좌표가 주어졌을 때, 화이트폰이 움직일 수 있는 칸인지 체크한다.")
    void when_give_position_check_white_pawn_rule(final String start, final String dest) {
        final Pawn whitePawn = new Pawn(1);
        final Position current = Position.of(start);
        final Position destination = Position.of(dest);
        assertThat(whitePawn.checkPositionRule(current, destination)).isTrue();
    }

    @Test
    @DisplayName("이미 이동한 화이트 폰이 움직일 수 있는 칸인지 체크한다.")
    void when_pawn_already_moved_check_can_move_that_position() {
        final Pawn whitePawn = new Pawn(1);
        whitePawn.moved();
        final Position current = Position.of("a4");
        final Position validDest = Position.of("a5");
        final Position invalidDest = Position.of("a6");

        assertThat(whitePawn.checkPositionRule(current, validDest)).isTrue();
        assertThat(whitePawn.checkPositionRule(current, invalidDest)).isFalse();
    }
}
