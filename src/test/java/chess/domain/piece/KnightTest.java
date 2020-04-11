package chess.domain.piece;

import chess.domain.coordinate.Vector;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class KnightTest {

    @DisplayName("나이트는 전진 후 같은 방향 대각선으로 한 칸 이동할 수 있다..")
    @ParameterizedTest
    @CsvSource(value = {
            "2,1,true",
            "2,-1,true",
            "1,2,true",
            "1,-2,true",
            "-1,2,true",
            "-1,-2,true",
            "-2,1,true",
            "-2,-1,true",
            "0,2,false"})
    void canMove(int file, int rank, boolean expect) {
        Piece knight = new Knight(Team.BLACK);
        assertThat(knight.canMove(new Vector(file, rank), new Blank())).isEqualTo(expect);
    }
}