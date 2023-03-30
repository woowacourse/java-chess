package chess.controller.command.chess;

import chess.domain.game.ChessGame;
import chess.service.ChessGameService;
import chess.view.OutputView;
import chess.view.dto.ChessBoardDto;

public class EnterChessGameCommand implements ChessGameCommand {

    private final Long chessGameId;

    public EnterChessGameCommand(final String input) {
        final String[] commands = input.split(" ");
        this.chessGameId = Long.valueOf(commands[1]);
    }

    @Override
    public ChessGame execute(final ChessGameService chessGameService, final OutputView outputView) {
        final ChessGame chessGame = chessGameService.loadChessGameById(chessGameId);
        outputView.printChessBoard(ChessBoardDto.from(chessGame));
        return chessGame;
    }
}
