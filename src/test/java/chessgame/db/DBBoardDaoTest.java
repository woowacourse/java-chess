package chessgame.db;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import java.util.Map;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import chessgame.controller.Command;
import chessgame.domain.Board;
import chessgame.domain.Game;
import chessgame.domain.GameBoardFactory;
import chessgame.domain.piece.Piece;
import chessgame.domain.point.Point;

@TestMethodOrder(value = MethodOrderer.OrderAnnotation.class)
class DBBoardDaoTest {
    private final DBBoardDao DBBoardDao = new DBBoardDao();

    @Test
    @Order(1)
    @DisplayName("보드를 추가한다")
    void addBoard() {
        DBBoardDao.addBoard();
    }

    @Test
    @Order(2)
    @DisplayName("처음 추가된 보드는 기본 상태가 ready이다")
    void getState() {
        int lastBoardNo = DBBoardDao.findLastBoardNo();
        String state = DBBoardDao.getState(lastBoardNo);

        Assertions.assertThat(state).isEqualTo("ready");
    }

    @Test
    @Order(3)
    @DisplayName("처음 추가된 보드에 start command를 입력하면 white 상태가 된다")
    void updateStateWhite() {
        //given
        int lastBoardNo = DBBoardDao.findLastBoardNo();
        Board board = DBBoardDao.findBoardByNo(lastBoardNo);
        Game game = new Game(board);

        // when
        game.setFrom(Command.of("start"));
        DBBoardDao.updateBoardState(game);
        String state = DBBoardDao.getState(lastBoardNo);

        // then
        Assertions.assertThat(state).isEqualTo("white");
    }

    @Test
    @Order(4)
    @DisplayName("보드에 end command를 입력하면 게임이 끝난다")
    void isNotRunning() {
        //given
        int lastBoardNo = DBBoardDao.findLastBoardNo();
        Board board = DBBoardDao.findBoardByNo(lastBoardNo);
        Game game = new Game(board);

        // when
        game.setFrom(Command.of("start"));
        game.setFrom(Command.of("end"));
        DBBoardDao.updateBoardState(game);

        // then
        Assertions.assertThat(DBBoardDao.isNotRunning(lastBoardNo)).isTrue();
    }

    @Test
    @Order(5)
    @DisplayName("진행중인 게임이 끝나면, 진행중인 게임 보드들의 개수가 하나 줄어든다")
    void isNotRunningBoards() {
        //given
        DBBoardDao.addBoard();
        int lastBoardNo = DBBoardDao.findLastBoardNo();
        Board board = DBBoardDao.findBoardByNo(lastBoardNo);
        Game game = new Game(board);

        // when
        int runningBoardsNumber = DBBoardDao.findRunningBoards().size();
        game.setFrom(Command.of("start"));
        game.setFrom(Command.of("end"));
        DBBoardDao.updateBoardState(game);

        // then
        Assertions.assertThat(DBBoardDao.findRunningBoards().size()).isEqualTo(runningBoardsNumber - 1);
    }

    @Test
    @Order(6)
    @DisplayName("Point테이블에는 GameBoardFactory로 초기화된 맵이 저장된다.")
    void findBoardByNo() {
        int lastBoardNo = DBBoardDao.findLastBoardNo();
        DBBoardDao.updatePoints(new Board(lastBoardNo));
        Map<Point, Piece> dbBoard = DBBoardDao.findBoardByNo(lastBoardNo).getBoard();
        Map<Point, Piece> initializedBoard = GameBoardFactory.create();
        for (Point dbBoardPoint : dbBoard.keySet()) {
            assertThat(initializedBoard.containsKey(dbBoardPoint)).isTrue();
            assertThat(dbBoard.get(dbBoardPoint).name()).isEqualTo(initializedBoard.get(dbBoardPoint).name());
        }
    }

}
