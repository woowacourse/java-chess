package chess.domain.position.cache;

import chess.dao.PositionDAO;
import chess.dao.entity.PositionEntity;
import chess.domain.position.type.File;
import chess.domain.position.type.Rank;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class PositionEntitiesCache {
    private static final PositionDAO POSITION_DAO = new PositionDAO();
    private static final List<PositionEntity> POSITION_ENTITIES = new ArrayList<>();

    private PositionEntitiesCache() {
    }

    static {
        try {
            cachePositions();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void cachePositions() throws SQLException {
        List<Rank> ranks = Arrays.asList(Rank.values());
        List<Rank> reversedRanks = ranks.stream()
            .sorted(Comparator.reverseOrder())
            .collect(Collectors.toList());

        for (Rank rank : reversedRanks) {
            cachingPositionsOfRank(rank);
        }
    }

    private static void cachingPositionsOfRank(Rank rank) throws SQLException {
        for (File file : File.values()) {
            PositionEntity positionEntity = POSITION_DAO.findByFileAndRank(file, rank);
            POSITION_ENTITIES.add(positionEntity);
        }
    }

    public static PositionEntity find(File file, Rank rank) {
        return POSITION_ENTITIES.stream()
            .filter(positionEntity ->
                positionEntity.getFile() == file && positionEntity.getRank() == rank)
            .findAny()
            .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 위치 입니다."));
    }

    public static PositionEntity get(int index) {
        return POSITION_ENTITIES.get(index);
    }

    public static PositionEntity findById(Long positionId) {
        return POSITION_ENTITIES.stream()
            .filter(positionEntity -> positionEntity.getId().equals(positionId))
            .findAny()
            .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 위치 입니다."));
    }
}
