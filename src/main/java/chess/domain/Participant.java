package chess.domain;

import chess.Member;
import java.util.Objects;

public class Participant {
    private final Member white;
    private final Member black;

    public Participant(Member white, Member black) {
        this.white = white;
        this.black = black;
    }

    public Member getWhite() {
        return white;
    }

    public Member getBlack() {
        return black;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Participant)) {
            return false;
        }
        Participant that = (Participant) o;
        return Objects.equals(white, that.white) && Objects.equals(black, that.black);
    }

    @Override
    public int hashCode() {
        return Objects.hash(white, black);
    }
}
