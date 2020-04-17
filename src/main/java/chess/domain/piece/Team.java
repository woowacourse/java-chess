package chess.domain.piece;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public enum Team {
    WHITE(a -> a.get(0), "흰색"),
    BLACK(a -> a.get(1), "검은색");

    private final Function<List<Character>, Character> teamRepresentation;
    private final String name;

    Team(Function<List<Character>, Character> teamRepresentation, String name) {
        this.teamRepresentation = teamRepresentation;
        this.name = name;
    }

    public static Team of(String name) {
        return Arrays.stream(Team.values())
            .filter(a -> a.name.equals(name))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("일치하는 팀이 없습니다."));
    }

    public static char getRepresentation(Piece piece) {
        return piece.getTeam().teamRepresentation.apply(piece.getRepresentations());
    }

    public String getName() {
        return name;
    }
}
