package chess.view;

import chess.dto.BoardDTO;
import chess.dto.LineDTO;
import java.util.List;
import java.util.stream.Collectors;

public enum PieceRepresentation {
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

    PieceRepresentation(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static List<String> mappingBoard(BoardDTO boardDTO) {
        return boardDTO.board().stream()
                .map(PieceRepresentation::mappingLine)
                .toList();
    }

    private static String mappingLine(LineDTO lineDTO) {
        return lineDTO.line().stream()
                .map(PieceRepresentation::mappingPiece)
                .map(PieceRepresentation::getValue)
                .collect(Collectors.joining());
    }

    private static PieceRepresentation mappingPiece(String pieceName) {
        return PieceRepresentation.valueOf(pieceName);
    }
}
