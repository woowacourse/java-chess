package domain.state;

import domain.pieces.Pieces;

import java.util.Arrays;
import java.util.function.Function;

public enum StateType {
	ENDED("ended", Ended::new),
	MOVED("moved", Moved::new),
	REPORTED("reported", Reported::new),
	STARTED("started", Started::new);

	private final String name;
	private final Function<Pieces, State> factory;

	StateType(final String name, final Function<Pieces, State> factory) {
		this.name = name;
		this.factory = factory;
	}

	public static StateType of(final String name) {
		return Arrays.stream(values())
				.filter(stateType -> stateType.name.equals(name))
				.findFirst()
				.orElseThrow(IllegalArgumentException::new);
	}

	public static Function<Pieces, State> getFactory(final String name) {
		return of(name).factory;
	}
}
