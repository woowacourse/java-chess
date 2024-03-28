package domain.chessboard;

import domain.ChessVector;
import domain.Team;
import domain.piece.*;
import domain.square.File;
import domain.square.Rank;
import domain.square.Square;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class ChessBoard {

    private static final Map<File, Piece> BLACK_PIECE_TYPE_ORDERS = Map.of(
            File.A, new Rook(Team.BLACK), File.B, new Knight(Team.BLACK),
            File.C, new Bishop(Team.BLACK), File.D, new Queen(Team.BLACK),
            File.E, new King(Team.BLACK), File.F, new Bishop(Team.BLACK),
            File.G, new Knight(Team.BLACK), File.H, new Rook(Team.BLACK)
    );
    private static final Map<File, Piece> WHITE_PIECE_TYPE_ORDERS = Map.of(
            File.A, new Rook(Team.WHITE), File.B, new Knight(Team.WHITE),
            File.C, new Bishop(Team.WHITE), File.D, new Queen(Team.WHITE),
            File.E, new King(Team.WHITE), File.F, new Bishop(Team.WHITE),
            File.G, new Knight(Team.WHITE), File.H, new Rook(Team.WHITE)
    );
    private static final Piece BLACK_PAWN = new Pawn(Team.BLACK);
    private static final Piece WHITE_PAWN = new Pawn(Team.WHITE);

    private final Map<Square, Piece> pieceSquares;
    private Team currentTeam;

    public ChessBoard(final Map<Square, Piece> pieceSquares, final Team currentTeam) {
        this.pieceSquares = pieceSquares;
        this.currentTeam = currentTeam;
    }

    public static ChessBoard create() {
        final Map<Square, Piece> chessBoard = new HashMap<>();

        for (final File file : File.values()) {
            chessBoard.put(new Square(file, Rank.SEVEN), BLACK_PAWN);
            chessBoard.put(new Square(file, Rank.TWO), WHITE_PAWN);
            chessBoard.put(new Square(file, Rank.EIGHT), BLACK_PIECE_TYPE_ORDERS.get(file));
            chessBoard.put(new Square(file, Rank.ONE), WHITE_PIECE_TYPE_ORDERS.get(file));
        }

        return new ChessBoard(chessBoard, Team.WHITE);
    }

    public void move(final Square source, final Square target) {
        validateMove(source, target);

        final Piece sourcePiece = pieceSquares.get(source);
        pieceSquares.put(target, sourcePiece);
        pieceSquares.remove(source);

        currentTeam = currentTeam.turn();
    }

    private void validateMove(final Square source, final Square target) {
        validateEmptySource(source);
        validateSameSquare(source, target);
        validateTeam(source);

        if (pieceSquares.containsKey(target)) {
            validateAttack(source, target);
        } else {
            validateDirection(source, target);
        }

        validateBlocking(source, target);
    }

    private void validateEmptySource(final Square source) {
        if (!pieceSquares.containsKey(source)) {
            throw new IllegalArgumentException("해당 위치에 기물이 없습니다.");
        }
    }

    private void validateSameSquare(final Square source, final Square target) {
        if (source.equals(target)) {
            throw new IllegalArgumentException("제자리 이동은 불가합니다.");
        }
    }

    private void validateTeam(final Square source) {
        final Piece sourcePiece = pieceSquares.get(source);
        if (sourcePiece.isOppositeTeam(currentTeam)) {
            throw new IllegalArgumentException("상대방의 말을 움직일 수 없습니다.");
        }
    }

    private void validateAttack(final Square source, final Square target) {
        final Piece sourcePiece = pieceSquares.get(source);
        if (sourcePiece.canNotAttack(source, target)) {
            throw new IllegalArgumentException("공격할 수 없는 경로입니다.");
        }

        final Piece targetPiece = pieceSquares.get(target);
        if (targetPiece.isSameTeam(sourcePiece)) {
            throw new IllegalArgumentException("같은 팀을 공격할 수 없습니다.");
        }
    }

    private void validateDirection(final Square source, final Square target) {
        final Piece sourcePiece = pieceSquares.get(source);
        if (sourcePiece.canNotMove(source, target)) {
            throw new IllegalArgumentException("움직일 수 없는 경로입니다.");
        }
    }

    private void validateBlocking(final Square source, final Square target) {
        if (isBlocked(source, target)) {
            throw new IllegalArgumentException("기물에 가로막혀 갈 수 없는 경로입니다.");
        }
    }

    private boolean isBlocked(final Square source, final Square target) {
        final ChessVector chessVector = target.calculateVector(source);

        final ChessVector unitVector = chessVector.scaleDown();
        final long pathCount = chessVector.findAbsGCD();

        return Stream.iterate(source.next(unitVector), square -> square.next(unitVector))
                .limit(pathCount)
                .filter(square -> !square.equals(target))
                .anyMatch(pieceSquares::containsKey);
    }

    public Map<Square, Piece> getPieceSquares() {
        return Collections.unmodifiableMap(pieceSquares);
    }

    public Team currentTeam() {
        return currentTeam;
    }
}
