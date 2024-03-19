package domain.piece;

import static domain.piece.PieceRole.BISHOP;
import static domain.piece.PieceRole.KING;
import static domain.piece.PieceRole.KNIGHT;
import static domain.piece.PieceRole.PAWN;
import static domain.piece.PieceRole.QUEEN;
import static domain.piece.PieceRole.ROOK;

import domain.chessboard.Square;
import domain.game.PieceMover;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class PieceGenerator {
    private static final List<PieceRole> BACK = List.of(ROOK, KNIGHT, BISHOP, QUEEN, KING, BISHOP, KNIGHT, ROOK);
    private static final List<PieceRole> FRONT = List.of(PAWN, PAWN, PAWN, PAWN, PAWN, PAWN, PAWN, PAWN);

    public static void generate(final PieceMover mover) {
        for (int row = 0; row < 8; row++) {
            List<Piece> pieces = generateRankPieces(row);
            for (int column = 0; column < pieces.size(); column++) {
                Square square = new Square(new Position(column, row));
                Piece piece = pieces.get(column);
                mover.add(square, piece);
            }
        }
    }

    public static List<Piece> generateRankPieces(final int row) {
        if (row == 0) {
            return generateListPiece(BACK, Color.BLACK, row);
        }
        if (row == 1) {
            return generateListPiece(FRONT, Color.BLACK, row);
        }
        if (row == 6) {
            return generateListPiece(FRONT, Color.WHITE, row);
        }
        if (row == 7) {
            return generateListPiece(BACK, Color.WHITE, row);
        }
        return new ArrayList<>();
    }

    private static List<Piece> generateListPiece(final List<PieceRole> pieceRoles, final Color color, final int row) {
        return IntStream.range(0, pieceRoles.size())
                .mapToObj(column ->
                        new Piece(new PieceType(pieceRoles.get(column), color), new Position(column, row)))
                .toList();
    }
}
