package chess.domain.piece;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class PawnTest {
    @Test
    @DisplayName("폰 생성")
    void constructor() {
        assertThat(new Pawn(Position.from("a2"), Color.WHITE)).isInstanceOf(Pawn.class);
    }

    @Test
    @DisplayName("폰 이동")
    void move() {
        Piece piece = new Pawn(Position.from("a2"), Color.WHITE);
        piece.move(new Blank(Position.from("a3")));
        assertThat(piece.getPosition()).isEqualTo(Position.from("a3"));
    }

    @Test
    @DisplayName("폰 처음 2칸 이동")
    void move_start() {
        Piece piece = new Pawn(Position.from("a2"), Color.WHITE);
        piece.move(new Blank(Position.from("a4")));
        assertThat(piece.getPosition()).isEqualTo(Position.from("a4"));
    }

    @Test
    @DisplayName("폰의 대각선에 적이 있으면 이동")
    void move_enemy() {
        Piece piece = new Pawn(Position.from("a2"), Color.WHITE);
        piece.move(new Pawn(Position.from("b3"), Color.BLACK));
        assertThat(piece.getPosition()).isEqualTo(Position.from("b3"));
    }

    @Test
    @DisplayName("폰 앞에 적이 있으면 예외 발생")
    void move_forward_enemy() {
        Piece piece = new Pawn(Position.from("a2"), Color.WHITE);
        assertThatExceptionOfType(RuntimeException.class).isThrownBy(
            () -> piece.move(new Pawn(Position.from("a3"), Color.BLACK)));
    }

    @ParameterizedTest
    @DisplayName("폰이 이동할 수 없는 위치인 경우 예외 발생")
    @CsvSource(value = {"a2,a5", "a2,b3"})
    void move_invalid_direction(String source, String target) {
        Piece piece = new Pawn(Position.from(source), Color.WHITE);
        assertThatExceptionOfType(RuntimeException.class).isThrownBy(
            () -> piece.move(new Blank(Position.from(target))));
    }
}
