package chess.domain.piece.movable;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.domain.position.positions.Positions;

import java.util.List;
import java.util.stream.Collectors;

/**
 * UnblockedMovable은 정해진 칸으로만 움직이는 King,Knight의 움직이는 방법을 구현한 클래스이다.
 * UnblockedMovable이란 이름은 막히지 않는(즉 Rook이나 Bishop처럼 누군가에 의해 막힐 때까지 전진하는 로직이 아닌) 움직임을 가졌다는 의미를 가진다.
 * UnblockedMovable을 사용하는 말의 움직임 규칙은 아래와 같다.
 * - 갈 수 있는 칸에 대해, 다른 말이 있는지 확인한다. 만약 아군이 있으면 이동할 수 없고, 비어있거나 적이 있다면 이동할 수 있다.
 */
public class UnblockedMovable implements Movable {
	/**
	 * moveDirections는 Pawn이 움직일 수 있는 방향들을 저장한다.
	 */
	private final Directions moveDirections;

	/**
	 * Constructor는 방향을 주입받아 이를 저장한다.
	 *
	 * @param moveDirections 움직일 수 있는 방향의 목록이다. 각 말의 설정에 따라 다른 값이 들어온다.
	 */
	public UnblockedMovable(Directions moveDirections) {
		this.moveDirections = moveDirections;
	}

	/**
	 * findMovablePositions는 이 인터페이스를 포함하는 상위 클래스(즉, King이나 Knight를 구현한 클래스)에서 호출된다.
	 * 현재 말의 위치, 모든 말들의 목록, 이 말의 색상(색상이 같으면 같은 팀이다) 을 전달받는다.
	 * 이후 말이 움직일 수 있는 방향들을 확인하여, 이를 순회한다.
	 * 순서는 아래와 같다.
	 * - 먼저, 방향값을 통해 구한 위치(현재 위치에서 이동하여 도착하는 위치)를 모은다.
	 * - 이 값들 중 움직일 수 없는 위치(다른 아군 말이 있는 위치)를 필터링한다.
	 * - 필터링되지 않은 위치들을 모은다.
	 * - 최종적으로 구한 Positions 객체를 반환한다.
	 *
	 * @param position 말의 현재 위치값이다. 이 위치에 필드변수 moveDirections를 더하여 말이 움직일 수 있는 위치들을 구한다.
	 * @param pieces   모든 말들을 저장한 리스트이다. 이를 통해 내가 움직이려는 자리에 아군 말이 있는지 확인한다.
	 * @param color    말의 색상값이다. 다른 말과 위치가 겹칠 때, 색이 다른지(적이라는 뜻이므로, 말을 잡을 수 있다)
	 *                 혹은 같은지(아군이 있는 곳으로는 이동할 수 없다) 여부를 판단한다.
	 * @return 최종적으로 말이 position에서 출발해 갈 수 있는 모든 위치를 Positions 객체에 저장해 반환한다.
	 */
	@Override
	public Positions findMovablePositions(Position position, List<Piece> pieces, Color color) {
		return moveDirections.getDirections().stream()
				.map(position::getMovedPositionBy)
				.filter(movablePosition -> checkMovable(movablePosition, pieces, color))
				.collect(Collectors.collectingAndThen(Collectors.toSet(), Positions::new));
	}

	/**
	 * checkMovable은 위치와 모든 말의 목록 및 말의 색상을 전달받아, 그 위치에 아군 말이 있는지 여부를 검사한다.
	 * 만약 그 위치에 아군 말이 있을 경우, 그 위치로는 이동할 수 없으므로 false를 리턴한다.
	 * 그러나 적군 말(잡을 수 있다)이 있거나 비어있는 경우 true를 반환한다.
	 *
	 * @param position 말의 현재 위치이다.
	 * @param pieces   모든 말의 목록이다.
	 * @param color    말의 색상(팀) 이다. 이를 바탕으로 같은 색을 찾는다.
	 * @return 찾고자 하는 위치에 아군 말이 있을 경우 false를, 그렇지 않은 경우 true를 반환한다.
	 */
	private boolean checkMovable(Position position, List<Piece> pieces, Color color) {
		return pieces.stream()
				.noneMatch(piece -> piece.isSamePosition(position) && piece.isSameColor(color));
	}
}