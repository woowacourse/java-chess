package chess.service;

import static chess.model.File.*;
import static chess.model.Rank.*;
import static org.assertj.core.api.Assertions.*;

import java.sql.Connection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.EmblemMapper;
import chess.Game;
import chess.dao.BoardDao;
import chess.dao.GameDao;
import chess.model.Board;
import chess.model.PieceArrangement.DefaultArrangement;
import chess.model.PieceArrangement.PieceArrangement;
import chess.model.PieceColor;
import chess.model.Position;
import chess.model.Turn;
import chess.model.piece.King;
import chess.model.piece.Piece;
import chess.model.piece.Rook;

public class ChessGameServiceTest {

    private static ChessGameService chessGameService;

    @BeforeEach
    void setUp() {
        GameDao gameDao = new fakeGameDao(new Game("white", "black"));
        chessGameService = new ChessGameService(new fakeBoardDao());
        chessGameService.setGameDao(gameDao);
    }

    @AfterEach
    void tearDown() {
        chessGameService.delete();
    }

    @Test
    @DisplayName("현재 게임 정보와 체스판의 위치, 기물 정보를 저장한다.")
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
    void move() {
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

    @Test
    @DisplayName("체스판 데이터를 삭제한다.")
    void delete() {
        //when
        chessGameService.save();
        chessGameService.delete();
        Map<String, String> actual = chessGameService.find();

        //then
        assertThat(actual).isEmpty();
    }

    @Test
    @DisplayName("체스 게임 초기화 기능을 검증한다.")
    void init() {
        //given
        Board board = new Board(new testPieceArrangement());
        //when
        chessGameService.init(new Turn(), new testPieceArrangement());
        chessGameService.save();
        Map<String, String> actual = chessGameService.find();
        Map<String, String> expected = EmblemMapper.StringPieceMapByPiecesByPositions(board.getValues());

        //then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("저장된 Turn에 맞는 색을 꺼내온다.")
    void findTurnById() {
        //when
        chessGameService.move(Position.of("a2"), Position.of("a4"));
        chessGameService.save();
        String actual = chessGameService.loadTurnColor();

        //then
        assertThat(actual).isEqualTo(PieceColor.BLACK.toString());
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
            return table.getOrDefault(gameId, Map.of());
        }

        @Override
        public void deleteById(int gameId) {
            table.remove(gameId);
        }

        @Override
        public Connection getConnection() {
            return null;
        }
    }

    public static class testPieceArrangement implements PieceArrangement {

        @Override
        public Map<Position, Piece> apply() {
            Map<Position, Piece> result = new HashMap<>();
            result.put(Position.of(A, TWO), Rook.colorOf(PieceColor.WHITE));
            result.put(Position.of(A, THREE), King.colorOf(PieceColor.BLACK));
            return result;
        }
    }

    private static class fakeGameDao implements GameDao {
        private final Game game;
        private final Map<Integer, List<String>> table = new LinkedHashMap<>();

        public fakeGameDao(Game game) {
            this.game = game;
        }

        @Override
        public Connection getConnection() {
            return null;
        }

        @Override
        public void save() {
            table.remove(game.getId());
            table.put(game.getId(), List.of(game.getIdWhitePlayer(), game.getIdBlackPlayer(),
                game.getTurn().toString()));
        }

        @Override
        public void deleteById(int id) {
            table.remove(game.getId());
        }

        @Override
        public int getId() {
            return game.getId();
        }

        @Override
        public List<String> findById(int id) {
            return table.getOrDefault(game.getId(), List.of());
        }

        @Override
        public String findTurnById(int id) {
            return table.get(game.getId()).get(2);
        }

        @Override
        public void nextTurn() {
            game.nextTurn();
        }
    }
}
