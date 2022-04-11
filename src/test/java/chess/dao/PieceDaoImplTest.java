package chess.dao;

import chess.db.DBConnector;
import chess.domain.position.Position;
import chess.dto.PieceDto;
import org.junit.jupiter.api.AfterEach;
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
        pieceDao = new PieceDaoImpl(new DBConnector());
        pieceDao.removeAll();
    }

    @AfterEach
    void clearData() {
        pieceDao.removeAll();
    }

    @Test
    @DisplayName("piece 삭제")
    void remove() {
        PieceDto pieceDto = PieceDto.of("a7", "white", "bishop");
        pieceDao.save(pieceDto);

        assertAll(
                () -> assertThatCode(() -> pieceDao.remove(Position.of("a7"))).doesNotThrowAnyException(),
                () -> assertThat(pieceDao.findAll()).isEmpty()
        );
    }

    @Test
    @DisplayName("piece 모두 삭제")
    void removeAll() {
        assertAll(
                () -> assertThatCode(() -> pieceDao.removeAll()).doesNotThrowAnyException(),
                () -> assertThat(pieceDao.findAll()).isEmpty()
        );
    }

    @Test
    @DisplayName("piece 모두 저장")
    void saveAll() {
        List<PieceDto> pieceDtos = List.of(
                PieceDto.of("a7", "white", "bishop"),
                PieceDto.of("a3", "white", "king")
        );
        assertAll(
                () -> assertThatCode(() -> pieceDao.saveAll(pieceDtos)).doesNotThrowAnyException(),
                () -> assertThat(pieceDao.findAll()).containsAll(pieceDtos),
                () -> assertThat(pieceDao.findAll().size()).isEqualTo(2)
        );
    }

    @Test
    @DisplayName("piece 저장")
    void save() {
        PieceDto pieceDto = PieceDto.of("a7", "white", "bishop");

        assertAll(
                () -> assertThatCode(() -> pieceDao.save(pieceDto)).doesNotThrowAnyException(),
                () -> assertThat(pieceDao.findAll()).contains(pieceDto),
                () -> assertThat(pieceDao.findAll().size()).isEqualTo(1)
        );
    }


    @Test
    @DisplayName("piece 모두 조회")
    void findAll() {
        List<PieceDto> pieceDtos = List.of(
                PieceDto.of("a7", "white", "bishop"),
                PieceDto.of("a3", "white", "king")
        );

        assertAll(
                () -> assertThatCode(() -> pieceDao.saveAll(pieceDtos)).doesNotThrowAnyException(),
                () -> assertThat(pieceDao.findAll()).containsAll(pieceDtos)
        );
    }

    @Test
    @DisplayName("piece 위치 업데이트")
    void update() {
        PieceDto pieceDto = PieceDto.of("a7", "white", "bishop");
        pieceDao.save(pieceDto);

        PieceDto updatedPieceDto = PieceDto.of("a8", "white", "bishop");

        assertAll(
                () -> assertThatCode(() -> pieceDao.update(Position.of("a7"), Position.of("a8")))
                        .doesNotThrowAnyException(),
                () -> assertThat(pieceDao.findAll()).contains(updatedPieceDto),
                () -> assertThat(pieceDao.findAll()).doesNotContain(pieceDto)
        );
    }
}
