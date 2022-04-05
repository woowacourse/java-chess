package chess.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.web.dto.PieceDto;
import chess.web.dto.PieceType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PieceDaoTest {

    private PieceDao pieceDao;

    @BeforeEach
    void setUp() {
        pieceDao = new MemoryPieceDao();
    }

    @DisplayName("기물을 저장한다.")
    @Test
    void 기물을_저장한다() {
        PieceDto pieceDto = new PieceDto("a2", PieceType.PAWN_BLACK);

        pieceDao.save(pieceDto);

        assertThat(pieceDao.findAll().size()).isEqualTo(1);
    }

    @DisplayName("기물은 존재하는 좌표에 중복 저장될 수 없다.")
    @Test
    void 기물은_존재하는_좌표에_저장될_수_없다() {
        PieceDto pieceDto1 = new PieceDto("a2", PieceType.PAWN_BLACK);
        PieceDto pieceDto2 = new PieceDto("a2", PieceType.PAWN_BLACK);

        pieceDao.save(pieceDto1);

        assertThatThrownBy(() -> pieceDao.save(pieceDto2))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("모든 기물을 조회한다.")
    @Test
    void 기물을_전체_조회한다() {
        PieceDto pieceDto1 = new PieceDto("a2", PieceType.PAWN_BLACK);
        PieceDto pieceDto2 = new PieceDto("b2", PieceType.PAWN_BLACK);

        pieceDao.save(pieceDto1);
        pieceDao.save(pieceDto2);

        assertThat(pieceDao.findAll().size()).isEqualTo(2);
    }
}
