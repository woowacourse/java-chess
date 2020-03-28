package domain.point;

import java.util.Arrays;
import java.util.function.BiFunction;

public enum Distance {
	ONE(DistanceFilters::isOne),
	VERTICAL_TWO(DistanceFilters::isVerticalTwo),
	ELSE(DistanceFilters::isElse);

	private final BiFunction<Integer, Integer, Boolean> distanceFilter;

	Distance(BiFunction<Integer, Integer, Boolean> distanceFilter) {
		this.distanceFilter = distanceFilter;
	}

	public static Distance of(Point from, Point to) {
		int rowDifference = Math.abs(to.getRowIndex() - from.getRowIndex());
		int columnDifference = Math.abs(to.getColumnIndex() - from.getColumnIndex());

		return Arrays.stream(values())
				.filter(distance -> distance.distanceFilter.apply(rowDifference, columnDifference))
				.findFirst()
				.orElseThrow(RuntimeException::new);
	}
}
