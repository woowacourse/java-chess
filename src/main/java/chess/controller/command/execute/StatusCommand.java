package chess.controller.command.execute;

import static chess.domain.piece.Color.BLACK;
import static chess.domain.piece.Color.WHITE;

import chess.domain.game.ChessGame;
import chess.view.OutputView;
import chess.view.dto.ChessStatusDto;

public class StatusCommand implements ExecuteCommand {

    @Override
    public void execute(final ChessGame chessGame, final OutputView outputView) {
        outputView.printChessStatus(
                ChessStatusDto.of(chessGame.calculateScore(BLACK), chessGame.calculateScore(WHITE))
        );
    }
}
