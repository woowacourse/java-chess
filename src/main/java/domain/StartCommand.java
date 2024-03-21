package domain;

import java.util.Collections;
import java.util.List;

public class StartCommand extends Command {
	public static StartCommand START_COMMAND = new StartCommand();

	private StartCommand() {
		this("ignore");
	}

	private StartCommand(String option) {
		super(option);
	}

	@Override
	public <T> List<T> getOptions() {
		return Collections.emptyList();
	}
}
