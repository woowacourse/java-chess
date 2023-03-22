package chess.domain;

import chess.domain.position.Position;
import chess.dto.GameScoreDto;

public class ChessGame {

    private static final String GAME_END_NO_MOVE_ERROR_MESSAGE = "게임이 종료되어 이동할 수 없습니다.";
    private static final String CANNOT_FIND_WINNER_ERROR_MESSAGE = "아직 게임이 종료되지 않아서 승자를 찾을 수 없습니다.";

    private final ChessBoard chessBoard;
    private TeamColor teamColor;
    private boolean isPlaying;

    public ChessGame(final ChessBoard chessBoard) {
        this.chessBoard = chessBoard;
        this.teamColor = TeamColor.WHITE;
        this.isPlaying = true;
    }

    public void move(final Position source, final Position dest) {
        if (!isPlaying) {
            throw new IllegalArgumentException(GAME_END_NO_MOVE_ERROR_MESSAGE);
        }
        chessBoard.move(source, dest, teamColor);
        if (chessBoard.isKingDead()) {
            isPlaying = false;
            return;
        }
        teamColor = teamColor.transfer();
    }

    public boolean isPlaying() {
        return isPlaying;
    }

    public TeamColor findWinningTeam() {
        if (isPlaying) {
            throw new IllegalArgumentException(CANNOT_FIND_WINNER_ERROR_MESSAGE);
        }
        return teamColor;
    }

    public GameScoreDto getCurrentScore() {
        GameResult gameResult = new GameResult(chessBoard.getPiecesCollectedByFile());
        return GameScoreDto.of(gameResult.calculateScoreOfTeam(TeamColor.WHITE),
            gameResult.calculateScoreOfTeam(TeamColor.BLACK));
    }

    public ChessBoard getChessBoard() {
        return chessBoard;
    }

}
