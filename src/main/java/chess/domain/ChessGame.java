package chess.domain;

import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.dto.GameScoreDto;
import chess.repository.PieceDao;
import java.util.Map;

public class ChessGame {

    private static final String GAME_END_NO_MOVE_ERROR_MESSAGE = "게임이 종료되어 이동할 수 없습니다.";
    private static final String CANNOT_FIND_WINNER_ERROR_MESSAGE = "아직 게임이 종료되지 않아서 승자를 찾을 수 없습니다.";

    private long gameId;
    private ChessBoard chessBoard;
    private TeamColor teamColor;
    private final PieceDao pieceDao;

    public ChessGame(final ChessBoard chessBoard) {
        this.chessBoard = chessBoard;
        this.teamColor = TeamColor.WHITE;
        this.pieceDao = new PieceDao();
    }

    private ChessGame(final long gameId, final TeamColor teamColor) {
        this.gameId = gameId;
        this.teamColor = teamColor;
        this.pieceDao = new PieceDao();
    }

    public static ChessGame fromDatabase(final long gameId, final TeamColor teamColor) {
        ChessGame chessGame = new ChessGame(gameId, teamColor);
        chessGame.insertPieces();
        return chessGame;
    }

    public void saveAllPieces() {
        pieceDao.save(chessBoard.piecesByPosition(), gameId);
    }

    private void insertPieces() {
        Map<Position, Piece> piecesByPosition = pieceDao.findAllByGameId(gameId);
        chessBoard = new ChessBoard(piecesByPosition);
    }

    public void updateNewGameId(final long gameId) {
        this.gameId = gameId;
    }

    public void move(final Position source, final Position dest) {
        if (isEnd()) {
            throw new IllegalArgumentException(GAME_END_NO_MOVE_ERROR_MESSAGE);
        }
        chessBoard.move(source, dest, teamColor);
        saveMovement(source, dest);
        if (isEnd()) {
            return;
        }
        teamColor = teamColor.transfer();
    }

    private boolean isEnd() {
        return !isPlaying();
    }

    public boolean isPlaying() {
        return !chessBoard.isKingDead();
    }

    private void saveMovement(final Position source, final Position dest) {
        boolean isMoveSuccess = chessBoard.isSourceMoved(source);
        if (isMoveSuccess) {
            pieceDao.deleteByPositionAndGameId(dest, gameId);
            pieceDao.updatePositionByPositionAndGameId(source, gameId, dest);
        }
    }

    public TeamColor findWinningTeam() {
        if (isPlaying()) {
            throw new IllegalArgumentException(CANNOT_FIND_WINNER_ERROR_MESSAGE);
        }
        return teamColor;
    }

    public GameScoreDto getCurrentScore() {
        GameResult gameResult = chessBoard.getGameResult();
        return GameScoreDto.of(gameResult.calculateScoreOfTeam(TeamColor.WHITE),
            gameResult.calculateScoreOfTeam(TeamColor.BLACK));
    }

    public ChessBoard getChessBoard() {
        return chessBoard;
    }

    public TeamColor getTeamColor() {
        return teamColor;
    }

    public long getGameId() {
        return gameId;
    }
}
