package chess.controller;

import chess.controller.command.Command;
import chess.controller.command.CommandAction;
import chess.controller.command.CommandType;
import chess.controller.command.parameter.StartOptionParameter;
import chess.dao.DbChessGameDao;
import chess.dao.DbGameRoomDao;
import chess.dao.DbPieceDao;
import chess.dao.GameRoomDao;
import chess.dao.connection.MySqlConnectionGenerator;
import chess.domain.piece.maker.EmptyPieceGenerator;
import chess.domain.piece.property.Color;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import chess.domain.state.ChessBeforeStart;
import chess.domain.state.ChessState;
import chess.dto.controllertoview.PieceInfo;
import chess.dto.domaintocontroller.GameStatus;
import chess.exception.ChessDbException;
import chess.exception.ChessException;
import chess.exception.ExceptionCode;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Map.entry;

public class ChessController {

    private final Map<CommandType, CommandAction> commandActionMap;
    private final GameRoomDao gameRoomDao;
    private final InputView inputView;
    private final OutputView outputView;

    public ChessController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.gameRoomDao = new DbGameRoomDao(new MySqlConnectionGenerator());

        commandActionMap = Map.ofEntries(
                entry(CommandType.START, new CommandAction(this::start)),
                entry(CommandType.MOVE, new CommandAction(this::move)),
                entry(CommandType.END, new CommandAction(this::end)),
                entry(CommandType.STATUS, new CommandAction(this::status))
        );
    }

    public void run() {
        outputView.printGameStartGuideMessage();
        printExisingRooms();
        ChessState chess = ChessState.start(
                new EmptyPieceGenerator(),
                new DbChessGameDao(new MySqlConnectionGenerator()),
                new DbPieceDao(new MySqlConnectionGenerator())
        );
        do {
            chess = readAndProcessCommand(chess);
        } while (!chess.isEnd());
    }

    private void printExisingRooms() {
        final List<Integer> exisingRoomNumbers = gameRoomDao.findExistingRoomNumbers();
        outputView.printExistingRoomNumbers(exisingRoomNumbers);
    }

    private ChessState readAndProcessCommand(final ChessState chess) {
        try {
            final Command command = Command.of(inputView.readGameCommand());
            return commandActionMap.getOrDefault(command.getType(), CommandAction.INVALID_ACTION)
                    .run(chess, command.getParameters());
        } catch (ChessException exception) {
            outputView.printErrorMessage(exception);
            return chess;
        } catch (ChessDbException exception) {
            outputView.printDbExceptionMessage();
            return chess.end();
        }
    }


    private ChessState start(final ChessState chess, final List<String> commandParameters) {
        final String option = commandParameters.get(Command.START_OPTION_INDEX);
        final ChessState newChessState = startWith(chess, option);
        printExistingPieces(newChessState);
        return newChessState;
    }

    private ChessState startWith(final ChessState chess, final String option) {
        if (option.equals(StartOptionParameter.NEW_GAME_OPTION)) {
            return chess.start(ChessBeforeStart.NEW_ROOM_ID_OPTION);
        }
        final List<Integer> existingRoomNumbers = gameRoomDao.findExistingRoomNumbers();
        if (existingRoomNumbers.contains(Integer.valueOf(option))) {
            return chess.start(Integer.parseInt(option));
        }
        throw new ChessException(ExceptionCode.NOT_VALID_ROOM_ID);
    }

    private void printExistingPieces(final ChessState chess) {
        final List<PieceInfo> pieceInfos = getPieceInfos(chess);
        outputView.printBoard(pieceInfos);
    }

    private static List<PieceInfo> getPieceInfos(final ChessState chess) {
        return chess.getExistingPieces()
                .stream()
                .map(PieceInfo::new)
                .collect(Collectors.toUnmodifiableList());
    }

    private ChessState move(final ChessState chess, final List<String> commandParameters) {
        final Position source = generatePositionBy(commandParameters.get(Command.MOVE_CURRENT_POSITION_INDEX));
        final Position target = generatePositionBy(commandParameters.get(Command.MOVE_TARGET_POSITION_INDEX));

        final ChessState newChessState = chess.move(source, target);
        printExistingPieces(newChessState);
        return newChessState;
    }

    private Position generatePositionBy(final String fileRankInput) {
        final String fileCode = String.valueOf(fileRankInput.charAt(0));
        final String rankCode = String.valueOf(fileRankInput.charAt(1));

        return Position.of(File.findByCode(fileCode), Rank.findByCode(rankCode));
    }

    private ChessState status(final ChessState chess, final List<String> commandParameters) {
        final GameStatus gameStatus = chess.status();
        printStatusByGameResult(gameStatus);
        printExistingPieces(chess);
        return chess;
    }

    private void printStatusByGameResult(final GameStatus gameStatus) {
        if (gameStatus.getWinningTeamColor() == Color.BLANK) {
            final Map<Color, Double> scores = Map.ofEntries(
                    entry(Color.BLACK, gameStatus.getBlackScore()),
                    entry(Color.WHITE, gameStatus.getWhiteScore())
            );
            outputView.printScores(scores);
            return;
        }
        outputView.printWinner(gameStatus.getWinningTeamColor());
    }

    private ChessState end(final ChessState chess, final List<String> commandParameters) {
        return chess.end();
    }
}
