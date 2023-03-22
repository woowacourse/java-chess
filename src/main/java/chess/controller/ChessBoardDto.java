package chess.controller;

import chess.chessboard.ChessBoard;
import chess.chessboard.File;
import chess.chessboard.Square;
import chess.piece.Piece;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class ChessBoardDto {
    private final List<List<PieceDto>> pieceDtos;

    private ChessBoardDto(List<List<PieceDto>> pieceDtos) {
        this.pieceDtos = pieceDtos;
    }

    public static ChessBoardDto of(ChessBoard chessBoard) {
        final Map<Square, Piece> pieces = chessBoard.getPieces();
        final List<List<PieceDto>> pieceDtos = initChessBoard();

        for (Square square : pieces.keySet()) {
            final File file = square.getFile();
            final int rowIndex = getRowIndex(square);
            final int columnIndex = getColumnIndex(file);
            final List<PieceDto> row = pieceDtos.get(rowIndex);
            final Piece piece = pieces.get(square);
            row.set(columnIndex, PieceDto.from(piece));
        }
        return new ChessBoardDto(pieceDtos);
    }

    private static List<List<PieceDto>> initChessBoard() {
        List<List<PieceDto>> chessBoard = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            chessBoard.add(new ArrayList<>(Collections.nCopies(8, null)));
        }
        return chessBoard;
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
