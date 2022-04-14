package chess.service;

import chess.dao.ChessPieceDao;
import chess.dao.ChessGameDao;
import chess.domain.ChessBoard;
import chess.domain.ChessBoardPosition;
import chess.domain.Team;
import chess.domain.piece.ChessPiece;
import chess.domain.state.BlackTurn;
import chess.domain.state.Finish;
import chess.domain.state.GameState;
import chess.domain.state.Ready;
import chess.domain.state.WhiteTurn;
import chess.dto.ChessStatusDto;
import chess.dto.web.ChessBoardDto;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class ChessService {
    private static final String CHESSPIECE_CLASS_PATH = "chess.domain.piece.";
    private static final String CHESSGAME_STATE_PATH = "chess.domain.state.";
    private static final String CHESS_BOARD_KEY = "chessboard";
    private static final String FINISH_MESSAGE_KEY = "finishMessage";
    private static final String STATUS_KEY = "status";
    private static final String NO_CHESS_PIECE_TYPE_EXCEPTION = "[ERROR] DB에 저장된 체스피스를 찾을 수 없습니다.";
    private static final String NO_STATE_TYPE_EXCEPTION = "[ERROR] DB에 저장된 게임 상태를 찾을 수 없습니다.";

    private final ChessPieceDao chessPieceDao;
    private final ChessGameDao chessGameDao;

    public ChessService() {
        this.chessPieceDao = new ChessPieceDao();
        this.chessGameDao = new ChessGameDao();
    }

    public GameState findChessGame() {
        try {
            String state = chessGameDao.findStateName();
            Map<String, List<String>> board = chessPieceDao.findAll();
            ChessBoard chessBoard = toChessBoard(board);
            return toGameState(state, chessBoard);
        } catch (IllegalArgumentException e) {
            return new Ready();
        }
    }

    private ChessBoard toChessBoard(Map<String, List<String>> board) {
        Map<ChessBoardPosition, ChessPiece> chessboard = new HashMap<>();
        for (Entry<String, List<String>> boardBlock : board.entrySet()) {
            ChessBoardPosition chessBoardPosition = ChessBoardPosition.from(boardBlock.getKey());
            ChessPiece chessPiece = toChessPiece(boardBlock.getValue().get(0), boardBlock.getValue().get(1));
            chessboard.put(chessBoardPosition, chessPiece);
        }
        return new ChessBoard(chessboard);
    }

    private ChessPiece toChessPiece(String chessPieceName, String teamName) {
        try {
            Class<?> chessPiece = Class.forName(CHESSPIECE_CLASS_PATH + chessPieceName);
            Team team = Team.valueOf(teamName);
            Constructor<?> constructor = chessPiece.getConstructor(Team.class);
            return (ChessPiece) constructor.newInstance(team);
        } catch (ClassNotFoundException | InvocationTargetException | NoSuchMethodException | InstantiationException | IllegalAccessException e) {
            throw new IllegalArgumentException(NO_CHESS_PIECE_TYPE_EXCEPTION);
        }
    }

    private GameState toGameState(String state, ChessBoard chessBoard) {
        try {
            Class<?> gameState = Class.forName(CHESSGAME_STATE_PATH + state);
            if (gameState.isInstance(BlackTurn.class) || gameState.isInstance(WhiteTurn.class) || gameState.isInstance(
                    Finish.class)) {
                Constructor<?> constructor = gameState.getConstructor();
                return (GameState) constructor.newInstance();
            }
            Constructor<?> constructor = gameState.getConstructor(ChessBoard.class);
            return (GameState) constructor.newInstance(chessBoard);
        } catch (ClassNotFoundException | InvocationTargetException | NoSuchMethodException | InstantiationException | IllegalAccessException e) {
            throw new IllegalArgumentException(NO_STATE_TYPE_EXCEPTION);
        }
    }

    public ChessBoardDto createChessGame() {
        GameState gameState = new WhiteTurn(ChessBoard.create());
        chessGameDao.save(gameState);
        int chessGameId = chessGameDao.findId();
        chessPieceDao.saveAll(chessGameId, gameState.getChessBoard().getBoard());
        return ChessBoardDto.of(gameState.getChessBoard());
    }

    public ChessBoardDto deleteChessGame() {
        chessPieceDao.deleteAll();
        chessGameDao.delete();
        return ChessBoardDto.empty();
    }

    public Map<String, Object> updateChessBoard(ChessBoardPosition sourcePosition, ChessBoardPosition targetPosition) {
        GameState gameState = findChessGame();
        gameState.move(sourcePosition, targetPosition);

        chessPieceDao.deleteByPosition(targetPosition.getRow(), targetPosition.getColumn());
        chessPieceDao.update(sourcePosition.getRow(), sourcePosition.getColumn(),
                targetPosition.getRow(), targetPosition.getColumn());
        updateChessGame(gameState);

        Map<String, Object> result = new HashMap<>();
        result.put(CHESS_BOARD_KEY, ChessBoardDto.of(gameState.getChessBoard()));
        result.putAll(getFinishStatus(gameState));
        return result;
    }

    private void updateChessGame(GameState gameState) {
        chessGameDao.update(gameState);
    }

    public ChessBoardDto getChessBoard() {
        GameState chessGame = findChessGame();
        return ChessBoardDto.of(chessGame.getChessBoard());
    }

    private Map<String, Object> getFinishStatus(GameState gameState) {
        if (!gameState.isFinished()) {
            return new HashMap<>();
        }
        Map<String, Object> finishStatus = new HashMap<>();
        finishStatus.put(STATUS_KEY, getStatus());
        finishStatus.put(FINISH_MESSAGE_KEY, true);
        return finishStatus;
    }

    public Map<String, Object> getStatus() {
        GameState gameState = findChessGame();
        ChessBoard chessBoard = gameState.getChessBoard();
        Team winner = gameState.findWinner();

        Map<String, Object> result = new HashMap<>();
        result.put(CHESS_BOARD_KEY, ChessBoardDto.of(gameState.getChessBoard()));
        result.put(STATUS_KEY, ChessStatusDto.of(chessBoard, winner));
        return result;
    }
}
