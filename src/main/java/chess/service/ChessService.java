package chess.service;

import chess.dao.ChessDao;
import chess.domain.ChessBoard;
import chess.domain.ChessBoardPosition;
import chess.domain.Team;
import chess.domain.piece.ChessPiece;
import chess.domain.state.BlackTurn;
import chess.domain.state.Finish;
import chess.domain.state.GameState;
import chess.domain.state.Ready;
import chess.domain.state.WhiteTurn;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class ChessService {
    private static final String CHESSPIECE_CLASS_PATH = "chess.domain.piece.";
    private static final String CHESSGAME_STATE_PATH = "chess.domain.state.";
    private static final String NO_CHESS_PIECE_TYPE_EXCEPTION = "[ERROR] DB에 저장된 체스피스를 찾을 수 없습니다.";
    private static final String NO_STATE_TYPE_EXCEPTION = "[ERROR] DB에 저장된 게임 상태를 찾을 수 없습니다.";
    private final ChessDao chessDao;

    public ChessService() {
        this.chessDao = new ChessDao();
    }

    public GameState findChessGame() {
        try {
            String state = chessDao.findState();
            Map<String, List<String>> board = chessDao.getChessBoard();
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

    public void createChessGame(GameState gameState) {
        chessDao.saveGameState(gameState);
        chessDao.saveChessBoard(gameState.getChessBoard());
    }

    public void deleteChessGame() {
        chessDao.deleteChessBoard();
        chessDao.deleteGameState();
    }

    public void updateChessBoard(ChessBoardPosition sourcePosition, ChessBoardPosition targetPosition) {
        chessDao.deleteChessPieceByPosition(targetPosition.getRow(), targetPosition.getColumn());
        chessDao.updateChessBoard(sourcePosition.getRow(), sourcePosition.getColumn(),
                targetPosition.getRow(), targetPosition.getColumn());
    }

    public void updateChessGame(GameState gameState) {
        chessDao.updateGameState(gameState);
    }
}
