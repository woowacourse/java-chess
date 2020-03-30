package chess.piece.movestrategy;

import chess.coordinate.Vector;
import chess.piece.MovedPawn;
import chess.piece.Piece;
import chess.piece.Pieces;
import chess.piece.Team;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class PawnMoveStrategyGroupTest {

    @DisplayName("직선방향에 아무도 없거나, 대각선 방향에 적이 있으면 움직일 수 있다. 직선도 대각선도 아니면 못움직인다.")
    @ParameterizedTest
    @CsvSource(value = {
            "1,1,BLACK_MOVED_PAWN,true",
            "1,1,BLANK,false",
            "0,1,BLACK_MOVED_PAWN,false",
            "0,1,BLANK,true",
            "1,2,BLANK,false"})
    void movable(int file, int rank, Pieces pieces, boolean expect) {
        //given
        Piece piece = Pieces.findBy(MovedPawn.class, Team.WHITE);

        Vector vector = new Vector(file, rank);
        Piece targetPiece = pieces.getPiece();

        //when
        boolean actual = piece.canMove(vector, targetPiece);

        //then
        assertThat(actual).isEqualTo(expect);
    }
}