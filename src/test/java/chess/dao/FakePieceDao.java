package chess.dao;

import chess.dto.PieceDto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FakePieceDao implements PieceDao {

    Map<String, PieceDto> pieces = new HashMap<>();

    @Override
    public void removeAll() {
        pieces.clear();
    }

    @Override
    public void saveAll(List<PieceDto> pieceDtos) {
        for (PieceDto pieceDto : pieceDtos) {
            save(pieceDto);
        }
    }

    @Override
    public void save(PieceDto pieceDto) {
        pieces.put(pieceDto.getPosition(), pieceDto);
    }

    @Override
    public List<PieceDto> findAll() {
        return new ArrayList<>(pieces.values());
    }
}
