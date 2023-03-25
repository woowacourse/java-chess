package chess.controller.command.chess;

import chess.dao.dto.ChessGameDto;
import chess.dao.dto.PieceDto;
import chess.domain.game.ChessGame;
import chess.service.ChessGameFactory;
import chess.service.ChessGameService;
import chess.service.PieceService;
import chess.view.OutputView;
import chess.view.dto.ChessBoardDto;
import java.util.List;

public class EnterChessGameCommand implements ChessGameCommand {

    private final Long chessGameId;

    public EnterChessGameCommand(final String input) {
        final String[] commands = input.split(" ");
        this.chessGameId = Long.valueOf(commands[1]);
    }

    @Override
    public ChessGame execute(
            final ChessGameService chessGameService,
            final PieceService pieceService,
            final OutputView outputView
    ) {
        final ChessGame chessGame = searchChessGame(chessGameService, pieceService);
        outputView.printChessBoard(ChessBoardDto.from(chessGame));
        return chessGame;
    }

    private ChessGame searchChessGame(final ChessGameService chessGameService, final PieceService pieceService) {
        final ChessGameDto chessGameDto = chessGameService.findById(chessGameId);
        final List<PieceDto> pieceDtos = pieceService.findAllByChessGameId(chessGameId);
        return ChessGameFactory.create(chessGameDto, pieceDtos);
    }
}
