package chess.controller;

import chess.domain.File;
import chess.domain.Piece;
import chess.domain.Square;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class ChessBoardDto {
    private List<List<String>> chessBoard;

    public static ChessBoardDto of(Map<Square, Piece> pieces) {
        final List<List<String>> chessBoard = initChessBoard();
        for (Square square : pieces.keySet()) {
            final File file = square.getFile();
            final int row = 8 - square.getRank();
            final int column = file.getPosition() - 1;
            chessBoard.get(row)
                      .set(column, PieceDto.from(pieces.get(square)));
        }
    }

    private static List<List<String>> initChessBoard() {
        List<List<String>> chessBoard = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            chessBoard.add(new ArrayList<>(Collections.nCopies(8, ".")));
        }
        return chessBoard;
    }
}
