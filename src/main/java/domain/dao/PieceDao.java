package domain.dao;

import domain.Location;
import domain.piece.Piece;
import java.util.Map;

public interface PieceDao {

    Map<Location, Piece> findAllByBoardId(final String boardId);

    Void insert(final Map<Location, Piece> board, final String boardId);

    Integer update(final Map<Location, Piece> board, final String boardId);
}
