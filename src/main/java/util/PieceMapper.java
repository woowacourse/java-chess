package util;

import domain.piece.Bishop;
import domain.piece.EmptyPiece;
import domain.piece.King;
import domain.piece.Knight;
import domain.piece.Pawn;
import domain.piece.Piece;
import domain.piece.PieceCategory;
import domain.piece.Queen;
import domain.piece.Rook;
import java.util.Arrays;
import java.util.function.Supplier;

public enum PieceMapper {
    WHITE_PAWN((() -> Pawn.createOfWhite()), PieceCategory.WHITE_PAWN, "p", "WHITE_PAWN"),
    WHITE_ROOK((() -> Rook.createOfWhite()), PieceCategory.WHITE_ROOK, "r", "WHITE_ROOK"),
    WHITE_KNIGHT((() -> Knight.createOfWhite()), PieceCategory.WHITE_KNIGHT, "n", "WHITE_KNIGHT"),
    WHITE_BISHOP((() -> Bishop.createOfWhite()), PieceCategory.WHITE_BISHOP, "b", "WHITE_BISHOP"),
    WHITE_QUEEN((() -> Queen.createOfWhite()), PieceCategory.WHITE_QUEEN, "q", "WHITE_QUEEN"),
    WHITE_KING((() -> King.createOfWhite()), PieceCategory.WHITE_KING, "k", "WHITE_KING"),
    BLACK_PAWN((() -> Pawn.createOfBlack()), PieceCategory.BLACK_PAWN, "P", "BLACK_PAWN"),
    BLACK_ROOK((() -> Rook.createOfBlack()), PieceCategory.BLACK_ROOK, "R", "BLACK_ROOK"),
    BLACK_KNIGHT((() -> Knight.createOfBlack()), PieceCategory.BLACK_KNIGHT, "N", "BLACK_KNIGHT"),
    BLACK_BISHOP((() -> Bishop.createOfBlack()), PieceCategory.BLACK_BISHOP, "B", "BLACK_BISHOP"),
    BLACK_QUEEN((() -> Queen.createOfBlack()), PieceCategory.BLACK_QUEEN, "Q", "BLACK_QUEEN"),
    BLACK_KING((() -> King.createOfBlack()), PieceCategory.BLACK_KING, "K", "BLACK_KING"),
    EMPTY_PIECE((() -> new EmptyPiece()), PieceCategory.EMPTY_PIECE, ".", "EMPTY_PIECE");


    private final Supplier<Piece> createPiece;
    private final PieceCategory category;
    private final String textForView;
    private final String textForDB;

    PieceMapper(Supplier<Piece> createPiece, PieceCategory category, String textForView, String textForDB) {
        this.createPiece = createPiece;
        this.category = category;
        this.textForView = textForView;
        this.textForDB = textForDB;
    }

    public static String convertPieceCategoryToTextForView(PieceCategory category) {
        return Arrays.stream(PieceMapper.values())
                .filter(pieceMapper -> pieceMapper.category.equals(category))
                .findFirst()
                .map(pieceMapper -> pieceMapper.textForView)
                .orElseThrow(
                        () -> new IllegalStateException("서버 내부 에러 - 존재하지 않는 PieceCategory를 Mapping 시도했습니다."));
    }

    public static Piece convertTextForDBToPiece(String textForDB) {
        return Arrays.stream(PieceMapper.values())
                .filter(pieceMapper -> pieceMapper.textForDB.equals(textForDB))
                .findFirst()
                .map(pieceMapper -> pieceMapper.createPiece.get())
                .orElseThrow(() -> new IllegalStateException("서버 내부 에러 - 존재하지 않는 text for DB of piece를 입력했습니다."));
    }
}
