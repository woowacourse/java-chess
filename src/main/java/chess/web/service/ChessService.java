package chess.web.service;

import chess.console.ChessGame;
import chess.web.commandweb.WebGameCommand;
import chess.web.dao.board.BoardDao;
import chess.web.dao.room.RoomDao;
import chess.web.dto.BoardDto;
import chess.web.dto.MoveReqDto;
import com.google.gson.Gson;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;
import spark.Request;

public class ChessService {

    private final RoomDao roomDao;
    private final BoardDao boardDao;
    private ChessGame chessGame;

    public ChessService(final RoomDao roomDao, final BoardDao boardDao) {
        this.roomDao = roomDao;
        this.boardDao = boardDao;
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
            processWhenRunning(model);
            processWhenEnd(model);

            return model;
        };
    }

    private void processWhenEnd(final HashMap<String, Object> model) {
        if (isEndInRunning()) {
            model.put("message", "현재 게임이 종료되었습니다.");
            model.put("isWhite", chessGame.getCamp().isWhite());
            model.put("status", chessGame.calculateStatus());
            model.put("isRunning", false);
            model.put("board", BoardDto.from(chessGame.getBoard()).getBoard());
        }
    }

    private void processWhenRunning(final HashMap<String, Object> model) {
        if (chessGame.isRunning()) {
            setCurrentStateToModelMessage(model);
            model.put("isWhite", chessGame.getCamp().isWhite());
            model.put("status", chessGame.calculateStatus());
            model.put("board", BoardDto.from(chessGame.getBoard()).getBoard());
        }
    }

    private void setCurrentStateToModelMessage(final HashMap<String, Object> model) {
        if (model.get("message") == null || model.get("message").equals("")) {
            model.put("message", "게임이 진행중 입니다.");
        }
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

    public HashMap<String, Object> getRooms() {
        return new HashMap(Map.of("rooms", roomDao.findAll()));
    }

    public void createRoom(final String name) {
        roomDao.save(name);
    }

    public void updateRoomName(final String id, final String roomName) {
        roomDao.updateNameById(Integer.parseInt(id), roomName);
    }

    public void removeRoom(final String id) {
        roomDao.removeById(Integer.parseInt(id));
    }

    public void saveCurrentRoom(final Request req) {
        final int roomId = Integer.parseInt(req.queryParams("roomId"));
        final int canJoin = Integer.parseInt(req.queryParams("canJoin"));
        String currentCamp = "BLACK";
        if (chessGame.getCamp().isWhite()) {
            currentCamp = "WHITE";
        }
        roomDao.updateRoom(roomId, canJoin, currentCamp);
    }
}
