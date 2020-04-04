package chess.domain.piece.movable;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.domain.position.positions.Positions;

import java.util.List;
import java.util.stream.Collectors;

/**
 * PawnMovable은 체스의 Pawn이 움직이는 방법을 구현한 클래스이다.
 * Pawn의 움직임 규칙은 아래와 같다.
 * - Pawn은 앞에 아무도 없을 경우 한 칸 전진할 수 있다.
 * - 만약 대각선으로 앞쪽에 적 말이 있을 경우, 그 칸으로 이동해 적 말을 잡을 수 있다.
 * - 만약 Pawn이 게임동안 한 번도 움직이지 않았다면(Initial 상태라면) 앞으로 2칸 움직일 수 있다.
 * 본 클래스는 세 가지 룰을 메서드로 분리하고, 추가적인 검사 메서드를 구현하였다.
 */
public class PawnMovable implements Movable {
	/**
	 * moveDirections는 Pawn이 움직일 수 있는 방향들을 저장한다.
	 */
	private final MovableDirections movableDirections;

	/**
	 * Constructor는 방향을 주입받아 이를 저장한다.
	 *
	 * @param movableDirections 움직일 수 있는 방향의 목록이다. 일반적으로 전진 및 대각선 전진(왼쪽방향, 오른쪽 방향)의 세 가지 방향을 포함한다.
	 */
	public PawnMovable(MovableDirections movableDirections) {
		this.movableDirections = movableDirections;
	}

	/**
	 * findMovablePositions는 이 인터페이스를 포함하는 상위 클래스(즉, Pawn을 구현한 클래스)에서 호출된다.
	 * 현재 말의 위치, 모든 말들의 목록, 이 말의 색상(색상이 같으면 같은 팀이다) 을 전달받는다.
	 * 이후 말이 움직일 수 있는 위치들을 확인하여, 이를 Positions 객체에 저장한다.
	 * 순서는 아래와 같다.
	 * - 먼저, 대각선으로 갈 수 있는지 확인하여 movablePositions를 만든다.
	 * - 전진하는 방향값을 구한 뒤, 전진이 가능한지(다른 말이 방해하지 않는지) 확인한다. 불가하다면 아래 두 줄은 생략한다.
	 * - 전진이 가능하다면 한 칸 전진하는 값을 movablePositions에 추가한다.
	 * - 만약 Pawn이 이번에 처음 움직이는 것이라면, 추가로 한 칸 더 전진할 수 있는지 검사하여 movablePositions에 추가한다.
	 * - 최종적으로 구한 movablePositions를 반환한다.
	 *
	 * @param position 말의 현재 위치값이다. 이 위치에 필드변수 moveDirections를 더하여 폰이 움직일 수 있는 위치들을 구한다.
	 * @param pieces   모든 말들을 저장한 리스트이다. 이를 통해 내가 움직이려는 자리에 아군/적군 말이 있는지 확인한다.
	 * @param color    말의 색상값이다. 다른 말과 위치가 겹칠 때, 색이 다른지(적이라는 뜻이므로, 말을 잡을 수 있다)
	 *                 혹은 같은지(아군이 있는 곳으로는 이동할 수 없다) 여부를 판단한다.
	 * @return 최종적으로 Pawn이 position에서 출발해 갈 수 있는 모든 위치를 Positions 객체에 저장해 반환한다.
	 */
	@Override
	public Positions findMovablePositions(Position position, List<Piece> pieces, Color color) {
		Positions movablePositions = getMovableDiagonalPositions(position, pieces, color);
		Position forwardPosition = position.getMovedPositionBy(getForwardDirection());

		if (isPossessed(forwardPosition, pieces)) {
			return movablePositions;
		}
		movablePositions.add(getMovableForwardPosition(position, pieces));
		if (position.isPawnInitial(color)) {
			movablePositions.add(getMovableForwardPosition(forwardPosition, pieces));
		}
		return movablePositions;
	}

	/**
	 * getMovableDiagonalPositions는 대각선으로 이동할 수 있는지 여부를 판단하고 이동 가능한 위치들을 반환한다.
	 * 먼저 클래스 필드변수로 저장되어 있는 Directions를 매핑하여, 이를 현재 좌표값에 더해 좌표값의 목록을 만든다.
	 * 그리고 이 중 같은 가로선상에 있는 경우(이는 getMovableForwardPositions에서 따로 처리한다)를 제외하고,
	 * 서로 다른 색상인 경우만 모아서 다시 목록을 만든다.(같은 색상인 경우 이동할 수 없다. 폰은 대각선에 적이 있는 경우에만 이동이 가능하다)
	 * 이를 인자로 하여 Positions를 생성하고, 이를 반환한다.
	 *
	 * @param position 말의 현재 위치값이다.
	 * @param pieces   모든 말의 목록값이다.
	 * @param color    말의 색상(팀) 정보이다.
	 * @return 대각선으로 이동 가능한 위치들을 모아 Positions 객체를 생성한 후 이를 반환한다.
	 */
	private Positions getMovableDiagonalPositions(Position position, List<Piece> pieces, Color color) {
		return movableDirections.getDirections()
				.stream()
				.map(position::getMovedPositionBy)
				.filter(movablePosition -> position.isDifferentRow(movablePosition) && isPossessedByDifferentColor(movablePosition, pieces, color))
				.collect(Collectors.collectingAndThen(Collectors.toSet(), Positions::new));
	}

