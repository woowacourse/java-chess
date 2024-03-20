package chess.domain;

public record Position(File file, Rank rank) {

    public static Position from(String position) {
        File file = File.from(position.substring(0, 1));
        Rank rank = Rank.from(position.substring(1, 2));
        return new Position(file, rank);
    }
}
