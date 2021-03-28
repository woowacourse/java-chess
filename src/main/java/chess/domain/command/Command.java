package chess.domain.command;

import chess.domain.ChessBoard;
import chess.domain.piece.info.Color;

public interface Command {
    String NOT_EXECUTE_ERROR = "[ERROR] 실행할 수 없는 상태입니다.";
    String COMMAND_ERROR = "[ERROR] 올바른 명령이 아닙니다.";

    Command move(ChessBoard chessBoard, Color turn);

    Command changeWaiting();

    Command changeRunningCommand(String input);
}
