package chess.dao;

import chess.dto.ChessDTO;
import chess.dto.GameIdDTO;

import java.util.List;

public interface BoardDAO {

    void savePieces(List<ChessDTO> chessDTOS, GameIdDTO gameIdDTO);

    List<ChessDTO> findAllPiece(GameIdDTO gameIdDTO);

    void deletePiece(String position, GameIdDTO gameIdDTO);
}
