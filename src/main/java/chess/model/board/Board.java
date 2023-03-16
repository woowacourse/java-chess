package chess.model.board;

import static chess.model.piece.PieceColor.BLACK;
import static chess.model.piece.PieceColor.WHITE;
import static chess.model.piece.PieceType.BISHOP;
import static chess.model.piece.PieceType.KING;
import static chess.model.piece.PieceType.KNIGHT;
import static chess.model.piece.PieceType.PAWN;
import static chess.model.piece.PieceType.QUEEN;
import static chess.model.piece.PieceType.ROOK;

import chess.model.piece.Direction;
import chess.model.piece.IndexConverter;
import chess.model.piece.Piece;
import chess.model.piece.PieceColor;
import chess.model.piece.PieceFactory;
import chess.model.piece.PieceType;
import chess.model.position.Distance;
import chess.model.position.File;
import chess.model.position.Position;
import chess.model.position.Rank;
import java.util.LinkedList;
import java.util.List;

public class Board {

    private static final List<PieceType> INITIAL_BACK_PIECES = List.of(ROOK, KNIGHT, BISHOP, QUEEN,
            KING, BISHOP, KNIGHT, ROOK);
    private static final List<PieceType> INITIAL_FRONT_PIECES = List.of(PAWN, PAWN, PAWN, PAWN,
            PAWN, PAWN, PAWN, PAWN);
    private static final int WHITE_BACK_START_POSITION = 0;
    private static final int WHITE_FRONT_START_POSITION = 8;
    private static final int BLACK_FRONT_START_POSITION = 48;
    private static final int BLACK_BACK_START_POSITION = 56;
    private static final int MINIMUM_TRY = 1;

    private final List<Square> squares;

    private Board(final List<Square> squares) {
        this.squares = squares;
    }

    public static Board create() {
        final List<Square> squares = createSquares();

        initializePieces(squares);

        return new Board(squares);
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

    private static void initializeBackPieces(final List<Square> squares,
            final PieceColor pieceColor, int position) {
        for (PieceType pieceType : INITIAL_BACK_PIECES) {
            final Square square = squares.get(position);
            final Piece piece = PieceFactory.create(pieceColor, pieceType);

            squares.remove(position);
            squares.add(position++, square.receivePiece(piece));
        }
    }

    private static void initializeFrontPieces(final List<Square> squares,
            final PieceColor pieceColor, int position) {
        for (PieceType pieceType : INITIAL_FRONT_PIECES) {
            final Square square = squares.get(position);
            final Piece piece = PieceFactory.create(pieceColor, pieceType);

            squares.remove(position);
            squares.add(position++, square.receivePiece(piece));
        }
    }

    public void move(final Position source, final Position target, final PieceColor pieceColor) {
        validateSource(source, pieceColor);
        validatePath(source, target, pieceColor);

        movePiece(source, target, pieceColor);

        final int sourceIndex = source.convertToIndex();
        final int targetIndex = target.convertToIndex();

        updateBoard(sourceIndex, targetIndex);
    }

    private void movePiece(final Position source, final Position target,
            final PieceColor pieceColor) {
        final Square sourceSquare = squares.get(source.convertToIndex());

        final Distance distance = target.differ(source);
        sourceSquare.validateMovable(distance);

        final int targetIndex = target.convertToIndex();
        if (sourceSquare.hasPawn()) {
            validatePawn(pieceColor, squares.get(targetIndex), distance.findDirection());
        }
    }

    private void updateBoard(final int sourceIndex, final int targetIndex) {
        final Square sourceSquare = squares.get(sourceIndex);

        final Square emptySquare = sourceSquare.removePiece();
        updateSquare(sourceIndex, emptySquare);

        final Square targetSquare = squares.get(targetIndex);
        final Square resultSquare = targetSquare.receivePiece(sourceSquare.pick());
        updateSquare(targetIndex, resultSquare);
    }

    private void validatePawn(final PieceColor pieceColor, final Square targetSquare,
            final Direction direction) {
        if (Direction.isDiagonal(direction)) {
            validatePawnAttack(pieceColor, targetSquare);
            return;
        }

        targetSquare.validatePassable();
    }

    private void validatePawnAttack(final PieceColor pieceColor, final Square targetSquare) {
        if (targetSquare.isEmpty() || targetSquare.isSameTeam(pieceColor)) {
            throw new IllegalArgumentException("해당 기물은 지정한 방향으로 움직일 수 없습니다.");
        }
    }

    private void updateSquare(final int index, final Square square) {
        squares.remove(index);
        squares.add(index, square);
    }

    private void validateSource(final Position source, final PieceColor pieceColor) {
        final int sourceIndex = source.convertToIndex();
        final Square sourceSquare = squares.get(sourceIndex);

        sourceSquare.validateExistence(pieceColor);
    }

    private void validatePath(final Position source, final Position target,
            final PieceColor pieceColor) {
        final Distance distance = target.differ(source);
        final Direction direction = distance.findDirection();
        final int totalDistance = distance.convertToIndex();

        validatePathSquare(source, direction, totalDistance);
        validateTarget(target, pieceColor);
    }

    private void validatePathSquare(final Position source, final Direction direction,
            final int totalDistance) {
        int count = IndexConverter.findCount(direction, totalDistance);

        final int sourceIndex = source.convertToIndex();

        checkPathSquare(direction, count, sourceIndex);
    }

    private void checkPathSquare(final Direction direction, int count, final int sourceIndex) {
        int nowIndex = IndexConverter.findNextIndex(direction, sourceIndex);

        while (count-- > MINIMUM_TRY) {
            final Square square = squares.get(nowIndex);

            square.validatePassable();
            nowIndex = IndexConverter.findNextIndex(direction, sourceIndex);
        }
    }

    private void validateTarget(final Position target, final PieceColor pieceColor) {
        final int targetIndex = target.convertToIndex();
        final Square targetSquare = squares.get(targetIndex);

        targetSquare.validateEnemyPiece(pieceColor);
    }

    public List<Square> getSquares() {
        return List.copyOf(squares);
    }
}
