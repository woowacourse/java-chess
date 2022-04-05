package chess.db;

import chess.domain.piece.Bishop;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import java.util.Arrays;
import java.util.function.BiPredicate;
import java.util.function.Supplier;

public enum PieceGenerator {
    WHITE_KING((name, color) -> name.equals("King") && color.equals("white"), King::createWhite),
    BLACK_KING((name, color) -> name.equals("King") && color.equals("black"), King::createBlack),
    WHITE_QUEEN((name, color) -> name.equals("Queen") && color.equals("white"), Queen::createWhite),
    BLACK_QUEEN((name, color) -> name.equals("Queen") && color.equals("black"), Queen::createBlack),
    WHITE_BISHOP((name, color) -> name.equals("Bishop") && color.equals("white"), Bishop::createWhite),
    BLACK_BISHOP((name, color) -> name.equals("Bishop") && color.equals("black"), Bishop::createBlack),
    WHITE_ROOK((name, color) -> name.equals("Rook") && color.equals("white"), Rook::createWhite),
    BLACK_ROOK((name, color) -> name.equals("Rook") && color.equals("black"), Rook::createBlack),
    WHITE_KNIGHT((name, color) -> name.equals("Knight") && color.equals("white"), Knight::createWhite),
    BLACK_KNIGHT((name, color) -> name.equals("Knight") && color.equals("black"), Knight::createBlack),
    WHITE_PAWN((name, color) -> name.equals("Pawn") && color.equals("white"), Pawn::createWhite),
    BLACK_PAWN((name, color) -> name.equals("Pawn") && color.equals("black"), Pawn::createBlack);

    private final BiPredicate<String, String> condition;
    private final Supplier<Piece> pieceSupplier;

    PieceGenerator(BiPredicate<String, String> condition, Supplier<Piece> pieceSupplier) {
        this.condition = condition;
        this.pieceSupplier = pieceSupplier;
    }

    public static Piece of(String name, String color) {
        return Arrays.stream(PieceGenerator.values()).filter(each -> each.condition.test(name, color))
                .map(each -> each.pieceSupplier.get())
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
