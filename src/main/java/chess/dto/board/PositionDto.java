package chess.dto.board;

public class PositionDto {

    private final int file;
    private final int rank;

    public PositionDto(final int file, final int rank) {
        this.file = file;
        this.rank = rank;
    }

    public int getFile() {
        return file;
    }

    public int getRank() {
        return rank;
    }

    @Override
    public String toString() {
        return file +""+ rank;
    }
}
