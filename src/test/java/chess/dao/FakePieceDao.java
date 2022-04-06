package chess.dao;

import chess.domain.position.Position;
import chess.dto.PieceDto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FakePieceDao implements PieceDao {

    private Map<String, PieceDto> pieces = new HashMap<>();

    @Override
    public void remove(Position position) {
        String positionName = position.getName();
        pieces.remove(positionName);
    }

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

    @Override
    public void update(Position source, Position target) {
        PieceDto pieceDto = pieces.get(source.getName());
        pieceDto.setPosition(target.getName());
        pieces.remove(source.getName());
        pieces.put(target.getName(), pieceDto);
    }
}
