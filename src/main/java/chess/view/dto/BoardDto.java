package chess.view.dto;

import chess.domain.board.Board;
import chess.domain.piece.AbstractPiece;
import chess.domain.piece.PieceColor;
import chess.domain.piece.PieceType;
import chess.domain.position.Position;
import chess.domain.position.XAxis;
import chess.domain.position.YAxis;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class BoardDto {
    private static final String EMPTY_PIECE_DISPLAY_NAME = ".";

    private final Map<Position, AbstractPiece> board;

    public BoardDto(Board board) {
        this.board = board.getValue();
    }

    public String getBoardText() {
        StringBuilder stringBuilder = new StringBuilder();
        List<YAxis> yAxisValues = Arrays.asList(YAxis.values());
        Collections.reverse(yAxisValues);
        for (YAxis yAxis : yAxisValues) {
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
        Optional.ofNullable(board.get(Position.from(xAxis, yAxis)))
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
