package chess.controller;

import chess.chessboard.ChessBoard;
import chess.chessboard.File;
import chess.chessboard.Rank;
import chess.chessboard.Square;
import chess.piece.Piece;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class ChessBoardDto {
    private static final int CHESS_BOARD_SIZE = 8;
    private final List<List<PieceDto>> pieceDtos;

    private ChessBoardDto(List<List<PieceDto>> pieceDtos) {
        this.pieceDtos = pieceDtos;
    }

    public static ChessBoardDto of(ChessBoard chessBoard) {
        final Map<Square, Piece> chessBoardPieces = chessBoard.getPieces();
        final List<List<PieceDto>> pieceDtos = initChessBoard();

        for (Square square : chessBoardPieces.keySet()) {
            final PieceDto nextPieceDto = getNextPieceDto(chessBoardPieces, square);
            addNextPieceDto(pieceDtos, square, nextPieceDto);
        }
        return new ChessBoardDto(pieceDtos);
    }

    private static PieceDto getNextPieceDto(final Map<Square, Piece> chessBoardPieces, final Square square) {
        final Piece piece = chessBoardPieces.get(square);

        return PieceDto.from(piece);
    }

    private static void addNextPieceDto(final List<List<PieceDto>> pieceDtos, final Square square, final PieceDto nextPieceDto) {
        final int rowIndex = getRowIndex(square);
        final int columnIndex = getColumnIndex(square);
        final List<PieceDto> row = pieceDtos.get(rowIndex);

        row.set(columnIndex, nextPieceDto);
    }

    private static List<List<PieceDto>> initChessBoard() {
        List<List<PieceDto>> chessBoard = new ArrayList<>();

        for (int i = 0; i < CHESS_BOARD_SIZE; i++) {
            chessBoard.add(generateEmptyRank());
        }

        return chessBoard;
    }

    private static ArrayList<PieceDto> generateEmptyRank() {
        return new ArrayList<>(Collections.nCopies(CHESS_BOARD_SIZE, null));
    }

    private static int getRowIndex(final Square square) {
        final Rank rank = square.getRank();

        return CHESS_BOARD_SIZE - rank.getPosition();
    }

    private static int getColumnIndex(final Square square) {
        final File file = square.getFile();

        return file.getPosition() - 1;
    }

    public List<List<PieceDto>> getPieceDtos() {
        return pieceDtos;
    }
}
