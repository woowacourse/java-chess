package chess.console.game.playstate;

import chess.console.view.OutputView;
import chess.domain.ChessBoard;
import chess.domain.Color;
import chess.domain.Position;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Running implements PlayState {

    private static final Pattern MOVE_COMMAND_PATTERN = Pattern.compile("move [a-h][1-8] [a-h][1-8]");
    private static final String COMMAND_DELIMITER = " ";
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
    public PlayState run(String command) {
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

    private PlayState moveNextCommand() {
        if (chessBoard.isPromotionStatus(color)) {
            OutputView.printPromotionGuide();
            return new Promotion(chessBoard, color);
        }
        if (chessBoard.isFinished()) {
            return new End();
        }
        return new Running(chessBoard, color.reverse());
    }

    private void movePieceByCommand(final String command) {
        List<String> values = Arrays.asList(command.split(COMMAND_DELIMITER));
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
