package chess.domain.piece;

import chess.domain.Board;
import chess.domain.Color;
import chess.domain.Direction;
import chess.domain.position.Position;
import java.util.HashSet;
import java.util.Set;

public class Pawn extends Piece {
    private static final int DEFAULT_WHITE_RANK = 2;
    private static final int DEFAULT_BLACK_RANK = 7;

    private Pawn(Color color, Set<Direction> directions) {
        super(color, PieceType.PAWN, directions);
    }

    public static Pawn ofBlack() {
        Set<Direction> directions = Direction.ofBlackPawn();
        return new Pawn(Color.BLACK, directions);
    }

    public static Pawn ofWhite() {
        Set<Direction> directions = Direction.ofWhitePawn();
        return new Pawn(Color.WHITE, directions);
    }

    // 폰이 2칸으로 기물을 건너 뛸 수 있기 때문에 for 2로 처리해야함.
    @Override
    public Set<Position> calculateMovablePositions(Position currentPosition, Board board) {
        Set<Position> movablePositions = new HashSet<>();

        directions.forEach(direction -> {
            Position position = currentPosition;
            if (!position.canMoveNext(direction)) {
                return;
            }
            // 1칸, 2칸 전진은 해당 위치에 아무것도 없을 때만 이동 가능하다.
            // 2칸 전진은 첫 위치에서 한 번이라도 움직인 후에는 할 수 없다.
            // 대각선 전진은 해당 위치에 적군이 있을 때만 이동 가능하다.

            // direction이 2칸 전진이면 시작 위치이고 해당 위치에 아무것도 없어야 이동 가능. -> 어떤 움직임 한 번 이라도 생기면 제거
            int currentRank = position.getRank();
            if ((DEFAULT_WHITE_RANK == currentRank && direction == Direction.NORTH_NORTH) ||
                    (DEFAULT_BLACK_RANK == currentRank && direction == Direction.SOUTH_SOUTH)) {
                position = position.next(direction);
                Piece piece = board.findPieceByPosition(position);
                if (piece.isEmpty()) {
                    movablePositions.add(position);
                    return;
                }
            }

            // direction이 1칸 전진이면 해당 칸이 비어있으면 이동 가능.
            if (direction == Direction.NORTH || direction == Direction.SOUTH) {
                position = position.next(direction);
                Piece piece = board.findPieceByPosition(position);
                if (piece.isEmpty()) {
                    movablePositions.add(position);
                    return;
                }
            }

            // direction이 대각선이면 대각선 위치에 적이 있어야만 이동할 수 있음.
            // direction이 한 칸 전진이면 해당 위치에 아무것도 없어야 이동 가능.
            position = position.next(direction);
            Piece piece = board.findPieceByPosition(position);

            if (!isSameColor(piece) && !piece.isEmpty()) {
                movablePositions.add(position);
            }
        });
        return movablePositions;
    }
}
