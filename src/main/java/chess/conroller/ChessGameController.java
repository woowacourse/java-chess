package chess.conroller;

import chess.conroller.dto.PieceMoveRequestDto;
import chess.conroller.dto.SessionCreationRequestDto;
import chess.domain.CoordinatePair;
import chess.domain.GameResult;
import chess.persistence.dto.GameSessionDto;
import chess.service.GameService;
import chess.service.GameServiceImpl;
import chess.service.SessionService;
import chess.service.SessionServiceImpl;
import chess.service.dto.CoordinatePairDto;
import com.google.gson.Gson;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChessGameController {
    private static final SessionService SESSION_SERVICE;
    private static final GameService GAME_SERVICE;

    static {
        SESSION_SERVICE = new SessionServiceImpl();
        GAME_SERVICE = new GameServiceImpl();
    }

    /**
     * 방 생성
     *
     * @param req JSON 요청 예시:
     *            {
     *            "title": "my new game room"
     *            }
     * @param res JSON 응답 예시:
     *            {
     *            "result": "ok",
     *            "message": "",
     *            "session": {
     *            "id": 47,
     *            "title": "아무나 오세요!!!"
     *            }
     *            }
     * @return
     */
    public static Map<String, Object> createSession(Request req, Response res) {
        Map<String, Object> resMap;
        try {
            SessionCreationRequestDto body = new Gson().fromJson(req.body(), SessionCreationRequestDto.class);
            GameSessionDto session = new GameSessionDto();
            session.setTitle(body.getTitle());
            session = SESSION_SERVICE.createSession(session);
            resMap = ResultState.OK.createResMap("");
            resMap.put("session", session);
        } catch (IllegalArgumentException e) {
            resMap = ResultState.FAIL.createResMap(e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            resMap = ResultState.ERROR.createResMap(e.getMessage());
            e.printStackTrace();
        }
        return resMap;
    }

    /**
     * @param req 요청 쿼리 문자열:
     *            limit: 결과로 받을 항목의 수, 기본값: 5
     * @param res 응답 JSON 예시:
     *            {
     *            "result": "ok",
     *            "sessions": [
     *            {
     *            "id": 47,
     *            "title": "아무나 오세요!!!"
     *            }, // ...
     *            ],
     *            "message": ""
     *            }
     * @return
     */
    public static Map<String, Object> retrieveSessions(Request req, Response res) {
        Map<String, Object> resMap;
        try {
            resMap = ResultState.OK.createResMap("");
            resMap.put("sessions", SESSION_SERVICE.findLatestSessions(getOrDefaultLimit(req.queryParams("limit"))));
        } catch (NumberFormatException e) {
            resMap = ResultState.FAIL.createResMap("숫자로 변환할 수 없는 인자가 있습니다.");
        } catch (IllegalArgumentException e) {
            resMap = ResultState.FAIL.createResMap(e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            resMap = ResultState.ERROR.createResMap(e.getMessage());
            e.printStackTrace();
        }
        return resMap;
    }

    private static int getOrDefaultLimit(String limit) {
        if (limit == null || limit.isEmpty()) {
            return 5;
        }
        return Integer.valueOf(limit);
    }

    public static Map<String, Object> retrieveSessionById(Request req, Response res) {
        Map<String, Object> resMap;
        try {
            long sessId = Long.valueOf(req.params("id"));
            resMap = ResultState.OK.createResMap("");
            resMap.put("session", SESSION_SERVICE.findSessionById(sessId));
            resMap.put("states", GAME_SERVICE.findBoardStatesBySessionId(sessId));
        } catch (NumberFormatException e) {
            resMap = ResultState.FAIL.createResMap("숫자로 변환할 수 없는 인자가 있습니다.");
        } catch (IllegalArgumentException e) {
            resMap = ResultState.FAIL.createResMap(e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            resMap = ResultState.ERROR.createResMap(e.getMessage());
            e.printStackTrace();
        }
        return resMap;
    }

    /**
     * 현재 상태에서 말이 이동 가능한 좌표를 조회
     *
     * @param req 요청 쿼리 문자열:
     *            sessionId: 게임 세션 ID
     *            from: 현재 위치
     * @param res
     * @return
     */
    public static Map<String, Object> movableCoordinates(Request req, Response res) {
        Map<String, Object> resMap;
        try {
            List<CoordinatePairDto> coords = GAME_SERVICE.findMovableCoordinates(Long.valueOf(req.queryParams("sessionId")),
                CoordinatePair.from(req.queryParams("from"))
                    .orElseThrow(() -> new IllegalArgumentException("올바르지 않은 좌표입니다: " + req.queryParams("from"))));
            resMap = ResultState.OK.createResMap("");
            resMap.put("movableCoordinates", coords);
        } catch(NumberFormatException e) {
            resMap = ResultState.FAIL.createResMap("숫자로 변환할 수 없는 인자가 있습니다.");
        } catch (IllegalArgumentException e) {
            resMap = ResultState.FAIL.createResMap(e.getMessage());
        } catch (Exception e) {
            resMap = ResultState.ERROR.createResMap(e.getMessage());
        }
        return resMap;
    }

    /**
     * 체스 말 이동을 요청
     *
     * @param req JSON 요청 예시:
     *            {
     *            "sessionId": 12,
     *            "from": "b2",
     *            "to": "b3"
     *            }
     * @param res JSON 응답 예시:
     *            {
     *            "result": "ok",
     *            "state": {
     *            "result": "KEEP",
     *            "board": {
     *            "a1": "ROOK_WHITE",
     *            // ...
     *            }
     *            }
     *            }
     * @return
     */
    public static Map<String, Object> movePiece(Request req, Response res) {
        Map<String, Object> resMap;
        try {
            PieceMoveRequestDto body = new Gson().fromJson(req.body(), PieceMoveRequestDto.class);
            CoordinatePair coordFrom = CoordinatePair.from(body.getFrom())
                .orElseThrow(() -> new IllegalArgumentException("잘못된 좌표입니다: " + body.getFrom()));
            CoordinatePair coordTo = CoordinatePair.from(body.getTo())
                .orElseThrow(() -> new IllegalArgumentException("잘못된 좌표입니다: " + body.getTo()));
            GameResult result = GAME_SERVICE.movePiece(coordFrom, coordTo, body.getSessionId());
            resMap = ResultState.OK.createResMap("");
            Map<String, Object> stateMap = new HashMap<>();
            stateMap.put("result", result.name());
            stateMap.put("board", GAME_SERVICE.findBoardStatesBySessionId(body.getSessionId()));
            resMap.put("state", stateMap);
        } catch (IllegalArgumentException e) {
            resMap = ResultState.FAIL.createResMap(e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            resMap = ResultState.ERROR.createResMap(e.getMessage());
            e.printStackTrace();
        }
        return resMap;
    }

}
