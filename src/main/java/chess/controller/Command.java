package chess.controller;

import chess.ChessService;
import chess.domain.ChessGame;
import chess.domain.Position;
import chess.view.OutputView;
import chess.view.InputView;

public enum Command {

	START {
		public Command run(final ChessService chessService) {
			if (chessService.checkLastGameExists()) {
				chessService.loadLastGame();
				OutputView.printBoard(OutputRenderer.toBoardDto(chessService.getBoard()));
				return this;
			}
			ChessGame chessGame = chessService.initGame();
			OutputView.printBoard(OutputRenderer.toBoardDto(chessGame.getBoard()));
			return this;
		}
	},
	MOVE {
		public Command run(final ChessService chessService) {
			Position source = InputRenderer.toPosition(InputView.readPosition());
			Position target = InputRenderer.toPosition(InputView.readPosition());
			chessService.movePiece(source, target);
			OutputView.printBoard(OutputRenderer.toBoardDto(chessService.getBoard()));
			return this;
		}
	},
	STATUS {
		public Command run(final ChessService chessService) {
			OutputView.printTempResult(OutputRenderer.toTempResultDto(chessService.getTempResult()));
			return this;
		}
	},
	END {
		public Command run(final ChessService chessService) {
			return this;
		}
	};

	public abstract Command run(final ChessService chessService);
}
