package chess.domain.service;

public class GameEndService implements GameService {
	@Override
	public void run() {
		System.exit(0);
	}
}
