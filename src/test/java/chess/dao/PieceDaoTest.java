package chess.dao;

import static org.junit.jupiter.api.Assertions.*;

import chess.dto.PieceDto;
import chess.dto.RunningGameDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@DisplayName("PieceDao 는")
class PieceDaoTest {

    private final PieceDao pieceDao = new PieceDao();

    @Test
    void 기물을_저장할_수_있다() {
        // given
        new RunningGameDao().create(new RunningGameDto("White"));
        final PieceDto pieceDto = new PieceDto(1, "Pawn", "a", 2, "White");

        // when & then
        assertDoesNotThrow(() -> pieceDao.create(pieceDto));
    }
}
