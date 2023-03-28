package domain.repository;

import domain.Board;
import domain.Location;
import domain.piece.Piece;
import domain.type.Color;
import java.util.Map;

public interface ChessRepository {

    Board findBoardById(final String id);

    Color findLastColorFromBoardById(final String id);

    boolean existBoard(final String id);

    void insert(final String boardId, final Map<Location, Piece> board, final Color color);

    void update(final String boardId, final Map<Location, Piece> board, final Color color);
}
