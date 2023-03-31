package chess.view;

import static chess.model.exception.ChessExceptionType.CANT_MOVE_FROM_TO;
import static chess.model.exception.ChessExceptionType.CANT_MOVE_TO_SAME_COLOR;
import static chess.model.exception.ChessExceptionType.COMMAND_NOT_FOUND;
import static chess.model.exception.ChessExceptionType.DATABASE_CAN_NOT_CONNECT;
import static chess.model.exception.ChessExceptionType.EMPTY_CANT_EXECUTE;
import static chess.model.exception.ChessExceptionType.END_CANT_EXECUTE;
import static chess.model.exception.ChessExceptionType.FILE_NOT_FOUND;
import static chess.model.exception.ChessExceptionType.FROM_IS_EMPTY;
import static chess.model.exception.ChessExceptionType.GAME_ID_ILLEGAL_TYPE;
import static chess.model.exception.ChessExceptionType.IS_NOT_PIECE_TURN;
import static chess.model.exception.ChessExceptionType.MOVEMENT_NOT_FOUND;
import static chess.model.exception.ChessExceptionType.NOT_STARTED_CANT_EXECUTE;
import static chess.model.exception.ChessExceptionType.OBSTACLE_EXIST;
import static chess.model.exception.ChessExceptionType.QUERY_FAIL;
import static chess.model.exception.ChessExceptionType.RANK_NOT_FOUND;
import static chess.model.exception.ChessExceptionType.REQUEST_NOT_CONTAIN_ID;
import static chess.model.exception.ChessExceptionType.REQUEST_NOT_CONTAIN_POSITION;
import static chess.model.exception.ChessExceptionType.STARTED_CANT_EXECUTE;

import chess.model.exception.ChessException;
import chess.model.exception.ChessExceptionType;
import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;

public class ExceptionViewMapper {

    private static final Map<ChessExceptionType, String> ERROR_MESSAGE_MAPPER;

    static {
        final Map<ChessExceptionType, String> errorMessageMapper = new EnumMap<>(ChessExceptionType.class);
        errorMessageMapper.put(COMMAND_NOT_FOUND, "일치하는 커맨드를 찾을 수 없습니다.");
        errorMessageMapper.put(FILE_NOT_FOUND, "일치하는 file을 찾을 수 없습니다.");
        errorMessageMapper.put(RANK_NOT_FOUND, "일치하는 rank를 찾을 수 없습니다.");
        errorMessageMapper.put(MOVEMENT_NOT_FOUND, "일치하는 movement를 찾을 수 없습니다.");
        errorMessageMapper.put(GAME_ID_ILLEGAL_TYPE, "게임 id가 long타입이 아닙니다.");
        errorMessageMapper.put(REQUEST_NOT_CONTAIN_ID, "입력에 ID가 누락되어있습니다.");
        errorMessageMapper.put(REQUEST_NOT_CONTAIN_POSITION, "입력에 Position이 누락되어있습니다.");
        errorMessageMapper.put(DATABASE_CAN_NOT_CONNECT, "데이터베이스 연결이 되지 않습니다.");
        errorMessageMapper.put(QUERY_FAIL, "쿼리문 실행에 실패하였습니다.");
        errorMessageMapper.put(END_CANT_EXECUTE, "종료 상태에서는 해당 기능을 실행할 수 없습니다.");
        errorMessageMapper.put(STARTED_CANT_EXECUTE, "시작된 상태에서는 해당 기능을 실행할 수 없습니다.");
        errorMessageMapper.put(NOT_STARTED_CANT_EXECUTE, "시작되지 않은 상태에서는 해당 기능을 실행할 수 없습니다.");
        errorMessageMapper.put(FROM_IS_EMPTY, "출발지에 말이 존재하지 않습니다.");
        errorMessageMapper.put(IS_NOT_PIECE_TURN, "해당 Piece의 턴이 아닙니다.");
        errorMessageMapper.put(CANT_MOVE_FROM_TO, "Piece가 해당 좌표로 이동할 수 없습니다.");
        errorMessageMapper.put(CANT_MOVE_TO_SAME_COLOR, "같은 색 말의 위치로 이동할 수 없습니다.");
        errorMessageMapper.put(OBSTACLE_EXIST, "이동 경로에 다른 말이 있습니다.");
        errorMessageMapper.put(EMPTY_CANT_EXECUTE, "빈 Piece는 기능을 실행할 수 없습니다.");
        ERROR_MESSAGE_MAPPER = Collections.unmodifiableMap(errorMessageMapper);
    }

    public static String convertErrorMessage(final ChessException exception) {
        return ERROR_MESSAGE_MAPPER.get(exception.getType());
    }
}
