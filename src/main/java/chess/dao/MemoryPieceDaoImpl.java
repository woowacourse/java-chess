package chess.dao;

import static java.util.stream.Collectors.toList;

import chess.web.dto.PieceDto;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class MemoryPieceDaoImpl implements PieceDao {

    private static Map<String, PieceDto> store = new HashMap<>();

    @Override
    public void save(PieceDto pieceDto) {
        store.put(pieceDto.getId(), pieceDto);
    }

    @Override
    public List<PieceDto> findAll() {
        return store.entrySet()
                .stream()
                .map(Entry::getValue)
                .collect(toList());
    }
}
