package chess.domain.piece;

import java.util.function.Function;

public enum PieceType {
    BISHOP("B", 3, false, color -> Bishop.from(color)),
    ROOK("R", 5, false, color -> Rook.from(color)),
    QUEEN("Q", 9, false, color -> Queen.from(color)),
    KNIGHT("N", 2.5, false, color -> Knight.from(color)),
    KING("K", 0, false, color -> King.from(color)),
    PAWN("P", 1, true, color -> Pawn.from(color)),
    INIT_PAWN("P", 1, true, color -> InitPawn.from(color)),
    EMPTY(".", 0, false, color -> Empty.getInstance());

    private final String name;
    private final double score;
    private final boolean isDuplicateDemeritPieceType;
    private final Function<Color, Piece> pieceGenerator;

    PieceType(String name, double score, boolean isDuplicateDemeritPieceType,
              Function<Color, Piece> pieceGenerator) {
        this.name = name;
        this.score = score;
        this.isDuplicateDemeritPieceType = isDuplicateDemeritPieceType;
        this.pieceGenerator = pieceGenerator;
    }

    public String formatName(Color color) {
        return color.formatName(name);
    }

    public double getScore(){
        return score;
    }
    public boolean isDuplicateDemeritPieceType() {
        return isDuplicateDemeritPieceType;
    }

    public double getDuplicatePawnScore(){
        return 0.5;
    }

    public Piece getPiece(Color color){
        return pieceGenerator.apply(color);
    }
}
