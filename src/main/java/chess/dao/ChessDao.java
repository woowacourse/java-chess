package chess.dao;

import chess.dto.ChessCellDto;

import java.util.List;

public interface ChessDao {

    void addPosition(ChessCellDto eachPosition);

    void addPositions(List<ChessCellDto> board);

    List<ChessCellDto> findPositions();

    void removePositions();
}
