package chess.domain.position;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Position implements Comparable<Position> {
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
    
    private static List<String> parsing(final String position) {
        return Arrays.stream(position.split("")).collect(Collectors.toList());
    }
    
    
    public boolean isRank(final int index) {
        Rank rank = Rank.findByIndex(index);
        return this.rank == rank;
    }
    
    public Direction calculateDirection(Position destination) {
        int sourceFIleIndex = this.getFile().getIndex();
        int sourceRankIndex = this.getRank().getIndex();
        int destinationFIleIndex = destination.getFile().getIndex();
        int destinationRankIndex = destination.getRank().getIndex();
        
        int fileGap = destinationFIleIndex - sourceFIleIndex;
        int sourceGap = destinationRankIndex - sourceRankIndex;
        
        if (fileGap > 0) {
            if (sourceGap > 0) {
                return Direction.NE;
            }
            if (sourceGap < 0) {
                return Direction.SE;
            }
            return Direction.E;
        }
        if (fileGap < 0) {
            if (sourceGap > 0) {
                return Direction.NW;
            }
            if (sourceGap < 0) {
                return Direction.SW;
            }
            return Direction.W;
        }
        
        if (sourceGap > 0) {
            return Direction.N;
        }
        return Direction.S;
        
    }
    
    public File getFile() {
        return file;
    }
    
    public Rank getRank() {
        return rank;
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
    
    @Override
    public String toString() {
        return "Position{" +
                "file=" + file +
                ", rank=" + rank +
                '}';
    }
    
    @Override
    public int compareTo(final Position o) {
        int thisRank = this.getRank().getIndex();
        int otherRank = o.getRank().getIndex();
        if (thisRank < otherRank) {
            return -1;
        }
        if (thisRank > otherRank) {
            return 1;
        }
        int thisFile = this.getFile().getIndex();
        int otherFile = o.getFile().getIndex();
        return Integer.compare(thisFile, otherFile);
    }
}
