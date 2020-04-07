package chess.controller.dao;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayerDAOTest {
    private PlayerDAO playerDAO;

    @BeforeEach
    private void setUp() {
        this.playerDAO = new PlayerDAO();
    }

    @Disabled
    @DisplayName("게임이 시작할 때 플레이어가 저장되는지 테스트")
    @Test
    void addPlayerTest() throws Exception {
        ChessBoard chessBoard = new ChessBoard(5);
        Player player = new Player("pobi", "json");

        this.playerDAO.addPlayer(chessBoard, player);
    }

    @Disabled
    @DisplayName("체스 아이디에 맞는 플레이어 정보 찾기")
    @Test
    void findPlayerTest() throws Exception {
        ChessBoard chessBoard = new ChessBoard(5);
        Player player = this.playerDAO.findPlayer(chessBoard);

        Assertions.assertThat(player.getWhitePlayer()).isEqualTo("pobi");
        Assertions.assertThat(player.getBlackPlayer()).isEqualTo("json");
    }

    @Disabled
    @DisplayName("게임이 끝나고 플레이어 정보 삭제")
    @Test
    void deletePlayerTest() throws Exception {
        ChessBoard chessBoard = new ChessBoard(5);

        this.playerDAO.deletePlayer(chessBoard);
    }
}
