package chess.domain.piece.pieces;

import chess.domain.board.position.Position;
import chess.domain.piece.Blank;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class Pieces {
	private static final String INVALID_INPUT_EXCEPTION_MESSAGE = "잘못된 위치를 입력하셨습니다.";
	private final List<Piece> pieces;

	// package accessed
	Pieces(List<Piece> pieces) {
		this.pieces = pieces;
	}

	public void move(Position start, Position end, Color color) {
		Piece piece = findBy(start, color).orElseThrow(() -> new IllegalArgumentException(INVALID_INPUT_EXCEPTION_MESSAGE)); //step1
		Set<Position> movablePosition = piece.createMovablePositions(pieces);//step2

		// 내일 예상
		// findRemovablePositions(movablePosition, check(movablePosition, this.pieces), color); // 내 컬러가 뭔지도 알 수 있다.
		// 각 피스에서 리무버를 생서앟ㄹ 때 본인의 디렉션을 넣어서 초기화하기
		// 적꺼 추가를 findRemovablePositions에서 컬러 기반으로 해주기. 우선 적이든 아군이든 포함하고 가고 그 뒤에 아군을 없에는 방식으로
		// return 은 movable이 나온다.

//		Set<Position> removablePositions = piece.findRemovablePositions(movablePosition, check(movablePosition, this.pieces));// step3\
//		Set<Position> alivePositions = piece.findRemovablePositions(movablePosition, check(movablePosition, this.pieces));// step3\
//		color check!!!! remove step 4!!!
//		movablePosition.removeAll(removablePositions);
//
//		removablePositions = piece.findRemovablePositions(movablePosition, check(movablePosition, opponent));// step4
//		for(Piece checkedPiece : check(movablePosition, opponent)) {
//			removablePositions.remove(checkedPiece.getPosition());
//		}
//		movablePosition.removeAll(removablePositions);

		//step5 : movablePosition

		//step6 start
		if (!movablePosition.contains(end)){
			throw new IllegalArgumentException(INVALID_INPUT_EXCEPTION_MESSAGE);
		}
		piece.changePosition(end);
		//step6 end

		//opponent.findAndDelete(end); //step7 end
	}

//	private Piece findBy(Position start) {
//		for(Piece piece : pieces) {
//			if(piece.getPosition().equals(start)){
//				return piece;
//			}
//		}
//		throw new IllegalArgumentException(INVALID_INPUT_EXCEPTION_MESSAGE);
//	}

	public Optional<Piece> findBy(Position start) {
		for(Piece piece : pieces) {
			if(piece.getPosition().equals(start)){ // todo piece에게 같은 위치인지 물어보는 걸로, 인덴트
				return Optional.ofNullable(piece);
			}
		}
		return Optional.empty();
	}

	public Optional<Piece> findBy(Position start, Color color) {
		Piece piece = findBy(start).orElseGet(Blank::new); //step1
		if(piece.getColor().isSame(color)) { // // TODO: 2020/03/26 piece에게 같은 컬러인지 물어보는 걸로, 인덴트
			return Optional.ofNullable(piece);
		}
		return Optional.empty();
	}

	private List<Piece> check(Set<Position> positions, List<Piece> userPieces){ // TODO: 2020/03/26  뭘 체크하는거지? , 인덴트
		List<Piece> result = new ArrayList<>();
		for(Piece piece : userPieces){
			if(positions.contains(piece.getPosition())) {
				result.add(piece);
			}
		}
		return result;
	}

	public List<Piece> getPieces() {
		return pieces;
	}
}