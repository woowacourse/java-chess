package chess.domain.position;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Position {
    //TODO : 캐싱?
    
    private final File file;
    private final Rank rank;
    
    private Position(final File file, final Rank rank) {
        this.file = file;
        this.rank = rank;
    }
    
    public static Position from(String position) {
        List<String> parsedPosition = parsing(position);
        File file = File.findByLabel(parsedPosition.get(0));
        Rank rank = Rank.findByLabel(parsedPosition.get(1));
        return new Position(file, rank);
    }
    
    public static List<String> parsing(final String position) {
        return Arrays.stream(position.split("")).collect(Collectors.toList());
    }
    
    
    public boolean isRank(final int index) {
        Rank rank = Rank.findByIndex(index);
        return this.rank == rank;
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(file, rank);
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Position position = (Position) o;
        return file == position.file && rank == position.rank;
    }
    
    public File getFile() {
        return file;
    }
    
    public Rank getRank() {
        return rank;
    }
}
