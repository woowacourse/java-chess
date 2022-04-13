package chess.dao;

import chess.domain.pieces.Color;
import chess.domain.pieces.Piece;
import chess.domain.position.Column;

import java.util.List;
import java.util.Optional;

public interface PieceDao<T> {

    T save(T piece);

    Optional<Piece> findByPositionId(int positionId);

    int updatePositionId(int sourcePositionId, int targetPositionId);

    int deleteByPositionId(int positionId);

    List<T> getAllByBoardId(int boardId);

    int countPawnsOnSameColumn(int boardId, Column column, Color color);
}
