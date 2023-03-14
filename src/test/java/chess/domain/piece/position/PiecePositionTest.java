package chess.domain.piece.position;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@DisplayName("PiecePosition 은")
class PiecePositionTest {

    @Test
    void Rank_와_File_을_받아_생성된다() {
        // when & then
        assertDoesNotThrow(() -> PiecePosition.of(1, 'a'));
    }
}
