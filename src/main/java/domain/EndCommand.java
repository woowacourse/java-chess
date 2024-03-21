package domain;

import java.util.List;

public class EndCommand extends Command {
	public static EndCommand END_COMMAND = new EndCommand();

	private EndCommand() {
		this("ignore");
	}

	private EndCommand(String option) {
		super(option);
	}

	@Override
	public <T> List<T> getOptions() {
		return null;
	}
}
