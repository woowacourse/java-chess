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
    PAWN(Pawn.class, "Pawn", Pawn::new),
    BISHOP(Bishop.class, "Bishop", Bishop::new),
    KING(King.class, "King", King::new),
    KNIGHT(Knight.class, "Knight", Knight::new),
    QUEEN(Queen.class, "Queen", Queen::new),
    ROOK(Rook.class, "Rook", Rook::new),
    ;

    private final Class<? extends Piece> piece;
    private final String value;
    private final Function<Camp, Piece> convert;

    PieceDtoMapper(Class<? extends Piece> piece, String value, Function<Camp, Piece> convert) {
        this.piece = piece;
        this.value = value;
        this.convert = convert;
    }

    public static String convertToPieceValue(Piece piece) {
        return Arrays.stream(PieceDtoMapper.values())
                .filter(it -> it.piece.isInstance(piece))
                .findFirst()
                .orElseThrow(NoSuchElementException::new)
                .value;
    }
    public static Piece convertToPiece(String pieceValue, Camp camp) {
        return Arrays.stream(PieceDtoMapper.values())
                .filter(it -> it.value.equals(pieceValue))
                .findFirst()
                .orElseThrow(NoSuchElementException::new)
                .convert
                .apply(camp);
    }
}
