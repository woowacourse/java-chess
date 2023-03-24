package chess.view;

import chess.domain.Position;
import chess.domain.piece.Piece;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class BoardViewForm {
    private static final int MAX_ROW = 8;

    private BoardViewForm() {
    }

    public static String createChessBoard(final Map<Position, Piece> board) {
        final StringBuilder boardBuilder = new StringBuilder();
        appendBoardSquares(board, boardBuilder);
        return boardBuilder.toString();
    }

    private static void appendBoardSquares(final Map<Position, Piece> board, final StringBuilder boardBuilder) {
        final List<Position> positions = new ArrayList<>(board.keySet());
        Collections.sort(positions);

        IntStream.range(0, positions.size())
                .forEach(index -> {
                    final String pieceName = createName(board, positions, index);
                    appendPiecesName(boardBuilder, pieceName, index);
                });
    }

    private static void appendPiecesName(final StringBuilder boardBuilder, final String pieceName, final int index) {
        if (index % MAX_ROW == 0) {
            boardBuilder.append(System.lineSeparator());
        }
        boardBuilder.append(pieceName);
    }

    private static String createName(final Map<Position, Piece> board, final List<Position> positions, final int index) {
        final Position findPosition = positions.get(index);
        final Piece findPiece = board.get(findPosition);
        return PieceViewForm.parseToName(findPiece);
    }
}
