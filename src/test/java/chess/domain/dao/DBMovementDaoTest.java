package chess.domain.dao;

import chess.domain.entity.Chess;
import chess.domain.entity.Movement;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

class DBMovementDaoTest {
    private static final String DUMMY_NAME = "테스트 게임";
    private final DBMovementDao DBMovementDao = new DBMovementDao();
    private final DBChessDao DBChessDao = new DBChessDao();

    @BeforeEach
    void init() {
        DBChessDao.save(new Chess(DUMMY_NAME));
    }

    @AfterEach
    void close() {
        DBChessDao.deleteByName(DUMMY_NAME);
    }

    @DisplayName("정상 저장 테스트")
    @Test
    void save() {
        //given
        Chess chess = DBChessDao.findByName(DUMMY_NAME).orElse(new Chess("없슈"));

        //when
        Movement movement = new Movement(chess.getId(), "2a", "4a");
        DBMovementDao.save(movement);

        //then
        Assertions.assertThat(chess.getId()).isEqualTo(movement.getChessId());
    }

    @DisplayName("체스게임 이름으로 Movement를 찾는다")
    @Test
    void findByChessName() {
        //given
        Chess chess = DBChessDao.findByName(DUMMY_NAME).orElse(new Chess("없슈"));
        //when
        DBMovementDao.save(new Movement(chess.getId(), "2a", "4a"));
        DBMovementDao.save(new Movement(chess.getId(), "4a", "6a"));
        DBMovementDao.save(new Movement(chess.getId(), "6a", "8a"));
        List<Movement> movements = DBMovementDao.findByChessName(DUMMY_NAME);

        //then
        Assertions.assertThat(movements).hasSize(3);
    }
}