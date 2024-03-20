package domain.piece;

import domain.piece.type.Pawn;
import domain.position.ChessFile;
import domain.position.ChessRank;
import domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PawnTest {

    @DisplayName("폰은 앞으로 한 칸 움직일 수 있다.")
    @Test
    void canMoveOneStep() {
        // given
        Piece pawn = new Pawn(PieceColor.BLACK);
        Position source = new Position(ChessFile.A, ChessRank.SIX);
        Position target = new Position(ChessFile.A, ChessRank.FIVE);

        // when
        boolean result = pawn.isMovable(source, target);

        // then
        assertThat(result).isTrue();
    }

    @DisplayName("폰은 처음(Rank2) 움직일 때, 앞으로 한 칸  또는 두 칸 움직일 수 있다.")
    @Test
    void canMoveTwoStep() {

    }

    @DisplayName("폰은 처음(Rank2) 움직이는 것이 아닐 때, 앞으로 두 칸 움직일 수 없다.")
    @Test
    void cannotMoveTwoStep() {

    }

    @DisplayName("폰은 앞 측 대각으로 한 칸 움직일 수 있다.")
    @Test
    void canMoveDiagonalOneStep() {

    }

}