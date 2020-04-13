package chess.domain.piece;

import static chess.domain.player.PlayerColor.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public enum GamePieces {

    BLACK_KING("K", () -> new King(BLACK)),
    WHITE_KING("k", () -> new King(WHITE)),
    BLACK_QUEEN("Q", () -> new Queen(BLACK)),
    WHITE_QUEEN("q", () -> new Queen(WHITE)),
    BLACK_BISHOP("B", () -> new Bishop(BLACK)),
    WHITE_BISHOP("b", () -> new Bishop(WHITE)),
    BLACK_KNIGHT("N", () -> new Knight(BLACK)),
    WHITE_KNIGHT("n", () -> new Knight(WHITE)),
    BLACk_ROOK("R", () -> new Rook(BLACK)),
    WHITE_ROOK("r", () -> new Rook(WHITE)),
    BLACK_PAWN("P", () -> new Pawn(BLACK)),
    WHITE_PAWN("p", () -> new Pawn(WHITE));

    private static Map<String, Supplier> gamePieces;

    static {
        gamePieces = Arrays.stream(GamePieces.values())
                .collect(Collectors.toMap(gamePiece -> gamePiece.name, gamePiece -> gamePiece.creator));
    }

    private String name;
    private Supplier<GamePiece> creator;

    GamePieces(String name, Supplier<GamePiece> creator) {
        this.name = name;
        this.creator = creator;
    }

    public static List<GamePiece> createGamePieces() {
        return Arrays.stream(GamePieces.values())
                .map(gamePiece -> gamePiece.creator.get())
                .collect(Collectors.toList());
    }

    public static GamePiece from(String name) {
        try {
            return (GamePiece)gamePieces.get(name).get();
        } catch (NullPointerException e) {
            return EmptyPiece.getInstance();
        }
    }
}
