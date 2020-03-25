package domain.commend;

public interface State {

	State start();

	State status();

	State end();

	State move(String from, String to);

	State pushCommend(String input);
}
