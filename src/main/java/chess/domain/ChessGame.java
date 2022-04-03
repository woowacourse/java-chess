package chess.domain;

import chess.dao.ChessPieceDao;
import chess.dao.StatusDao;
import chess.domain.chessboard.ChessBoard;
import chess.domain.chessboard.ChessBoardFactory;
import chess.domain.chesspiece.ChessPiece;
import chess.domain.chesspiece.Color;
import chess.domain.position.Position;
import chess.dto.ChessPieceDto;
import chess.dto.StatusDto;
import chess.result.EndResult;
import chess.result.MoveResult;
import chess.result.StartResult;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class ChessGame {

    private ChessBoard chessBoard;
    private GameStatus gameStatus;

    public ChessGame() {
        final ChessPieceDao chessPieceDao = new ChessPieceDao();
        Map<Position, ChessPiece> pieceByPosition = chessPieceDao.findAll()
                .stream()
                .collect(Collectors.toMap(
                        ChessPieceDto::getPosition,
                        ChessPieceDto::getChessPiece
                ));

        if (pieceByPosition.isEmpty()) {
            pieceByPosition = ChessBoardFactory.createInitPieceByPosition();
        }

        final StatusDao statusDao = new StatusDao();
        final StatusDto statusDto = statusDao.find();

        Color currentTurn = null;
        GameStatus gameStatus = null;
        if (Objects.isNull(statusDto)) {
            currentTurn = Color.WHITE;
            gameStatus = GameStatus.READY;
        }
        if (Objects.nonNull(statusDto)) {
            currentTurn = statusDto.getCurrentTurn();
            gameStatus = statusDto.getGameStatus();
        }

        this.chessBoard = new ChessBoard(pieceByPosition, currentTurn);
        this.gameStatus = gameStatus;
    }

    public ChessGame(final ChessBoard chessBoard) {
        this.chessBoard = chessBoard;
        this.gameStatus = GameStatus.READY;
    }

    public void updateDB() {
        final ChessPieceDao chessPieceDao = new ChessPieceDao();
        chessPieceDao.deleteAll();
        chessPieceDao.saveAll(chessBoard.findAllPiece());

        final StatusDao statusDao = new StatusDao();
        statusDao.delete();
        statusDao.save(gameStatus, chessBoard.currentTurn());
    }

    public MoveResult move(final Position from, final Position to) {
        gameStatus.checkPlaying();

        final MoveResult result = chessBoard.move(from, to);
        if (chessBoard.isKingDie()) {
            gameStatus = GameStatus.KING_DIE;
        }
        return result;
    }

    public Score calculateScore() {
        gameStatus.checkPlaying();
        return chessBoard.calculateScore();
    }

    public StartResult start() {
        gameStatus.checkReady();
        if (gameStatus.isEnd()) {
            chessBoard = ChessBoardFactory.createChessBoard();
        }
        gameStatus = GameStatus.PLAYING;
        return new StartResult(chessBoard.findAllPiece());
    }

    public EndResult end() {
        gameStatus = GameStatus.END;
        final Score score = new Score(chessBoard.findAllPiece());
        return new EndResult(score);
    }

    public boolean canPlay() {
        return !gameStatus.isEnd();
    }

    public Map<Position, ChessPiece> findAllPiece() {
        gameStatus.checkPlaying();
        return chessBoard.findAllPiece();
    }

    public Color findCurrentTurn() {
        gameStatus.checkPlaying();
        return chessBoard.currentTurn();
    }

    public Color findWinColor() {
        gameStatus.checkEnd();
        return chessBoard.findWinColor();
    }
}
