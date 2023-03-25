package chess.controller.command.chess;

import chess.dao.dto.ChessGameDto;
import chess.domain.board.BoardFactory;
import chess.domain.game.ChessGame;
import chess.domain.game.state.GameState;
import chess.service.ChessGameService;
import chess.service.PieceService;
import chess.view.OutputView;
import chess.view.dto.ChessBoardDto;

public class NewChessGameCommand implements ChessGameCommand {

    @Override
    public ChessGame execute(
            final ChessGameService chessGameService,
            final PieceService pieceService,
            final OutputView outputView
    ) {
        final ChessGame chessGame = savedChessGame(chessGameService, pieceService);
        outputView.printChessBoard(ChessBoardDto.from(chessGame));
        return chessGame;
    }

    private ChessGame savedChessGame(final ChessGameService chessGameService, final PieceService pieceService) {
        final ChessGame chessGame = new ChessGame(BoardFactory.create(), GameState.START);
        chessGameService.save(chessGame);

        final ChessGameDto chessGameDto = chessGameService.findLatest();
        chessGame.setId(chessGameDto.getId());
        pieceService.saveAll(chessGame.getId(), chessGame.getBoard());

        return chessGame;
    }
}
