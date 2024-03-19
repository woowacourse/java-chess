package chess.domain.board;

import chess.domain.position.File;
import chess.domain.position.Square;
import chess.domain.position.Rank;
import chess.domain.piece.ColorType;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;

import java.util.HashMap;
import java.util.Map;

// TODO: 코드 리팩토링
public class BoardFactory {

    public BoardFactory() {

    }

    public Map<Square, Piece> create() {
        Map<Square, Piece> board = new HashMap<>();

        for (Rank rank : Rank.values()) {
            createByRank(rank, board);
        }

        return board;
    }

    private void createByRank(Rank rank, Map<Square, Piece> board) {
        for (File file : File.values()) {
            Square square = Square.of(file, rank);
            Piece piece = new Piece(PieceType.EMPTY, ColorType.EMPTY);

            if (rank.equals(Rank.ONE) || rank.equals(Rank.EIGHT)) {
                piece = makePiece(file, piece, rank);
            }

            if (rank.equals(Rank.TWO) || rank.equals(Rank.SEVEN)) {
                piece = decideColorType(rank, PieceType.PAWN);
            }

            board.put(square, piece);
        }
    }

    private Piece makePiece(File file, Piece piece, Rank rank) {
        if (file.equals(File.a) || file.equals(File.h)) {
            return decideColorType(rank, PieceType.ROOK);
        }

        if (file.equals(File.b) || file.equals(File.g)) {
            return decideColorType(rank, PieceType.KNIGHT);
        }

        if (file.equals(File.c) || file.equals(File.f)) {
            return decideColorType(rank, PieceType.BISHOP);
        }

        if (file.equals(File.d)) {
            return decideColorType(rank, PieceType.QUEEN);
        }

        if (file.equals(File.e)) {
            return decideColorType(rank, PieceType.KING);
        }

        return piece;
    }

    private Piece decideColorType(Rank rank, PieceType pieceType) {
        if (rank == Rank.ONE || rank == Rank.TWO) {
            return new Piece(pieceType, ColorType.WHITE);
        }

        return new Piece(pieceType, ColorType.BLACK);
    }
}
