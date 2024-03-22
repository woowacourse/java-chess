package domain.piece;

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
    private static final List<PieceRole> BACK = List.of(
            new Rook(), new Knight(), new Bishop(), new Queen(), new King(), new Bishop(), new Knight(), new Rook());
    private static final List<PieceRole> FRONT_BLACK = IntStream.range(1, CHESS_BOARD_SIZE + 1)
            .mapToObj(number -> (PieceRole) new Pawn(Color.BLACK))
            .toList();

    private static final List<PieceRole> FRONT_WHITE = IntStream.range(1, CHESS_BOARD_SIZE + 1)
            .mapToObj(number -> (PieceRole) new Pawn(Color.WHITE))
            .toList();

    private static final Map<Integer, List<Piece>> rankPieces = new HashMap<>();

    static {
        rankPieces.put(8, generateListPiece(BACK, Color.BLACK));
        rankPieces.put(7, generateListPiece(FRONT_BLACK, Color.BLACK));
        rankPieces.put(2, generateListPiece(FRONT_WHITE, Color.WHITE));
        rankPieces.put(1, generateListPiece(BACK, Color.WHITE));
    }

    private PieceGenerator() {
    }

    public static Map<Square, Piece> generate() {
        Map<Square, Piece> initChessBoard = new HashMap<>();
        for (int row = CHESS_BOARD_SIZE; row >= 1; row--) {
            List<Piece> pieces = rankPieces.getOrDefault(row, new ArrayList<>());
            initializeSquares(initChessBoard, pieces, row);
        }
        return initChessBoard;
    }

    private static void initializeSquares(Map<Square, Piece> initChessBoard, List<Piece> pieces, int row) {
        for (int column = 0; column < pieces.size(); column++) {
            Square square = new Square(new Position(new File((char) ('a' + column)), new Rank(row)));
            Piece piece = pieces.get(column);
            initChessBoard.put(square, piece);
        }
    }

    private static List<Piece> generateListPiece(final List<PieceRole> pieceRoles, final Color color) {
        return pieceRoles.stream()
                .map(pieceRole -> new Piece(pieceRole, color)).toList();
    }
}
