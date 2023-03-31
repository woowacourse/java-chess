package chess.domain.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import chess.dao.ChessGameDao;
import chess.dao.JdbcChessGameDao;
import chess.dao.JdbcPieceDao;
import chess.dao.PieceDao;
import chess.dao.entity.ChessGameEntity;
import chess.dao.entity.PieceEntity;
import chess.domain.ChessGame;
import chess.domain.board.Board;
import chess.domain.board.Score;
import chess.controller.command.Turn;
import chess.domain.piece.*;
import chess.domain.position.Position;

public class ChessGameService {

    private static final Map<Class<? extends Piece>, String> typeByPiece = new HashMap<>();

    static {
        typeByPiece.put(King.class, "KING");
        typeByPiece.put(Queen.class, "QUEEN");
        typeByPiece.put(Bishop.class, "BISHOP");
        typeByPiece.put(Knight.class, "KNIGHT");
        typeByPiece.put(Rook.class, "ROOK");
        typeByPiece.put(Pawn.class, "PAWN");
    }

    private final ChessGame chessGame;
    private final ChessGameDao chessGameDao;
    private final PieceDao pieceDao;

    public static ChessGameService createInitChessGameService() {
        ChessGameDao chessGameDao = JdbcChessGameDao.getInstance();
        long recentGameId = chessGameDao.findRecentGameId();
        ChessGame chessGame = new ChessGame(++recentGameId, new Board(new Pieces()), Turn.WHITE);
        return new ChessGameService(chessGame);
    }

    public ChessGameService(final ChessGame chessGame) {
        this.chessGame = chessGame;
        this.chessGameDao = JdbcChessGameDao.getInstance();
        this.pieceDao = JdbcPieceDao.getInstance();
    }

    public void saveNewChessGame() {
        Long gameId = chessGameDao.saveNewChessGame();
        for (Piece piece : chessGame.getPieces()) {
            pieceDao.savePiece(piece, gameId);
        }
    }

    public ChessGame movePiece(Position sourcePosition, Position targetPosition) {
        checkTurn(sourcePosition);
        chessGame.checkPieceMoveCondition(sourcePosition, targetPosition);
        piecePositionUpdateWhenOnlyMove(sourcePosition, targetPosition);
        piecePositionUpdateWhenTakePiece(sourcePosition, targetPosition);
        chessGame.movePiece(sourcePosition, targetPosition);
        return getMovedChessGame();
    }

    private ChessGame getMovedChessGame() {
        Board currentBoard = new Board(new Pieces(chessGame.getPieces()));
        ChessGame movedChessGame = new ChessGame(chessGame.getId(), currentBoard, chessGame.turnChange());
        chessGameDao.updateTurn(movedChessGame);
        return movedChessGame;
    }

    private void checkTurn(Position sorucePosition) {
        Piece sourcePiece = chessGame.findPieceByPosition(sorucePosition);
        if (!chessGame.isCorrectTurn(sourcePiece.getSide())) {
            throw new IllegalArgumentException("[ERROR] 현재 턴인 진영의 기물만 이동할 수 있습니다.");
        }
    }

    public boolean isTargetPieceOppositeKing(Position sourcePosition, Position targetPosition) {
        return chessGame.isTargetPieceOppositeKing(sourcePosition, targetPosition);
    }

    private void piecePositionUpdateWhenOnlyMove(Position sourcePosition, Position targetPosition) {
        if (chessGame.isOnlyMove(targetPosition)) {
            Long gameId = chessGame.getId();
            Piece sourcePiece = chessGame.findPieceByPosition(sourcePosition);
            Piece movedPiece = sourcePiece.move(targetPosition);
            pieceDao.deletePieceByGameId(sourcePiece, gameId);
            pieceDao.savePiece(movedPiece, gameId);
        }
    }

    private void piecePositionUpdateWhenTakePiece(Position sourcePosition, Position targetPosition) {
        if (chessGame.isTakePieceMove(targetPosition)) {
            Long gameId = chessGame.getId();
            Piece sourcePiece = chessGame.findPieceByPosition(sourcePosition);
            Piece targetPiece = chessGame.findPieceByPosition(targetPosition);
            Piece movedPiece = sourcePiece.move(targetPosition);
            pieceDao.deletePieceByGameId(sourcePiece, gameId);
            pieceDao.deletePieceByGameId(targetPiece, gameId);
            pieceDao.savePiece(movedPiece, gameId);
        }
    }

    public boolean isExistPreviousChessGame(Long previousChessGameId) {
        return chessGameDao.isExistPreviousChessGame(previousChessGameId);
    }

    public ChessGame findChessGameByGameId(Long gameId) {
        ChessGameEntity chessGameEntity = chessGameDao.findChessGameByGameId(gameId);
        List<PieceEntity> pieceEntities = pieceDao.findAllPieceByGameId(gameId);
        List<Piece> pieces = new ArrayList<>();
        for (PieceEntity pieceEntity : pieceEntities) {
            pieces.add(pieceEntity.generatePiece());
        }
        Board findChessGameBoard = new Board(new Pieces(pieces));
        Turn findChessGameTurn = Turn.from(chessGameEntity.getTurn());
        return new ChessGame(chessGameEntity.getId(), findChessGameBoard, findChessGameTurn);
    }

    public Score getTotalScoreBySide(Side side) {
        return chessGame.getTotalScoreBySide(side);
    }

    public List<Piece> getPieces() {
        return chessGame.getPieces();
    }

    public String getTurnDisplayName() {
        return chessGame.getTurnDisplayName();
    }
}
