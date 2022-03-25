package chess.command;

import chess.domain.ChessBoard;
import chess.domain.Color;
import chess.domain.Position;
import chess.view.OutputView;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Running implements Command {

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
        return new Running(chessBoard, Color.WHITE);
    }

    @Override
    public Command run(String command) {
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
            return moveNextCommand();
        }
        throw new IllegalArgumentException("게임 진행상태에서 불가능한 명령어입니다.");
    }

    private Command moveNextCommand() {
        if (chessBoard.isPromotionStatus(color)) {
            OutputView.printPromotionGuide();
            return new PromotionStatus(chessBoard, color);
        }
        return new Running(chessBoard, color.reverse());
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

    public Color color() {
        return color;
    }

    @Override
    public boolean isEnd() {
        return false;
    }
}
