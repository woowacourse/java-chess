package chess.web.service;

import chess.console.ChessGame;
import chess.web.commandweb.WebGameCommand;
import chess.web.dao.board.BoardDao;
import chess.web.dao.camp.CampDao;
import chess.web.dao.member.MemberDaoImpl;
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

    public Map<String, Object> executeCommand(final Request req) {
        final String command = req.queryParams("command");
        System.err.println("front에서 받은 command = " + command);
        final WebGameCommand webgameCommand = WebGameCommand.from(command);
        return webgameCommand.execute(command, chessGame, getModelToState());
    }

    private Supplier getModelToState() {
        final HashMap<String, Object> model = new HashMap<>();
        return () -> {
            if (chessGame.isRunning()) {
//                OutputView.printBoard(getBoard());
                System.err.println("게임이 진행중입니다. 현재 데이터를 받아와요");
                model.put("board", chessGame.getBoard());
                model.put("camp", chessGame.getCamp());
            }
            return model;
//            if (isStatusInRunning()) {
//                OutputView.printStatus(calculateStatus());
//            }
//            if (isEndInRunning()) {
//                OutputView.printFinalStatus(calculateStatus());
//            }
//            if (isEndInGameOff()) {
//                OutputView.printEndMessage();
//            }
        };
    }

    private boolean isStatusInRunning() {
        if (isEndInGameOff()) {
            return false;
        }
        return chessGame.isStatusInRunning();
    }

    private boolean isEndInRunning() {
        if (isEndInGameOff()) {
            return false;
        }
        return chessGame.isEndInRunning();
    }

    private boolean isEndInGameOff() {
        return chessGame.isEndInGameOff();
    }
}
