package chess.dao;

import chess.domain.pieces.Piece;
import chess.domain.position.Column;
import chess.domain.position.Row;

import java.util.List;
import java.util.Map;

public interface PositionDao<T> {

    T save(T position);

    T getByColumnAndRowAndBoardId(Column column, Row row, int boardId);

    int saveAll(int boardId);

    int getIdByColumnAndRowAndBoardId(Column column, Row row, int boardId);

    Map<T, Piece> findAllPositionsAndPieces(int boardId);

    List<T> getPaths(List<T> positions, int roomId);
}
