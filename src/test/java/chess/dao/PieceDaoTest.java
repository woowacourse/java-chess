package chess.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import chess.domain.piece.Piece;
import chess.dto.PieceDto;
import chess.dto.GameDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@DisplayName("PieceDao 는")
class PieceDaoTest {

    private final PieceDao pieceDao = new PieceDao();
    private final GameDao gameDao = new GameDao();

    @Test
    void id로_생성된_기물을_조회할_수_있다() {
        final int gameId = gameDao.create(GameDto.create());
        final int id = pieceDao.create(PieceDto.of(gameId, "Pawn", "a", 2, "White"));
        final PieceDto pieceDto = PieceDto.from(id);

        final Piece piece = pieceDao.findPieceById(pieceDto);
        assertThat(piece.isPawn()).isTrue();
        pieceDao.delete(pieceDto);
        gameDao.delete(id);
    }

    @Test
    void 생성된_모든_기물_id를_조회할_수_있다() {
        assertDoesNotThrow(pieceDao::findAllIds);
    }
}
