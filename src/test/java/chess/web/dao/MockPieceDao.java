package chess.web.dao;

import chess.web.dto.PieceDto;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MockPieceDao implements PieceDao {

    private final Map<String, PieceDto> pieces = new HashMap<>();

    @Override
    public void save(PieceDto pieceDto) {
        pieces.put(pieceDto.getPosition(), pieceDto);
    }

    @Override
    public void update(PieceDto pieceDto) {
        pieces.computeIfPresent(pieceDto.getPosition(), (k, v) -> pieceDto);
    }

    @Override
    public List<PieceDto> selectAll() {
        return new ArrayList<>(pieces.values());
    }

    @Override
    public void deleteAll() {
        pieces.clear();
    }

    public PieceDto getPieceDtoByPosition(String position) {
        return pieces.get(position);
    }

    public int getSize() {
        return pieces.size();
    }
}
