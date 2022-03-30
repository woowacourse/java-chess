package web.dto;

import static chess.position.File.A;
import static chess.position.File.B;
import static chess.position.File.C;
import static chess.position.File.D;
import static chess.position.File.E;
import static chess.position.File.F;
import static chess.position.File.G;
import static chess.position.File.H;
import static chess.position.Rank.EIGHT;
import static chess.position.Rank.FIVE;
import static chess.position.Rank.FOUR;
import static chess.position.Rank.ONE;
import static chess.position.Rank.SEVEN;
import static chess.position.Rank.SIX;
import static chess.position.Rank.THREE;
import static chess.position.Rank.TWO;

import chess.piece.Color;
import chess.piece.Piece;
import chess.position.File;
import chess.position.Position;
import chess.position.Rank;
import java.util.Map;

public class PieceDTO {

    private static final Map<File, String> FILES = Map.of(
            A, "A", B, "B", C, "C", D, "D", E, "E", F, "F", G, "G", H, "H"
    );
    private static final Map<Rank, String> RANKS = Map.of(
            ONE, "1", TWO, "2", THREE, "3", FOUR, "4", FIVE, "5", SIX, "6", SEVEN, "7", EIGHT, "8"
    );

    private final Position position;
    private final Color color;
    private final PieceType type;

    public PieceDTO(Position position, Piece piece) {
        this.position = position;
        this.color = piece.getColor();
        this.type = PieceType.valueOf(piece);
    }

    public Color getColor() {
        return color;
    }

    public PieceType getType() {
        return type;
    }

    public String getPosition() {
        return FILES.get(position.getFile()) + RANKS.get(position.getRank());
    }
}
