package chess.domain;

import chess.domain.position.Position;
import chess.dto.GameScoreDto;
import chess.repository.GameService;

public class ChessGame {

    private static final String GAME_END_NO_MOVE_ERROR_MESSAGE = "게임이 종료되어 이동할 수 없습니다.";
    private static final String CANNOT_FIND_WINNER_ERROR_MESSAGE = "아직 게임이 종료되지 않아서 승자를 찾을 수 없습니다.";

    private long gameId;
    private final ChessBoard chessBoard;
    private TeamColor teamColor;
    private final GameService gameService;


    public ChessGame(final ChessBoard chessBoard, final GameService gameService) {
        this.chessBoard = chessBoard;
        this.teamColor = TeamColor.WHITE;
        this.gameService = gameService;
    }

    private ChessGame(final long gameId,
        final TeamColor teamColor,
        final GameService gameService,
        final ChessBoard chessBoard) {
        this.gameId = gameId;
        this.teamColor = teamColor;
        this.gameService = gameService;
        this.chessBoard = chessBoard;
    }

    public static ChessGame fromDatabase(final long gameId,
        final TeamColor teamColor,
        final GameService gameService,
        final ChessBoard chessBoard) {
        return new ChessGame(gameId, teamColor, gameService, chessBoard);
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
            endGame();
            return;
        }
        transferTurn();
    }

    public boolean isEnd() {
        return !isPlaying();
    }

    private boolean isPlaying() {
        return !chessBoard.isKingDead();
    }

    private void saveMovement(final Position source, final Position dest) {
        if (chessBoard.isSourceMoved(source)) {
            gameService.updateMovement(source, dest, gameId);
        }
    }

    private void endGame() {
        gameService.updateGameStatusEnd(gameId);
    }

    private void transferTurn() {
        teamColor = teamColor.transfer();
        gameService.updateGameTurn(gameId, teamColor);
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

}
