package chess.service;

import chess.db.ChessGameDao;
import chess.db.PieceDao;
import chess.domain.ChessGame;
import chess.domain.GameResult;
import chess.domain.GameTurn;
import chess.domain.board.Board;
import chess.domain.board.InitialBoardGenerator;
import chess.domain.board.SavedBoardGenerator;
import chess.domain.position.Square;

public class DBService {
    private final ChessGameDao chessGameDao;
    private final PieceDao pieceDao;

    public DBService() {
        this.chessGameDao = new ChessGameDao();
        this.pieceDao = new PieceDao();
    }

    public ChessGame loadGame(String gameID) {
        ChessGame chessGame;
        try {
            GameTurn gameTurn = getTurn(gameID);
            checkCanContinue(gameTurn);
            chessGame = loadSavedChessGame(gameID, gameTurn);
        } catch (IllegalArgumentException e) {
            chessGame = loadNewChessGame();
        }
        chessGame.startGame();
        return chessGame;
    }

    public GameTurn getTurn(String gameID) {
        return GameTurn.find(chessGameDao.findTurnByID(gameID));
    }

    private void checkCanContinue(GameTurn gameTurn) {
        if (GameTurn.FINISHED.equals(gameTurn)) {
            throw new IllegalArgumentException();
        }
    }

    public ChessGame loadSavedChessGame(String gameID, GameTurn gameTurn) {
        return new ChessGame(new SavedBoardGenerator(pieceDao.findByGameID(gameID)), gameTurn);
    }

    private ChessGame loadNewChessGame() {
        return new ChessGame(new InitialBoardGenerator(), GameTurn.READY);
    }

    public void startGame(String gameID, ChessGame chessGame) {
        chessGameDao.save(gameID, chessGame);
        updateTurn(gameID, chessGame);
    }

    public void updateTurn(String gameID, ChessGame chessGame) {
        chessGameDao.updateTurn(gameID, chessGame);
    }

    public void loadPieces(String gameID) {
        pieceDao.deleteAll(gameID);
        pieceDao.save(gameID);
    }

    public GameResult getGameResult(String gameID) {
        Board board = new Board(new SavedBoardGenerator(pieceDao.findByGameID(gameID)));
        return new GameResult(board);
    }

    public void movePiece(String gameID, String source, String target) {
        pieceDao.deleteByPosition(new Square(target));
        pieceDao.updatePosition(new Square(source), new Square(target));
        pieceDao.insertNone(gameID, new Square(source));
    }
}
