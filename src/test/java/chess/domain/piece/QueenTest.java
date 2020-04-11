package chess.domain.piece;

import chess.domain.coordinate.Vector;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class QueenTest {

    @DisplayName("퀸은 직선 대각선으로 무한정 움직일수 있다.")
    @ParameterizedTest
    @CsvSource(value = {"0,3,true", "3,-3,true", "3,-2,false"})
    void canMove(int file, int rank, boolean expect) {
        //given
        Piece piece = new Queen(Team.BLACK);

        //when
        boolean actual = piece.canMove(new Vector(file, rank), new Blank());

        //then
        assertThat(actual).isEqualTo(expect);
    }
}