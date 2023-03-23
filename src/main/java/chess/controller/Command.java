package chess.controller;

import chess.domain.ChessGame;
import chess.domain.Position;
import chess.view.OutputView;
import chess.view.InputView;

public enum Command {

	START {
		public Command run(final ChessGame chessGame) {
			chessGame.initialize();
			OutputView.printBoard(OutputRenderer.toBoardDto(chessGame.getBoard()));
			return this;
		}
	},
	MOVE {
		public Command run(final ChessGame chessGame) {
			PositionDto positionDto = InputRenderer.toPosition(InputView.readPosition(), InputView.readPosition());
			Position source = positionDto.getSourcePosition();
			Position target = positionDto.getTargetPosition();
			chessGame.movePiece(source, target);
			OutputView.printBoard(OutputRenderer.toBoardDto(chessGame.getBoard()));
			return this;
		}
	},
	STATUS {
		public Command run(final ChessGame chessGame) {
			OutputView.printTempResult(OutputRenderer.toTempResultDto(chessGame.getTempResult()));
			return this;
		}
	},
	END {
		public Command run(final ChessGame chessGame) {
			return this;
		}
	};

	public abstract Command run(ChessGame chessGame);
}
