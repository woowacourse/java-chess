package chess.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;

import chess.domain.board.Board;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChessGameDaoTest {

    private final ChessGameDao chessGameDao = new ChessGameDao();

    @BeforeEach
    void setUp() {
        chessGameDao.deleteAll();
    }

    @Test
    @DisplayName("DB와 연결한다")
    void DB와_연결한다() {
        Assertions.assertAll(
                () -> assertThat(chessGameDao.getConnection()).isNotNull(),
                () -> assertThatNoException().isThrownBy(chessGameDao::getConnection)
        );
    }

    @Test
    @DisplayName("board에 대한 정보를 DB에 insert 한다")
    void board에_대한_정보를_DB에_insert_한다() {
        Board board = Board.create();

        assertThatNoException().isThrownBy(
                () -> chessGameDao.insert(board)
        );
    }

    @Test
    @DisplayName("테이블이 비어있으면 null을 반환한다")
    void 테이블이_비어있으면_null을_반환한다() {
        assertThat(chessGameDao.findBoard()).isNull();
    }

    @Test
    @DisplayName("테이블이 비어있지 않으면 Board를 반환한다.")
    void 테이블이_비어있지_않으면_Board를_반환한다() {
        chessGameDao.insert(Board.create());
        Assertions.assertAll(
                () -> assertThat(chessGameDao.findBoard()).isNotNull(),
                () -> assertThatNoException().isThrownBy(chessGameDao::findBoard)
        );
    }

    @Test
    @DisplayName("board에 대한 정보를 DB에서 모두 지운다.")
    void board에_대한_정보를_DB에서_모두_지운다() {
        assertThatNoException().isThrownBy(chessGameDao::deleteAll);
    }

    @Test
    @DisplayName("board에 대한 정보를 모두 업데이트한다")
    void board에_대한_정보를_모두_업데이트한다() {
        Board board = Board.create();

        assertThatNoException().isThrownBy(
                () -> chessGameDao.updateBoard(board)
        );
    }
}
