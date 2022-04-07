package chess.domain.piece;

import java.util.Arrays;

public enum PieceConvertor {

    WHITE_PAWN("white_pawn", new WhitePawn()),
    BLACK_PAWN("black_pawn", new BlackPawn()),
    WHITE_ROOK("white_rook", new Rook(Team.WHITE)),
    BLACK_ROOK("black_rook", new Rook(Team.BLACK)),
    WHITE_KNIGHT("white_knight", new Knight(Team.WHITE)),
    BLACK_KNIGHT("black_knight", new Knight(Team.BLACK)),
    WHITE_BISHOP("white_bishop", new Bishop(Team.WHITE)),
    BLACK_BISHOP("black_bishop", new Bishop(Team.BLACK)),
    WHITE_QUEEN("white_queen", new Queen(Team.WHITE)),
    BLACK_QUEEN("black_queen", new Queen(Team.BLACK)),
    WHITE_KING("white_king", new King(Team.WHITE)),
    BLACK_KING("black_king", new King(Team.BLACK)),
    BLANK("blank", new Blank());

    private final String name;
    private final Piece type;

    PieceConvertor(String name, Piece type) {
        this.name = name;
        this.type = type;
    }

    public static Piece of(final String name) {
        return Arrays.stream(values())
                .filter(value -> name.equals(value.name))
                .map(value -> value.type)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("해당 하는 말이 없습니다."));
    }
}