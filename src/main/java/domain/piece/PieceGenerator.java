package domain.piece;

import domain.chessboard.Square;
import domain.game.ChessBoard;
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
import java.util.List;
import java.util.stream.IntStream;

public class PieceGenerator {
    private static final int CHESS_BOARD_SIZE = 8;
    private static final List<PieceRole> BACK = List.of(
            new Rook(), new Knight(), new Bishop(), new Queen(), new King(), new Bishop(), new Knight(), new Rook());
    private static final List<PieceRole> FRONT_BLACK = IntStream.range(1, CHESS_BOARD_SIZE)
            .mapToObj(number -> (PieceRole) new Pawn(Color.BLACK))
            .toList();

    private static final List<PieceRole> FRONT_WHITE = IntStream.range(1, CHESS_BOARD_SIZE)
            .mapToObj(number -> (PieceRole) new Pawn(Color.WHITE))
            .toList();


    private PieceGenerator() {
    }

    public static void generate(final ChessBoard mover) {
        for (int row = CHESS_BOARD_SIZE; row >= 1; row--) {
            List<Piece> pieces = generateRankPieces(row);
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

    public static List<Piece> generateRankPieces(final int row) {
        if (row == 8) {
            return generateListPiece(BACK, Color.BLACK);
        }
        if (row == 7) {
            return generateListPiece(FRONT_BLACK, Color.BLACK);
        }
        if (row == 2) {
            return generateListPiece(FRONT_WHITE, Color.WHITE);
        }
        if (row == 1) {
            return generateListPiece(BACK, Color.WHITE);
        }
        return new ArrayList<>();
    }

    private static List<Piece> generateListPiece(final List<PieceRole> pieceRoles, final Color color) {
        return pieceRoles.stream()
                .map(pieceRole -> new Piece(pieceRole, color)).toList();
    }
}
