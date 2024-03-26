package chess.db;

import static org.assertj.core.api.Assertions.assertThat;

import chess.dto.PieceDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PiecesDaoTest {
    private PiecesDao piecesDao;

    @BeforeEach
    void setUp() {
        piecesDao = DBFixtures.createPiecesDao();
    }

    @AfterEach
    void tearDown() {
        piecesDao.deleteAll();
    }

    @Test
    @DisplayName("피스를 저장한다.")
    void createPieces() {
        PieceDto pieceDto = new PieceDto(1, 1, "WHITE_ROOK");

        piecesDao.create(pieceDto);

        assertThat(piecesDao.findAll()).containsExactly(pieceDto);
    }

    @Test
    @DisplayName("저장된 피스를 모두 가져온다.")
    void findAllPieces() {
        PieceDto pieceDto1 = new PieceDto(1, 1, "WHITE_ROOK");
        PieceDto pieceDto2 = new PieceDto(2, 1, "WHITE_KNIGHT");
        PieceDto pieceDto3 = new PieceDto(3, 1, "WHITE_BISHOP");
        piecesDao.create(pieceDto1);
        piecesDao.create(pieceDto2);
        piecesDao.create(pieceDto3);

        assertThat(piecesDao.findAll()).containsExactly(pieceDto1, pieceDto2, pieceDto3);
    }

    @Test
    @DisplayName("저장된 피스를 모두 삭제한다.")
    void deleteAllPieces() {
        PieceDto pieceDto1 = new PieceDto(1, 1, "WHITE_ROOK");
        PieceDto pieceDto2 = new PieceDto(2, 1, "WHITE_KNIGHT");
        PieceDto pieceDto3 = new PieceDto(3, 1, "WHITE_BISHOP");
        piecesDao.create(pieceDto1);
        piecesDao.create(pieceDto2);
        piecesDao.create(pieceDto3);

        piecesDao.deleteAll();

        assertThat(piecesDao.findAll()).isEmpty();
    }
}
