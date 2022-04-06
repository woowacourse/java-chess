package chess.service;

import java.util.LinkedHashMap;
import java.util.Map;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.dao.BoardDao;

public class ChessGameServiceTest {

    private static ChessGameService chessGameService;

    @BeforeEach
    void setUp() {
        chessGameService = new ChessGameService(1, new fakeBoardDao());
    }

    @Test
    @DisplayName("현재 체스판의 위치, 기물 정보를 저장한다.")
    void save() {
        Assertions.assertThatCode(() -> chessGameService.save())
            .doesNotThrowAnyException();
    }

    private static class fakeBoardDao implements BoardDao {
        private final Map<Integer, Map<String, String>> table;

        private fakeBoardDao() {
            this.table = new LinkedHashMap<>();
        }

        @Override
        public void save(int gameId, Map<String, String> StringPieceMapByPiecesByPositions) {
            table.put(gameId, StringPieceMapByPiecesByPositions);
        }
    }
}
