package chess.domain.piece;

import chess.domain.coordinate.Direction;
import chess.domain.coordinate.Vector;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class PieceTest {

    @DisplayName("체스말이 움직이는 경로 찾기")
    @ParameterizedTest
    @CsvSource(value = {
            "0,1,UP",
            "0,-1,DOWN",
            "1,0,RIGHT",
            "1,-1,RIGHT_DOWN",
            "1,1,RIGHT_UP",
            "-1,0,LEFT",
            "-1,1,LEFT_UP",
            "-1,-1,LEFT_DOWN",
    })
    void findPath(int file, int rank, Direction expect) {
        Piece king = new King(Team.BLACK);
        assertThat(king.findPath(new Vector(file, rank))).containsExactly(expect);
    }


    @DisplayName("현재 체스말이 빈칸이면 true 아니면 false")
    @ParameterizedTest
    @CsvSource(value = {"BLANK,true", "BLACK_KING,false"})
    void isBlank(Pieces pieces, boolean expect) {
        //given
        Piece piece = pieces.getPiece();

        //when
        boolean actual = piece.isBlank();

        //then
        assertThat(actual).isEqualTo(expect);
    }

}