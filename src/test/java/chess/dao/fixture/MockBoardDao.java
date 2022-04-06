//package chess.dao.fixture;
//
//import chess.dao.BoardDao;
//import chess.piece.detail.Color;
//import java.util.HashMap;
//import java.util.Map;
//
//public class MockBoardDao implements BoardDao {
//
//    private final Map<Integer, String> board = new HashMap<>();
//
//    @Override
//    public int save(final Color color) {
//        final int boardId = 1;
//        board.put(boardId, color.name());
//        return boardId;
//    }
//
//    @Override
//    public Color findTurnById(final int id) {
//        final String value = board.get(id);
//        return Color.valueOf(value);
//    }
//}
