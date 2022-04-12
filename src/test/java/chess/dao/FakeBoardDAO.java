package chess.dao;

import chess.dto.ChessDTO;
import chess.dto.GameIdDTO;

import java.util.*;

public class FakeBoardDAO implements BoardDAO {

    private final Map<Integer, Map<String, String>> pieces = new HashMap<>();
    private int id = 0;

    @Override
    public void savePieces(List<ChessDTO> chessDTOS, GameIdDTO gameIdDTO) {
        for (ChessDTO chessDto : chessDTOS) {
            id++;
            Map<String, String> piece = new HashMap<>();
            piece.put("gameId", Integer.toString(gameIdDTO.getId()));
            piece.put("piece", chessDto.getPiece().toLowerCase());
            piece.put("position", chessDto.getPosition());
            piece.put("color", chessDto.getColor());
            pieces.put(id, piece);
        }
    }

    @Override
    public List<ChessDTO> findAllPiece(GameIdDTO gameIdDTO) {
        List<ChessDTO> boards = new ArrayList<>();
        for (Integer id : pieces.keySet()) {
            toDTO(gameIdDTO, boards, id);
        }
        return boards;
    }

    private void toDTO(GameIdDTO gameIdDTO, List<ChessDTO> boards, Integer id) {
        if (pieces.get(id).get("gameId").equals(Integer.toString(gameIdDTO.getId()))) {
            boards.add(new ChessDTO(pieces.get(id).get("color"),
                    pieces.get(id).get("piece"), pieces.get(id).get("position")));
        }
    }

    @Override
    public void deletePiece(String position, GameIdDTO gameIdDTO) {
        for (int id : pieces.keySet()) {
            deleteByCondition(position, gameIdDTO, id);
        }
    }

    private void deleteByCondition(String position, GameIdDTO gameIdDTO, int id) {
        if (pieces.get(id).get("gameId").equals(Integer.toString(gameIdDTO.getId()))
                && pieces.get(id).get("position").equals(position)) {
            pieces.remove(id);
        }
    }
}
