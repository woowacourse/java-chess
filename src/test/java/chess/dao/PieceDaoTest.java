package chess.dao;

import static chess.web.dto.PieceType.BISHOP_BLACK;
import static chess.web.dto.PieceType.BISHOP_WHITE;
import static chess.web.dto.PieceType.KING_BLACK;
import static chess.web.dto.PieceType.KING_WHITE;
import static chess.web.dto.PieceType.KNIGHT_BLACK;
import static chess.web.dto.PieceType.KNIGHT_WHITE;
import static chess.web.dto.PieceType.PAWN_BLACK;
import static chess.web.dto.PieceType.PAWN_WHITE;
import static chess.web.dto.PieceType.QUEEN_BLACK;
import static chess.web.dto.PieceType.QUEEN_WHITE;
import static chess.web.dto.PieceType.ROOK_BLACK;
import static chess.web.dto.PieceType.ROOK_WHITE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.web.dto.PieceDto;
import chess.web.dto.PieceType;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PieceDaoTest {

    private PieceDao pieceDao;

    @BeforeEach
    void setUp() {
        pieceDao = new PieceDaoImpl();
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

    @DisplayName("기물 단건을 조회한다.")
    @Test
    void 기물_단건_조회한다() {
        String id = "a2";
        PieceDto pieceDto = new PieceDto(id, PieceType.PAWN_BLACK);
        pieceDao.save(pieceDto);

        PieceDto findPieceDto = pieceDao.findById(id).get();

        assertThat(findPieceDto).isEqualTo(pieceDto);
    }

    @DisplayName("기물이 존재하지 않는 위치를 조회할 경우 Optional은 비어있다.")
    @Test
    void 기물_존재하지_않는다() {
        Optional<PieceDto> optionalPieceDto = pieceDao.findById("a2");

        assertThat(optionalPieceDto.isPresent()).isFalse();
    }

    @DisplayName("해당 위치의 기물이 존재하는 경우 삭제한다.")
    @Test
    void 기물을_삭제한다() {
        String id = "a2";
        PieceDto pieceDto = new PieceDto(id, PieceType.PAWN_BLACK);
        pieceDao.save(pieceDto);

        pieceDao.remove(id);

        assertThat(pieceDao.findAll().size()).isEqualTo(0);
    }

    @DisplayName("모든 기물을 조회한다.")
    @Test
    void 기물을_전체_조회한다() {
        List<PieceDto> pieces = initPieces();
        for (PieceDto piece : pieces) {
            pieceDao.save(piece);
        }

        assertThat(pieceDao.findAll().size()).isEqualTo(32);
    }

    @AfterEach
    void cleanUp() {
        pieceDao.removeAll();
    }

    private List<PieceDto> initPieces() {
        return List.of(
                new PieceDto("a1", ROOK_WHITE),
                new PieceDto("b1", KNIGHT_WHITE),
                new PieceDto("c1", BISHOP_WHITE),
                new PieceDto("d1", QUEEN_WHITE),
                new PieceDto("e1", KING_WHITE),
                new PieceDto("f1", BISHOP_WHITE),
                new PieceDto("g1", KNIGHT_WHITE),
                new PieceDto("h1", ROOK_WHITE),
                new PieceDto("a2", PAWN_WHITE),
                new PieceDto("b2", PAWN_WHITE),
                new PieceDto("c2", PAWN_WHITE),
                new PieceDto("d2", PAWN_WHITE),
                new PieceDto("e2", PAWN_WHITE),
                new PieceDto("f2", PAWN_WHITE),
                new PieceDto("g2", PAWN_WHITE),
                new PieceDto("h2", PAWN_WHITE),
                new PieceDto("a8", ROOK_BLACK),
                new PieceDto("b8", KNIGHT_BLACK),
                new PieceDto("c8", BISHOP_BLACK),
                new PieceDto("d8", QUEEN_BLACK),
                new PieceDto("e8", KING_BLACK),
                new PieceDto("f8", BISHOP_BLACK),
                new PieceDto("g8", KNIGHT_BLACK),
                new PieceDto("h8", ROOK_BLACK),
                new PieceDto("a7", PAWN_BLACK),
                new PieceDto("b7", PAWN_BLACK),
                new PieceDto("c7", PAWN_BLACK),
                new PieceDto("d7", PAWN_BLACK),
                new PieceDto("e7", PAWN_BLACK),
                new PieceDto("f7", PAWN_BLACK),
                new PieceDto("g7", PAWN_BLACK),
                new PieceDto("h7", PAWN_BLACK)
        );
    }
}
