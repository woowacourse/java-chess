package chess.domain;

public class RelativePosition {

	private final int x;
	private final int y;

	public RelativePosition(final int x, final int y) {
		this.x = x;
		this.y = y;
	}

	public RelativePosition toUnit() {
		if (x == 0 && y == 0) {
			return new RelativePosition(0, 0);
		}
		if (x == 0) {
			return new RelativePosition(0, 1);
		}
		if (y == 0) {
			return new RelativePosition(1, 0);
		}
		return toUnitNoneZeroPosition();
	}

	private RelativePosition toUnitNoneZeroPosition() {
		int absMin = Math.min(Math.abs(x), Math.abs(y));
		int absMax = Math.max(Math.abs(x), Math.abs(y));
		int quotient = absMax / absMin;
		int remainder = absMax % absMin;
		if (remainder == 0) {
			return new RelativePosition(x / quotient, y / quotient);
		}
		return new RelativePosition(x, y);
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		RelativePosition that = (RelativePosition)o;

		if (x != that.x)
			return false;
		return y == that.y;
	}

	@Override
	public int hashCode() {
		int result = x;
		result = 31 * result + y;
		return result;
	}
}
