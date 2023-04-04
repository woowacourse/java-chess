package chessgame.controller.commandstatus;

import chessgame.domain.chessgame.Camp;
import chessgame.domain.chessgame.ChessGame;
import chessgame.domain.coordinate.Coordinate;
import chessgame.service.ChessGameService;
import chessgame.view.OutputView;

import java.util.List;

public class PlayCommand implements CommandStatus {

    private static final int START_COORDINATE_INDEX = 1;
    private static final int END_COORDINATE_INDEX = 2;
    private static final int ROW_INDEX = 1;
    private static final int COLUMN_INDEX = 0;
    private static final char ASCII_ALPHABET_A = 'a';

    private final ChessGame chessGame;
    private final ChessGameService chessGameService;
    private final OutputView outputView;

    public PlayCommand(final ChessGame chessGame, final ChessGameService chessGameService, final OutputView outputView) {
        this.chessGame = chessGame;
        this.chessGameService = chessGameService;
        this.outputView = outputView;
        printBoard();
    }

    private void printBoard() {
        outputView.printBoard(chessGame.getBoard());
    }

    @Override
    public boolean canContinue() {
        return true;
    }

    @Override
    public CommandStatus start(List<String> commands) {
        throw new IllegalArgumentException("[ERROR] 게임이 이미 시작되었습니다.");
    }

    @Override
    public CommandStatus move(final List<String> commands) {
        return processMove(commands);
    }

    private CommandStatus processMove(final List<String> commands) {
        Coordinate startCoordinate = convertCoordinate(commands.get(START_COORDINATE_INDEX));
        Coordinate endCoordinate = convertCoordinate(commands.get(END_COORDINATE_INDEX));
        boolean isKing = chessGame.move(startCoordinate, endCoordinate);
        printBoard();
        if (isKing) {
            processWhenCatchKing();
            return new RedayCommand(chessGameService, outputView);
        }
        chessGameService.updateGame(chessGame);
        return this;
    }

    private Coordinate convertCoordinate(final String frontCoordinate) {
        int row = Character.getNumericValue(frontCoordinate.charAt(ROW_INDEX)) - 1;
        int column = (int) frontCoordinate.charAt(COLUMN_INDEX) - ASCII_ALPHABET_A;
        return Coordinate.createOnBoard(row, column);
    }

    private void processWhenCatchKing() {
        printGameWinnerWhenCatchKing(chessGame.getTurn());
        chessGameService.deleteGame(chessGame);
    }

    private void printGameWinnerWhenCatchKing(Camp loseCamp) {
        Camp winner = loseCamp.change();
        outputView.printGameWinningResult(winner);
    }

    @Override
    public CommandStatus status() {
        outputView.printGameStatus(chessGame.getStatus());
        printBoard();
        return this;
    }

    @Override
    public CommandStatus end() {
        outputView.printGameResult(chessGame.getBoard(), chessGame.getStatus());
        return new RedayCommand(chessGameService, outputView);
    }
}
