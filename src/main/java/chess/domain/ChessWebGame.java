package chess.domain;

import chess.domain.generator.BlackGenerator;
import chess.domain.generator.WhiteGenerator;
import chess.domain.player.Player;
import chess.domain.player.Team;
import chess.dto.ResultDto;
import chess.dto.ScoreDto;
import chess.view.ChessMap;

import java.util.List;

public class ChessWebGame {

    private Player whitePlayer;
    private Player blackPlayer;
    private Team turn;

    public ChessWebGame(Player whitePlayer, Player blackPlayer) {
        this.whitePlayer = whitePlayer;
        this.blackPlayer = blackPlayer;
        this.turn = Team.WHITE;
    }

    public ChessWebGame() {
        this(new Player(new WhiteGenerator(), Team.WHITE), new Player(new BlackGenerator(), Team.BLACK));
    }

    public ChessMap initializeChessGame() {
        whitePlayer = new Player(new WhiteGenerator(), Team.WHITE);
        blackPlayer = new Player(new BlackGenerator(), Team.BLACK);
        turn = Team.WHITE;
        return createMap();
    }

    public ChessMap createMap() {
        return ChessMap.of(whitePlayer.findAll(), blackPlayer.findAll());
    }

    public void move(final Position currentPosition, final Position destinationPosition) {
        final Player currentPlayer = getCurrentPlayer();
        final Player opponentPlayer = getOpponentPlayer();
        validateMovable(currentPlayer, currentPosition, destinationPosition);
        changeTurn();
        if (opponentPlayer.hasPiece(destinationPosition)) {
            currentPlayer.capture(currentPosition, destinationPosition);
            opponentPlayer.remove(destinationPosition);
            return;
        }
        currentPlayer.move(currentPosition, destinationPosition);
    }

    private void validateMovable(final Player currentPlayer, final Position currentPosition,
                                 final Position destinationPosition) {
        if (!currentPlayer.hasPiece(currentPosition)) {
            throw new IllegalArgumentException("선택한 출발 위치에 현재 턴에 해당하는 팀의 체스말이 존재하지 않습니다.");
        }
        if (currentPlayer.hasPiece(destinationPosition)) {
            throw new IllegalArgumentException("선택한 도착 위치에 이미 체스말이 존재합니다.");
        }
        if (hasPieceBetweenPosition(currentPosition, destinationPosition)) {
            throw new IllegalArgumentException("이동 경로에 체스말이 존재합니다.");
        }
    }

    private boolean hasPieceBetweenPosition(final Position current, final Position destination) {
        final List<Position> positions = current.findAllBetweenPosition(destination);
        boolean hasPieceOfBlackPlayer = positions.stream()
                .anyMatch(blackPlayer::hasPiece);
        boolean hasPieceOfWhitePlayer = positions.stream()
                .anyMatch(whitePlayer::hasPiece);

        return hasPieceOfBlackPlayer || hasPieceOfWhitePlayer;
    }

    public void changeTurn() {
        if (turn == Team.WHITE) {
            turn = Team.BLACK;
            return;
        }
        turn = Team.WHITE;
    }

    public Player getCurrentPlayer() {
        if (turn == Team.WHITE) {
            return whitePlayer;
        }
        return blackPlayer;
    }

    public Player getOpponentPlayer() {
        if (turn == Team.WHITE) {
            return blackPlayer;
        }
        return whitePlayer;
    }

    public ResultDto getResult() {
        if (!whitePlayer.hasKing()) {
            return new ResultDto("블랙이 화이트의 킹을 캡처하여 승리하였습니다!");
        }
        if (!blackPlayer.hasKing()) {
            return new ResultDto("화이트가 블랙의 킹을 캡처하여 승리하였습니다!");
        }
        return getResultByScore();
    }

    private ResultDto getResultByScore() {
        final double whiteScore = whitePlayer.calculateScore();
        final double blackScore = blackPlayer.calculateScore();
        final String status = String.format("%s: %.1f\n%s: %.1f\n",
                whitePlayer.getTeamName(), whiteScore, blackPlayer.getTeamName(), blackScore);
        return findWinner(whiteScore, blackScore, status);
    }

    private ResultDto findWinner(final double whiteScore, final double blackScore, final String status) {
        if (whiteScore > blackScore) {
            return new ResultDto(status.concat("화이트 승!"));
        }
        if (whiteScore == blackScore) {
            return new ResultDto(status.concat("블랙 승!"));
        }
        return new ResultDto(status.concat("무승부!"));
    }

    public ScoreDto getScoreStatus() {
        final double whiteScore = whitePlayer.calculateScore();
        final double blackScore = blackPlayer.calculateScore();
        final String status = String.format("%s: %.1f\n%s: %.1f",
                whitePlayer.getTeamName(), whiteScore, blackPlayer.getTeamName(), blackScore);
        return new ScoreDto(status);
    }
}
