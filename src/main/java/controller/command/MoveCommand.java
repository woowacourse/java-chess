package controller.command;

import controller.status.ChessProgramStatus;
import controller.status.RunningStatus;
import controller.status.StartingStatus;
import domain.ChessGameResult;
import domain.Team;
import domain.piece.Piece;
import domain.player.Player;
import domain.square.Square;
import service.ChessBoardService;
import service.ChessGameService;
import service.ChessResultService;
import view.OutputView;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class MoveCommand implements Command {

    private static final int SOURCE_INDEX = 1;
    private static final int TARGET_INDEX = 2;

    private final ChessBoardService chessBoardService;
    private final ChessGameService chessGameService;
    private final ChessResultService chessResultService;

    public MoveCommand(
            final ChessBoardService chessBoardService,
            final ChessGameService chessGameService,
            final ChessResultService chessResultService
    ) {
        this.chessBoardService = chessBoardService;
        this.chessGameService = chessGameService;
        this.chessResultService = chessResultService;
    }

    @Override
    public ChessProgramStatus executeStart() {
        throw new UnsupportedOperationException("사용할 수 없는 기능입니다.");
    }

    @Override
    public ChessProgramStatus executePlay(final List<String> command, final int gameId) throws SQLException {
        validateCommand(command);
        final Square source = Square.from(command.get(SOURCE_INDEX));
        final Square target = Square.from(command.get(TARGET_INDEX));

        chessBoardService.move(gameId, source, target);

        final Map<Square, Piece> pieceSquares = chessBoardService.getPieceSquares(gameId);
        OutputView.printChessBoard(pieceSquares);

        final Team currentTeam = chessGameService.findCurrentTeam(gameId);
        final Player currentPlayer = chessGameService.findPlayer(gameId, currentTeam);

        if (chessGameService.isEnd(gameId)) {
            chessResultService.saveResult(gameId);
            final ChessGameResult chessGameResult = chessResultService.calculateResult(gameId);
            OutputView.printStatus(chessGameResult);

            return new StartingStatus();
        }

        return new RunningStatus(gameId, currentPlayer, currentTeam);
    }

    private void validateCommand(final List<String> command) {
        if (command.size() != 3) {
            throw new IllegalArgumentException("잘못된 command입니다.");
        }
    }
}
