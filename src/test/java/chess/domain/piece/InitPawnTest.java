package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class InitPawnTest {

    @ParameterizedTest
    @CsvSource(value = {"B, 3", "B, 4"})
    @DisplayName("움직이지 않은 화이트 폰은 최대 2만큼 위로 전진할 수 있다.")
    void WhiteInitPawnMoveTest(String file, int rank) {
        // given
        InitPawn whitePawn = new InitPawn(Color.WHITE);
        Position whitePosition = Position.of(File.B, Rank.TWO);
        // when
        boolean movable = whitePawn.isMovable(whitePosition, Position.of(File.from(file), Rank.from(rank)));
        // then
        assertThat(movable).isTrue();
    }

    @ParameterizedTest
    @CsvSource(value = {"B, 5", "B, 6"})
    @DisplayName("움직이지 않은 블랙 폰은 최대 2만큼 아래로 전진할 수 있다.")
    void BlackInitPawnMoveTest(String file, int rank) {
        // given
        InitPawn blackPawn = new InitPawn(Color.BLACK);
        Position blackPosition = Position.of(File.B, Rank.SEVEN);
        // when
        boolean movable = blackPawn.isMovable(blackPosition, Position.of(File.from(file), Rank.from(rank)));
        // then
        assertThat(movable).isTrue();
    }

    @Test
    @DisplayName("이동하지 않은 폰은 InitPawn 이다.")
    void isInitPawnTest() {
        // given
        Pawn whitePawn = new InitPawn(Color.WHITE);
        Pawn blackPawn = new InitPawn(Color.BLACK);
        // when
        boolean iswhiteInitPawn = whitePawn.isInitPawn();
        boolean isblackInitPawn = blackPawn.isInitPawn();
        // then
        assertThat(iswhiteInitPawn).isTrue();
        assertThat(isblackInitPawn).isTrue();
    }
}
