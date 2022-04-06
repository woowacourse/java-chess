package chess.service;

import static chess.model.File.*;
import static chess.model.Rank.*;
import static org.assertj.core.api.Assertions.*;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.EmblemMapper;
import chess.dao.BoardDao;
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
        assertThat(actual).isNull();
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
}
