package chess.domain.dao;

import chess.domain.entity.Chess;
import chess.domain.piece.Color;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class DBChessDaoTest {
    private static final String NAME = "1번방";

    private final DBChessDao DBChessDao = new DBChessDao(MemoryConnectionPool.create());
    private Chess chess;

    @BeforeEach
    void init() {
        chess = new Chess(NAME);
    }

    @AfterEach()
    void clear() {
        DBChessDao.deleteByName(NAME);
    }

    @DisplayName("체스 저장 테스트")
    @Test
    void save() {
        //when
        DBChessDao.save(chess);
        Chess findChess = DBChessDao.findByName(NAME).orElseThrow(() -> new NullPointerException("없는 이름임!"));

        //then
        assertThat(findChess.getName()).isEqualTo(chess.getName());
    }

    @DisplayName("같은 이름의 방이 생성될 시 예외가 발생한다.")
    @Test
    void saveException() {
        //when
        DBChessDao.save(chess);

        //then
        assertThatThrownBy(() -> DBChessDao.save(chess))
                .isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("체스 업데이트 테스트")
    @Test
    void update() {
        //when
        DBChessDao.save(chess);
        chess.changeWinnerColor(Color.BLACK);
        chess.changeRunning(false);
        DBChessDao.update(chess);

        Chess findChess = DBChessDao.findByName(NAME).orElseThrow(() -> new NullPointerException("없는 이름임!"));

        assertThat(findChess.getWinnerColor()).isEqualTo(Color.BLACK);
        assertThat(findChess.isRunning()).isEqualTo(false);
    }

    @DisplayName("체스 삭제 테스트")
    @Test
    void delete() {
        //when
        DBChessDao.deleteByName(NAME);
        Optional<Chess> findByName = DBChessDao.findByName(NAME);

        //then
        assertThat(findByName).isEqualTo(Optional.empty());
    }
}
