package chess.domain;

import chess.domain.generator.BlackGenerator;
import chess.domain.generator.WhiteGenerator;
import chess.domain.piece.Piece;
import chess.domain.player.Player;
import chess.domain.player.Team;
import chess.view.ChessMap;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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

    public void loadPlayers(List<Piece> whitePieces, List<Piece> blackPieces) {
        this.whitePlayer = new Player(whitePieces, Team.WHITE);
        this.blackPlayer = new Player(blackPieces, Team.BLACK);
    }

    public void loadTurn(Team turn) {
        if (this.turn != turn) {
            changeTurn();
        }
    }

    public ChessMap createMap() {
        return ChessMap.of(whitePlayer.findAll(), blackPlayer.findAll());
    }

    public void move(final Position currentPosition, final Position destinationPosition) {
        final Player currentPlayer = getCurrentPlayer();
        final Player opponentPlayer = getOpponentPlayer();
        validateMovable(currentPlayer, currentPosition, destinationPosition);
        if (opponentPlayer.hasPiece(destinationPosition)) {
            capture(currentPosition, destinationPosition, currentPlayer, opponentPlayer);
            return;
        }
        currentPlayer.move(currentPosition, destinationPosition);
    }

    private void capture(final Position currentPosition, final Position destinationPosition,
                         final Player currentPlayer, final Player opponentPlayer) {
        currentPlayer.capture(currentPosition, destinationPosition);
        opponentPlayer.remove(destinationPosition);
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

    public Map<String, Double> getScoreStatus() {
        final Map<String, Double> scores = new LinkedHashMap<>();
        scores.put(whitePlayer.getTeamName(), whitePlayer.calculateScore());
        scores.put(blackPlayer.getTeamName(), blackPlayer.calculateScore());
        return scores;
    }

    public Result getResult() {
        if (!whitePlayer.hasKing()) {
            return Result.BLACK_WIN_CAPTURE_KING;
        }
        if (!blackPlayer.hasKing()) {
            return Result.WHITE_WIN_CAPTURE_KING;
        }
        return getResultByScore();
    }

    private Result getResultByScore() {
        final double whiteScore = whitePlayer.calculateScore();
        final double blackScore = blackPlayer.calculateScore();
        return Result.of(whiteScore, blackScore);
    }
}
