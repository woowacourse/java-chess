package chess.domain.dao;


import chess.domain.Color;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DbTurnDaoTest {
    TurnDao turnDao = new TurnDaoImpl();

    @DisplayName("업데이트 하기")
    @Test
    void updateCurrentColor() {
        //given
        Color before = turnDao.getCurrentTurn();
        Color after = Color.changeColor(before);
        //when
        turnDao.update(after);
        //then
        Assertions.assertThat(turnDao.getCurrentTurn()).isEqualTo(after);
    }
}
