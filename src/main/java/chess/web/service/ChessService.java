package chess.web.service;

import chess.console.ChessGame;
import chess.domain.Camp;
import chess.domain.board.Position;
import chess.domain.piece.NullPiece;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceProperty;
import chess.web.commandweb.WebGameCommand;
import chess.web.dao.board.BoardDaoImpl;
import chess.web.dao.room.RoomDaoImpl;
import chess.web.dto.BoardDto;
import chess.web.dto.MoveReqDto;
import com.google.gson.Gson;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import spark.Request;

public class ChessService {

    private final RoomDaoImpl roomDaoImpl;
    private final BoardDaoImpl boardDaoImpl;
    private ChessGame chessGame;

    public ChessService(final RoomDaoImpl roomDaoImpl, final BoardDaoImpl boardDaoImpl) {
        this.roomDaoImpl = roomDaoImpl;
        this.boardDaoImpl = boardDaoImpl;
    }

    public void init() {
        chessGame = new ChessGame();
    }

    public void restart() {
        //추가 재시작시, 스위치 꺼지면 올려야한다.
        if (chessGame.isEndInGameOff()) {
            chessGame.gameSwitchOn();
        }
        chessGame.run();
    }

    public Map<String, Object> executeCommand(final Request req) {
        String command = extractCommandFrom(req);
        final WebGameCommand webgameCommand = WebGameCommand.from(command);
        updateGame(req, webgameCommand);
        return webgameCommand.execute(command, chessGame, getModelToState());
    }

    private void updateGame(final Request req, final WebGameCommand webgameCommand) {
        if (webgameCommand == WebGameCommand.START) {
            final int roomId = Integer.parseInt(req.queryParams("roomId"));
            chessGame.changeBoard(convertToGameBoard(boardDaoImpl.findAll()),
                roomDaoImpl.findById(roomId).getCurrentCamp());
        }
    }

    private Map<Position, Piece> convertToGameBoard(final Map<String, String> board) {
        return board.entrySet()
            .stream()
            .collect(Collectors.toMap(
                e -> Position.from(e.getKey()),
                e -> findPieceBy(e.getValue()))
            );
    }

    private Piece findPieceBy(final String value) {
        if (value.equals("null")) {
            return new NullPiece(null);
        }
        final String[] splitPiece = value.split("_");
        final Camp camp = Camp.getCamp(splitPiece[0]);
        final String pieceStr = splitPiece[1];
        return PieceProperty.createPieceWith(pieceStr, camp);
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
            model.put("isRunning", false);
            model.put("isWhite", chessGame.getCamp().isWhite());
            model.put("status", chessGame.calculateStatus());
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
        return new HashMap(Map.of("rooms", roomDaoImpl.findAll()));
    }

    public void createRoomAndBoard(final String name) {
        roomDaoImpl.save(name);
        final int roomId = roomDaoImpl.findIdByName(name);
        final Map<String, String> board = BoardDto.from(chessGame.getBoard()).getBoard();
        boardDaoImpl.save(roomId, board);
    }

    public void updateRoomName(final String id, final String roomName) {
        roomDaoImpl.updateNameById(Integer.parseInt(id), roomName);
    }

    public void removeRoom(final String id) {
        roomDaoImpl.removeById(Integer.parseInt(id));
    }

    public void saveCurrentRoomAndBoard(final Request req) {
        final int roomId = Integer.parseInt(req.queryParams("roomId"));
        final int canJoin = Integer.parseInt(req.queryParams("canJoin"));
        String currentCamp = "BLACK";
        if (chessGame.getCamp().isWhite()) {
            currentCamp = "WHITE";
        }
        roomDaoImpl.updateRoom(roomId, canJoin, currentCamp);
        final Map<String, String> board = BoardDto.from(chessGame.getBoard()).getBoard();
        boardDaoImpl.updateBoard(roomId, board);
    }
}
