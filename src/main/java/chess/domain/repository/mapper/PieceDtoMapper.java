package chess.domain.repository.mapper;

import chess.domain.piece.Bishop;
import chess.domain.piece.Camp;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.function.Function;

public enum PieceDtoMapper {
    PAWN(Pawn.class, Pawn::new),
    BISHOP(Bishop.class, Bishop::new),
    KING(King.class, King::new),
    KNIGHT(Knight.class, Knight::new),
    QUEEN(Queen.class, Queen::new),
    ROOK(Rook.class, Rook::new),
    ;

    private final Class<? extends Piece> piece;
    private final Function<Camp, Piece> convert;

    PieceDtoMapper(Class<? extends Piece> piece, Function<Camp, Piece> convert) {
        this.piece = piece;
        this.convert = convert;
    }

    public static String convertToPieceValue(Piece piece) {
        return Arrays.stream(PieceDtoMapper.values())
                .filter(it -> it.piece.isInstance(piece))
                .findFirst()
                .orElseThrow(NoSuchElementException::new)
                .name();
    }

    public static Piece convertToPiece(String pieceValue, Camp camp) {
        return Arrays.stream(PieceDtoMapper.values())
                .filter(it -> it.name().equalsIgnoreCase(pieceValue))
                .findFirst()
                .orElseThrow(NoSuchElementException::new)
                .convert
                .apply(camp);
    }
}
