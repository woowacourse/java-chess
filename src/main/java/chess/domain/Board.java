package chess.domain;

import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Board {

    private final Map<Position, Piece> board;

    public Board() {
        board = new HashMap<>();
        init();
    }

    private void init() {
        for (PieceSettings pieceSetting : PieceSettings.values()) {
            board.put(pieceSetting.getPosition(), pieceSetting.getPiece());
        }
    }

    public void move(Position source, Position target) {
        // 1. source에 기물이 없으면 예외던진다.
        if (!board.containsKey(source)) {
            throw new IllegalArgumentException("[ERROR] source 위치에 기물이 없습니다.");
        }
        // 2. 맵에서 꺼내와서 isMovable 호출한다.
        // 3. isMovable이 false이면 예외를 던진다.
        Piece sourcePiece = board.get(source);
        if (!sourcePiece.isMovable(source, target)) {
            throw new IllegalArgumentException("[ERROR] 이동할 수 없는 위치입니다.");
        }
        // 4. findPath를 호출하여 경로를 받는다.
        List<Position> path = sourcePiece.findPath(source, target);
        // 5. target전까지 기물이 있으면 예외를 던진다. (나이트 예외)
        if (sourcePiece.getClass() != Knight.class) {
            for (int i = 0; i < path.size() - 1; i++) {
                if (board.containsKey(path.get(i))) {
                    throw new IllegalArgumentException("[ERROR] 이동 경로에 기물이 있습니다.");
                }
            }
        }

        // 6. target에 아무 것도 없으면 그냥 이동한다.
        // - 폰은 target이 대각선이면 예외를 던진다.
        if (!board.containsKey(target)) {
            if (sourcePiece.getClass() == Pawn.class) {
                int rankDiff = source.rankDiff(target);
                int fileDiff = source.fileDiff(target);

                if (Math.abs(rankDiff) == Math.abs(fileDiff)) {
                    throw new IllegalArgumentException("[ERROR] 폰은 상대 기물이 없을 경우, 대각선으로 움직일 수 없습니다.");
                }
            }

            // 이동한다.
            Piece piece = board.get(source);
            board.remove(source);
            board.put(target, piece);
            return;
        }

        // 7. target에서 우리팀이면 예외를 던진다.
        Piece targetPiece = board.get(target);
        if (targetPiece.isSameTeam(sourcePiece)) {
            throw new IllegalArgumentException("[ERROR] 타겟 위치에 같은 팀 기물이 있습니다.");
        }

        // 8. target이 상대팀이면, 상대팀 기물을 삭제하고, 이동한다.
        // - 폰은 target이 일직선이면 예외를 던진다.
        if(sourcePiece.getClass() == Pawn.class) {
            int rankDiff = source.rankDiff(target);
            int fileDiff = source.fileDiff(target);

            if(rankDiff == 0 || fileDiff == 0) {
                throw new IllegalArgumentException("[ERROR] 폰은 대각선으로만 공격할 수 있습니다.");
            }
        }

        // 덮어쓴다.
        Piece piece = board.get(source);
        board.remove(source);
        board.put(target, piece);
    }
}
