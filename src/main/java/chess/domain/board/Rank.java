package chess.domain.board;

import java.util.List;

public class Rank {

    private final RankCoordinate rankCoordinate;
    private final List<Square> squares;

    Rank(RankCoordinate rankCoordinate, List<Square> squares) {
        this.rankCoordinate = rankCoordinate;
        this.squares = squares;
    }

    public Square getSquareByCoordinate(FileCoordinate coordinate) {
        return squares.stream()
                .filter(square -> square.isSameWith(coordinate))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("올바르지 않은 열 위치를 입력했습니다."));
    }

    public List<Square> getSquares() {
        return List.copyOf(squares);
    }
}
