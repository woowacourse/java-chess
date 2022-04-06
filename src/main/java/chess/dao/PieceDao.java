package chess.dao;

import chess.dto.PieceDto;
import chess.piece.Piece;
import chess.position.Position;
import java.util.Map;

public interface PieceDao {

    void save(final int boardId, final PieceDto pieceDto);

    void saveAll(int boardId, Map<Position, Piece> init);

    Map<Position, Piece> findAllByBoardId(int boardId);

    void deleteAllById(int boardId);

    boolean isExist();

    Map<Position, Piece> load();
}
