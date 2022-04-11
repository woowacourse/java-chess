package chess.service;

import static chess.model.File.*;
import static chess.model.Rank.*;
import static org.assertj.core.api.Assertions.*;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.Game;
import chess.MappingUtil;
import chess.dao.BoardDao;
import chess.dao.GameDao;
import chess.model.Board;
import chess.model.ChessGame;
import chess.model.PieceArrangement.DefaultArrangement;
import chess.model.PieceArrangement.PieceArrangement;
import chess.model.PieceArrangement.SavedPieceArrangement;
import chess.model.PieceColor;
import chess.model.Position;
import chess.model.Turn;
import chess.model.piece.King;
import chess.model.piece.Piece;
import chess.model.piece.PieceCache;
import chess.model.piece.Rook;

public class ChessGameServiceTest {

    private static final ChessGame chessGame = new ChessGame(new Turn(), new DefaultArrangement());
    private static final Game game = new Game("white", "black", 1);
    private static ChessGameService chessGameService;

    @BeforeEach
    void setUp() {
        GameDao gameDao = new fakeGameDao(new Game("white", "black", 1));
        chessGameService = new ChessGameService(gameDao, new fakeBoardDao());
    }

    @AfterEach
    void tearDown() {
        chessGameService.deleteById(1);
    }

    @Test
    @DisplayName("현재 게임 정보와 체스판의 위치, 기물 정보를 저장한다.")
    void save() {
        assertThatCode(() -> chessGameService.save(game, chessGame))
            .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("저장된 체스판의 위치, 기물 정보를 불러온다.")
    void find() {
        //given
        Board board = new Board(new DefaultArrangement());

        //when
        chessGameService.save(game, chessGame);
        Map<String, String> actual = chessGameService.findById(1);
        Map<String, String> expected = MappingUtil.StringPieceMapByPiecesByPositions(board.getValues());

        //then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("체스판 데이터를 삭제한다.")
    void delete() {
        //when
        chessGameService.save(game, chessGame);
        chessGameService.deleteById(1);
        Map<String, String> actual = chessGameService.findById(1);

        //then
        assertThat(actual).isEmpty();
    }

    @Test
    @DisplayName("체스 게임 초기화 기능을 검증한다.")
    void init() {
        //when
        ChessGame chessGame = chessGameService.init(game.getId(), new Turn(), new testPieceArrangement());
        chessGameService.save(game, chessGame);
        Map<String, String> actual = chessGameService.findById(1);
        Map<String, String> expected = MappingUtil.StringPieceMapByPiecesByPositions(chessGame.getBoardValue());

        //then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("저장된 Position과 Piece를 불러온다.")
    void getPiecesByPositions() {
        //given
        Map<Position, Piece> expected = Map.of(Position.of("a2"), PieceCache.of("b"),
            Position.of("a4"), PieceCache.of("B"));

        PieceArrangement pieceArrangement = new SavedPieceArrangement(
            MappingUtil.StringPieceMapByPiecesByPositions(expected));

        ChessGame chessGame = chessGameService.init(game.getId(), new Turn(), pieceArrangement);

        //when
        Map<Position, Piece> actual = chessGame.getBoardValue();

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
            return table.getOrDefault(gameId, Map.of());
        }

        @Override
        public void deleteById(int gameId) {
            table.remove(gameId);
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
        public void save(Game game) {
            table.remove(game.getId());
            table.put(game.getId(), List.of(game.getIdWhitePlayer(), game.getIdBlackPlayer(),
                game.getTurn().toString()));
        }

        @Override
        public void deleteById(int id) {
            table.remove(game.getId());
        }

        @Override
        public String findTurnById(int id) {
            if (table.containsKey(game.getId())) {
                return table.get(game.getId()).get(2);
            }
            return "WHITE";
        }

        @Override
        public int findIdByPlayerNames(String idPlayerWhite, String idPlayerBlack) {
            return 0;
        }
    }
}
