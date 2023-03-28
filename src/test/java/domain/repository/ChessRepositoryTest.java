package domain.repository;

import static org.assertj.core.api.Assertions.assertThat;

import domain.Board;
import domain.Location;
import domain.dao.BoardDao;
import domain.dao.PieceDao;
import domain.piece.King;
import domain.piece.Knight;
import domain.piece.Pawn;
import domain.piece.Piece;
import domain.piece.Queen;
import domain.type.Color;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChessRepositoryTest {

    private final Map<Location, Piece> board = Map.of(
        Location.of(1, 2), Pawn.makeBlack(),
        Location.of(2, 2), King.makeBlack(),
        Location.of(3, 2), Queen.makeBlack(),
        Location.of(4, 2), Knight.makeBlack()
    );

    @Test
    @DisplayName("체스판 찾기 테스트")
    public void testFindBoardById() {
        //given
        final ChessRepository chessRepository = new ChessRepositoryImpl(null, new PieceDao() {
            @Override
            public Map<Location, Piece> findAllByBoardId(final String boardId) {
                return board;
            }

            @Override
            public Void insert(final Map<Location, Piece> board, final String boardId) {
                return null;
            }

            @Override
            public Integer update(final Map<Location, Piece> board, final String boardId) {
                return null;
            }
        });
        final String boardId = "test";

        //when
        final Board result = chessRepository.findBoardById(boardId);

        //then
        final Map<Location, Piece> resultBoard = result.getBoard();
        for (final Location location : resultBoard.keySet()) {
            assertThat(resultBoard.get(location)).isEqualTo(board.get(location));
        }
    }

    @Test
    @DisplayName("마지막 색 찾기 테스트")
    public void testFindLastColorFromBoardById() {
        //given
        final ChessRepository chessRepository = new ChessRepositoryImpl(new BoardDao() {
            @Override
            public Color findLastColor(final String id) {
                return Color.WHITE;
            }

            @Override
            public Integer count(final String id) {
                return null;
            }

            @Override
            public Void insert(final String boardId, final Color color) {
                return null;
            }

            @Override
            public Integer update(final String boardId, final Color color) {
                return null;
            }
        }, null);
        final String boardId = "test";

        //when
        final Color result = chessRepository.findLastColorFromBoardById(boardId);

        //then
        assertThat(result).isEqualTo(Color.WHITE);
    }

    @Test
    @DisplayName("id에 맞는 board가 존재하는지 테스트")
    public void testExistBoard() {
        //given
        final ChessRepository chessRepository = new ChessRepositoryImpl(new BoardDao() {
            @Override
            public Color findLastColor(final String id) {
                return Color.WHITE;
            }

            @Override
            public Integer count(final String id) {
                return 1;
            }

            @Override
            public Void insert(final String boardId, final Color color) {
                return null;
            }

            @Override
            public Integer update(final String boardId, final Color color) {
                return null;
            }
        }, null);
        final String boardId = "test";

        //when
        final boolean result = chessRepository.existBoard(boardId);

        //then
        assertThat(result).isTrue();
    }
}
