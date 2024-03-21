package chess.domain;

import chess.domain.piece.Direction;
import chess.domain.piece.Piece;
import java.util.List;
import java.util.Map;

public class ChessBoard {

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
        // 소스 위치에 해당하는 피스를 찾는다.
        // 현재는 Piece 대신 piece을 사용
        Piece piece = chessBoard.get(source);

        // TODO: direction을 찾는 행위를 ChessBoard에서 하는게 이상하지 않나?
        //      List<Direction> 을 일급 컬렉션으로 포장해서 아래 direction을 찾는 메서드를 만들어주고, PieceAbstract에 넣어주면?

        // Position을 통해 Direction 구하기
        Direction direction = source.calculateDirection(target);

        // 방향 검증
        if (!piece.canMoveInTargetDirection(direction)) {
            throw new IllegalArgumentException("[ERROR] 선택한 기물이 이동할 수 없는 방향입니다.");
        }

        // "한칸씩" 움직여서 뭐 있는지 검증
        Position nextPosition = source.moveTowardDirection(direction);

        // 다음 위치가 목적지가 아니고, 다음 위치에 아무 기물도 없는 경우만 진행
        while (piece.canMoveMoreThenOnce() && !nextPosition.equals(target) && chessBoard.get(nextPosition) == null) {
            nextPosition = nextPosition.moveTowardDirection(direction);
        }
        // 한번 이동이 끝인 / 타겟에 도착함 / 장애물이 있는 경우

        // 여기까지 도달했다면 다음 위치가 목적지이거나, 다음 위치에 어떤 기물이 존재하는 경우이거나, 정말로 도착을 못한 경우 (eg 폰이 2칸 가는 경우)
        if (!nextPosition.equals(target)) {
            throw new IllegalArgumentException("[ERROR] 선택한 기물은 해당 위치에 도달할 수 없습니다.");
        }

        // 경로에 기물이 존재하는 것
        if (piece.canMoveMoreThenOnce() && !nextPosition.equals(target) && chessBoard.get(nextPosition) != null) {
            throw new IllegalArgumentException("[ERROR] 이동 경로에 기물이 존재합니다.");
        }

        // 여기까지 도달했다면 다음 위치가 목적지이지만, 해당 위치에 기물이 존재하는 경우
        if(chessBoard.get(source).isAlly(chessBoard.get(nextPosition))) {
            throw new IllegalArgumentException("[ERROR] 이동하려는 위치에 아군 기물이 존재합니다.");
        }

        // 여기까지 도달했다면 다음 위치에 상대팀이 있거나, 다음 위치가 원래 비어있던 경우
        chessBoard.put(target, piece);
        chessBoard.put(source, null);
    }
}
