package chess.gamestate;

import chess.domain.ChessBoard;
import chess.domain.Color;
import chess.domain.Position;
import chess.view.OutputView;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Running implements GameState {

    private static final Pattern MOVE_COMMAND_PATTERN = Pattern.compile("move [a-h][1-8] [a-h][1-8]");
    private static final int START_POSITION_INDEX = 1;
    private static final int TARGET_POSITION_INDEX = 2;

    private final ChessBoard chessBoard;
    private final Color color;

    protected Running(ChessBoard chessBoard, Color color) {
        this.chessBoard = chessBoard;
        this.color = color;
    }

    public static Running createFirstTurnRunning(ChessBoard chessBoard) {
        return new WhiteRunning(chessBoard);
    }

    @Override
    public GameState run(String command) {
        if (command.equals("end")) {
            return new End();
        }
        if (command.equals("status")) {
            OutputView.printChessBoardStatus(chessBoard.calcualteScoreStatus());
            return this;
        }
        Matcher matcher = MOVE_COMMAND_PATTERN.matcher(command);
        if (matcher.find()) {
            movePieceByCommand(command);
            OutputView.printChessBoard(chessBoard.getPieces());
            return otherState(this.chessBoard);
        }
        throw new IllegalArgumentException("게임 진행상태에서 불가능한 명령어입니다.");
    }

    private void movePieceByCommand(final String command) {
        List<String> values = Arrays.asList(command.split(" "));
        Position source = position(values.get(START_POSITION_INDEX));
        Position target = position(values.get(TARGET_POSITION_INDEX));
        chessBoard.movePiece(source, target, color);
    }

    private Position position(String command) {
        return Position.of(command.charAt(0), command.charAt(1));
    }

    @Override
    public boolean isEnd() {
        return false;
    }

    abstract protected Running otherState(ChessBoard chessBoard);
}
