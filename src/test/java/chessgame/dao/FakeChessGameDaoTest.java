package chessgame.dao;

import chessgame.domain.Board;
import chessgame.domain.ChessBoardFactory;
import chessgame.domain.state.White;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class FakeChessGameDaoTest {
    private static final String gameName = "test";
    private final FakeGameDao fakeGameDao = new FakeGameDao();

    @BeforeEach
    void beforeEach() {
        fakeGameDao.remove(gameName);
    }

    @Test
    @DisplayName("FakeDao 저장 테스트")
    void save() {
        Board board = new Board(ChessBoardFactory.create());

        Assertions.assertThatNoException().isThrownBy(() -> fakeGameDao.save(board, gameName, new White()));
    }

    @Test
    @DisplayName("FakeDao 읽기 테스트")
    void read() {
        Board board = new Board(ChessBoardFactory.create());
        fakeGameDao.save(board, gameName, new White());

        Assertions.assertThat(fakeGameDao.read(gameName).board()).usingRecursiveComparison().isEqualTo(board);

    }

    @Test
    @DisplayName("FakeDao 삭제 테스트")
    void remove() {
        Board board = new Board(ChessBoardFactory.create());
        fakeGameDao.save(board, gameName, new White());

        fakeGameDao.remove(gameName);

        Assertions.assertThat(fakeGameDao.read(gameName).board().getBoard().size()).isEqualTo(0);
    }

    @Test
    @DisplayName("FakeDao Turn 조회 테스트")
    void findTurn() {
        Board board = new Board(ChessBoardFactory.create());
        fakeGameDao.save(board, gameName, new White());
        System.out.println(fakeGameDao.findTurnByGame("test"));
        Assertions.assertThat(fakeGameDao.findTurnByGame("test")).isEqualTo(new White().getClass().toString());
    }
}