	/**
	 * isPossessedByDifferentColor는 전달받은 position에 나와 다른 색상(즉, 나와 다른 팀)의 말이 있는지 확인한다.
	 * 전달받은 모든 말의 목록인 pieces를 순회하며 말의 위치와 색상이 같은 경우를 찾고,
	 * 찾을 경우 true를, 찾지 못했을 경우 false를 반환한다.
	 *
	 * @param position 말의 현재 위치값이다.
	 * @param pieces   모든 말의 목록이다.
	 * @param color    말의 색상(팀) 값이다.
	 * @return 전달된 위치에 다른 색 말이 존재할 경우, true를 반환한다. 그렇지 않을 경우 false를 반환한다.
	 */
	private boolean isPossessedByDifferentColor(Position position, List<Piece> pieces, Color color) {
		return pieces.stream()
				.anyMatch(piece -> piece.isSamePosition(position) && piece.isNotSameColor(color));
	}

	/**
	 * getForwardDirection은 클래스가 필드변수로 가지는 Directions 값 중 전진하는 값(즉, 내 위치에서 x값이 바뀌지 않고 움직이는 값)을 찾는다.
	 * Pawn의 경우 전진은 하나의 방향만 가지므로(2칸 이동 룰 역시 이를 재활용하기에, moveDirections에는 1개만 존재한다) Direction을 반환한다.
	 * 찾지 못했을 경우, Direction.NONE을 반환한다. 이는 움직임이 없는 방향값이다.
	 * (이 방향값으로 움직임을 실행 시, 모든 말의 목록에 포함되는 자기 자신때문에 isPossessed가 false로 나온다. 즉 의도하지 않은 이동이 불가하다.)
	 *
	 * @return 전진하는 Direction 값을 찾아 반환한다. 없을 경우 Direction.NONE을 반환한다.
	 */
	private Direction getForwardDirection() {
		return movableDirections.getDirections()
				.stream()
				.filter(Direction::canOnlyMoveVertical)
				.findFirst()
				.orElse(Direction.NONE);
	}

	/**
	 * getMovableForwardPosition은 전진할 수 있는지 여부를 판단하고 가능할 시 그 위치를 반환하는 메서드이다.
	 * 전달받은 위치값을 바탕으로 movableForwardPosition이라는, 전진시에 도착하게 될 Position을 먼저 구한다.
	 * 그리고 이 값에 다른 말이 없을 경우(폰은 전진할 때 다른 말을 잡을 수 없으므로, 아무 말도 없어야만 전진이 가능하다)
	 * 그 위치를 반환한다.
	 * 만약 이동이 불가할 경우, 원래의 위치를 반환한다.
	 * 원래의 위치로 이동하려고 할 경우 WrongPositionException이 걸리므로, 로직상의 문제가 없으리라 판단했다.
	 *
	 * @param position 말의 현재 위치 값이다.
	 * @param pieces   모든 말의 목록이다. 말이 움직일 위치에 다른 말이 있는지 여부를 확인한다.
	 * @return 움직일 수 있을 경우, 그 위치를 반환한다. 움직일 수 없을 경우, 현재의 위치를 반환한다.
	 */
	private Position getMovableForwardPosition(Position position, List<Piece> pieces) {
		Position movableForwardPosition = position.getMovedPositionBy(getForwardDirection());
		if (isNotPossessed(movableForwardPosition, pieces)) {
			return movableForwardPosition;
		}
		return position;
	}

	/**
	 * isPossessed는 위치값과 말의 목록을 받아 그 위치에 말이 있는지 점검한다.
	 * 만약 말이 하나라도 있을 경우, true를 반환한다.
	 * 그렇지 않을 경우 false를 반환한다.
	 *
	 * @param position 확인할 Position 값이다.
	 * @param pieces   모든 말의 목록이다.
	 * @return Position에 말이 있는지 여부를 boolean으로 반환한다.
	 */
	private boolean isPossessed(Position position, List<Piece> pieces) {
		return pieces.stream()
				.anyMatch(piece -> piece.isSamePosition(position));
	}

	/**
	 * isNotPossessed는 위치값과 말의 목록을 받아 그 위치에 말이 없는지 점검한다.
	 * 만약 말이 하나도 없을 경우, true를 반환한다.
	 * 그렇지 않을 경우 false를 반환한다.
	 *
	 * @param position 확인할 Position 값이다.
	 * @param pieces   모든 말의 목록이다.
	 * @return Position에 말이 없는지 여부를 boolean으로 반환한다.
	 */
	private boolean isNotPossessed(Position position, List<Piece> pieces) {
		return pieces.stream()
				.noneMatch(piece -> piece.isSamePosition(position));
	}
}