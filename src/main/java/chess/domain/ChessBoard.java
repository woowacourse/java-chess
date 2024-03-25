package chess.domain;

import chess.domain.piece.Direction;
import chess.domain.piece.Empty;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import java.util.List;
import java.util.Map;

public class ChessBoard {

    private static final int BLACK_PAWN_INITIAL_RANK = 7;
    private static final int WHITE_PAWN_INITIAL_RANK = 2;

    private final Map<Position, Piece> chessBoard;

    public ChessBoard(final Map<Position, Piece> chessBoard) {
        this.chessBoard = chessBoard;
    }

    public List<Piece> findAllPieces() {
        return chessBoard.values()
                .stream()
                .toList();
    }

    public Piece findPieceByPosition(final Position position) {
        return chessBoard.get(position);
    }

    public void move(final Position source, final Position target) {
        Piece piece = chessBoard.get(source);
        Direction direction = source.calculateDirection(target);

        validateEmptyPiece(piece);
        validateDirection(piece, direction);
        validateNotAlly(source, target);

        Position nextPosition = source.moveTowardDirection(direction);
        Piece nextPiece = chessBoard.get(nextPosition);
        while (piece.canMoveMoreThenOnce() && !nextPosition.equals(target) && nextPiece.isEmpty()) {
            nextPosition = nextPosition.moveTowardDirection(direction);
            nextPiece = chessBoard.get(nextPosition);
        }

        // 폰이고, direction이 대각선 방향이고, 해당 칸에 적이 없는 경우 예외 발생
        if (isPawn(piece)
                && direction.isDiagonal()
                && nextPiece.isEmpty()) {
            throw new IllegalArgumentException("[ERROR] 폰은 도착 위치에 적이 있는 경우에만 대각선으로 이동할 수 있습니다.");
        }

        // Pawn한테 주는 한칸 뽀나스 ㅋ
        if (isPawn(piece)
                && isPawnOnInitialPosition(piece, source)
                && !direction.isDiagonal()
                && !nextPosition.equals(target)
                && nextPiece.isEmpty()) {
            nextPosition = nextPosition.moveTowardDirection(direction);
            nextPiece = chessBoard.get(nextPosition);
        }

        if (!nextPosition.equals(target)) {
            throw new IllegalArgumentException("[ERROR] 선택한 기물은 해당 위치에 도달할 수 없습니다.");
        }

        if (piece.canMoveMoreThenOnce() && !nextPosition.equals(target) && !nextPiece.isEmpty()) {
            throw new IllegalArgumentException("[ERROR] 이동 경로에 기물이 존재합니다.");
        }

        if (isPawn(piece)
                && !direction.isDiagonal()
                && !nextPiece.isEmpty()) {
            throw new IllegalArgumentException("[ERROR] 폰은 도착 위치에 적이 있는 경우에만 대각선으로 이동할 수 있습니다.");
        }

        chessBoard.put(target, piece);
        chessBoard.put(source, Empty.of());
    }

    private boolean isPawn(final Piece piece) {
        return PieceType.PAWN.name().equals(piece.getOwnPieceType().name());
    }

    private boolean isPawnOnInitialPosition(final Piece piece, final Position position) {
        if (piece.isBlack()) {
            return position.isSameRank(BLACK_PAWN_INITIAL_RANK);
        }
        return position.isSameRank(WHITE_PAWN_INITIAL_RANK);
    }

    private static void validateEmptyPiece(final Piece piece) {
        if (piece.equals(Empty.of())) {
            throw new IllegalArgumentException("[ERROR] 선택한 칸에 기물이 존재하지 않습니다.");
        }
    }

    private void validateDirection(final Piece piece, final Direction direction) {
        if (!piece.canMoveInTargetDirection(direction)) {
            throw new IllegalArgumentException("[ERROR] 선택한 기물이 이동할 수 없는 방향입니다.");
        }
    }

    private void validateNotAlly(final Position source, final Position target) {
        if (chessBoard.get(source).isAlly(chessBoard.get(target))) {
            throw new IllegalArgumentException("[ERROR] 이동하려는 위치에 아군 기물이 존재합니다.");
        }
    }
}
