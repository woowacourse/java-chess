package chess.dao;

import chess.dto.TurnChangeRequestDto;
import chess.dto.TurnRequestDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class TurnDaoTest {
    private TurnDao turnDao;

    @BeforeEach
    public void setUp() {
        turnDao = new TurnDao();
    }

    @Order(1)
    @DisplayName("데이터베이스 연결을 확인한다.")
    @Test
    public void connect() {
        Connection con = turnDao.getConnection();
        assertNotNull(con);
    }

    @Order(2)
    @DisplayName("turn 테이블에 레코드를 삽입한다.")
    @Test
    public void initializeTurn() {
        turnDao.initializeTurn();
    }

    @Order(3)
    @DisplayName("turn 테이블에서 모든 레코드를 읽어온다.")
    @Test
    public void showCurrentTurn() {
        List<TurnRequestDto> turns = turnDao.showCurrentTurn();
        assertThat(turns).hasSize(1);
    }

    @Order(4)
    @DisplayName("white 턴에서 black 턴으로 변경한다.")
    @Test
    public void changeTurnWhite() {
        TurnChangeRequestDto turnChangeRequestDto = new TurnChangeRequestDto("white", "black");
        turnDao.changeTurn(turnChangeRequestDto);
    }

    @Order(5)
    @DisplayName("black 턴에서 white 턴으로 변경한다.")
    @Test
    public void changeTurnBlack() {
        TurnChangeRequestDto turnChangeRequestDto = new TurnChangeRequestDto("black", "white");
        turnDao.changeTurn(turnChangeRequestDto);
    }

    @Order(6)
    @DisplayName("turn 테이블의 레코드를 삭제한다.")
    @Test
    public void removeTurn() {
        turnDao.removeTurn();
    }
}
