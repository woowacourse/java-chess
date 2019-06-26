package model.piece;

import model.Position;
import model.piece.impl.Pawn;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PieceFactoryTest {
    @Test
    @DisplayName("타입과 색깔, 위치를 입력받아 체스말을 만들어준다.")
    void createTest() {
        Piece pawn = PieceFactory.create(Pawn.class, PieceColor.BLACK, Position.of(1, 1));

        assertThat(pawn.getClass()).isEqualTo(Pawn.class);
        assertThat(pawn.getPieceColor()).isEqualTo(PieceColor.BLACK);
        assertThat(pawn.getPosition()).isEqualTo(Position.of(1, 1));
    }
}