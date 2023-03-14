package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
class SquareTest {

    @Test
    void 체스_칸을_생성한다() {
        Square square = new Square(FileCoordinate.A, new Piece(PieceType.BISHOP, Color.BLACK));

        assertAll(
                () -> assertThat(square.getPiece().getPieceType()).isEqualTo(PieceType.BISHOP),
                () -> assertThat(square.getFileCoordinate()).isEqualTo(FileCoordinate.A)
        );

    }
}
