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
        this.board = this.generateBoard();
    }

    private Map<Square, Piece> generateBoard() {
        final Map<Square, Piece> board = new LinkedHashMap<>();
        final List<Piece> pieces = this.generatePieces();
        final List<Square> squares = this.generateSquares();

        for (int i = 0; i < squares.size(); i++) {
            board.put(squares.get(i), pieces.get(i));
        }

        return board;
    }

    private List<Piece> generatePieces() {
        final List<Piece> pieces = new ArrayList<>();

        pieces.addAll(this.generateFirstLine(Team.BLACK));
        pieces.addAll(this.generateSecondLine(Team.BLACK));
        pieces.addAll(this.generateEmptyLine());
        pieces.addAll(this.generateEmptyLine());
        pieces.addAll(this.generateEmptyLine());
        pieces.addAll(this.generateEmptyLine());
        pieces.addAll(this.generateSecondLine(Team.WHITE));
        pieces.addAll(this.generateFirstLine(Team.WHITE));

        return pieces;
    }

    private List<Piece> generateFirstLine(final Team team) {
        final List<Piece> pieces = new ArrayList<>();

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

    private List<Piece> generateSecondLine(final Team team) {
        final List<Piece> pieces = new ArrayList<>();

        for (int i = 0; i < BOARD_LINE_SIZE; i++) {
            pieces.add(new Pawn(team));
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
        if (this.isMovable(source, target)) {
            this.moveIfPawn(source);
            this.board.put(target, this.getPiece(source));
            this.board.put(source, new Empty());
            return;
        }
        throw new PieceCanNotMoveException();
    }

    private void moveIfPawn(final Square source) {
        if (this.isSameRole(source, Role.PAWN)) {
            this.board.put(source, new Pawn(this.getPiece(source).getTeam(), IS_MOVED));
        }
    }

    private boolean isMovable(final Square source, final Square target) {
        final Piece sourcePiece = this.getPiece(source);
        final Direction direction = Direction.calculateDirection(source, target);

        if (this.isPathBlocked(source, target, direction) && !this.isSameRole(source, Role.KNIGHT)) {
            return false;
        }
        if (this.isSameRole(source, Role.PAWN)) {
            return this.isPawnMovable(source, target, direction);
        }
        return sourcePiece.isMovable(source, target, direction);
    }

    private boolean isPawnMovable(final Square source, final Square target, final Direction direction) {
        final boolean isTargetEmpty = this.getPiece(target).isEmpty();

        if (Direction.isMoveForward(direction) && !isTargetEmpty) {
            return false;
        }
        if (Direction.isMoveDiagonal(direction) && (this.isSameTeam(source, target) || isTargetEmpty)) {
            return false;
        }
        return this.getPiece(source).isMovable(source, target, direction);
    }

    private boolean isPathBlocked(final Square source, final Square target, final Direction direction) {
        return this.isBlocked(source, target, direction) || this.isSameTeam(source, target);
    }

    private boolean isBlocked(final Square source, final Square target, final Direction direction) {
        final Square nextSquare = source.nextSquare(source, direction.getFile(), direction.getRank());

        if (nextSquare.equals(target)) {
            return false;
        }
        if (this.isEmptyPiece(nextSquare)) {
            return this.isBlocked(nextSquare, target, direction);
        }
        return true;
    }

    private boolean isSameTeam(final Square source, final Square target) {
        final Piece sourcePiece = this.getPiece(source);
        final Team targetTeam = this.getPiece(target).getTeam();

        return sourcePiece.isSameTeam(targetTeam);
    }

    private boolean isSameRole(final Square source, final Role role) {
        final Piece sourcePiece = this.getPiece(source);

        return sourcePiece.isSameRole(role);
    }

    public boolean isEmptyPiece(final Square source) {
        return this.getPiece(source).isEmpty();
    }

    public List<Piece> getPieces() {
        return new ArrayList<>(this.board.values());
    }

    public Piece getPiece(final Square source) {
        return this.board.get(source);
    }
}
