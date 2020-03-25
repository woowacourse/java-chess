package domain.commend;

public interface State {

	State start();

	State status();

	State end();

	State move(String before, String after);

	State pushCommend(String input);
}
