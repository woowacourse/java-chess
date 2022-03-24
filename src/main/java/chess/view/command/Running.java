package chess.view.command;

import chess.domain.ChessBoard;
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

    public Running(ChessBoard chessBoard) {
        this.chessBoard = chessBoard;
    }

    @Override
    public Command run(String command) {
        if (command.equals("end")) {
            return new End();
        }
        Matcher matcher = MOVE_COMMAND_PATTERN.matcher(command);
        if (matcher.find()) {
            movePieceByCommand(command);
            OutputView.printChessBoard(chessBoard.getPieces());
            return this;
        }
        throw new IllegalArgumentException("게임 진행상태에서 불가능한 명령어입니다.");
   }

    private void movePieceByCommand(final String command) {
        List<String> values = Arrays.asList(command.split(" "));
        Position start = position(values.get(START_POSITION_INDEX));
        Position target = position(values.get(TARGET_POSITION_INDEX));
        chessBoard.movePiece(start, target);
    }

    private Position position(String command) {
        return new Position(command.charAt(0), command.charAt(1));
   }

    @Override
    public boolean isEnd() {
        return false;
    }
}
