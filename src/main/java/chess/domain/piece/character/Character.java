package chess.domain.piece.character;

import java.util.stream.Stream;

public record Character(Team team, Kind kind) {
    public static double sumPoint(Stream<Character> characters) {
        return Kind.sumPoint(characters.map(character -> character.kind));
    }
}
