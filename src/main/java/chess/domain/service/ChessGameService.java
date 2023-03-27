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
import chess.domain.command.Turn;
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

    public ChessGameService() {
        this.chessGameDao = JdbcChessGameDao.getInstance();
        this.pieceDao = JdbcPieceDao.getInstance();
        Long recentGameId = chessGameDao.findRecentGameId();
        this.chessGame = new ChessGame(++recentGameId, new Board(new Pieces()), Turn.WHITE);
    }

    public ChessGameService(final ChessGame chessGame) {
        this.chessGame = chessGame;
        this.chessGameDao = JdbcChessGameDao.getInstance();
        this.pieceDao = JdbcPieceDao.getInstance();
    }

    public void saveNewChessGame() {
        Long gameId = chessGameDao.saveNewChessGame();
        for (Piece piece : chessGame.getPieces()) {
            Long recentPieceId = pieceDao.findRecentPieceId();
            PieceEntity pieceEntity = generatePieceEntity(gameId, piece, recentPieceId);
            pieceDao.savePiece(pieceEntity);
        }
    }

    private PieceEntity generatePieceEntity(Long gameId, Piece piece, Long recentPieceId) {
        return new PieceEntity.Builder()
                .id(++recentPieceId)
                .rank(String.valueOf(piece.getRank()))
                .file(String.valueOf(piece.getFile()))
                .type(typeByPiece.get(piece.getClass()))
                .side(piece.getSide().getDisplayName())
                .gameId(gameId)
                .build();
    }

    public ChessGame movePiece(Position sourcePosition, Position targetPosition) {
        checkTurn(sourcePosition);
        chessGame.checkPieceMoveCondition(sourcePosition, targetPosition);

        piecePositionUpdateWhenOnlyMove(sourcePosition, targetPosition);
        piecePositionUpdateWhenTakePiece(sourcePosition, targetPosition);
        chessGame.movePiece(sourcePosition, targetPosition);
        Board currentBoard = new Board(new Pieces(chessGame.getPieces()));
        chessGameDao.updateTurn(generateChessGameEntity());
        return new ChessGame(chessGame.getId(), currentBoard, chessGame.turnChange());
    }

    private ChessGameEntity generateChessGameEntity() {
        return new ChessGameEntity.Builder()
                .id(chessGame.getId())
                .turn(chessGame.turnChange().getDisplayName())
                .build();
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
            PieceEntity pieceEntityToFind = generateSourcePieceEntity(sourcePosition);
            PieceEntity pieceEntityToUpdate = generateUpdatePieceEntity(targetPosition);
            pieceDao.updatePiecePosition(pieceEntityToUpdate, pieceEntityToFind);
        }
    }

    private void piecePositionUpdateWhenTakePiece(Position sourcePosition, Position targetPosition) {
        if (chessGame.isTakePieceMove(targetPosition)) {
            PieceEntity sourcePieceEntity = generateSourcePieceEntity(sourcePosition);
            PieceEntity pieceEntityToUpdate = generateUpdatePieceEntity(sourcePosition);
            PieceEntity pieceEntityDelete = generateDeletePieceEntity(targetPosition);
            pieceDao.deletePieceByPosition(pieceEntityDelete);
            pieceDao.updatePiecePosition(pieceEntityToUpdate, sourcePieceEntity);
        }
    }

    private PieceEntity generateSourcePieceEntity(Position sourcePosition) {
        return new PieceEntity.Builder()
                .gameId(chessGame.getId())
                .rank(String.valueOf(sourcePosition.getRank()))
                .file(String.valueOf(sourcePosition.getFile()))
                .build();
    }

    private PieceEntity generateUpdatePieceEntity(Position targetPosition) {
        return new PieceEntity.Builder()
                .rank(String.valueOf(targetPosition.getRank()))
                .file(String.valueOf(targetPosition.getFile()))
                .build();
    }

    private PieceEntity generateDeletePieceEntity(Position targetPosition) {
        return new PieceEntity.Builder()
                .gameId(chessGame.getId())
                .rank(String.valueOf(targetPosition.getRank()))
                .file(String.valueOf(targetPosition.getFile()))
                .build();
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
