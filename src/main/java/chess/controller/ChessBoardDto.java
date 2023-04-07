package chess.controller;

import chess.domain.chessboard.ChessBoard;
import chess.domain.piece.Piece;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;

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

    public static ChessBoardDto from(ChessBoard chessBoard) {
        final Map<Position, Piece> chessBoardPieces = chessBoard.getPieces();
        final List<List<PieceDto>> pieceDtos = initChessBoard();

        for (Position position : chessBoardPieces.keySet()) {
            final PieceDto nextPieceDto = getNextPieceDto(chessBoardPieces, position);
            addNextPieceDto(pieceDtos, position, nextPieceDto);
        }
        return new ChessBoardDto(pieceDtos);
    }

    private static PieceDto getNextPieceDto(final Map<Position, Piece> chessBoardPieces, final Position position) {
        final Piece piece = chessBoardPieces.get(position);

        return PieceDto.from(piece);
    }

    private static void addNextPieceDto(final List<List<PieceDto>> pieceDtos, final Position position, final PieceDto nextPieceDto) {
        final int rowIndex = getRowIndex(position);
        final int columnIndex = getColumnIndex(position);
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

    private static int getRowIndex(final Position position) {
        final Rank rank = position.getRank();

        return CHESS_BOARD_SIZE - rank.getPosition();
    }

    private static int getColumnIndex(final Position position) {
        final File file = position.getFile();

        return file.getPosition() - 1;
    }

    public List<List<PieceDto>> getPieceDtos() {
        return pieceDtos;
    }
}
