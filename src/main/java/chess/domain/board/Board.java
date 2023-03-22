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
            this.board.put(target, this.board.get(source));
            this.board.put(source, new Empty());
            return;
        }
        throw new PieceCanNotMoveException();
    }

    private void moveIfPawn(final Square source) {
        if (this.isSameRole(source, Role.PAWN)) {
            this.board.put(source, new Pawn(this.board.get(source).getTeam(), IS_MOVED));
        }
    }

    private boolean isMovable(final Square source, final Square target) {
        final Piece sourcePiece = this.board.get(source);
        final Direction direction = Direction.calculateDirection(source, target);

        if (this.isSameRole(source, Role.KNIGHT)) {
            return this.isKnightMovable(source, target, sourcePiece);
        }
        if (this.isPathBlocked(source, target, direction)) {
            return false;
        }
        if (this.isSameRole(source, Role.PAWN)) {
            return this.isPawnMovable(source, target, direction);
        }
        return sourcePiece.isMovable(source, target, direction);
    }

    private boolean isKnightMovable(final Square source, final Square target, final Piece piece) {
        final Direction direction = Direction.calculateKnightDirection(source, target);
        return piece.isMovable(source, target, direction);
    }

    private boolean isPawnMovable(final Square source, final Square target, final Direction direction) {
        final boolean isTargetEmpty = this.board.get(target).isEmpty();

        if (Direction.isMoveForward(direction) && !isTargetEmpty) {
            return false;
        }
        if (Direction.isMoveDiagonal(direction) && (this.isSameCamp(source, target) || isTargetEmpty)) {
            return false;
        }
        return this.board.get(source).isMovable(source, target, direction);
    }

    private boolean isPathBlocked(final Square source, final Square target, final Direction direction) {
        if (direction.equals(Direction.EMPTY)) {
            return this.isSameRole(source, Role.KNIGHT);
        }
        return this.isBlocked(source, target, direction) || this.isSameCamp(source, target);
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

    private boolean isSameCamp(final Square source, final Square target) {
        final Piece sourcePiece = this.board.get(source);
        final Team targetTeam = this.board.get(target).getTeam();

        return sourcePiece.isSameTeam(targetTeam);
    }

    private boolean isSameRole(final Square source, final Role role) {
        final Piece sourcePiece = this.board.get(source);

        return sourcePiece.isSameRole(role);
    }

    public boolean isNotMyTurn(final Square source, final Team turn) {
        return this.board.get(source).isAnotherTeam(turn);
    }

    public boolean isEmptyPiece(final Square source) {
        return this.board.get(source).isEmpty();
    }

    public List<Piece> getPieces() {
        return new ArrayList<>(this.board.values());
    }
}
