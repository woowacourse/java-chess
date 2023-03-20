package chess.dto;

import chess.domain.square.File;
import chess.domain.square.Rank;

public class SquareDto {

    private final File file;
    private final Rank rank;

    private SquareDto(final File file, final Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    // TODO: 검증 필요
    public static SquareDto of(String input) {
        File file = File.from(input.charAt(0));
        Rank rank = Rank.from(input.charAt(1));
        return new SquareDto(file, rank);
    }

    public File getFile() {
        return file;
    }

    public Rank getRank() {
        return rank;
    }
}
