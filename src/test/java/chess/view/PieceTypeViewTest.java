package chess.view;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.piece.Pawn;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
class PieceTypeViewTest {

    @Test
    void 체스_말에_대한_메세지를_반환한다() {
        PieceTypeView pieceTypeView = PieceTypeView.of(Pawn.class);

        assertThat(pieceTypeView).isEqualTo(PieceTypeView.PAWN);
    }
}
