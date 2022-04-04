package chess.console;

import java.util.List;

import chess.console.view.ChessView;
import chess.console.domain.CommandRequest;
import chess.domain.Position;
import chess.domain.game.ChessGame;
import chess.dto.PlayerScoresDto;
import chess.dto.PlayersDto;

public class ChessApplication {

    private final ChessView chessView;

    public ChessApplication(final ChessView chessView) {
        this.chessView = chessView;
    }

    public void run() {
        final ChessGame chessGame = ChessGame.initializeChessGame();
        chessView.printGameCommandGuide();

        do {
            play(chessGame);
        } while (chessGame.isRunning());
    }

    private void play(final ChessGame chessGame) {
        final CommandRequest commandRequest = chessView.requestGameCommand();
        final String command = commandRequest.getCommand();
        final List<String> commandOptions = commandRequest.getOptions();

        final CommandExecutor commandExecutor = CommandExecutor.from(command);
        commandExecutor.execute(this, chessGame, commandOptions);
    }

    public void executeStart(final ChessGame chessGame) {
        chessGame.start();
        chessView.printChessBoard(PlayersDto.toDto(chessGame.getPlayers()));
    }

    public void executeMove(final ChessGame chessGame, final List<String> commandOptions) {
        validateMoveCommandOptionSizeEnough(commandOptions);

        final Position source = Position.from(commandOptions.get(0));
        final Position target = Position.from(commandOptions.get(1));
        chessGame.movePiece(source, target);
        chessView.printChessBoard(PlayersDto.toDto(chessGame.getPlayers()));

        executePromotionIfPossible(chessGame);
    }

    private void validateMoveCommandOptionSizeEnough(final List<String> commandOptions) {
        if (!CommandExecutor.MOVE.equalsOptionCount(commandOptions.size())) {
            throw new IllegalArgumentException("MOVE 옵션이 잘못되었습니다.");
        }
    }

    private void executePromotionIfPossible(final ChessGame chessGame) {
        if (chessGame.isRunning() && chessGame.isPromotable()) {
            chessGame.promotePiece(chessView.requestPieceInitialToPromotion());
            chessView.printChessBoard(PlayersDto.toDto(chessGame.getPlayers()));
        }
    }

    public void executeStatus(final ChessGame chessGame) {
        chessView.printChessBoard(PlayersDto.toDto(chessGame.getPlayers()));
        final PlayerScoresDto playerScoresDto = PlayerScoresDto.toDto(chessGame.getPlayerScores());
        chessView.printPlayerScores(playerScoresDto);
    }

    public void executeEnd(final ChessGame chessGame) {
        chessGame.end();
    }
}
