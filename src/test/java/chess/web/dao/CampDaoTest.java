package chess.web.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Connection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CampDaoTest {
    private CampDao campDao;

    @BeforeEach
    void setUp() {
        campDao = new CampDao();
    }

    //connection
    @DisplayName("camp 테이블에 대한 connection 생성한다.")
    @Test
    void connection() {
        final Connection connection = campDao.getConnection();
        assertThat(connection).isNotNull();
    }

    @DisplayName("camp 테이블에서 현재의 turn이 어느 camp인지 확인한다.")
    @Test
    void findCurrentCamp() {
        final String currentCamp = campDao.findCurrentCamp();

        assertThat(currentCamp).isEqualTo("WHITE");
    }

    @DisplayName("camp 테이블에 현재 turn의 camp을 반영한다.")
    @Test
    void updateCamp() {
        campDao.update("BLACK");
        final String actual = campDao.findCurrentCamp();

        assertThat(actual).isEqualTo("BLACK");
    }
}
