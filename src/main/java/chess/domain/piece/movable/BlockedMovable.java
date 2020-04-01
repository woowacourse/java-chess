package chess.domain.piece.movable;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.domain.position.positions.Positions;

import java.util.List;

/**
 * BlockedMovable은 정해진 방향으로 더 이상 진행할 수 없을 때까지 이동하는,
 * Queen,Bishop,Rook의 움직이는 방법을 구현한 클래스이다.
 * BlockedMovable이란 이름은 막히는(즉 범위를 벗어나거나 다른 말이 있어 진행할 수 없는) 형태 움직임을 가졌다는 의미를 가진다.
 */
public class BlockedMovable implements Movable {
    /**
     * moveDirections는 Pawn이 움직일 수 있는 방향들을 저장한다.
     */
    private final Directions moveDirections;

    /**
     * Constructor는 방향을 주입받아 이를 저장한다.
     *
     * @param moveDirections 움직일 수 있는 방향의 목록이다.일반적으로 전진 및 대각선 전진(왼쪽방향, 오른쪽 방향)의 세 가지 방향을 포함한다.
     */
    public BlockedMovable(Directions moveDirections) {
        this.moveDirections = moveDirections;
    }

    /**
     * findMovablePositions는 이 인터페이스를 포함하는 상위 클래스(즉, Queen이나 Rook,Bishop을 구현한 클래스)에서 호출된다.
     * 현재 말의 위치, 모든 말들의 목록, 이 말의 색상(색상이 같으면 같은 팀이다) 을 전달받는다.
     * 이후 말이 움직일 수 있는 방향들을 확인하여, 이를 순회한다.
     * 순서는 아래와 같다.
     * - 각 방향에 대해 먼저 순회한다.
     * - 현재 위치에 갈 수 있는 방향을 더하여 새로운 위치를 만든다.
     * - 생성한 위치에 대해, 갈 수 있는지 여부를 검사한다.
     * - 만약 갈 수 없다면, 루프를 종료하고 다음 방향을 찾는다.
     * - 갈 수 있다면, 그 위치를 목록에 추가하고 다음 위치를 생성한다.
     * - 종합적으로 모인, 한 방향으로 갈 수 있는 모든 위치의 목록을 반환하여 기존 목록과 합친다.
     * - 최종적으로 만들어진 목록을 Positions로 반환한다.
     *
     * @param position 말의 현재 위치값이다. 이 위치에 필드변수 moveDiretions를 더하여 말이 움직일 수 있는 위치들을 구한다.
     * @param pieces   모든 말들을 저장한 리스트이다. 이를 통해 내가 움직이려는 자리에 아군 말이 있는지 확인한다.
     * @param color    말의 색상값이다. 다른 말과 위치가 겹칠 때, 색이 다른지(적이라는 뜻이므로, 말을 잡을 수 있다)
     *                 혹은 같은지(아군이 있는 곳으로는 이동할 수 없다) 여부를 판단한다.
     * @return 최종적으로 말이 position에서 출발해 갈 수 있는 모든 위치를 Positions 객체에 저장해 반환한다.
     */
    @Override
    public Positions findMovablePositions(Position position, List<Piece> pieces, Color color) {
        Positions movablePositions = Positions.create();
        for (Direction direction : moveDirections.getDirections()) {
            Positions positions = createMovablePositionsByDirection(position, direction, pieces, color);
            movablePositions.addAll(positions);
        }
        return movablePositions;
    }

    /**
     * createMovablePositionsByDirection은, 방향값과 위치를 받은 뒤 그 방향으로 더 이상 진행이 불가할 때까지 탐색하는 메서드이다.
     * 탐색 과정에서 모든 말의 목록인 pieces와 내 말의 색상인 color를 참조한다.
     * 순서는 다음과 같다.
     * - 다른 말이 경로를 막거나, 판의 범위를 벗어나는 등 이동이 불가할 때까지 순회를 진행한다.
     * - 위치를 방향값을 바탕으로 한 칸 이동시킨다.
     * - 이동한 방향값을 목록에 추가한다.
     * - 순회하면서 구한 값들의 목록을 Positions로 만들어 반환한다.
     *
     * @param position  내 말의 초기 위치이다. 이 값이 바뀌면 상위 메서드에서도 바뀔 수 있으므로, 별도의 movablePosition을 지역변수로 만든다.
     * @param direction 내 말이 이동할 방향값이다. 이를 바탕으로 movablePosition을 한 칸씩 이동시킨다.
     * @param pieces    모든 말의 목록이다. 이를 통해 다른 말이 경로를 막는지 확인할 수 있다.
     * @param color     내 말의 색상(팀) 이다.
     *                  이를 통해 특정 칸을 적 말이 막을 경우(적 말을 잡을 수 있으므로 그 칸까지는 전진 가능)와
     *                  아군 말이 막는 경우(그 칸부터 바로 전진 불가, 순회 종료)를 구분할 수 있다.
     * @return 입력된 방향으로 전진하여 갈 수 있는 모든 Position을 Positions로 묶어 반환한다.
     */
    Positions createMovablePositionsByDirection(Position position, Direction direction, List<Piece> pieces, Color color) {
        Positions movablePositions = Positions.create();
        Position movablePosition = position;
        while (isOpen(movablePosition, direction, pieces, color)) {
            movablePosition = movablePosition.getMovedPositionBy(direction);
            movablePositions.add(movablePosition);
        }
        return movablePositions;
    }

