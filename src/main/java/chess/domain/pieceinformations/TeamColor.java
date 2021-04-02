package chess.domain.pieceinformations;

import java.util.Arrays;

public enum TeamColor {
    BLACK, WHITE, NONE;

    public TeamColor counterpart() {
        if (this == BLACK) {
            return WHITE;
        }
        if (this == WHITE) {
            return BLACK;
        }
        throw new IllegalArgumentException("어느팀의 턴도 아닙니다.");
    }

    public static TeamColor getInstance(String value){
        return Arrays.stream(TeamColor.values())
                .filter(color -> color.name().equals(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("해당하는 팀이 없네요ㅠ"));
    }
}
