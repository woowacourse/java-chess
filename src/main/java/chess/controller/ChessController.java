package chess.controller;

import chess.database.DBChessBoardDao;
import chess.domain.ChessBoard;
import chess.domain.ChessGame;
import chess.domain.position.Position;
import chess.view.*;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public final class ChessController {

    private static final int COMMAND_INDEX = 0;
    private static final int FROM_POSITION_INDEX = 1;
    private static final int TO_POSITION_INDEX = 2;
    private static final int FILE_INDEX = 0;
    private static final int RANK_INDEX = 1;
    public static final int POSITION_SET_INDEX = 2;

    private final Map<ChessCommand, GameAction> commandMapper = new EnumMap<>(ChessCommand.class);
    private final DBChessBoardDao dbChessBoardDao;
    private final ChessGame game;

    public ChessController(ChessGame game, DBChessBoardDao dbChessBoardDao) {
        this.game = game;
        this.dbChessBoardDao = dbChessBoardDao;
        initController();
    }

    private void initController() {
        commandMapper.put(ChessCommand.START, this::start);
        commandMapper.put(ChessCommand.MOVE, this::move);
        commandMapper.put(ChessCommand.STATUS, this::status);
        commandMapper.put(ChessCommand.END, (ignored1, ignored2) -> {
        });
    }

    public void run() {
        OutputView.printStartPrefix();
        List<Position> positions = dbChessBoardDao.select();
        checkPlay(positions);
        ChessCommand gameCommand = ChessCommand.WAIT;
        while (game.isKingsLive() && gameCommand != ChessCommand.END) {
            gameCommand = play(game);
        }
        OutputView.printWinner(game.getWinnerCamp());
    }

    private void checkPlay(final List<Position> positions) {
        if (positions != null) {
            OutputView.printReStart();
            getNotation(positions, game);
        }
    }

    private void getNotation(List<Position> positions, ChessGame game) {
        for (int i = 0; i < positions.size(); i += POSITION_SET_INDEX) {
            game.move(positions.get(i), positions.get(i + 1));
        }
    }

    private ChessCommand play(ChessGame game) {
        try {
            List<String> commands = InputView.readCommand();
            ChessCommand command = ChessCommand.from(commands.get(COMMAND_INDEX));
            GameAction gameAction = commandMapper.get(command);
            gameAction.execute(commands, game);
            return command;
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return ChessCommand.WAIT;
        }
    }

    private void start(List<String> commands,ChessGame game) {

        ChessCommand.validateStartCommand(commands);
        printBoard(game.getChessBoard());
    }

    private void move(List<String> commands,ChessGame game) {
        ChessCommand.validatePlayingCommand(commands);
        String fromInput = commands.get(FROM_POSITION_INDEX);
        String toInput = commands.get(TO_POSITION_INDEX);
        game.move(toPosition(fromInput), toPosition(toInput));
        dbChessBoardDao.save(toPosition(fromInput), toPosition(toInput));
        printBoard(game.getChessBoard());
    }

    private void status(List<String> commands,ChessGame game) {
        ChessCommand.validateStatusCommand(commands);
        OutputView.printStatusScore(game.getWhiteScore(), game.getBlackScore());
    }

    private Position toPosition(String positionInput) {
        String fileInput = String.valueOf(positionInput.charAt(FILE_INDEX));
        String rankInput = String.valueOf(positionInput.charAt(RANK_INDEX));

        return Position.of(ViewFile.from(fileInput), ViewRank.from(rankInput));
    }

    private void printBoard(ChessBoard chessBoard) {
        OutputView.printChessState(chessBoard.getBoard());
    }
}
