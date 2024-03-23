package domain.piece;

import domain.game.ChessBoard;
import domain.game.Square;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class PieceGenerator {
    private static final int CHESS_BOARD_SIZE = 8;
    private static final List<PieceRole> ROYAL_PIECES = List.of(
            Rook.from(), Knight.from(), Bishop.from(), Queen.from(),
            King.from(), Bishop.from(), Knight.from(), Rook.from()
    );
    private static final List<PieceRole> BLACK_PAWNS = IntStream.range(1, CHESS_BOARD_SIZE + 1)
            .mapToObj(number -> (PieceRole) Pawn.from(Color.BLACK))
            .toList();

    private static final List<PieceRole> WHITE_PAWNS = IntStream.range(1, CHESS_BOARD_SIZE + 1)
            .mapToObj(number -> (PieceRole) Pawn.from(Color.WHITE))
            .toList();

    private static final Map<Integer, List<Piece>> rankPieces = new HashMap<>();

    static {
        rankPieces.put(8, generateListPiece(ROYAL_PIECES, Color.BLACK));
        rankPieces.put(7, generateListPiece(BLACK_PAWNS, Color.BLACK));
        rankPieces.put(2, generateListPiece(WHITE_PAWNS, Color.WHITE));
        rankPieces.put(1, generateListPiece(ROYAL_PIECES, Color.WHITE));
    }

    private PieceGenerator() {
    }

    public static void generate(final ChessBoard mover) {
        for (int row = CHESS_BOARD_SIZE; row >= 1; row--) {
            List<Piece> pieces = rankPieces.getOrDefault(row, new ArrayList<>());
            initializeSquares(mover, pieces, row);
        }
    }

    private static void initializeSquares(ChessBoard mover, List<Piece> pieces, int row) {
        for (int column = 0; column < pieces.size(); column++) {
            Square square = new Square(new Position(new File((char) ('a' + column)), new Rank(row)));
            Piece piece = pieces.get(column);
            mover.add(square, piece);
        }
    }

    private static List<Piece> generateListPiece(final List<PieceRole> pieceRoles, final Color color) {
        return pieceRoles.stream()
                .map(pieceRole -> new Piece(pieceRole, color))
                .toList();
    }
}
