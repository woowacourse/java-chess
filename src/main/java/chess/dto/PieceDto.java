package chess.dto;

import chess.model.piece.Piece;
import chess.model.square.File;
import chess.model.square.Rank;
import java.util.Locale;

public class PieceDto {

    private final String imagePath;
    private final String position;
    private final String background;

    private PieceDto(String imagePath, String position, String background) {
        this.imagePath = imagePath;
        this.position = position;
        this.background = background;
    }

    public static PieceDto of(Piece piece, File file, Rank rank) {
        String position = file.name().toLowerCase(Locale.ROOT) + rank.value();
        String background = findBackground(file, rank);
        if (piece.isBlack()) {
            return new PieceDto("black-" + piece.name() + ".png", position, background);
        }
        if (!piece.isNotEmpty()) {
            return new PieceDto(piece.name() + ".png", position, background);
        }
        return new PieceDto("white-" + piece.name() + ".png", position, background);
    }

    private static String findBackground(File file, Rank rank) {
        if ((file.value() + rank.value()) % 2 == 0) {
            return "black";
        }
        return "white";
    }

    public String getImagePath() {
        return imagePath;
    }

    public String getPosition() {
        return position;
    }

    public String getBackground() {
        return background;
    }
}
