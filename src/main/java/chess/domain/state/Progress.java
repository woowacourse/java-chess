package chess.domain.state;

import static chess.utils.Constant.END_COMMAND;
import static chess.utils.Constant.MOVE_COMMAND;
import static chess.utils.Constant.START_COMMAND;

import chess.domain.board.ChessBoard;
import chess.domain.piece.Color;
import chess.domain.position.Position;
import java.util.List;

public class Progress implements GameState {
    private final ChessBoard chessBoard;

    public Progress(ChessBoard chessBoard) {
        this.chessBoard = chessBoard;
    }

    @Override
    public GameState play(List<String> inputCommand) {
        String command = inputCommand.get(0);
        if (START_COMMAND.equals(command)) {
            throw new UnsupportedOperationException("이미 시작한 게임은 다시 시작할 수 없습니다.");
        }
        if (MOVE_COMMAND.equals(command)) {
            Position source = Position.from(inputCommand.get(1));
            Position target = Position.from(inputCommand.get(2));

            chessBoard.move(source, target);
            if (chessBoard.isKingCaptured()) {
                return new End(chessBoard);
            }
            return new Progress(chessBoard);
        }
        if (END_COMMAND.equals(command)) {
            return new End(chessBoard);
        }
        throw new IllegalArgumentException("올바르지 않은 command입니다.");
    }

    @Override
    public boolean isEnd() {
        return false;
    }

    @Override
    public double calculateScore(Color color) {
        return chessBoard.calculateScore(color);
    }

    @Override
    public Color getWinnerColor() {
        double blackColor = chessBoard.calculateScore(Color.BLACK);
        double whiteColor = chessBoard.calculateScore(Color.WHITE);
        if (blackColor > whiteColor) {
            return Color.BLACK;
        }
        if (blackColor < whiteColor) {
            return Color.WHITE;
        }
        return Color.NONE;
    }
}
