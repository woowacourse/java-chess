package chess.domain.board;

import chess.domain.piece.Bishop;
import chess.domain.piece.Empty;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Role;
import chess.domain.piece.Rook;
import chess.domain.piece.Team;
import chess.exception.PieceCanNotMoveException;
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
        board = generateBoard();
    }

    private Map<Square, Piece> generateBoard() {
        Map<Square, Piece> board = new LinkedHashMap<>();
        List<Piece> pieces = generatePieces();
        List<Square> squares = generateSquares();

        for (int i = 0; i < squares.size(); i++) {
            board.put(squares.get(i), pieces.get(i));
        }

        return board;
    }

    private List<Piece> generatePieces() {
        List<Piece> pieces = new ArrayList<>();

        pieces.addAll(generateFirstLine(Team.BLACK));
        pieces.addAll(generateSecondLine(Team.BLACK));
        pieces.addAll(generateEmptyLine());
        pieces.addAll(generateEmptyLine());
        pieces.addAll(generateEmptyLine());
        pieces.addAll(generateEmptyLine());
        pieces.addAll(generateSecondLine(Team.WHITE));
        pieces.addAll(generateFirstLine(Team.WHITE));

        return pieces;
    }

    private List<Piece> generateFirstLine(Team team) {
        List<Piece> pieces = new ArrayList<>();

        pieces.add(new Rook(team));
        pieces.add(new Knight(team));
        pieces.add(new Bishop(team));
        pieces.add(new Queen(team));
        pieces.add(new King(team));
        pieces.add(new Bishop(team));
        pieces.add(new Knight(team));
        pieces.add(new Rook(team));

        return pieces;
    }

    private List<Piece> generateSecondLine(Team team) {
        List<Piece> pieces = new ArrayList<>();

        for (int i = 0; i < BOARD_LINE_SIZE; i++) {
            pieces.add(new Pawn(team));
        }

        return pieces;
    }

    private List<Piece> generateEmptyLine() {
        List<Piece> pieces = new ArrayList<>();

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

    public void move(Square source, Square target) {
        if (isMovable(source, target)) {
            moveIfPawn(source);
            board.put(target, getPiece(source));
            board.put(source, new Empty());
            return;
        }
        throw new PieceCanNotMoveException();
    }

    private void moveIfPawn(Square source) {
        if (isSameRole(source, Role.PAWN)) {
            board.put(source, new Pawn(getPiece(source).getTeam(), IS_MOVED));
        }
    }

    private boolean isMovable(Square source, Square target) {
        Piece sourcePiece = getPiece(source);
        Direction direction = Direction.calculateDirection(source, target);

        if (isPathBlocked(source, target, direction) && !isSameRole(source, Role.KNIGHT)) {
            return false;
        }
        if (isSameRole(source, Role.PAWN)) {
            return isPawnMovable(source, target, direction);
        }
        return sourcePiece.isMovable(source, target, direction);
    }

    private boolean isPawnMovable(Square source, Square target, Direction direction) {
        boolean isTargetEmpty = getPiece(target).isEmpty();

        if (Direction.isMoveForward(direction) && !isTargetEmpty) {
            return false;
        }
        if (Direction.isMoveDiagonal(direction) && (isSameTeam(source, target) || isTargetEmpty)) {
            return false;
        }
        return getPiece(source).isMovable(source, target, direction);
    }

    private boolean isPathBlocked(Square source, Square target, Direction direction) {
        return isBlocked(source, target, direction) || isSameTeam(source, target);
    }

    private boolean isBlocked(Square source, Square target, Direction direction) {
        Square nextSquare = source.nextSquare(source, direction.getFile(), direction.getRank());

        if (nextSquare.equals(target)) {
            return false;
        }
        if (isEmptyPiece(nextSquare)) {
            return isBlocked(nextSquare, target, direction);
        }
        return true;
    }

    private boolean isSameTeam(Square source, Square target) {
        Piece sourcePiece = getPiece(source);
        Team targetTeam = getPiece(target).getTeam();

        return sourcePiece.isSameTeam(targetTeam);
    }

    private boolean isSameRole(Square source, Role role) {
        Piece sourcePiece = getPiece(source);

        return sourcePiece.isSameRole(role);
    }

    public boolean isEmptyPiece(Square source) {
        return getPiece(source).isEmpty();
    }

    public List<Piece> getPieces() {
        return new ArrayList<>(board.values());
    }

    public Piece getPiece(Square source) {
        return board.get(source);
    }
}
