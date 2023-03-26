package chess.view;

import chess.domain.piece.Pawn;
import chess.domain.piece.PieceType;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayNameGeneration(ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
class PieceMapperTest {

    @Test
    void 체스_말에_대한_메세지를_반환한다() {
        PieceMapper pieceMapper = PieceMapper.of(PieceType.PAWN);

        assertThat(pieceMapper).isEqualTo(PieceMapper.PAWN);
    }
}
