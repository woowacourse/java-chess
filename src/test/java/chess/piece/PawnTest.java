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

class PawnTest {

    @ParameterizedTest
    @CsvSource(value = {"THREE:true", "FOUR:true", "FIVE:false"}, delimiter = ':')
    @DisplayName("pawn은 처음에 한 번 혹은 두 번 하는 것이 가능하다, 그리고 세번 이동하는 것은 불가능 하다")
    void pawn_when_first_moving_can_go_one_or_two_point_moving(Rank rank, boolean expected) {
        Pawn pawn = new Pawn(PieceColor.WHITE);
        Position source = new Position(TWO, A);
        Position target = new Position(rank, A);
        boolean actual = pawn.isMovable(new Path(source, target), MoveType.EMPTY);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("폰은 처음 초기 위치가 아니라면 길이 2만큼 이동하는 것이 불가하다")
    void pawn_first_move_then_cant_move_as_two_point_moving() {
        Pawn pawn = new Pawn(PieceColor.WHITE);
        Position source = new Position(FOUR, A);
        Position target = new Position(SIX, A);
        boolean actual = pawn.isMovable(new Path(source, target), MoveType.EMPTY);
        assertThat(actual).isFalse();
    }

    @Test
    @DisplayName("폰은 뒤로 이동하는 것이 불가하다(흰색)")
    void pawn_cant_move_backward_white() {
        Pawn pawn = new Pawn(PieceColor.WHITE);
        Position source = new Position(FOUR, A);
        Position target = new Position(THREE, A);
        boolean actual = pawn.isMovable(new Path(source, target), MoveType.EMPTY);

        assertThat(actual).isFalse();
    }

    @Test
    @DisplayName("폰은 뒤로 이동하는 것이 불가하다(흑색)")
    void pawn_cant_move_backward_black() {
        Pawn pawn = new Pawn(PieceColor.BLACK);
        Position source = new Position(FIVE, A);
        Position target = new Position(SIX, A);
        boolean actual = pawn.isMovable(new Path(source, target), MoveType.EMPTY);

        assertThat(actual).isFalse();
    }

    @ParameterizedTest
    @CsvSource(value = {"B:true", "A:false"}, delimiter = ':')
    @DisplayName("폰은 대각선으로만 공격할 수 있다")
    void when_pawn_can_attack_diagonal(File file, boolean expected) {
        Pawn pawn = new Pawn(PieceColor.WHITE);

        Position source = new Position(FOUR, A);
        Position target = new Position(FIVE, file);

        boolean actual = pawn.isMovable(new Path(source, target), MoveType.ENEMY);
        assertThat(actual).isEqualTo(expected);
    }
}
