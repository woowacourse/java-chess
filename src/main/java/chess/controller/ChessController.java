package chess.controller;

import chess.constant.ExceptionCode;
import chess.controller.command.Command;
import chess.controller.command.Type;
import chess.domain.piece.maker.EmptyPieceGenerator;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import chess.domain.state.ChessState;
import chess.dto.controllertoview.PieceInfo;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.List;
import java.util.stream.Collectors;

public class ChessController {

    private final InputView inputView;
    private final OutputView outputView;

    public ChessController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
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
            return runCommand(chess, command);
        } catch (RuntimeException exception) {
            outputView.printErrorMessage(exception);
            return chess;
        }
    }

    private ChessState runCommand(final ChessState chess, final Command command) {
        if (command.is(Type.START)) {
            final ChessState newChessState = chess.start();
            printExistingPieces(newChessState);
            return newChessState;
        }
        if (command.is(Type.MOVE)) {
            final Position source = generatePositionBy(command.getParameterAt(Command.MOVE_CURRENT_POSITION_INDEX));
            final Position target = generatePositionBy(command.getParameterAt(Command.MOVE_TARGET_POSITION_INDEX));

            final ChessState newChessState = chess.move(source, target);
            printExistingPieces(newChessState);
            return newChessState;
        }
        if (command.is(Type.END)) {
            return chess.end();
        }
        throw new IllegalArgumentException(ExceptionCode.INVALID_COMMAND.name());
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

    private Position generatePositionBy(String fileRankInput) {
        final String fileCode = String.valueOf(fileRankInput.charAt(0));
        final String rankCode = String.valueOf(fileRankInput.charAt(1));

        return Position.of(File.findByCode(fileCode), Rank.findByCode(rankCode));
    }

}
