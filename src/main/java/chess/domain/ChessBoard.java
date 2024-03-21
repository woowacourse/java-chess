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

    Piece findBy(final Position input) {
        return pieces.stream()
                .filter(piece -> piece.isPosition(input))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 해당 위치에 기물이 존재하지 않습니다."));
    }

    public void move(final List<String> positions) {
        final String file = positions.get(0);
        final String rank = positions.get(1);
        move(new Position(file), new Position(rank));
    }

    public void move(final Position currentPosition, final Position nextPosition) {
        final Piece currentPiece = findBy(currentPosition);
        if (canCatchByPawn(nextPosition, currentPiece)) {
            catchPiece(nextPosition, currentPiece);
            return;
        }

        checkStrategy(nextPosition, currentPiece);
        checkJumpOver(nextPosition, currentPiece);

        if (isTargetExist(nextPosition)) {
            checkColor(currentPiece, findBy(nextPosition));
            catchPiece(nextPosition, currentPiece);
        }

        if (isTargetEmpty(nextPosition)) {
            currentPiece.move(nextPosition);
        }
    }

    private boolean canCatchByPawn(final Position nextPosition, final Piece currentPiece) {
        return currentPiece instanceof Pawn && canPawnCatch(currentPiece, nextPosition);
    }

    private boolean canPawnCatch(final Piece currentPiece, final Position nextPosition) {
        return currentPiece.getPosition().isDiagonalWithDistance(nextPosition, Pawn.DEFAULT_STEP) &&
                currentPiece.isOtherColor(findBy(nextPosition));
    }

    private void catchPiece(final Position nextPosition, final Piece currentPiece) {
        pieces.remove(findBy(nextPosition));
        currentPiece.move(nextPosition);
    }

    private void checkStrategy(final Position nextPosition, final Piece currentPiece) {
        if (!currentPiece.canMoveTo(nextPosition)) {
            throw new IllegalArgumentException("[ERROR] 전략상 이동할 수 없는 위치입니다.");
        }
    }

    private void checkJumpOver(final Position nextPosition, final Piece currentPiece) {
        if (existInWay(currentPiece, nextPosition)) {
            throw new IllegalArgumentException("[ERROR] 경로상 기물이 존재합니다.");
        }
    }

    private boolean existInWay(final Piece currentPiece, final Position nextPosition) {
        final Set<Position> route = currentPiece.getRoute(nextPosition);
        return pieces.stream().anyMatch(piece -> route.contains(piece.getPosition()));
    }

    private void checkColor(final Piece currentPiece, final Piece targetPiece) {
        if (!currentPiece.isOtherColor(targetPiece)) {
            throw new IllegalArgumentException("[ERROR] 잡을 수 없는 기물입니다.");
        }
    }

    private boolean isTargetExist(final Position target) {
        return pieces.stream().anyMatch(piece -> piece.isPosition(target));
    }

    private boolean isTargetEmpty(final Position target) {
        return !isTargetExist(target);
    }

    public Set<Piece> getPieces() {
        return pieces;
    }
}
