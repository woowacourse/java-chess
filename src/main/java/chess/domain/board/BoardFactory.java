package chess.domain.board;

import chess.domain.piece.ColorType;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.Position;

import java.util.HashMap;
import java.util.Map;

// TODO: 코드 리팩토링
public class BoardFactory {

    private static final int RANK_START = 1;
    private static final int RANK_END = 8;
    private static final char FILE_START = 'a';
    private static final char FILE_END = 'h';

    public BoardFactory() {

    }

    public Map<Position, Piece> create() {
        Map<Position, Piece> board = new HashMap<>();

        for (int rank = RANK_START; rank <= RANK_END; rank++) {
            createByRank(rank, board);
        }

        return board;
    }

    private void createByRank(int rank, Map<Position, Piece> board) {
        for (char file = FILE_START; file <= FILE_END; file++) {
            Position position = new Position(file, rank);
            Piece piece = new Piece(PieceType.EMPTY, ColorType.EMPTY);

            if (rank == 1 || rank == 8) {
                piece = makePiece(file, piece, rank);
            }

            if (rank == 2 || rank == 7) {
                piece = decideColorType(rank, PieceType.PAWN);
            }

            board.put(position, piece);
        }
    }

    private Piece makePiece(char file, Piece piece, int rank) {
        if (file == 'a' || file == 'h') {
            return decideColorType(rank, PieceType.ROOK);
        }

        if (file == 'b' || file == 'g') {
            return decideColorType(rank, PieceType.KNIGHT);
        }

        if (file == 'c' || file == 'f') {
            return decideColorType(rank, PieceType.BISHOP);
        }

        if (file == 'd') {
            return decideColorType(rank, PieceType.QUEEN);
        }

        if (file == 'e') {
            return decideColorType(rank, PieceType.KING);
        }

        return piece;
    }

    private Piece decideColorType(int rank, PieceType pieceType) {
        if (rank == 1 || rank == 2) {
            return new Piece(pieceType, ColorType.WHITE);
        }

        return new Piece(pieceType, ColorType.BLACK);
    }
}
