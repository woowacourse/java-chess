package chess.model.board;

import chess.model.piece.Piece;

import java.util.List;

public class InitialBoardGenerator implements BoardGenerator {
    private static final List<Piece> INITIAL_PIECES = List.of(
            Piece.BLACK_ROOK, Piece.BLACK_KNIGHT, Piece.BLACK_BISHOP, Piece.BLACK_QUEEN,
            Piece.BLACK_KING, Piece.BLACK_BISHOP, Piece.BLACK_KNIGHT, Piece.BLACK_ROOK,
            Piece.BLACK_PAWN, Piece.BLACK_PAWN, Piece.BLACK_PAWN, Piece.BLACK_PAWN,
            Piece.BLACK_PAWN, Piece.BLACK_PAWN, Piece.BLACK_PAWN, Piece.BLACK_PAWN,
            Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY,
            Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY,
            Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY,
            Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY,
            Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY,
            Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY,
            Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY,
            Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY,
            Piece.WHITE_PAWN, Piece.WHITE_PAWN, Piece.WHITE_PAWN, Piece.WHITE_PAWN,
            Piece.WHITE_PAWN, Piece.WHITE_PAWN, Piece.WHITE_PAWN, Piece.WHITE_PAWN,
            Piece.WHITE_ROOK, Piece.WHITE_KNIGHT, Piece.WHITE_BISHOP, Piece.WHITE_QUEEN,
            Piece.WHITE_KING, Piece.WHITE_BISHOP, Piece.WHITE_KNIGHT, Piece.WHITE_ROOK
    );

    @Override
    public Board create() {
        List<Square> squares = INITIAL_PIECES.stream()
                .map(Square::new)
                .toList();
        return new Board(squares);
    }
}
