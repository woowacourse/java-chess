package domain;

import java.util.List;

public abstract class Command {
	protected final String option;

	public Command(String option) {
		this.option = option;
	}

	public abstract <T> List<T> getOptions();
}
