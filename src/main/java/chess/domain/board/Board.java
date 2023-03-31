package chess.domain.board;

import chess.domain.piece.Empty;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.piece.Team;
import chess.exception.ErrorCode;
import chess.exception.PathBlockedException;
import chess.exception.PawnMoveDiagonalException;
import chess.exception.PawnMoveForwardException;
import chess.exception.TargetSameTeamException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class Board {
    private static final boolean IS_MOVED = true;

    private final Map<Square, Piece> board;

    public Board(Map<Square, Piece> board) {
        this.board = board;
    }

    public void move(Square source, Square target) {
        validateMovable(source, target);
        Piece sourcePiece = board.get(source);
        if (sourcePiece.isSamePieceType(PieceType.PAWN)) {
            Team currentTeam = sourcePiece.getTeam();
            board.put(source, new Pawn(currentTeam, IS_MOVED));
        }
        board.put(target, sourcePiece);
        board.put(source, new Empty());
    }

    public void validateMovable(Square source, Square target) {
        Piece sourcePiece = board.get(source);
        Direction direction = Direction.calculateDirection(source, target);
        validatePathBlocked(source, target, direction);
        if (sourcePiece.isSamePieceType(PieceType.PAWN)) {
            validatePawnPathBlocked(target, direction);
        }
        sourcePiece.validateMovableRange(source, target);
    }

    private void validatePathBlocked(Square source, Square target, Direction direction) {
        Piece sourcePiece = board.get(source);
        Piece targetPiece = board.get(target);

        if (isBlocked(source, target, direction) && !sourcePiece.isSamePieceType(PieceType.KNIGHT)) {
            throw new PathBlockedException(ErrorCode.PATH_BLOCKED);
        }
        if (sourcePiece.isSameTeam(targetPiece.getTeam())) {
            throw new TargetSameTeamException(ErrorCode.TARGET_SAME_TEAM);
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
            throw new PawnMoveForwardException(ErrorCode.PAWN_MOVE_FORWARD);
        }
        if (isTargetEmpty && Direction.isMoveDiagonal(direction)) {
            throw new PawnMoveDiagonalException(ErrorCode.PAWN_MOVE_DIAGONAL);
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

    public double calculateScoreOfTeam(Team team) {
        double score = 0;
        Map<Piece, Square> pieces = getTeamPieces(team);
        Map<Piece, Integer> pawnPiece = getPawnPieces(pieces);
        for (Entry<Piece, Square> entry : pieces.entrySet()) {
            PieceType pieceType = entry.getKey().getPieceType();
            score += pieceType.getScore();
            score -= pawnDuplicateMinusScore(pawnPiece, entry.getValue(), pieceType);
        }
        return score;
    }

    public Map<Piece, Square> getTeamPieces(Team team) {
        return board.entrySet().stream()
                .filter(entry -> entry.getValue().isSameTeam(team))
                .collect(Collectors.toMap(Entry::getValue, Entry::getKey));
    }

    private Map<Piece, Integer> getPawnPieces(Map<Piece, Square> pieces) {
        return pieces.entrySet().stream()
                .filter(entry -> entry.getKey().isSamePieceType(PieceType.PAWN))
                .collect(Collectors.toMap(
                        Entry::getKey,
                        entry -> entry.getValue().getFileToInt()
                ));
    }

    private double pawnDuplicateMinusScore(Map<Piece, Integer> pawnPiece, Square square, PieceType pieceType) {
        if (pieceType == PieceType.PAWN) {
            int fileToInt = square.getFileToInt();
            long pawnCount = pawnPiece.values().stream()
                    .filter(file -> file.equals(fileToInt)).count();
            return pawnMinusScore(pawnCount);
        }
        return 0;
    }

    private double pawnMinusScore(long pawnCount) {
        if (pawnCount > 1) {
            return 0.5;
        }
        return 0;
    }

    public List<Piece> getPieces() {
        return new ArrayList<>(board.values());
    }

    public Map<Square, Piece> getBoard() {
        return board;
    }
}
