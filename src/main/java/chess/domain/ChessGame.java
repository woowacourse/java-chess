package chess.domain;

import chess.domain.position.Position;
import chess.dto.GameScoreDto;

public class ChessGame {

    private static final String GAME_END_NO_MOVE_ERROR_MESSAGE = "게임이 종료되어 이동할 수 없습니다.";
    private static final String CANNOT_FIND_WINNER_ERROR_MESSAGE = "아직 게임이 종료되지 않아서 승자를 찾을 수 없습니다.";

    private final ChessBoard chessBoard;
    private TeamColor teamColor;

    public ChessGame(final ChessBoard chessBoard) {
        this.chessBoard = chessBoard;
        this.teamColor = TeamColor.WHITE;
    }

    public void move(final Position source, final Position dest) {
        if (isEnd()) {
            throw new IllegalArgumentException(GAME_END_NO_MOVE_ERROR_MESSAGE);
        }
        chessBoard.move(source, dest, teamColor);
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
