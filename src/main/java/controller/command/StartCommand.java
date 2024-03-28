package controller.command;

import controller.status.ChessProgramStatus;
import controller.status.RunningStatus;
import domain.Team;
import domain.piece.Piece;
import domain.player.Player;
import domain.square.Square;
import service.ChessBoardService;
import service.ChessGameService;
import service.PlayerService;
import view.InputView;
import view.OutputView;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class StartCommand implements Command {

    private final PlayerService playerService;
    private final ChessGameService chessGameService;
    private final ChessBoardService chessBoardService;

    public StartCommand(final PlayerService playerService, final ChessGameService chessGameService, final ChessBoardService chessBoardService) {
        this.playerService = playerService;
        this.chessGameService = chessGameService;
        this.chessBoardService = chessBoardService;
    }

    @Override
    public ChessProgramStatus executeStart() throws SQLException {
        final Player blackPlayer = roadPlayer(Team.BLACK);
        final Player whitePlayer = roadPlayer(Team.WHITE);
        final int gameId = chessGameService.createNewGame(blackPlayer, whitePlayer);

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

    private Player roadPlayer(final Team team) {
        while (true) {
            try {
                final String name = InputView.readTeamPlayerName(team);
                return playerService.roadPlayer(name);
            } catch (final IllegalArgumentException e) {
                OutputView.printError(e.getMessage());
            }
        }
    }
}
