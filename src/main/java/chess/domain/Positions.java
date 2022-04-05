package chess.domain;

import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Positions {

    private static final Map<String, Position> CACHE = new HashMap<>();

    static {
        List<String> rankFiles = Arrays.stream(Rank.values()).
                flatMap(rank -> Arrays.stream(File.values())
                        .map(file -> file.file() + rank.rank()))
                .collect(Collectors.toList());

        List<Position> positions = Arrays.stream(Rank.values()).
                flatMap(rank -> Arrays.stream(File.values())
                        .map(file -> Position.of(rank.rank(), file.file())))
                .collect(Collectors.toList());

        for (int idx = 0; idx < rankFiles.size(); idx++) {
            CACHE.put(rankFiles.get(idx), positions.get(idx));
        }
    }

    private Positions() {
    }

    public static Position findPosition(String rankFile) {
        return CACHE.get(rankFile);
    }
}
