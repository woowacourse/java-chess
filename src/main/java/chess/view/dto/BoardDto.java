package chess.view.dto;

import chess.domain.board.Board;
import chess.domain.piece.AbstractPiece;
import chess.domain.piece.PieceColor;
import chess.domain.piece.PieceType;
import chess.domain.position.Position;
import chess.domain.position.XAxis;
import chess.domain.position.YAxis;
import java.util.NoSuchElementException;
import java.util.stream.Stream;

enum PieceName {
    PAWN("P", "p"),
    ROOK("R", "r"),
    KNIGHT("N", "n"),
    BISHOP("B", "b"),
    QUEEN("Q", "q"),
    KING("K", "k");

    public final String blackName;
    public final String whiteName;

    PieceName(String blackName, String whiteName) {
        this.blackName = blackName;
        this.whiteName = whiteName;
    }

    public static PieceName find(PieceType pieceType) {
        return Stream.of(PieceName.values())
                .filter(pieceName -> pieceName.name().equals(pieceType.name()))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("존재하지 않는 말 타입입니다."));
    }

    public String getName(PieceColor pieceColor) {
        if (pieceColor == PieceColor.BLACK) {
            return blackName;
        }

        return whiteName;
    }
}

public class BoardDto {
    private static final String EMPTY_PIECE_DISPLAY_NAME = ".";
    private final Board board;

    public BoardDto(Board board) {
        this.board = board;
    }

    public String getBoardText() {
        StringBuilder stringBuilder = new StringBuilder();

        for (YAxis yAxis : YAxis.values()) {
            generateRowText(stringBuilder, yAxis);
        }

        return stringBuilder.toString();
    }

    private void generateRowText(StringBuilder stringBuilder, YAxis yAxis) {
        for (XAxis xAxis : XAxis.values()) {
            generatePieceText(stringBuilder, yAxis, xAxis);
        }
        stringBuilder.append(System.lineSeparator());
    }

    private void generatePieceText(StringBuilder stringBuilder, YAxis yAxis, XAxis xAxis) {
        board.find(Position.from(xAxis, yAxis))
                .ifPresentOrElse(
                        piece -> stringBuilder.append(generateExistingPieceName(piece)),
                        () -> generateNotFoundPieceName(stringBuilder)
                );
    }

    private String generateExistingPieceName(AbstractPiece piece) {
        PieceType pieceType = piece.getPieceType();
        PieceColor pieceColor = piece.getPieceColor();

        return PieceName.find(pieceType).getName(pieceColor);
    }

    private void generateNotFoundPieceName(StringBuilder stringBuilder) {
        stringBuilder.append(EMPTY_PIECE_DISPLAY_NAME);
    }
}
