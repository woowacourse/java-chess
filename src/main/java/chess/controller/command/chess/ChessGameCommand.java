package chess.controller.command.chess;

import chess.domain.game.ChessGame;
import chess.service.ChessGameService;
import chess.service.PieceService;
import chess.view.OutputView;

public interface ChessGameCommand {

    ChessGame execute(
            final ChessGameService chessGameService,
            final PieceService pieceService,
            final OutputView outputView
    );
}
