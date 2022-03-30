package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.board.Board;
import chess.domain.board.coordinate.Coordinate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class PawnTest {

    @ParameterizedTest
    @CsvSource(value = {
            "c2,c3",
            "c2,c4",
            "c3,c4"
    })
    @DisplayName("폰은 한칸 또는 두칸 앞으로 이동할 수 있다.")
    void white_pawn_move(String from, String to) {
        Board board = Board.create();
        Pawn pawn = new Pawn(Symbol.PAWN, Team.WHITE);
        boolean actual = pawn.isMovable(board, Coordinate.of(from), Coordinate.of(to));
        assertThat(actual).isTrue();
    }

    @Test
    @DisplayName("흰색팀 폰은 DOWN 방향으로 이동할 수 없다.")
    void white_pawn_not_move() {
        Board board = Board.create();
        Pawn pawn = new Pawn(Symbol.PAWN, Team.WHITE);
        boolean actual = pawn.isMovable(board, Coordinate.of("c6"), Coordinate.of("c5"));
        assertThat(actual).isFalse();
    }

    @Test
    @DisplayName("검정팀 폰은 UP 방향으로 이동할 수 없다.")
    void black_pawn_not_move() {
        Board board = Board.create();
        Pawn pawn = new Pawn(Symbol.PAWN, Team.BLACK);
        boolean actual = pawn.isMovable(board, Coordinate.of("c4"), Coordinate.of("c5"));
        assertThat(actual).isFalse();
    }

    @Test
    @DisplayName("대각선에 적이 있다면 이동할 수 있다.")
    void pawn_move_diagonal() {
        Board board = Board.create();
        Pawn pawn = new Pawn(Symbol.PAWN, Team.WHITE);

        boolean actual = pawn.isMovable(board, Coordinate.of("c6"), Coordinate.of("d7"));

        assertThat(actual).isTrue();
    }
}
