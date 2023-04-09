package chess.controller.command;

import chess.domain.chessgame.ChessGame;
import chess.domain.position.Position;
import chess.dto.PieceDto;
import chess.service.ChessGameService;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.List;

public class MoveCommand extends AbstractCommand {

    static final String COMMAND = "move";
    private static final int TOTAL_COMMAND_OPTIONS_LENGTH = 3;
    private static final int FROM_POSITION_INDEX = 1;
    private static final int TO_POSITION_INDEX = 2;

    private final Position from;
    private final Position to;


    public MoveCommand(final String fromPosition, final String toPosition) {
        this.from = Position.of(fromPosition);
        this.to = Position.of(toPosition);
    }

    public static MoveCommand of(final List<String> commandWithOptions) {
        validateLength(commandWithOptions, TOTAL_COMMAND_OPTIONS_LENGTH);
        return new MoveCommand(commandWithOptions.get(FROM_POSITION_INDEX)
                , commandWithOptions.get(TO_POSITION_INDEX));
    }

    @Override
    public ChessGame execute(final InputView inputView, final OutputView outputView, final ChessGameService chessGameService, final ChessGame chessGame) {
        validatePlaying(chessGame);

        final PieceDto[][] pieceDtos = chessGameService.moveWithCapture(chessGame, from, to);
        outputView.printChessBoard(pieceDtos);
        if (chessGameService.isGameOver(chessGame)) {
            outputView.printWinner(chessGameService.getWinner(chessGame));
            return null;
        }
        return chessGame;
    }
}
