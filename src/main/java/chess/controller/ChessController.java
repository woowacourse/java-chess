package chess.controller;

import chess.controller.command.Command;
import chess.domain.piece.maker.EmptyPieceGenerator;
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
            final ChessState newChessState = chess.process(command);
            printExistingPieces(newChessState);
            return newChessState;
        } catch (RuntimeException exception) {
            outputView.printErrorMessage(exception);
            return chess;
        }
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

}
