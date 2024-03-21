package chess.domain;

import chess.domain.piece.Color;
import chess.domain.piece.File;
import chess.domain.piece.Piece;
import chess.domain.piece.Position;
import chess.domain.piece.Rank;
import chess.domain.piece.type.Bishop;
import chess.domain.piece.type.King;
import chess.domain.piece.type.Night;
import chess.domain.piece.type.Pawn;
import chess.domain.piece.type.Queen;
import chess.domain.piece.type.Rook;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ChessBoard {

    private final Set<Piece> pieces;

    private ChessBoard(final Set<Piece> pieces) {
        this.pieces = pieces;
    }

    public static ChessBoard init() {
        final Set<Piece> pieces = new HashSet<>();

        pieces.addAll((createPieceWithoutPawn(Color.BLACK, Rank.EIGHT)));
        pieces.addAll((createPawn(Color.BLACK, Rank.SEVEN)));
        pieces.addAll((createPawn(Color.WHITE, Rank.TWO)));
        pieces.addAll((createPieceWithoutPawn(Color.WHITE, Rank.ONE)));

        return new ChessBoard(pieces);
    }

    private static Set<Piece> createPawn(final Color color, final Rank rank) {
        return Set.of(new Pawn(color, new Position(File.A, rank)),
                new Pawn(color, new Position(File.B, rank)),
                new Pawn(color, new Position(File.C, rank)),
                new Pawn(color, new Position(File.D, rank)),
                new Pawn(color, new Position(File.E, rank)),
                new Pawn(color, new Position(File.F, rank)),
                new Pawn(color, new Position(File.G, rank)),
                new Pawn(color, new Position(File.H, rank)));
    }

    private static Set<Piece> createPieceWithoutPawn(final Color color, final Rank rank) {
        return Set.of(new Rook(color, new Position(File.A, rank)),
                new Night(color, new Position(File.B, rank)),
                new Bishop(color, new Position(File.C, rank)),
                new Queen(color, new Position(File.D, rank)),
                new King(color, new Position(File.E, rank)),
                new Bishop(color, new Position(File.F, rank)),
                new Night(color, new Position(File.G, rank)),
                new Rook(color, new Position(File.H, rank)));
    }

    public void move(final List<String> positions) {
        final String currentPosition = positions.get(0);
        final String targetPosition = positions.get(1);
        move(new Position(currentPosition), new Position(targetPosition));
    }

    void move(final Position currentPosition, final Position targetPosition) {
        final Piece currentPiece = findPieceBy(currentPosition);

        if (currentPiece instanceof Pawn && canPawnCatch(currentPiece, targetPosition)) {
            catchPiece(currentPiece, targetPosition);
            return;
        }

        validateStrategy(currentPiece, targetPosition);
        validateJumpOver(currentPiece, targetPosition);

        if (isPieceExist(targetPosition)) {
            validateNotMySide(currentPiece, targetPosition);
            catchPiece(currentPiece, targetPosition);
        }

        currentPiece.move(targetPosition);
    }

    Piece findPieceBy(final Position input) {
        return pieces.stream()
                .filter(piece -> piece.isPosition(input))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 해당 위치에 기물이 존재하지 않습니다."));
    }

    private boolean canPawnCatch(final Piece currentPiece, final Position targetPosition) {
        if (!isPieceExist(targetPosition)) {
            return false;
        }
        return currentPiece.getPosition().isDiagonalWithDistance(targetPosition, Pawn.DEFAULT_STEP) &&
                !currentPiece.isMySide(findPieceBy(targetPosition));
    }

    private boolean isPieceExist(final Position input) {
        return pieces.stream().anyMatch(piece -> piece.isPosition(input));
    }

    private void catchPiece(final Piece currentPiece, final Position targetPosition) {
        pieces.remove(findPieceBy(targetPosition));
        currentPiece.move(targetPosition);
    }

    private void validateStrategy(final Piece currentPiece, final Position targetPosition) {
        if (!currentPiece.canMoveTo(targetPosition)) {
            throw new IllegalArgumentException("[ERROR] 전략상 이동할 수 없는 위치입니다.");
        }
    }

    private void validateJumpOver(final Piece currentPiece, final Position targetPosition) {
        if (existPieceInWay(currentPiece, targetPosition)) {
            throw new IllegalArgumentException("[ERROR] 경로상 기물이 존재합니다.");
        }
    }

    private boolean existPieceInWay(final Piece currentPiece, final Position targetPosition) {
        final Set<Position> route = currentPiece.getRoute(targetPosition);
        return pieces.stream().anyMatch(piece -> route.contains(piece.getPosition()));
    }

    private void validateNotMySide(final Piece currentPiece, final Position targetPosition) {
        final Piece targetPiece = findPieceBy(targetPosition);
        if (currentPiece.isMySide(targetPiece)) {
            throw new IllegalArgumentException("[ERROR] 잡을 수 없는 기물입니다.");
        }
    }

    public Set<Piece> getPieces() {
        return pieces;
    }
}
