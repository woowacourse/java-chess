package chess;

import java.util.List;

public class Position {
    private final Rank rank;
    private final File file;
    private static final List<File> initFiles = List.of(File.ONE, File.TWO, File.SEVEN, File.EIGHT);

    public Position(Rank rank, File file) {
        this.rank = rank;
        this.file = file;
    }

    public boolean isInitPosition() {
        return initFiles.contains(file);
    }
}
