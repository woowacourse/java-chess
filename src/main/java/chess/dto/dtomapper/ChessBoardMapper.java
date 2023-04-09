package chess.dto.dtomapper;

import chess.domain.chessboard.ChessBoard;
import chess.domain.piece.Piece;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import chess.dto.PieceDto;

import java.util.Collection;

public final class ChessBoardMapper {

    private static final int CHESS_BOARD_SIZE = 8;
    private static final PieceMapper PIECE_MAPPER = new PieceMapper();

    public PieceDto[][] toDto(ChessBoard chessBoard) {
        PieceDto[][] dto = getEmptyBoard();
        return putPieces(dto, chessBoard);
    }

    private PieceDto[][] getEmptyBoard() {
        PieceDto[][] dto = new PieceDto[CHESS_BOARD_SIZE][];
        for (int i = 0; i < dto.length; i++) {
            dto[i] = new PieceDto[CHESS_BOARD_SIZE];
        }
        return dto;
    }

    private PieceDto[][] putPieces(final PieceDto[][] dto, final ChessBoard chessBoard) {
        final Collection<Piece> pieces = chessBoard.getPieces()
                                                   .values();
        for (final Piece piece : pieces) {
            final int rowIndex = getRowIndex(piece.getPosition());
            final int columnIndex = getColumnIndex(piece.getPosition());
            dto[rowIndex][columnIndex] = PIECE_MAPPER.toDto(piece);
        }
        return dto;
    }

    private int getRowIndex(final Position position) {
        final Rank rank = position.getRank();
        return CHESS_BOARD_SIZE - rank.getPosition();
    }

    private int getColumnIndex(final Position position) {
        final File file = position.getFile();
        return file.getPosition() - 1;
    }
}
