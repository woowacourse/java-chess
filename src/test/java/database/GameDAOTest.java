package database;

import chess.domain.position.Position;
import chess.history.Move;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameDAOTest {
    
    @Test
    @DisplayName("게임 번호 추가 테스트")
    void add_game_id_test() {
        GameDAO gameDAO = new GameDAO("game_test");
        gameDAO.reset();
        gameDAO.addGame();
        Assertions.assertThat(gameDAO.fetchGames().size()).isEqualTo(1);
    }
    
    @Test
    @DisplayName("게임 번호 추가 테스트2")
    void add_game_id_test2() {
        GameDAO gameDAO = new GameDAO("game_test");
        gameDAO.reset();
        gameDAO.addGame();
        gameDAO.addGame();
        Assertions.assertThat(gameDAO.fetchGames().size()).isEqualTo(2);
    }
    
    @Test
    @DisplayName("게임 삭제 테스트")
    void delete_game_test() {
        GameDAO gameDAO = new GameDAO("game_test");
        gameDAO.reset();
        gameDAO.addGame();
        gameDAO.addGame();
        int lastGameID = gameDAO.getLastGameID();
        gameDAO.resetGame(lastGameID);
        Assertions.assertThat(gameDAO.fetchGames().size()).isEqualTo(1);
    }
    
    @Test
    @DisplayName("게임 삭제 테스트2 - move 테이블의 데이터도 삭제되는지 확인")
    void delete_game_test2() {
        GameDAO gameDAO = new GameDAO("game_test");
        gameDAO.reset();
        gameDAO.addGame();
        gameDAO.addGame();
        int lastGameID = gameDAO.getLastGameID();
        MoveDAO2 moveDAO = new MoveDAO2("move_test");
        moveDAO.addMove(Move.create(Position.from("a2"), Position.from("a3")), lastGameID);
        moveDAO.addMove(Move.create(Position.from("a7"), Position.from("a6")), lastGameID);
        gameDAO.resetGame(lastGameID);
        Assertions.assertThat(moveDAO.fetchMoves(lastGameID).size()).isEqualTo(0);
    }
    
}