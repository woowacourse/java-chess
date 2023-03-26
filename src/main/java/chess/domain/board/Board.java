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
import chess.exception.PathBlockedException;
import chess.exception.PawnMoveDiagonalException;
import chess.exception.PawnMoveForwardException;
import chess.exception.TargetSameTeamException;
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
        Piece sourcePiece = getPiece(source);
        validateMovable(source, target);
        sourcePiece.validateMovableRange(source, target);
        movePieceSourceToTarget(source, target);
    }

    private void validateMovable(Square source, Square target) {
        Direction direction = Direction.calculateDirection(source, target);

        validatePathBlocked(source, target, direction);
        if (isSameRole(source, Role.PAWN)) {
            validatePawnPathBlocked(target, direction);
        }
    }

    private void validatePathBlocked(Square source, Square target, Direction direction) {
        if (isBlocked(source, target, direction) && !isSameRole(source, Role.KNIGHT)) {
            throw new PathBlockedException();
        }
        if (isSameTeam(source, target)) {
            throw new TargetSameTeamException();
        }
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

    private void validatePawnPathBlocked(Square target, Direction direction) {
        boolean isTargetEmpty = getPiece(target).isEmpty();
        if (!isTargetEmpty && Direction.isMoveForward(direction)) {
            throw new PawnMoveForwardException();
        }
        if (isTargetEmpty && Direction.isMoveDiagonal(direction)) {
            throw new PawnMoveDiagonalException();
        }
    }

    private void movePieceSourceToTarget(Square source, Square target) {
        moveIfPawn(source);
        board.put(target, getPiece(source));
        board.put(source, new Empty());
    }

    private void moveIfPawn(Square source) {
        if (isSameRole(source, Role.PAWN)) {
            board.put(source, new Pawn(getPiece(source).getTeam(), IS_MOVED));
        }
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
