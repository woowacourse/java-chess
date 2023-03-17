package chess.domain.board;

import chess.domain.piece.Bishop;
import chess.domain.piece.Camp;
import chess.domain.piece.Empty;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Role;
import chess.domain.piece.Rook;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Board {
    private static final boolean IS_MOVED = true;
    private static final int BOARD_LINE_SIZE = 8;

    private final Map<Square, Piece> board;

    public Board() {
        this.board = generateBoard();
    }

    private LinkedHashMap<Square, Piece> generateBoard() {
        final LinkedHashMap<Square, Piece> board = new LinkedHashMap<>();
        final List<Piece> pieces = generatePieces();
        final List<Square> squares = generateSquares();

        for (int i = 0; i < squares.size(); i++) {
            board.put(squares.get(i), pieces.get(i));
        }

        return board;
    }

    private List<Piece> generatePieces() {
        final List<Piece> pieces = new ArrayList<>();

        pieces.addAll(generateFirstLine(Camp.BLACK));
        pieces.addAll(generateSecondLine(Camp.BLACK));
        pieces.addAll(generateEmptyLine());
        pieces.addAll(generateEmptyLine());
        pieces.addAll(generateEmptyLine());
        pieces.addAll(generateEmptyLine());
        pieces.addAll(generateSecondLine(Camp.WHITE));
        pieces.addAll(generateFirstLine(Camp.WHITE));

        return pieces;
    }

    private List<Piece> generateFirstLine(final Camp camp) {
        final List<Piece> pieces = new ArrayList<>();

        pieces.add(new Rook(camp));
        pieces.add(new Knight(camp));
        pieces.add(new Bishop(camp));
        pieces.add(new Queen(camp));
        pieces.add(new King(camp));
        pieces.add(new Bishop(camp));
        pieces.add(new Knight(camp));
        pieces.add(new Rook(camp));

        return pieces;
    }

    private List<Piece> generateSecondLine(final Camp camp) {
        final List<Piece> pieces = new ArrayList<>();

        for (int i = 0; i < BOARD_LINE_SIZE; i++) {
            pieces.add(new Pawn(camp));
        }

        return pieces;
    }

    private List<Piece> generateEmptyLine() {
        final List<Piece> pieces = new ArrayList<>();

        for (int i = 0; i < BOARD_LINE_SIZE; i++) {
            pieces.add(new Empty());
        }

        return pieces;
    }

    private List<Square> generateSquares() {
        return Arrays.stream(Rank.values())
                .flatMap(rank -> Arrays.stream(File.values())
                        .map(file -> new Square(file, rank)))
                .collect(Collectors.toList());
    }

    public void move(final Square source, final Square target) {
        if (isMovable(source, target)) {
            moveIfPawn(source);
            board.put(target, board.get(source));
            board.put(source, new Empty());
            return;
        }
        throw new IllegalArgumentException("이동할 수 없습니다.");
    }

    private void moveIfPawn(final Square source) {
        if (isSameRole(source, Role.PAWN)) {
            board.put(source, new Pawn(board.get(source).getCamp(), IS_MOVED));
        }
    }

    private boolean isMovable(final Square source, final Square target) {
        final Piece sourcePiece = board.get(source);
        final Move move = Move.calculateDirection(source, target);

        if (isSameRole(source, Role.KNIGHT)) {
            return isKnightMovable(source, target, sourcePiece);
        }
        if (isPathBlocked(source, target, move)) {
            return false;
        }
        if (isSameRole(source, Role.PAWN)) {
            return isPawnMovable(source, target, move);
        }
        return sourcePiece.isMovable(source, target, move);
    }

    private boolean isKnightMovable(final Square source, final Square target, final Piece piece) {
        final KnightMove knightMove = KnightMove.calculateDirection(source, target);
        return piece.isMovable(source, target, knightMove);
    }

    private boolean isPawnMovable(final Square source, final Square target, final Move move) {
        final boolean isTargetEmpty = board.get(target).equals(new Empty());

        if (Move.isMoveForward(move) && !isTargetEmpty) {
            return false;
        }
        if (Move.isMoveDiagonal(move) && (isSameCamp(source, target) || isTargetEmpty)) {
            return false;
        }
        return board.get(source).isMovable(source, target, move);
    }

    private boolean isPathBlocked(final Square source, final Square target, final Move move) {
        if (move.equals(Move.EMPTY)) {
            return isSameRole(source, Role.KNIGHT);
        }
        return isBlocked(source, target, move) || isSameCamp(source, target);
    }

    private boolean isBlocked(final Square source, final Square target, final Move move) {
        final Square nextSquare = source.nextSquare(source, move.getFile(), move.getRank());

        if (nextSquare.equals(target)) {
            return false;
        }
        if (isEmptyPiece(nextSquare)) {
            return isBlocked(nextSquare, target, move);
        }
        return true;
    }

    private boolean isSameCamp(final Square source, final Square target) {
        final Piece sourcePiece = board.get(source);
        final Camp targetCamp = board.get(target).getCamp();

        return sourcePiece.isSameCamp(targetCamp);
    }

    private boolean isSameRole(final Square source, final Role role) {
        final Piece sourcePiece = board.get(source);

        return sourcePiece.isSameRole(role);
    }

    public boolean isNotMyTurn(final Square source, final Camp turn) {
        return board.get(source).isAnotherCamp(turn);
    }

    public boolean isEmptyPiece(final Square source) {
        return board.get(source).equals(new Empty());
    }

    public List<Piece> getPieces() {
        return new ArrayList<>(board.values());
    }
}
