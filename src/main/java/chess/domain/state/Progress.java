package chess.domain.state;

import static chess.utils.Constant.END_COMMAND;
import static chess.utils.Constant.MOVE_COMMAND;
import static chess.utils.Constant.START_COMMAND;

import chess.domain.board.ChessBoard;
import chess.domain.piece.Color;
import chess.domain.position.Position;
import chess.domain.vo.Score;
import java.util.List;

public class Progress implements GameState {
    private static final int COMMAND_FUNCTION_INDEX = 0;
    private static final int MOVE_COMMAND_SOURCE_INDEX = 1;
    private static final int MOVE_COMMAND_TARGET_INDEX = 2;

    private final ChessBoard chessBoard;

    public Progress(ChessBoard chessBoard) {
        this.chessBoard = chessBoard;
    }

    @Override
    public GameState play(List<String> inputCommand) {
        String command = inputCommand.get(COMMAND_FUNCTION_INDEX);
        if (START_COMMAND.equals(command)) {
            throw new UnsupportedOperationException("이미 시작한 게임은 다시 시작할 수 없습니다.");
        }
        if (MOVE_COMMAND.equals(command)) {
            return moveToTarget(inputCommand);
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
    public Score calculateScore(Color color) {
        return chessBoard.calculateScore(color);
    }

    @Override
    public Color getWinnerColor() {
        Score blackScore = chessBoard.calculateScore(Color.BLACK);
        Score whiteScore = chessBoard.calculateScore(Color.WHITE);
        if (blackScore.isGreaterThan(whiteScore)) {
            return Color.BLACK;
        }
        if (whiteScore.isGreaterThan(blackScore)) {
            return Color.WHITE;
        }
        return Color.NONE;
    }

    private GameState moveToTarget(List<String> inputCommand) {
        Position source = Position.from(inputCommand.get(MOVE_COMMAND_SOURCE_INDEX));
        Position target = Position.from(inputCommand.get(MOVE_COMMAND_TARGET_INDEX));

        chessBoard.move(source, target);
        if (chessBoard.isKingCaptured()) {
            return new End(chessBoard);
        }
        return new Progress(chessBoard);
    }
}
