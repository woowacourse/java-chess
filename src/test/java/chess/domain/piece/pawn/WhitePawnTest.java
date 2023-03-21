package chess.domain.piece.pawn;

import chess.domain.exception.WrongDirectionException;
import chess.domain.square.Direction;
import chess.domain.square.File;
import chess.domain.square.Rank;
import chess.domain.square.Square;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class WhitePawnTest {

    private WhitePawn pawn;

    @BeforeEach
    void setup() {
        pawn = new WhitePawn();
    }

    @Test
    @DisplayName("화이트 더블 폰 푸시 성공")
    void white_double_pawn_push_success() {
        Square current = Square.of(File.A, Rank.TWO);
        Square destination = Square.of(File.A, Rank.FOUR);

        assertThat(pawn.findDirection(current, destination)).isEqualTo(Direction.UP);
    }

    @Test
    @DisplayName("Rank가 2가 아니면 화이트 더블 폰 푸시 실패")
    void white_double_pawn_push_fail() {
        Square current = Square.of(File.A, Rank.THREE);
        Square destination = Square.of(File.A, Rank.FIVE);

        assertThatThrownBy(() -> pawn.findDirection(current, destination))
                .isInstanceOf(WrongDirectionException.class);
    }
}