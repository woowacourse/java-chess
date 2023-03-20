package chess.model.board;

import static chess.model.piece.PieceColor.BLACK;
import static chess.model.piece.PieceColor.WHITE;
import static chess.model.piece.PieceType.BISHOP;
import static chess.model.piece.PieceType.KING;
import static chess.model.piece.PieceType.KNIGHT;
import static chess.model.piece.PieceType.PAWN;
import static chess.model.piece.PieceType.QUEEN;
import static chess.model.piece.PieceType.ROOK;

import chess.model.piece.Piece;
import chess.model.piece.PieceColor;
import chess.model.piece.PieceFactory;
import chess.model.piece.PieceType;
import chess.model.position.File;
import chess.model.position.Position;
import chess.model.position.Rank;
import java.util.LinkedList;
import java.util.List;

class BoardFactory {

    private static final List<PieceType> INITIAL_BACK_PIECES = List.of(ROOK, KNIGHT, BISHOP, QUEEN,
            KING, BISHOP, KNIGHT, ROOK);
    private static final List<PieceType> INITIAL_FRONT_PIECES = List.of(PAWN, PAWN, PAWN, PAWN,
            PAWN, PAWN, PAWN, PAWN);
    private static final int WHITE_BACK_START_POSITION = 0;
    private static final int WHITE_FRONT_START_POSITION = 8;
    private static final int BLACK_FRONT_START_POSITION = 48;
    private static final int BLACK_BACK_START_POSITION = 56;

    private BoardFactory() {
    }

    static List<Square> create() {
        final List<Square> squares = createSquares();

        initializePieces(squares);

        return squares;
    }

    private static List<Square> createSquares() {
        final List<Square> squares = new LinkedList<>();

        for (File file : File.values()) {
            createSquare(squares, file);
        }

        return squares;
    }

    private static void createSquare(final List<Square> squares, final File file) {
        for (Rank rank : Rank.values()) {
            final Position position = new Position(file, rank);

            squares.add(new EmptySquare(position));
        }
    }

    private static void initializePieces(final List<Square> squares) {
        initializeBackPieces(squares, WHITE, WHITE_BACK_START_POSITION);
        initializeFrontPieces(squares, WHITE, WHITE_FRONT_START_POSITION);
        initializeFrontPieces(squares, BLACK, BLACK_FRONT_START_POSITION);
        initializeBackPieces(squares, BLACK, BLACK_BACK_START_POSITION);
    }

    private static void initializeBackPieces(final List<Square> squares, final PieceColor pieceColor, int position) {
        for (PieceType pieceType : INITIAL_BACK_PIECES) {
            final Square square = squares.get(position);
            final Piece piece = PieceFactory.create(pieceColor, pieceType);

            squares.remove(position);
            squares.add(position++, square.receivePiece(piece));
        }
    }

    private static void initializeFrontPieces(final List<Square> squares, final PieceColor pieceColor, int position) {
        for (PieceType pieceType : INITIAL_FRONT_PIECES) {
            final Square square = squares.get(position);
            final Piece piece = PieceFactory.create(pieceColor, pieceType);

            squares.remove(position);
            squares.add(position++, square.receivePiece(piece));
        }
    }
}