    /**
     * isOpen은 방향값과 위치값을 바탕으로, 한 칸 더 이동할 수 있는지 여부를 검사해주는 메서드이다.
     * 만약 아래 조건에 해당되면, 다음 칸으로 갈 수 없다고 판단한다.
     * - 다음 칸이 범위 바깥일 경우
     * - 현재 칸에 적 말이 있는 경우
     * - 다음 칸에 아군 말이 있는 경우
     * 조건 중 "현재 칸에 적 말이 있는 경우"의 의미는 아래와 같다.
     * - 만약 Rook이 전진한다고 가정해 보자.
     * 적 말이 경로를 막고 있다면, 그 칸까지는 갈 수 있다.(적 말을 잡을 수 있다.)
     * 그러나, 그 다음 칸부터는 갈 수 없다.
     * 이를 다르게 말하면, 바로 이전 칸에서 적 말을 만났다면, 그때부터는 전진할 수 없다는 뜻이다.
     * 그렇기 때문에, 아직 움직이지 않은 position값을 바탕으로 검사하는 것이다.
     * (세 번째 조건 검사시에는 position.getMovedPositionBy를 사용하여, 한 칸 이동했을 때 아군이 있는지 검사한다)
     *
     * @param position  말의 현재 위치값이다.
     * @param direction 말이 이동할 수 있는지 검사할 방향값이다.
     * @param pieces    모든 말의 목록이다. 이를 바탕으로 검사를 수행한다.
     * @param color     말의 색상(팀) 값이다. 이를 통해 아군/적군 말을 구분한다.
     * @return 만약 다음 칸으로 진행할 수 있다면 true를, 아니라면 false를 반환한다.
     */
    private boolean isOpen(Position position, Direction direction, List<Piece> pieces, Color color) {
        if (!position.checkBound(direction)) {
            return false;
        }
        if (isPossessedByDifferentColor(position, pieces, color)) {
            return false;
        }
        return !isPossessedBySameColor(position.getMovedPositionBy(direction), pieces, color);
    }

    /**
     * isPossessedByDifferentColor는 위치값을 전달받아 그 위치에 적이 있는지 검사한다.
     * 만약 그 칸에 적이 있다면, true를 반환한다.
     * 그렇지 않다면 false를 반환한다.
     *
     * @param position 말의 현재 위치값이다.
     * @param pieces   모든 말의 목록이다. 이 값을 바탕으로 탐색을 진행한다.
     * @param color    말의 색상(팀) 값이다. 이를 통해 아군/적군을 구분한다.
     * @return 만약 주어진 위치에 적이 있다면 true를, 아니라면 false를 반환한다.
     */
    private boolean isPossessedByDifferentColor(Position position, List<Piece> pieces, Color color) {
        return pieces.stream()
                .anyMatch(piece -> piece.isSamePosition(position) && piece.isNotSameColor(color));
    }


    /**
     * isPossessedBySameColor는 위치값을 전달받아 그 위치에 아군이 있는지 검사한다.
     * 만약 그 칸에 아군이 있다면, true를 반환한다.
     * 그렇지 않다면 false를 반환한다.
     *
     * @param position 말의 현재 위치값이다.
     * @param pieces   모든 말의 목록이다. 이 값을 바탕으로 탐색을 진행한다.
     * @param color    말의 색상(팀) 값이다. 이를 통해 아군/적군을 구분한다.
     * @return 만약 주어진 위치에 아군이 있다면 true를, 아니라면 false를 반환한다.
     */
    private boolean isPossessedBySameColor(Position position, List<Piece> pieces, Color color) {
        return pieces.stream()
                .anyMatch(piece -> piece.isSamePosition(position) && piece.isSameColor(color));
    }
}