package chess.domain;

import chess.domain.piece.Direction;
import chess.domain.piece.Empty;
import chess.domain.piece.Piece;
import java.util.List;
import java.util.Map;

public class ChessBoard {

    private static final int BLACK_PAWN_INITIAL_RANK = 7;
    private static final int WHITE_PAWN_INITIAL_RANK = 2;

    private final Map<Position, Piece> chessBoard;

    public ChessBoard(final Map<Position, Piece> chessBoard) {
        this.chessBoard = chessBoard;
    }

    public void move(final Position source, final Position target) {
        Piece piece = findPieceByPosition(source);
        Direction direction = source.calculateDirection(target);

        if (piece.isEmpty()) {
            throw new IllegalArgumentException("[ERROR] 선택한 칸에 기물이 존재하지 않습니다.");
        }

        if (!piece.canMoveInTargetDirection(direction)) {
            throw new IllegalArgumentException("[ERROR] 선택한 기물이 이동할 수 없는 방향입니다.");
        }

        if (piece.isAlly(findPieceByPosition(target))) {
            throw new IllegalArgumentException("[ERROR] 이동하려는 위치에 아군 기물이 존재합니다.");
        }

        Position nextPosition = source.moveTowardDirection(direction);
        while (piece.canMoveMoreThenOnce() &&
                !nextPosition.equals(target) &&
                findPieceByPosition(nextPosition).isEmpty()) {
            nextPosition = nextPosition.moveTowardDirection(direction);
        }

        if (piece.isPawn()) {
            // 해당 칸에 적이 없음에도 대각선 방향으로 움직이려 하는 경우 예외 발생
            if (findPieceByPosition(nextPosition).isEmpty() && direction.isDiagonal()) {
                throw new IllegalArgumentException("[ERROR] 폰은 도착 위치에 적이 있는 경우에만 대각선으로 이동할 수 있습니다.");
            }

            // 해당 칸에 적이 있음에도 직선 방향으로 움직이려 하는 경우 예외 발생
            if (!findPieceByPosition(target).isEmpty() && !direction.isDiagonal()) {
                throw new IllegalArgumentException("[ERROR] 폰은 적 기물이 경로에 존재하는 경우, 직선 방향으로 이동할 수 없습니다.");
            }

            // Pawn한테 주는 한칸 뽀나스 ㅋ
            if (isPawnOnInitialPosition(piece, source) &&
                    !direction.isDiagonal() &&
                    !nextPosition.equals(target) &&
                    findPieceByPosition(nextPosition).isEmpty()) {
                nextPosition = nextPosition.moveTowardDirection(direction);
            }
        }

        if (!nextPosition.equals(target)) {
            throw new IllegalArgumentException("[ERROR] 선택한 기물은 해당 위치에 도달할 수 없습니다.");
        }

        if (piece.canMoveMoreThenOnce() &&
                !nextPosition.equals(target) &&
                !findPieceByPosition(nextPosition).isEmpty()) {
            throw new IllegalArgumentException("[ERROR] 이동 경로에 기물이 존재합니다.");
        }

        chessBoard.put(target, piece);
        chessBoard.put(source, Empty.of());
    }

    private boolean isPawnOnInitialPosition(final Piece piece, final Position position) {
        if (piece.isBlack()) {
            return position.isSameRank(BLACK_PAWN_INITIAL_RANK);
        }
        return position.isSameRank(WHITE_PAWN_INITIAL_RANK);
    }

    public List<Piece> findAllPieces() {
        return chessBoard.values()
                .stream()
                .toList();
    }

    public Piece findPieceByPosition(final Position position) {
        return chessBoard.get(position);
    }
}
