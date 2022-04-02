package chess.domain.command;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum Term {
    START("start"),
    END("end"),
    MOVE("move"),
    STATUS("status");

    public final String value;

    Term(String value) {
        this.value = value;
    }

    public static List<String> words() {
        return Stream.of(values())
                .map(command -> command.value)
                .collect(Collectors.toList());
    }
}
