//package chess.dao.fixture;
//
//import chess.dao.PieceDao;
//import chess.dto.PieceDto;
//import java.util.HashMap;
//import java.util.Map;
//
//public class MockPieceDao implements PieceDao {
//
//    private final Map<Map<Integer, Integer>, PieceDto> piece = new HashMap<>();
//
//    @Override
//    public void save(final int boardId, final PieceDto pieceDto) {
//        piece.put(Map.of(1, boardId), pieceDto);
//    }
//}
