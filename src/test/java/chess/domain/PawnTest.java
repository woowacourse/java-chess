package chess.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class PawnTest {

    @Test
    void 폰의_이름이_대문자일때_흰색_진영이라면_예외를_던진다() {
        assertThatThrownBy(() -> new Pawn("P", "white"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("흰색 진영일때는 대문자 이름이 올 수 없습니다.");
    }

    @Test
    void 폰의_이름이_소문자일때_검정색_진영이라면_예외를_던진다() {
        assertThatThrownBy(() -> new Pawn("p", "black"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("검정색 진영일때는 소문자 이름이 올 수 없습니다.");
    }
}

class Pawn extends Pr {

    private static final String UPPER_NAME = "P";
    private static final String LOWER_NAME = "p";
    private static final String WHITE_SIDE = "white";
    private static final String BLACk_SIDE = "black";
    private final String name;
    private final String side;

    public Pawn(final String name, final String side) {
        validate(name, side);
        this.name = name;
        this.side = side;
    }

    private static void validate(final String name, final String side) {
        if (name.equals(UPPER_NAME) && side.equals(WHITE_SIDE)) {
            throw new IllegalArgumentException("흰색 진영일때는 대문자 이름이 올 수 없습니다.");
        }
        if (name.equals(LOWER_NAME) && side.equals(BLACk_SIDE)) {
            throw new IllegalArgumentException("검정색 진영일때는 소문자 이름이 올 수 없습니다.");
        }
    }
}