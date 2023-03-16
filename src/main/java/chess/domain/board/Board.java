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
    Map<Square, Piece> board;

    public Board() {
        this.board = initializeBoard();
    }

    private LinkedHashMap<Square, Piece> initializeBoard() {
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

        for (int i = 0; i < 8; i++) {
            pieces.add(new Pawn(camp));
        }

        return pieces;
    }

    private List<Piece> generateEmptyLine() {
        final List<Piece> pieces = new ArrayList<>();

        for (int i = 0; i < 8; i++) {
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

    public boolean move(final Square source, final Square target) {
        if (isMovable(source, target)) {
            if (board.get(source).isSameRole(Role.PAWN)) {
                board.put(source, new Pawn(board.get(source).camp(), true));
            }
            board.put(target, board.get(source));
            board.put(source, new Empty());
            return true;
        }
        return false;
    }

    private boolean isMovable(final Square source, final Square target) {
        final Piece piece = board.get(source);
        final Move move = Move.calculateDirection(source, target);
        final boolean isPathBlocked = isPathBlocked(source, target, move);

        if (piece.isSameRole(Role.KNIGHT)) {
            final KnightMove knightMove = KnightMove.calculateDirection(source, target);
            return piece.isMovable(source, target, knightMove);
        }
        if (isPathBlocked) {
            return false;
        }
        if (piece.isSameRole(Role.PAWN)) {
            if ((move == Move.UP || move == Move.DOWN) && !board.get(target).isSameRole(Role.EMPTY)) {
                return false;
            }
            if ((move == Move.RIGHT_UP || move == Move.RIGHT_DOWN || move == Move.LEFT_UP || move == Move.LEFT_DOWN)
                    && !board.get(source).isSameCamp(board.get(target).camp())
                    && !board.get(target).isSameCamp(Camp.EMPTY)) {
                return false;
            }
        }
        return piece.isMovable(source, target, move);
    }

    private boolean isPathBlocked(final Square source, final Square target, final Move move) {
        if (move.equals(Move.EMPTY)) {
            final Piece piece = board.get(source);
            return piece.isSameRole(Role.KNIGHT);
        }
        return isBlocked(source, target, move) || isSameCamp(source, target);
    }

    private boolean isBlocked(final Square source, final Square target, final Move move) {
        final Square nextSquare = source.nextSquare(source, move.getFile(), move.getRank());
        final boolean isNextSquareEmpty = board.get(nextSquare).equals(new Empty());
        final boolean isNextSquareTarget = nextSquare.equals(target);
        if (isNextSquareTarget) {
            return false;
        }
        if (isNextSquareEmpty) {
            return isBlocked(nextSquare, target, move);
        }
        return true;
    }

    private boolean isSameCamp(final Square source, final Square target) {
        final Piece sourcePiece = board.get(source);
        final Camp targetCamp = board.get(target).camp();

        return sourcePiece.isSameCamp(targetCamp);
    }

    public Piece getPiece(final Square square) {
        return board.get(square);
    }

    public List<Piece> getPieces() {
        return new ArrayList<>(board.values());
    }

    public boolean isEmptyPiece(final Square source) {
        return board.get(source).equals(new Empty());
    }
}
