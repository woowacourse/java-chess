package chess.domain;

import java.util.List;

public class Rank {

    private final RankCoordinate rankCoordinate;
    private final List<Square> squares;

    public Rank(RankCoordinate rankCoordinate, List<Square> squares) {
        this.rankCoordinate = rankCoordinate;
        this.squares = squares;
    }

    public Square getSquareByCoordinate(FileCoordinate coordinate) {
        return squares.stream()
                .filter(square -> square.getFileCoordinate() == coordinate)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("올바르지 않은 열 위치를 입력했습니다."));
    }
}
