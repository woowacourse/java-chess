package chess.piece;

import static chess.model.File.*;
import static chess.model.Rank.*;
import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import chess.model.File;
import chess.model.MoveType;
import chess.model.Path;
import chess.model.PieceColor;
import chess.model.Position;
import chess.model.Rank;
import chess.model.piece.Pawn;
import chess.model.piece.Piece;

class PawnTest {

    @ParameterizedTest
    @CsvSource(value = {"THREE:true", "FOUR:true", "FIVE:false"}, delimiter = ':')
    @DisplayName("pawn은 처음에 한 번 혹은 두 번 하는 것이 가능하다, 그리고 세번 이동하는 것은 불가능 하다")
    void pawn_when_first_moving_can_go_one_or_two_point_moving(Rank rank, boolean expected) {
        Piece pawn = Pawn.colorOf(PieceColor.WHITE);
        Position source = Position.of(TWO, A);
        Position target = Position.of(rank, A);
        boolean actual = pawn.isMovable(new Path(source, target), MoveType.EMPTY);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("폰은 처음 초기 위치가 아니라면 길이 2만큼 이동하는 것이 불가하다")
    void pawn_first_move_then_cant_move_as_two_point_moving() {
        Piece pawn = Pawn.colorOf(PieceColor.WHITE);
        Position source = Position.of(FOUR, A);
        Position target = Position.of(SIX, A);
        boolean actual = pawn.isMovable(new Path(source, target), MoveType.EMPTY);
        assertThat(actual).isFalse();
    }

    @Test
    @DisplayName("폰은 뒤로 이동하는 것이 불가하다(흰색)")
    void pawn_cant_move_backward_white() {
        Piece pawn = Pawn.colorOf(PieceColor.WHITE);
        Position source = Position.of(FOUR, A);
        Position target = Position.of(THREE, A);
        boolean actual = pawn.isMovable(new Path(source, target), MoveType.EMPTY);

        assertThat(actual).isFalse();
    }

    @Test
    @DisplayName("폰은 뒤로 이동하는 것이 불가하다(흑색)")
    void pawn_cant_move_backward_black() {
        Piece pawn = Pawn.colorOf(PieceColor.BLACK);
        Position source = Position.of(FIVE, A);
        Position target = Position.of(SIX, A);
        boolean actual = pawn.isMovable(new Path(source, target), MoveType.EMPTY);

        assertThat(actual).isFalse();
    }

    @ParameterizedTest
    @CsvSource(value = {"B:true", "A:false"}, delimiter = ':')
    @DisplayName("폰은 대각선으로만 공격할 수 있다(백색)")
    void when_pawn_can_attack_diagonal_white(File file, boolean expected) {
        Piece pawn = Pawn.colorOf(PieceColor.WHITE);

        Position source = Position.of(FOUR, A);
        Position target = Position.of(FIVE, file);

        boolean actual = pawn.isMovable(new Path(source, target), MoveType.ENEMY);
        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(value = {"B:true", "A:false"}, delimiter = ':')
    @DisplayName("폰은 대각선으로만 공격할 수 있다(흑색)")
    void when_pawn_can_attack_diagonal_black(File file, boolean expected) {
        Piece pawn = Pawn.colorOf(PieceColor.BLACK);

        Position source = Position.of(FIVE, A);
        Position target = Position.of(FOUR, file);

        boolean actual = pawn.isMovable(new Path(source, target), MoveType.ENEMY);
        assertThat(actual).isEqualTo(expected);
    }
}
