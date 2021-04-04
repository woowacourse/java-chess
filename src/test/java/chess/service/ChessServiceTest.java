package chess.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ChessServiceTest {
    private final ChessService chessService = new ChessService();

    @DisplayName("새로운 체스 게임을 만든다.")
    @Test
    void generateChessGame() throws Exception {
        chessService.generateChessGame();
    }

    @DisplayName("체스 게임 아이디들을 조회한다.")
    @Test
    void getAllChessGameId() throws Exception {
        List<Integer> chessGameIds = chessService.getAllChessGameId();
        System.out.println(chessGameIds);
    }
}
