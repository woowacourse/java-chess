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
            final Command command = Command.of(inputView.readGameCommand());
            chess = chess.process(command);

            final List<PieceInfo> pieceInfos = getPieceInfos(chess);
            outputView.printBoard(pieceInfos);
        } while (!chess.isEnd());
    }

    private static List<PieceInfo> getPieceInfos(final ChessState chess) {
        return chess.getExistingPieces()
                .stream()
                .map(PieceInfo::new)
                .collect(Collectors.toUnmodifiableList());
    }

}
