package chess.dao;

import chess.dto.PieceDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertAll;

public class PieceDaoImplTest {

    private PieceDao pieceDao;

    @BeforeEach
    void setUp() {
        pieceDao = new PieceDaoImpl();
        pieceDao.removeAll();
    }

    @Test
    @DisplayName("piece 모두 삭제")
    void removeAll() {
        assertThatCode(() -> pieceDao.removeAll())
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("piece 모두 저장")
    void saveAll() {
        List<PieceDto> pieceDtos = List.of(
                PieceDto.of("a7", "white", "bishop"),
                PieceDto.of("a3", "white", "king")
        );
        assertThatCode(() -> pieceDao.saveAll(pieceDtos))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("piece 저장")
    void save() {
        PieceDto pieceDto = PieceDto.of("a7", "white", "bishop");

        assertThatCode(() -> pieceDao.save(pieceDto))
                .doesNotThrowAnyException();
    }


    @Test
    @DisplayName("piece 모두 조회")
    void findAll() {
        List<PieceDto> pieceDtos = List.of(
                PieceDto.of("a7", "white", "bishop"),
                PieceDto.of("a3", "white", "king")
        );
        assertAll(
                () -> assertThatCode(() -> pieceDao.saveAll(pieceDtos))
                        .doesNotThrowAnyException(),
                () -> assertThat(pieceDao.findAll()).containsAll(pieceDtos)
        );
    }
}
