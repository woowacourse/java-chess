package chess.domain;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Position {
    
    private final File file;
    private final Rank rank;
    
    private Position(final File file, final Rank rank) {
        this.file = file;
        this.rank = rank;
    }
    
    public static Position from(String position) {
        // TODO : String 검증
        List<String> parsedPosition = parsing(position);
        File file = File.findByLabel(parsedPosition.get(0));
        Rank rank = Rank.findByLabel(parsedPosition.get(1));
        return new Position(file, rank);
    }
    
    public static List<String> parsing(final String position) {
        return Arrays.stream(position.split("")).collect(Collectors.toList());
    }
    
    public File getFile() {
        return file;
    }
    
    public Rank getRank() {
        return rank;
    }
}
