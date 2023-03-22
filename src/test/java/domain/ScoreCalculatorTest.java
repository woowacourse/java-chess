package domain;

import static org.assertj.core.api.Assertions.assertThat;

import domain.piece.Pawn;
import domain.piece.Piece;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ScoreCalculatorTest {

    private final ScoreCalculator scoreCalculator = new ScoreCalculator();

    @Test
    @DisplayName("흰색 돌 점수 계산한다 - 같은 편 Pawn이 같은 Column에 없는 경우")
    public void testCalculateWhite() {
        //given
        final Map<Location, Piece> board = new HashMap<>();
        for (int column = 1; column <= 8; column++) {
            board.put(Location.of(column, 2), Pawn.makeWhite());
        }

        //when
        double result = scoreCalculator.calculateWhite(board);

        //then
        assertThat(result).isEqualTo(8D);
    }

    @Test
    @DisplayName("흰색 돌 점수 계산한다 - 같은 편 Pawn이 같은 Column에 있는 경우")
    public void testCalculateWhiteWhenPawnInSameColumn() {
        //given
        final Map<Location, Piece> board = new HashMap<>();
        for (int row = 1; row <= 8; row++) {
            board.put(Location.of(2, row), Pawn.makeWhite());
        }

        //when
        double result = scoreCalculator.calculateWhite(board);

        //then
        assertThat(result).isEqualTo(4D);
    }

    @Test
    @DisplayName("검은색 돌 점수 계산한다 - 같은 편 Pawn이 같은 Column에 없는 경우")
    public void testCalculateBlack() {
        //given
        final Map<Location, Piece> board = new HashMap<>();
        for (int column = 1; column <= 8; column++) {
            board.put(Location.of(column, 2), Pawn.makeBlack());
        }

        //when
        double result = scoreCalculator.calculateBlack(board);

        //then
        assertThat(result).isEqualTo(8D);
    }

    @Test
    @DisplayName("검은색 돌 점수 계산한다 - 같은 편 Pawn이 같은 Column에 있는 경우")
    public void testCalculateBlackWhenPawnInSameColumn() {
        //given
        final Map<Location, Piece> board = new HashMap<>();
        for (int row = 1; row <= 8; row++) {
            board.put(Location.of(2, row), Pawn.makeBlack());
        }

        //when
        double result = scoreCalculator.calculateBlack(board);

        //then
        assertThat(result).isEqualTo(4D);
    }
}
