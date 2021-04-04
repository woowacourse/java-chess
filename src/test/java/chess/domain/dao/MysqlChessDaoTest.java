package chess.domain.dao;

import chess.domain.entity.Chess;
import chess.domain.piece.Color;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MysqlChessDaoTest {
    private final MysqlChessDao mysqlChessDao = new MysqlChessDao();

    @DisplayName("체스 저장 테스트")
    @Test
    void save() {
        //given
        String name = "1번방";
        Chess chess = new Chess(name);

        //when
        mysqlChessDao.save(chess);
        Chess findByName = mysqlChessDao.findByName(name).get();

        //then
        assertThat(findByName.getName()).isEqualTo(chess.getName());
        mysqlChessDao.deleteByName(name);

    }

    @DisplayName("같은 이름의 방이 생성될 시 예외가 발생한다.")
    @Test
    void saveExcpetion() {
        //given
        String name = "2번방";
        Chess chess = new Chess(name);

        //when
        mysqlChessDao.save(chess);

        //then
        assertThatThrownBy(() -> mysqlChessDao.save(chess))
                .isInstanceOf(IllegalStateException.class);
        mysqlChessDao.deleteByName(name);

    }

    @DisplayName("체스 업데이트 테스트")
    @Test
    void update() {
        //given
        String name = "3번방";
        Chess chess = new Chess(name);

        //when
        mysqlChessDao.save(chess);
        chess.changeWinnerColor(Color.BLACK);
        chess.changeRunning(false);
        mysqlChessDao.update(chess);

         if(mysqlChessDao.findByName(name).isPresent()){
             Chess findChess = mysqlChessDao.findByName(name).get();

             assertThat(findChess.getWinnerColor()).isEqualTo(Color.BLACK);
             assertThat(findChess.isRunning()).isEqualTo(false);
         }
        mysqlChessDao.deleteByName(name);
    }

    @DisplayName("체스 삭제 테스트")
    @Test
    void delete() {
        //given
        String name = "3번방";
        Chess chess = new Chess(name);

        //when
        mysqlChessDao.save(chess);
        mysqlChessDao.deleteByName(name);
        Optional<Chess> findByName = mysqlChessDao.findByName(name);

        //then
        assertThat(findByName).isEqualTo(Optional.empty());
    }
}
