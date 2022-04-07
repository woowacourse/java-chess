package chess.domain;

import java.util.Objects;

public class Participant {
    private final Member white;
    private final Member black;

    public Participant(Member white, Member black) {
        this.white = white;
        this.black = black;
    }

    public Long getWhiteId() {
        return white.getId();
    }

    public Long getBlackId() {
        return black.getId();
    }

    public String getWhiteName() {
        return white.getName();
    }

    public String getBlackName() {
        return black.getName();
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
