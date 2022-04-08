package chess.web.service;

import chess.console.ChessGame;
import chess.web.commandweb.WebGameCommand;
import chess.web.dao.board.BoardDao;
import chess.web.dao.camp.CampDao;
import chess.web.dao.member.MemberDaoImpl;
import chess.web.dto.BoardDto;
import chess.web.dto.MoveReqDto;
import com.google.gson.Gson;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;
import spark.Request;

public class ChessService {

    private final BoardDao boardDao;
    private final CampDao campDao;
    private final MemberDaoImpl memberDao;
    private ChessGame chessGame;

    public ChessService(final BoardDao boardDao, final CampDao campDao, final MemberDaoImpl memberDao) {
        this.boardDao = boardDao;
        this.campDao = campDao;
        this.memberDao = memberDao;
    }

    public void init() {
        chessGame = new ChessGame();
    }

    public void restart() {
        chessGame.run();
    }

    public Map<String, Object> executeCommand(final Request req) {
        String command = extractCommandFrom(req);
        final WebGameCommand webgameCommand = WebGameCommand.from(command);
        return webgameCommand.execute(command, chessGame, getModelToState());
    }

    private String extractCommandFrom(final Request req) {
        String command = req.queryParams("command");
        if (command == null) {
            final Gson gson = new Gson();
            final MoveReqDto moveReqDto = gson.fromJson(req.body(), MoveReqDto.class);
            command = moveReqDto.getCommand();
        }
        return command;
    }

    public Supplier<Map<String, Object>> getModelToState() {
        final HashMap<String, Object> model = new HashMap<>();
        return () -> {
            if (chessGame.isRunning()) {
                setCurrentStateToModelMessage(model);
                model.put("board", BoardDto.from(chessGame.getBoard()).getBoard());
                model.put("isWhite", chessGame.getCamp().isWhite());

                // status요청이 아니라 항상 status를 같이 반환하도록 수정
//                OutputView.printStatus(calculateStatus());
                model.put("status", chessGame.calculateStatus());
                System.err.println("모델갓다주기 직전인데, game.html은 없데이트가 안되는 듯싶넹.");
                return model;
            }
//            if (isStatusInRunning()) {
//                OutputView.printStatus(calculateStatus());
//            }
            if (isEndInRunning()) {
                model.put("message", "현재 게임이 종료되었습니다.");
                model.put("isWhite", chessGame.getCamp().isWhite());
                model.put("status", chessGame.calculateStatus());
                model.put("isRunning", false);
                model.put("board", BoardDto.from(chessGame.getBoard()).getBoard());

                return model;
            }
//            if (isEndInGameOff()) {
//
//            }
            return model;
        };
    }

    private void setCurrentStateToModelMessage(final HashMap<String, Object> model) {
        // WebCommand에서 move 로직 실행 후 -> 체크, 체크메이트 등 별 message가 들어있지 않으면, 진행 중 메세지를 입력함.
        if (model.get("message") == null || model.get("message").equals("")) {
            model.put("message", "게임이 진행중 입니다.");
        }
        // WebCommand에서 move 로직 실행 후 -> 체크메이트 등 에 대한 표기를 안했을 때 <-> 종료 표기를 위한 게임 진행상태도 같이 전달
        if (model.get("isRunning") == null || model.get("isRunning").equals("")) {
            model.put("isRunning", true);
        }
    }

    private boolean isEndInRunning() {
        if (isEndInGameOff()) {
            return false;
        }
        return chessGame.isEndInRunning();
    }

    public boolean isEndInGameOff() {
        return chessGame.isEndInGameOff();
    }

    public boolean isNotExistGame() {
        return chessGame == null;
    }
}
