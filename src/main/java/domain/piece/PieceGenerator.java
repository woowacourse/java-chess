package domain.piece;

import domain.chessboard.Square;
import domain.game.PieceMover;
import domain.piece.piecerole.Bishop;
import domain.piece.piecerole.King;
import domain.piece.piecerole.Knight;
import domain.piece.piecerole.Pawn;
import domain.piece.piecerole.PieceRole;
import domain.piece.piecerole.Queen;
import domain.piece.piecerole.Rook;
import domain.position.File;
import domain.position.Position;
import domain.position.Rank;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class PieceGenerator {
    private static final List<PieceRole> BACK = List.of(
            new Rook(), new Knight(), new Bishop(), new Queen(), new King(), new Bishop(), new Knight(), new Rook());
    private static final List<PieceRole> FRONT_BLACK = Collections.nCopies(8, new Pawn(Color.BLACK));

    private static final List<PieceRole> FRONT_WHITE = Collections.nCopies(8, new Pawn(Color.WHITE));

    private PieceGenerator() {
    }

    public static void generate(final PieceMover mover) {
        for (int row = 8; row >= 1; row--) {
            List<Piece> pieces = generateRankPieces(row);
            for (int column = 0; column < pieces.size(); column++) {
                Square square = new Square(new Position(new File((char) ('a' + column)), new Rank(row)));
                Piece piece = pieces.get(column);
                mover.add(square, piece);
            }
        }
    }

    public static List<Piece> generateRankPieces(final int row) {
        if (row == 8) {
            return generateListPiece(BACK, Color.BLACK, row);
        }
        if (row == 7) {
            return generateListPiece(FRONT_BLACK, Color.BLACK, row);
        }
        if (row == 2) {
            return generateListPiece(FRONT_WHITE, Color.WHITE, row);
        }
        if (row == 1) {
            return generateListPiece(BACK, Color.WHITE, row);
        }
        return new ArrayList<>();
    }

    private static List<Piece> generateListPiece(final List<PieceRole> pieceRoles, final Color color, final int row) {
        return IntStream.range(0, pieceRoles.size())
                .mapToObj(column ->
                        new Piece(new PieceType(pieceRoles.get(column), color),
                                new Position(
                                        new File((char) ('a' + column)), new Rank(row)))).toList();

    }
}
