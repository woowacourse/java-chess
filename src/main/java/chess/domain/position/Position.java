package chess.domain.position;

import java.math.BigInteger;
import java.util.Objects;

public final class Position implements Comparable<Position> {
    
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
        return PositionFactory.from(position);
    }
    
    public static Position from(File file, Rank rank) {
        return new Position(file, rank);
    }
    
    public boolean isRank(final int index) {
        Rank rank = Rank.findByIndex(index);
        return this.rank == rank;
    }
    
    public Direction calculateDirection(Position destination) {
        int sourceFileIndex = this.getFileIndex();
        int sourceRankIndex = this.getRankIndex();
        int destinationFIleIndex = destination.getFileIndex();
        int destinationRankIndex = destination.getRankIndex();
        
        int fileGap = destinationFIleIndex - sourceFileIndex;
        int sourceGap = destinationRankIndex - sourceRankIndex;
        
        int unit = this.getUnit(fileGap, sourceGap);
        int fileUnit = fileGap / unit;
        int sourceUnit = sourceGap / unit;
        
        return Direction.findByVector(fileUnit, sourceUnit);
        
    }
    
    public boolean isNotEqualTo(final Position move) {
        return !this.equals(move);
    }
    
    private int getUnit(final int fileGap, final int sourceGap) {
        BigInteger fileGapBigInteger = BigInteger.valueOf(fileGap);
        BigInteger sourceGapBigInteger = BigInteger.valueOf(sourceGap);
        BigInteger gcd = fileGapBigInteger.gcd(sourceGapBigInteger);
        
        return gcd.intValue();
    }
    
    public Position addDirection(Direction direction) {
        int fileIndex = this.file.getIndex();
        int rankIndex = this.rank.getIndex();
        
        return new Position(fileIndex + direction.getX(), rankIndex + direction.getY());
    }
    
    public int calculateDistance(Position destination) {
        int sourceFileIndex = this.getFileIndex();
        int sourceRankIndex = this.getRankIndex();
        int destinationFIleIndex = destination.getFileIndex();
        int destinationRankIndex = destination.getRankIndex();
        
        int fileGap = destinationFIleIndex - sourceFileIndex;
        int rankGap = destinationRankIndex - sourceRankIndex;
        
        return fileGap * fileGap + rankGap * rankGap;
    }
    
    public boolean isFile(final int file) {
        return this.file.getIndex() == file;
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
        int thisRank = this.getRankIndex();
        int otherRank = o.getRankIndex();
        if (thisRank < otherRank) {
            return -1;
        }
        if (thisRank > otherRank) {
            return 1;
        }
        int thisFile = this.getFileIndex();
        int otherFile = o.getFileIndex();
        return Integer.compare(thisFile, otherFile);
    }
    
    public int getFileIndex() {
        return this.file.getIndex();
    }
    
    public int getRankIndex() {
        return this.rank.getIndex();
    }
    
    public Rank getRank() {
        return this.rank;
    }
}
