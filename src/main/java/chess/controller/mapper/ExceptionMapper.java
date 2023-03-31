package chess.controller.mapper;

import java.util.Map;
import java.util.Objects;

import chess.domain.exception.ChessGameException;
import chess.domain.exception.DifferentTeamException;
import chess.domain.exception.DirectionalException;
import chess.domain.exception.IllegalMoveException;
import chess.domain.exception.NotPlayableException;

public class ExceptionMapper {

    private static final Map<Class<? extends ChessGameException>, String> KOREAN_EXCEPTION_MAP = Map.ofEntries(
            Map.entry(DifferentTeamException.class, "다른 팀은 움직일 수 없습니다"),
            Map.entry(IllegalMoveException.class, "잘못된 움직임입니다"),
            Map.entry(DirectionalException.class, "잘못된 방향이 생성되었습니다"),
            Map.entry(NotPlayableException.class, "게임 진행중이 아닙니다")
    );

    public static String map(ChessGameException chessGameException) {
        String predefinedMessage = KOREAN_EXCEPTION_MAP.get(chessGameException.getClass());
        if (Objects.isNull(predefinedMessage)) {
            return "알 수 없는 오류입니다";
        }
        return predefinedMessage;
    }
}
