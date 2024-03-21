package chess.controller;

import chess.domain.ChessBoard;
import chess.domain.ChessBoardFactory;
import chess.domain.Position;
import chess.view.GameStartCommand;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.List;

// TODO: 이름 고민 - er??
public class ChessController {

    private final InputView inputView;
    private final OutputView outputView;

    public ChessController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        final ChessBoard chessBoard = ChessBoardFactory.makeChessBoard();

        GameStartCommand gameStartCommand = inputView.readGameStartCommand();
        if (GameStartCommand.START.equals(gameStartCommand)) {
            outputView.printChessBoard(chessBoard);
        }
        
        // TODO: 검증
        List<String> positions = inputView.readPositions();
        String rawSourcePosition = positions.get(0);
        String rawTargetPosition = positions.get(1);

        char sourceFile = rawSourcePosition.substring(0, 1).charAt(0);
        int sourceRank = Integer.parseInt(rawSourcePosition.substring(1, 2));
        Position source = Position.of(sourceFile, sourceRank);

        char targetFile = rawTargetPosition.substring(0, 1).charAt(0);
        int targetRank = Integer.parseInt(rawTargetPosition.substring(1, 2));
        Position target = Position.of(targetFile, targetRank);

        chessBoard.move(source, target);

        outputView.printChessBoard(chessBoard);
    }
}
