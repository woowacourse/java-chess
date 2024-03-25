package view.message;

import constant.ErrorCode;
import exception.MessageDoesNotExistException;
import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import model.Camp;
import model.piece.Bishop;
import model.piece.BlackPawn;
import model.piece.King;
import model.piece.Knight;
import model.piece.Piece;
import model.piece.Queen;
import model.piece.Rook;
import model.piece.WhitePawn;

public enum PieceType {

    BISHOP_BLACK(new Bishop(Camp.BLACK), "B"),
    BISHOP_WHITE(new Bishop(Camp.WHITE), "b"),
    KING_BLACK(new King(Camp.BLACK), "K"),
    KING_WHITE(new King(Camp.WHITE), "k"),
    KNIGHT_BLACK(new Knight(Camp.BLACK), "N"),
    KNIGHT_WHITE(new Knight(Camp.WHITE), "n"),
    PAWN_BLACK(new BlackPawn(), "P"),
    PAWN_WHITE(new WhitePawn(), "p"),
    QUEEN_BLACK(new Queen(Camp.BLACK), "Q"),
    QUEEN_WHITE(new Queen(Camp.WHITE), "q"),
    ROOK_BLACK(new Rook(Camp.BLACK), "R"),
    ROOK_WHITE(new Rook(Camp.WHITE), "r");

    private static final Map<Piece, PieceType> SUIT_MESSAGE = Arrays.stream(values())
            .collect(Collectors.toMap(PieceType::getPiece, Function.identity()));

    private final Piece piece;
    private final String value;

    PieceType(Piece piece, String value) {
        this.piece = piece;
        this.value = value;
    }

    public static PieceType from(Piece target) {
        if (SUIT_MESSAGE.containsKey(target)) {
            return SUIT_MESSAGE.get(target);
        }
        throw new MessageDoesNotExistException(ErrorCode.NO_MESSAGE);
    }

    public String getValue() {
        return value;
    }

    private Piece getPiece() {
        return piece;
    }
}
