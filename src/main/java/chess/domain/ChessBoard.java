package chess.domain;

import chess.domain.piece.Piece;
import chess.domain.piece.Position;
import chess.domain.piece.type.Pawn;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ChessBoard {

    private Set<Piece> pieces; // map<position, piece>으로 갖고있을 수 있을듯

    public ChessBoard(final Set<Piece> pieces) {
        this.pieces = pieces;
    }

    public void move(final List<String> positions) {
        final String currentPosition = positions.get(0);
        final String targetPosition = positions.get(1);

        move(Position.from(currentPosition), Position.from(targetPosition));
    }

    void move(final Position currentPosition, final Position targetPosition) {
        final Piece currentPiece = findPieceBy(currentPosition);

        if (currentPiece.isClass(Pawn.class) && canPawnCatch(currentPiece, targetPosition)) {
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
        return ((currentPiece.getPosition().isDiagonalWith(targetPosition)
                && currentPiece.getPosition().getRankDistance(targetPosition) == Pawn.DEFAULT_STEP))
                && !currentPiece.isMySide(findPieceBy(targetPosition));
    }

    private boolean isPieceExist(final Position input) {
        return pieces.stream().anyMatch(piece -> piece.isPosition(input));
    }

    private void catchPiece(final Piece currentPiece, final Position targetPosition) {
        pieces = removePiece(targetPosition);
        currentPiece.move(targetPosition);
    }

    private Set<Piece> removePiece(final Position targetPosition) {
        return pieces.stream()
                .filter(piece -> !piece.isPosition(targetPosition))
                .collect(Collectors.toSet());
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

    @Override
    public String toString() {
        return "ChessBoard{" +
                "pieces=" + pieces +
                '}';
    }
}
