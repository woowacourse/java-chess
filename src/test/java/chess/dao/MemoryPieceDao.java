package chess.dao;

import static java.util.stream.Collectors.toList;

import chess.web.dto.PieceDto;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

public class MemoryPieceDao implements PieceDao {

    private static Map<String, PieceDto> store = new HashMap<>();

    @Override
    public void save(PieceDto pieceDto) {
        if (store.containsKey(pieceDto.getId())) {
            throw new IllegalArgumentException("기물의 위치는 중복될 수 없습니다.");
        }

        store.put(pieceDto.getId(), pieceDto);
    }

    @Override
    public Optional<PieceDto> findById(String id) {
        if (!store.containsKey(id)) {
            return Optional.empty();
        }

        return Optional.of(store.get(id));
    }

    @Override
    public void remove(String id) {
        if (!store.containsKey(id)) {
            throw new IllegalArgumentException("기물이 존재하지 않습니다.");
        }

        store.remove(id);
    }

    @Override
    public List<PieceDto> findAll() {
        return store.entrySet()
                .stream()
                .map(Entry::getValue)
                .collect(toList());
    }

    @Override
    public void removeAll() {
        store = new HashMap<>();
    }
}
