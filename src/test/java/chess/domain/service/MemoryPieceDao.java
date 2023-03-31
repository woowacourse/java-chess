package chess.domain.service;

import chess.domain.repository.PieceDao;
import chess.domain.repository.entity.PieceEntity;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class MemoryPieceDao extends PieceDao {
    private final static Map<String, PieceEntity> pieceEntities = new HashMap<>();

    @Override
    public void savePieces(List<PieceEntity> pieces) {
        for (PieceEntity piece : pieces) {
            pieceEntities.put(piece.getPosition(), piece);
        }
    }

    @Override
    public List<PieceEntity> findAllPieces() {
        return new ArrayList<>(pieceEntities.values());
    }

    @Override
    public void deleteAll() {
        pieceEntities.clear();
    }

    @Override
    public void updatePiecePositionTo(String from, String to) {
        PieceEntity pieceEntity = pieceEntities.get(from);
        pieceEntities.put(to, pieceEntity);
        pieceEntities.remove(from);
    }

    @Override
    public void deleteByPosition(String position) {
        pieceEntities.remove(position);
    }

    @Override
    public boolean isExistByPosition(String position) {
        return pieceEntities.containsKey(position);
    }

    @Override
    public PieceEntity findByPosition(String position) {
        return pieceEntities.get(position);
    }
}
