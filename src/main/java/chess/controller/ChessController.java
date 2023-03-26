package chess.controller;

import chess.controller.command.Command;
import chess.controller.command.CommandAction;
import chess.controller.command.Type;
import chess.domain.piece.maker.EmptyPieceGenerator;
import chess.domain.piece.property.Color;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import chess.domain.state.ChessState;
import chess.dto.controllertoview.PieceInfo;
import chess.dto.domaintocontroller.GameStatus;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Map.entry;

public class ChessController {

    private final Map<Type, CommandAction> commandActionMap;
    private final InputView inputView;
    private final OutputView outputView;

    public ChessController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;

        commandActionMap = Map.ofEntries(
                entry(Type.START, new CommandAction(this::start)),
                entry(Type.MOVE, new CommandAction(this::move)),
                entry(Type.END, new CommandAction(this::end)),
                entry(Type.STATUS, new CommandAction(this::status))
        );
    }

    public void run() {
        outputView.printGameStartGuideMessage();
        ChessState chess = ChessState.start(new EmptyPieceGenerator());
        do {
            chess = readAndProcessCommand(chess);
        } while (!chess.isEnd());
    }

    private ChessState readAndProcessCommand(final ChessState chess) {
        try {
            final Command command = Command.of(inputView.readGameCommand());
            return commandActionMap.getOrDefault(command.getType(), CommandAction.INVALID_ACTION)
                    .run(chess, command.getParameters());
        } catch (RuntimeException exception) {
            outputView.printErrorMessage(exception);
            return chess;
        }
    }


    private ChessState start(final ChessState chess, final List<String> commandParameters) {
        final ChessState newChessState = chess.start();
        printExistingPieces(newChessState);
        return newChessState;
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
