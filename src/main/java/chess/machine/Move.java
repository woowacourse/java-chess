package chess.machine;

import chess.domain.chessBoard.ChessGame;
import chess.domain.position.Coordinate;
import chess.domain.position.Position;
import chess.view.OutputView;
import java.util.Arrays;
import java.util.List;

public class Move implements Command {

    private static final String INPUT_COMMAND = "move";
    private static final int REQUIRED_INPUT_SIZE = 3;
    private static final int SECOND = 1;
    private static final int THIRD = 2;

    private final String from;
    private final String to;

    private Move(String from, String to) {
        this.from = from;
        this.to = to;
    }

    public static Move of(String input) {
        validateInputCommand(input);
        List<String> splitInput = Arrays.stream(input.split(" ")).toList();
        validateInputSize(splitInput);
        return new Move(splitInput.get(SECOND), splitInput.get(THIRD));
    }

    private static void validateInputCommand(String input) {
        if (!input.startsWith(INPUT_COMMAND)) {
            throw new IllegalArgumentException("이동 명령이 아닙니다");
        }
    }

    private static void validateInputSize(List<String> splitInput) {
        if (splitInput.size() != REQUIRED_INPUT_SIZE) {
            throw new IllegalArgumentException("잘못된 입력 명령입니다");
        }
    }

    @Override
    public void conductCommand(ChessGame chessGame, OutputView outputView) {
        chessGame.move(
                Position.fromCoordinate(Coordinate.of(from)),
                Position.fromCoordinate(Coordinate.of(to)));
        outputView.printChessBoard(chessGame);
    }
}
