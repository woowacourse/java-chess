package chess.domain.board;

import chess.domain.piece.Bishop;
import chess.domain.piece.Empty;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.piece.Queen;
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
        validateMovable(source, target);

        if (board.get(source).isSamePieceType(PieceType.PAWN)) {
            board.put(source, new Pawn(board.get(source).getTeam(), IS_MOVED));
        }
        board.put(target, board.get(source));
        board.put(source, new Empty());
    }

    public void validateMovable(Square source, Square target) {
        Direction direction = Direction.calculateDirection(source, target);

        validatePathBlocked(source, target, direction);
        if (board.get(source).isSamePieceType(PieceType.PAWN)) {
            validatePawnPathBlocked(target, direction);
        }
        board.get(source).validateMovableRange(source, target);
    }

    private void validatePathBlocked(Square source, Square target, Direction direction) {
        Piece sourcePiece = board.get(source);
        Piece targetPiece = board.get(target);

        if (isBlocked(source, target, direction) && !board.get(source).isSamePieceType(PieceType.KNIGHT)) {
            throw new PathBlockedException();
        }
        if (sourcePiece.isSameTeam(targetPiece.getTeam())) {
            throw new TargetSameTeamException();
        }
    }

    private boolean isBlocked(Square source, Square target, Direction direction) {
        Square nextSquare = source.nextSquare(source, direction.getFile(), direction.getRank());
        if (nextSquare.equals(target)) {
            return false;
        }
        if (board.get(nextSquare).isEmpty()) {
            return isBlocked(nextSquare, target, direction);
        }
        return true;
    }

    private void validatePawnPathBlocked(Square target, Direction direction) {
        boolean isTargetEmpty = board.get(target).isEmpty();
        if (!isTargetEmpty && Direction.isMoveForward(direction)) {
            throw new PawnMoveForwardException();
        }
        if (isTargetEmpty && Direction.isMoveDiagonal(direction)) {
            throw new PawnMoveDiagonalException();
        }
    }

    public boolean isEmptyPiece(Square square) {
        return board.get(square).isEmpty();
    }

    public boolean isSquarePieceNotCurrentTurn(Square square, Team turn) {
        return board.get(square).isAnotherTeam(turn);
    }

    public boolean haveOneKing() {
        int kingCount = (int) board.values().stream()
                .filter(piece -> piece.isSamePieceType(PieceType.KING))
                .count();

        return kingCount == 1;
    }

    public List<Piece> getPieces() {
        return new ArrayList<>(board.values());
    }

    public List<Piece> getWhitePieces() {
        return board.values().stream()
                .filter(piece -> piece.isSameTeam(Team.WHITE))
                .collect(Collectors.toList());
    }

    public List<Piece> getBlackPieces() {
        return board.values().stream()
                .filter(piece -> piece.isSameTeam(Team.BLACK))
                .collect(Collectors.toList());
    }
}
