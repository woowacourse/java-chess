package chess.service;

import static org.assertj.core.api.Assertions.*;

import java.util.LinkedHashMap;
import java.util.Map;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.EmblemMapper;
import chess.dao.BoardDao;
import chess.model.Board;
import chess.model.PieceArrangement.DefaultArrangement;
import chess.model.Position;

public class ChessGameServiceTest {

    private static ChessGameService chessGameService;

    @BeforeEach
    void setUp() {
        chessGameService = new ChessGameService(1, new fakeBoardDao());
    }

    @Test
    @DisplayName("현재 체스판의 위치, 기물 정보를 저장한다.")
    void save() {
        assertThatCode(() -> chessGameService.save())
            .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("저장된 체스판의 위치, 기물 정보를 불러온다.")
    void find() {
        //given
        Board board = new Board(new DefaultArrangement());

        //when
        chessGameService.save();
        Map<String, String> actual = chessGameService.find();
        Map<String, String> expected = EmblemMapper.StringPieceMapByPiecesByPositions(board.getValues());

        //then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("체스말을 이동시킨다.")
    void init() {
        //given
        Board board = new Board(new DefaultArrangement());
        Position source = Position.of("a2");
        Position target = Position.of("a4");

        //when
        chessGameService.move(source, target);
        chessGameService.save();
        Map<String, String> actual = chessGameService.find();
        board.move(source, target);

        Map<String, String> expected = EmblemMapper.StringPieceMapByPiecesByPositions(board.getValues());

        //then
        assertThat(actual).isEqualTo(expected);
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

        @Override
        public Map<String, String> findById(int gameId) {
            return table.get(gameId);
        }
    }
}
