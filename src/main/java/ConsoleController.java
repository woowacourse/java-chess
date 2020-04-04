import chess.GameManager;
import chess.board.Location;
import chess.piece.PieceFactory;
import command.FirstCommand;
import command.RunningCommand;
import view.InputView;
import view.OutputView;

public class ConsoleController {
	public final GameManager gameManager = new GameManager(new PieceFactory().createPieces());

	public void run() {
		start();
		running();
		end();
	}

	private void start() {
		OutputView.printInformation();

		FirstCommand firstCommand = readFirstCommand();
		if (firstCommand == FirstCommand.END) {
			end();
		}

		OutputView.printBoard(gameManager);
	}

	private FirstCommand readFirstCommand() {
		try {
			return FirstCommand.of(InputView.inputCommand());
		} catch (IllegalArgumentException e) {
			OutputView.printMessage(e);
			return readFirstCommand();
		}
	}

	private void running() {
		RunningCommand runningCommand;
		do {
			runningCommand = readRunningCommand();
			if (runningCommand.isMove()) {
				move(runningCommand.getNowLocation(), runningCommand.getDestinationLocation());
			}
			if (runningCommand.isStatus()) {
				status();
			}
		} while (gameManager.isRunning() && runningCommand.isNotEnd());
		OutputView.printWinner(gameManager);
	}

	private void move(String nowLocation, String destinationLocation) {
		try {
			gameManager.movePiece(Location.of(nowLocation), Location.of(destinationLocation));
		} catch (IllegalArgumentException | NullPointerException e) {
			OutputView.printMessage(e);
			running();
			return;
		}
		OutputView.printBoard(gameManager);
	}

	private void status() {
		OutputView.printStatus(gameManager.createStatistics());
	}

	private RunningCommand readRunningCommand() {
		try {
			return RunningCommand.from(InputView.inputCommand());
		} catch (IllegalArgumentException e) {
			OutputView.printMessage(e);
			return readRunningCommand();
		}
	}

	private void end() {
		System.exit(1);
	}
}
