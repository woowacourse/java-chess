package chess;

import chess.domain.chessBoard.ChessBoard;
import chess.domain.chessBoard.generator.ChessSpaceGenerator;
import chess.domain.position.Coordinate;
import chess.domain.position.Position;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.List;

public class ChessMachine {

    private static final String START_COMMAND = "start";
    private static final String MOVE_COMMAND = "move";
    private static final String END_COMMAND = "end";

    private final OutputView outputView;
    private final InputView inputView;

    public ChessMachine(OutputView outputView, InputView inputView) {
        this.outputView = outputView;
        this.inputView = inputView;
    }

    public void run() {
        outputView.printStartGameMessage();
        outputView.printCommandGuideMessage();

        validateFirstCommand();

        ChessBoard chessBoard = new ChessBoard(new ChessSpaceGenerator());
        outputView.printChessBoard(chessBoard);

        playChess(chessBoard);
    }

    private void validateFirstCommand() {
        if (!inputView.getCommand().equals(START_COMMAND)) {
            throw new IllegalArgumentException("잘못된 입력입니다.");
        }
    }

    private void playChess(ChessBoard chessBoard) {
        while (true) {
            List<String> fromTo = inputView.getMoveCommand();
            String command = fromTo.get(0);
            if (command.equals(END_COMMAND)) {
                break;
            }

            if (command.equals(MOVE_COMMAND)) {
                move(chessBoard, fromTo.get(1), fromTo.get(2));
            }
        }
    }

    private void move(ChessBoard chessBoard, String from, String to) {
        chessBoard.move(
                Position.fromCoordinate(Coordinate.of(from)),
                Position.fromCoordinate(Coordinate.of(to)));
        outputView.printChessBoard(chessBoard);
    }
}
