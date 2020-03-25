package domain.commend;

import domain.commend.exceptions.StateException;
import domain.pieces.Pieces;
import domain.point.Column;
import domain.point.Point;
import domain.point.Row;

import java.util.Arrays;
import java.util.List;

public class Start extends Playing {
	protected Start(Pieces pieces) {
		super(pieces);
	}

	@Override
	public State end() {
		return new End(getPieces());
	}

	@Override
	public State move(String before, String after) {
		Point beforePoint = Point.of(before);
		Point afterPoint = Point.of(after);

		getPieces().move(beforePoint, afterPoint);

		return null;
	}

	@Override
	public State pushCommend(String input) {
		List<String> tokens = Arrays.asList(input.split(" "));

		if (tokens.get(0).equals("end")) {
			if (tokens.size() != 1) {
				throw new StateException("잘못된 명령어입니다.");
			}
			return end();
		}

		if (tokens.get(0).equals("move")) {
			if (tokens.size() != 3) {
				throw new StateException("잘못된 명령어입니다.");
			}
			return move(tokens.get(1), tokens.get(2));
		}

		return null;
	}
}
