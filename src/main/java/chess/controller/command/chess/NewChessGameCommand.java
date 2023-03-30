package chess.controller.command.chess;

import chess.domain.board.BoardFactory;
import chess.domain.game.ChessGame;
import chess.domain.game.state.GameState;
import chess.service.ChessGameService;
import chess.view.OutputView;
import chess.view.dto.ChessBoardDto;

public class NewChessGameCommand implements ChessGameCommand {

    @Override
    public ChessGame execute(final ChessGameService chessGameService, final OutputView outputView) {
        final ChessGame chessGame = savedChessGame(chessGameService);
        outputView.printChessBoard(ChessBoardDto.from(chessGame));
        return chessGame;
    }

    private ChessGame savedChessGame(final ChessGameService chessGameService) {
        final ChessGame chessGame = new ChessGame(BoardFactory.create(), GameState.START);
        chessGameService.saveChessGame(chessGame);
        return chessGame;
    }
}
