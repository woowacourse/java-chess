package chess.dao;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PieceDaoTest {

    @DisplayName("존재하는 게임에 대한 검색은 예외를 반환하지 않는다")
    @Test
    void findByGameID() {
        PieceDao pieceDao = new PieceDao();
        pieceDao.save("yaho");

        assertThatNoException().isThrownBy(() -> pieceDao.findByGameID("yaho"));
    }

    @DisplayName("존재하지 않는 게임에 대한 검색은 예외를 반환한다")
    @Test
    void findByGameID_NoSuchGame() {
        PieceDao pieceDao = new PieceDao();
        pieceDao.save("yaho");

        assertThatThrownBy(() -> pieceDao.findByGameID("yaahoo"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("헉.. 저장 안한거 아냐? 그런 게임은 없어!");
    }
}
