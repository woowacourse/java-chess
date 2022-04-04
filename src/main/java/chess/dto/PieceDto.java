package chess.dto;

import chess.model.square.File;
import chess.model.square.Rank;
import java.util.Locale;

public class PieceDto {

    private final String name;
    private final String position;

    private PieceDto(String name, String position) {
        this.name = name;
        this.position = position;
    }

    public static PieceDto of(String name, Rank rank, File file) {
        String position = rank.name() + file.name().toLowerCase(Locale.ROOT);
        return new PieceDto(name, position);
    }

    public String getName() {
        return name;
    }

    public String getPosition() {
        return position;
    }
}
