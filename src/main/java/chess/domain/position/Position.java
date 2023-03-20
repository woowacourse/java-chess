package chess.domain.position;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Position implements Comparable<Position> {
    
    private final File file;
    private final Rank rank;
    
    private Position(final File file, final Rank rank) {
        this.file = file;
        this.rank = rank;
    }
    
    private Position(final int file, final int rank) {
        this.file = File.findByIndex(file);
        this.rank = Rank.findByIndex(rank);
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
    
    public static Position from(File file, Rank rank) {
        return new Position(file, rank);
    }
    
    public boolean isRank(final int index) {
        Rank rank = Rank.findByIndex(index);
        return this.rank == rank;
    }
    
    public Direction calculateDirection(Position destination) {
        int sourceFIleIndex = getFile().getIndex();
        int sourceRankIndex = getRank().getIndex();
        int destinationFIleIndex = destination.getFile().getIndex();
        int destinationRankIndex = destination.getRank().getIndex();
        
        int fileGap = destinationFIleIndex - sourceFIleIndex;
        int sourceGap = destinationRankIndex - sourceRankIndex;
        
        try {
            return Direction.findByVector(fileGap, sourceGap);
        } catch (Exception ignored) {
        }
        
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
        return this.file;
    }
    
    public Rank getRank() {
        return this.rank;
    }
    
    public Position addDirection(Direction direction) {
        int fileIndex = file.getIndex();
        int rankIndex = rank.getIndex();
        
        return new Position(fileIndex + direction.getX(), rankIndex + direction.getY());
    }
    
    public int calculateDistance(Position destination) {
        int sourceFIleIndex = getFile().getIndex();
        int sourceRankIndex = getRank().getIndex();
        int destinationFIleIndex = destination.getFile().getIndex();
        int destinationRankIndex = destination.getRank().getIndex();
        
        int fileGap = destinationFIleIndex - sourceFIleIndex;
        int rankGap = destinationRankIndex - sourceRankIndex;
        
        return fileGap * fileGap + rankGap * rankGap;
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(this.file, this.rank);
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        final Position position = (Position) o;
        return this.file == position.file && this.rank == position.rank;
    }
    
    @Override
    public String toString() {
        return "Position{" +
                "file=" + this.file +
                ", rank=" + this.rank +
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
