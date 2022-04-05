package chess.dao;

import static java.util.stream.Collectors.toList;

import chess.web.dto.PieceDto;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class MemoryPieceDao implements PieceDao {

    private static final Map<String, PieceDto> store = new HashMap<>();

    @Override
    public void save(PieceDto pieceDto) {
        if (store.containsKey(pieceDto.getId())) {
            throw new IllegalArgumentException("기물의 좌표를 중복될 수 없습니다.");
        }

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
