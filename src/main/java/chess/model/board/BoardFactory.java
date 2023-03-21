package chess.model.board;

import static chess.model.piece.PieceColor.BLACK;
import static chess.model.piece.PieceColor.WHITE;
import static chess.model.piece.PieceType.BISHOP;
import static chess.model.piece.PieceType.KING;
import static chess.model.piece.PieceType.KNIGHT;
import static chess.model.piece.PieceType.PAWN;
import static chess.model.piece.PieceType.QUEEN;
import static chess.model.piece.PieceType.ROOK;
import static chess.model.position.Rank.EIGHTH;
import static chess.model.position.Rank.FIRST;
import static chess.model.position.Rank.SECOND;
import static chess.model.position.Rank.SEVENTH;

import chess.model.piece.PieceColor;
import chess.model.piece.PieceFactory;
import chess.model.piece.PieceType;
import chess.model.piece.type.Empty;
import chess.model.piece.type.Piece;
import chess.model.position.Position;
import chess.model.position.Positions;
import chess.model.position.Rank;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class BoardFactory {

    private static final List<PieceType> INITIAL_BACK_PIECES = List.of(ROOK, KNIGHT, BISHOP, QUEEN, KING, BISHOP, KNIGHT, ROOK);
    private static final List<PieceType> INITIAL_FRONT_PIECES = List.of(PAWN, PAWN, PAWN, PAWN, PAWN, PAWN, PAWN, PAWN);

    private BoardFactory() {
    }

    static Map<Position, Piece> create() {
        final Map<Position, Piece> squares = new HashMap<>();

        initializePieces(squares);

        return squares;
    }

    private static void initializePieces(final Map<Position, Piece> squares) {
        for (Position position : Positions.all()) {
            squares.put(position, Empty.getInstance());
        }

        initializeBackPieces(squares, FIRST, WHITE);
        initializeFrontPieces(squares, SECOND, WHITE);
        initializeFrontPieces(squares, SEVENTH, BLACK);
        initializeBackPieces(squares, EIGHTH, BLACK);
    }

    private static void initializeBackPieces(final Map<Position, Piece> squares, final Rank rank, final PieceColor pieceColor) {
        final List<Position> backPositions = Positions.getPositionsBy(rank);

        for (int i = 0, size = backPositions.size(); i < size; i++) {
            final Piece piece = PieceFactory.create(pieceColor, INITIAL_BACK_PIECES.get(i));
            squares.put(backPositions.get(i), piece);
        }
    }

    private static void initializeFrontPieces(final Map<Position, Piece> squares, final Rank rank, final PieceColor pieceColor) {
        final List<Position> frontPositions = Positions.getPositionsBy(rank);

        for (int i = 0, size = frontPositions.size(); i < size; i++) {
            final Piece piece = PieceFactory.create(pieceColor, INITIAL_FRONT_PIECES.get(i));
            squares.put(frontPositions.get(i), piece);
        }
    }
}
