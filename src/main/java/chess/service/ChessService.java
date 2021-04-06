package chess.service;

import chess.dao.ChessBoardDao;

import java.util.List;

public interface ChessService {

    void addPosition(ChessBoardDao eachPosition);

    void addPositions(List<ChessBoardDao> board);

    List<ChessBoardDao> findByGameId(String gameId);

    void removePositions();
}
