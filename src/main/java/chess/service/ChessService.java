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
    private final ChessDao chessDao;

    public ChessService() {
        this.chessDao = new ChessDao();
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

    public GameState findState() {
        String state = chessDao.findChessGameState();
        if (state == null) {
            return new Ready();
        }
        Map<String, List<String>> board = chessDao.getChessBoard();
        ChessBoard chessBoard = toChessBoard(board);
        return toGameState(state, chessBoard);
    }

    private GameState toGameState(String state, ChessBoard chessBoard) {
        try {
            Class<?> gameState = Class.forName("chess.domain.state." + state);
            if (gameState.isInstance(BlackTurn.class) || gameState.isInstance(WhiteTurn.class) || gameState.isInstance(Finish.class)) {
                Constructor<?> constructor = gameState.getConstructor();
                return (GameState) constructor.newInstance();
            }
            Constructor<?> constructor = gameState.getConstructor(ChessBoard.class);
            return (GameState) constructor.newInstance(chessBoard);
        } catch (ClassNotFoundException | InvocationTargetException | NoSuchMethodException | InstantiationException | IllegalAccessException e) {
            throw new IllegalArgumentException("no chess piece type");
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
            System.out.println(chessPieceName);
            System.out.println(teamName);
            Class<?> chessPiece = Class.forName("chess.domain.piece." + chessPieceName);
            System.out.println(chessPiece.getName());
            Team team = Team.valueOf(teamName);
            Constructor<?> constructor = chessPiece.getConstructor(Team.class);
            return (ChessPiece) constructor.newInstance(team);
        } catch (ClassNotFoundException | InvocationTargetException | NoSuchMethodException | InstantiationException | IllegalAccessException e) {
            throw new IllegalArgumentException("no chess piece type");
        }
    }
}
