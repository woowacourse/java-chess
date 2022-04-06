package chess.web.dao;

import chess.web.dto.PieceDto;
import java.util.HashMap;
import java.util.Map;

public class PieceDaoForTest implements PieceDao {

    private final Map<String, PieceDto> pieces = new HashMap<>();

    @Override
    public void save(PieceDto pieceDto) {
        pieces.put(pieceDto.getPosition(), pieceDto);
    }

    @Override
    public void deleteAll() {
        pieces.clear();
    }

    public int getSize() {
        return pieces.size();
    }
}
