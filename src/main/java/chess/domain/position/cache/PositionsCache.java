package chess.domain.position.cache;

import chess.dao.position.PositionDAO;
import chess.dao.position.PositionRepository;
import chess.domain.position.Position;
import chess.domain.position.type.File;
import chess.domain.position.type.Rank;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class PositionsCache {
    private static final PositionRepository POSITION_DAO = new PositionDAO();
    private static final List<Position> POSITIONS = new ArrayList<>();

    private PositionsCache() {
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
            Position position = POSITION_DAO.findByFileAndRank(file, rank);
            POSITIONS.add(position);
        }
    }

    public static Position find(File file, Rank rank) {
        return POSITIONS.stream()
            .filter(position -> position.getFile() == file && position.getRank() == rank)
            .findAny()
            .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 위치 입니다."));
    }

    public static Position findById(Long positionId) {
        return POSITIONS.stream()
            .filter(position -> position.getId().equals(positionId))
            .findAny()
            .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 위치 입니다."));
    }

    public static Position get(int index) {
        return POSITIONS.get(index);
    }
}
