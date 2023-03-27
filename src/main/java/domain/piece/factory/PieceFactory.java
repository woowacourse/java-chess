package domain.piece.factory;

import java.util.Arrays;
import java.util.function.Function;

import domain.piece.Camp;
import domain.piece.Piece;
import domain.piece.type.Empty;
import domain.piece.type.Pawn;
import domain.piece.type.PieceType;
import domain.piece.type.restricted.King;
import domain.piece.type.restricted.Knight;
import domain.piece.type.unrestricted.Bishop;
import domain.piece.type.unrestricted.Queen;
import domain.piece.type.unrestricted.Rook;

public enum PieceFactory {
    KING(King::new),
    QUEEN(Queen::new),
    ROOK(Rook::new),
    BISHOP(Bishop::new),
    KNIGHT(Knight::new),
    PAWN(Pawn::new),
    EMPTY((ignored) -> Empty.getInstance());

    private final PieceGenerator pieceGenerator;

    PieceFactory(PieceGenerator pieceGenerator) {
        this.pieceGenerator = pieceGenerator;
    }

    public Piece generatePiece(Camp camp) {
        return this.pieceGenerator.apply(camp);
    }

    public static PieceFactory find(String pieceName) {
        return Arrays.stream(PieceFactory.values())
                .filter(factory -> factory.name().equals(pieceName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("올바르지 않은 기물의 이름입니다."));
    }
}
