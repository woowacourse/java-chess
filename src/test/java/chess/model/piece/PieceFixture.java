package chess.model.piece;

import chess.model.board.Board;
import java.util.List;
import java.util.stream.Collectors;

public enum PieceFixture {
    BLACK_KING("K"),
    BLACK_QUEEN("Q"),
    BLACK_ROOK("R"),
    BLACK_BISHOP("B"),
    BLACK_KNIGHT("N"),
    BLACK_PAWN("P"),
    WHITE_KING("k"),
    WHITE_QUEEN("q"),
    WHITE_ROOK("r"),
    WHITE_BISHOP("b"),
    WHITE_KNIGHT("n"),
    WHITE_PAWN("p"),
    NONE_NONE(".");

    private final String value;

    PieceFixture(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static List<String> mappingBoard(Board board) {
        return board.getLines().stream()
                .map(PieceFixture::mappingLine)
                .toList();
    }

    public static String mappingLine(List<Piece> line) {
        return line.stream()
                .map(PieceFixture::mappingPiece)
                .map(PieceFixture::getValue)
                .collect(Collectors.joining());
    }

    private static PieceFixture mappingPiece(Piece piece) {
        return PieceFixture.valueOf(piece.getName());
    }
}
