package chess.domain;

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

	public Pieces(List<Piece> pieces) {
		this.pieces = pieces;
	}

	public void move(Position start, Position end, Color color) {
		Piece piece = findBy(start, color).orElseThrow(() -> new IllegalArgumentException(INVALID_INPUT_EXCEPTION_MESSAGE)); //step1
		Set<Position> movablePosition = piece.getAvailablePositions();//step2

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
			if(piece.getPosition().equals(start)){
				return Optional.ofNullable(piece);
			}
		}
		return Optional.empty();
	}

	public Optional<Piece> findBy(Position start, Color color) {
		Piece piece = findBy(start).orElseGet(Blank::new); //step1
		if(piece.getColor().isSame(color)) {
			return Optional.ofNullable(piece);
		}
		return Optional.empty();
	}

	private List<Piece> check(Set<Position> positions, List<Piece> userPieces){
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