package chess.model.service;

import chess.model.domain.board.BoardInitialByDB;
import chess.model.domain.board.BoardInitialization;
import chess.model.domain.board.BoardSquare;
import chess.model.domain.board.CastlingSetting;
import chess.model.domain.board.ChessGame;
import chess.model.domain.board.EnPassant;
import chess.model.domain.piece.Color;
import chess.model.domain.piece.Piece;
import chess.model.domain.piece.Type;
import chess.model.domain.state.MoveOrder;
import chess.model.domain.state.MoveSquare;
import chess.model.domain.state.MoveState;
import chess.model.dto.ChessGameDto;
import chess.model.dto.MoveDto;
import chess.model.dto.PromotionTypeDto;
import chess.model.repository.ChessBoardDao;
import chess.model.repository.ChessGameDao;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Set;

public class ChessGameService {

    private static final ChessGameDao CHESS_GAME_DAO = ChessGameDao.getInstance();
    private static final ChessBoardDao CHESS_BOARD_DAO = ChessBoardDao.getInstance();
    private static final ChessGameService INSTANCE = new ChessGameService();

    private ChessGameService() {
    }

    public static ChessGameService getInstance() {
        return INSTANCE;
    }

    public String getId(String roomId) throws SQLException, IllegalAccessException {
        String formatDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        String roomNumberDate = String.format("%s-%s-", roomId, formatDate);
        String number = String
            .format("%03d", CHESS_GAME_DAO.getGameNumberLatest(roomNumberDate) + 1);
        return roomNumberDate + number;
    }

    public String getIdBefore(String roomId) throws SQLException, IllegalAccessException {
        String formatDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        String roomNumberDate = String.format("%s-%s-", roomId, formatDate);
        String number = String.format("%03d", CHESS_GAME_DAO.getGameNumberLatest(roomNumberDate));
        return roomNumberDate + number;
    }

    public void createChessGame(String gameId, Map<Color, String> userNames)
        throws SQLException {
        ChessGame chessGame = new ChessGame();
        CHESS_GAME_DAO.insert(gameId, chessGame.getGameTurn(), userNames);
        CHESS_BOARD_DAO.insert(gameId, chessGame.getChessBoard(), chessGame.getCastlingElements());
    }

    public ChessGameDto loadChessGame(String gameId) throws SQLException {
        return new ChessGameDto(getChessGame(gameId));
    }

    private ChessGame getChessGame(String gameId) throws SQLException {
        BoardInitialization boardInitialByDB = new BoardInitialByDB(
            CHESS_BOARD_DAO.getBoard(gameId));
        Color gameTurn = CHESS_GAME_DAO.getGameTurn(gameId).orElseThrow(IllegalAccessError::new);
        Set<CastlingSetting> castlingElements = CHESS_BOARD_DAO.getCastlingElements(gameId);
        EnPassant enPassant = CHESS_BOARD_DAO.getEnpassantBoard(gameId);
        return new ChessGame(boardInitialByDB, gameTurn, castlingElements, enPassant);
    }

    public ChessGameDto move(MoveDto moveDTO) throws SQLException {
        MoveSquare moveSquare = new MoveSquare(moveDTO.getSource(), moveDTO.getTarget());
        String gameId = moveDTO.getGameId();
        EnPassant enPassant = CHESS_BOARD_DAO.getEnpassantBoard(gameId);
        ChessGame chessGame = getChessGame(gameId);
        boolean canCastling = chessGame.canCastling(moveSquare);
        boolean pawnSpecialMove = chessGame.isPawnSpecialMove(moveSquare);
        MoveState moveState = chessGame.movePieceWhenCanMove(moveSquare);
        Color gameTurn = CHESS_GAME_DAO.getGameTurn(gameId).orElseThrow(IllegalAccessError::new);
        if (moveState.isSucceed()) {
            CHESS_BOARD_DAO.deleteBoardSquare(gameId, moveSquare.get(MoveOrder.AFTER));
            CHESS_BOARD_DAO.copyBoardSquare(gameId, moveSquare);
            CHESS_BOARD_DAO.deleteBoardSquare(gameId, moveSquare.get(MoveOrder.BEFORE));
            if (pawnSpecialMove) {
                CHESS_BOARD_DAO.updateEnPassant(gameId, moveSquare);
            }
            if (enPassant.hasOtherEnpassant(moveSquare.get(MoveOrder.AFTER), gameTurn)) {
                CHESS_BOARD_DAO.deleteEnpassant(gameId, moveSquare.get(MoveOrder.AFTER));
            }
            if (canCastling) {
                MoveSquare moveSquareRook = CastlingSetting.getMoveCastlingRook(moveSquare);
                CHESS_BOARD_DAO.copyBoardSquare(gameId, moveSquareRook);
                CHESS_BOARD_DAO.deleteBoardSquare(gameId, moveSquareRook.get(MoveOrder.BEFORE));
            }
        }
        if (moveState == MoveState.SUCCESS) {
            CHESS_GAME_DAO.updateTurn(gameId, chessGame.getGameTurn());
        }
        ChessGameDto chessGameDTO = new ChessGameDto(getChessGame(gameId), moveState);
        if (moveState == MoveState.KING_CAPTURED) {
            CHESS_BOARD_DAO.deleteBoardSquare(gameId, moveSquare.get(MoveOrder.AFTER));
            CHESS_BOARD_DAO.copyBoardSquare(gameId, moveSquare);
            CHESS_BOARD_DAO.deleteBoardSquare(gameId, moveSquare.get(MoveOrder.BEFORE));
            CHESS_GAME_DAO.updateProceedN(gameId);
            chessGameDTO.clearPiece();
        }
        return chessGameDTO;
    }

    public ChessGameDto promotion(PromotionTypeDto promotionTypeDTO) throws SQLException {
        Type type = Type.of(promotionTypeDTO.getPromotionType());
        String gameId = promotionTypeDTO.getGameId();
        ChessGame chessGame = getChessGame(gameId);
        BoardSquare finishPawnBoard = chessGame.getFinishPawnBoard();
        Piece hopePiece = chessGame.getHopePiece(type);
        MoveState moveState = chessGame.promotion(type);
        if (moveState == MoveState.SUCCESS_PROMOTION) {
            CHESS_BOARD_DAO.updatePromotion(gameId, finishPawnBoard, hopePiece);
            CHESS_GAME_DAO.updateTurn(gameId, chessGame.getGameTurn());
        }
        return new ChessGameDto(getChessGame(gameId), moveState);
    }
}
