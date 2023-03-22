package chess.command;

import chess.action.Action;
import chess.domain.Status;

public interface Command {
    
    String COMMAND_ERROR_PREFIX = "[COMMAND ERROR] ";
    String INVALID_QUERY_ERROR_MESSAGE = " 명령어는 query를 지원하지 않습니다.";
    String INVALID_ARGUMENT_ERROR_MESSAGE = " 명령어는 인자를 입력할 수 없습니다.";
    String INVALID_EXECUTE_ERROR_MESSAGE = " 명령어는 execute를 지원하지 않습니다.";
    String INVALID_ARGUMENT_COUNT_ERROR_MESSAGE = " 명령어는 인자를 2개만 가질 수 있습니다.";
    
    void execute(Action action);
    
    Status query(Action action);
    
    boolean isNotEnd();
    
    boolean isStatus();
}
