package controller.command;

import controller.status.ChessProgramStatus;
import controller.status.RunningStatus;
import domain.Team;
import domain.piece.Piece;
import domain.player.Player;
import domain.square.Square;
import service.ChessBoardService;
import service.ChessGameService;
import view.InputView;
import view.OutputView;

import java.util.List;
import java.util.Map;

public class ContinueCommand implements Command {

    private final ChessGameService chessGameService;
    private final ChessBoardService chessBoardService;

    public ContinueCommand(final ChessGameService chessGameService, final ChessBoardService chessBoardService) {
        this.chessGameService = chessGameService;
        this.chessBoardService = chessBoardService;
    }

    @Override
    public ChessProgramStatus executeStart() {
        final List<Integer> runningGame = chessGameService.findRunningGame();
        final int gameId = readGameId(runningGame);

        final Player blackPlayer = chessGameService.findPlayer(gameId, Team.BLACK);
        final Player whitePlayer = chessGameService.findPlayer(gameId, Team.WHITE);

        OutputView.printGameOption(gameId, blackPlayer.getName(), whitePlayer.getName());

        final Map<Square, Piece> pieceSquares = chessBoardService.getPieceSquares(gameId);
        OutputView.printChessBoard(pieceSquares);

        final Team currentTeam = chessGameService.findCurrentTeam(gameId);
        final Player currentPlayer = chessGameService.findPlayer(gameId, currentTeam);
        return new RunningStatus(gameId, currentPlayer, currentTeam);
    }

    @Override
    public ChessProgramStatus executePlay(final List<String> command, final int gameId) {
        throw new UnsupportedOperationException("사용할 수 없는 기능입니다.");
    }

    private int readGameId(final List<Integer> runningGame) {
        while (true) {
            final int input = InputView.readContinueGame(runningGame);
            final boolean hasGame = chessGameService.containRunningGame(input);
            if (hasGame) {
                return input;
            }
            OutputView.printError("게임 ID가 존재하지 않습니다.");
        }
    }
}
