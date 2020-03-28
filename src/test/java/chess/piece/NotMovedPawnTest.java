package chess.piece;

import chess.coordinate.Vector;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class NotMovedPawnTest {
    @DisplayName("2칸 움직일 수 있는지 확인")
    @ParameterizedTest
    @CsvSource(value = {
            "0,2,WHITE,BLANK,true",
            "0,2,WHITE,BLACK_KING,false"})
    void canMove(int fileVariation, int rankVariation, Team team, Pieces pieces, boolean expect) {
        Piece piece = new NotMovedPawn(team);

        boolean actual = piece.canMove(new Vector(fileVariation, rankVariation), pieces.getPiece());

        assertThat(actual).isEqualTo(expect);
    }
}