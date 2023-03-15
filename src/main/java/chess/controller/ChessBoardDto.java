package chess.controller;

import chess.domain.File;
import chess.domain.Piece;
import chess.domain.Square;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class ChessBoardDto {
    private final List<List<PieceDto>> pieceDtos;

    private ChessBoardDto(List<List<PieceDto>> pieceDtos) {
        this.pieceDtos = pieceDtos;
    }

    public static ChessBoardDto of(Map<Square, Piece> pieces) {
        final List<List<PieceDto>> chessBoard = initChessBoard();

        for (Square square : pieces.keySet()) {
            final File file = square.getFile();
            final int row = getRowIndex(square);
            final int column = getColumnIndex(file);
            chessBoard.get(row)
                      .set(column, PieceDto.from(pieces.get(square)));
        }
        return new ChessBoardDto(chessBoard);
    }

    private static ArrayList<List<PieceDto>> initChessBoard() {
        final List<PieceDto> emptyRank = Collections.nCopies(8, null);
        return new ArrayList<>(Collections.nCopies(8, emptyRank));
    }

    private static int getRowIndex(final Square square) {
        return 8 - square.getRank();
    }

    private static int getColumnIndex(final File file) {
        return file.getPosition() - 1;
    }

    public List<List<PieceDto>> getPieceDtos() {
        return pieceDtos;
    }
}
