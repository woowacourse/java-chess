package chess.domain;

import chess.domain.piece.Piece;
import chess.domain.player.Player;
import chess.view.ChessMap;

import java.util.List;

public class ChessGame {

    private final Player whitePlayer;
    private final Player blackPlayer;

    public ChessGame(Player whitePlayer, Player blackPlayer) {
        this.whitePlayer = whitePlayer;
        this.blackPlayer = blackPlayer;
    }

    public ChessMap createMap() {
        return ChessMap.of(whitePlayer.findAll(), blackPlayer.findAll());
    }

    public boolean isRunning() {
        return whitePlayer.hasKing() && blackPlayer.hasKing();
    }

    public List<Piece> move(final Player currentPlayer, final Player opponentPlayer,
                            final Position currentPosition, final Position destinationPosition) {
        validateMovable(currentPlayer, currentPosition, destinationPosition);
        if (opponentPlayer.hasPiece(destinationPosition)) {
            currentPlayer.capture(currentPosition, destinationPosition);
            opponentPlayer.remove(destinationPosition);
            return currentPlayer.findAll();
        }
        currentPlayer.move(currentPosition, destinationPosition);
        return currentPlayer.findAll();
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

    public boolean isWin(final Player currentPlayer, final Player opponentPlayer) {
        if (hasNoKing(currentPlayer)) {
            return false;
        }
        return hasNoKing(opponentPlayer) || isHigherScore(currentPlayer, opponentPlayer);
    }

    public boolean hasNoKing(final Player currentPlayer) {
        return !currentPlayer.hasKing();
    }

    public boolean isHigherScore(final Player currentPlayer, final Player opponentPlayer) {
        return getPlayerScore(currentPlayer) > getPlayerScore(opponentPlayer);
    }

    public double getPlayerScore(final Player currentPlayer) {
        return currentPlayer.calculateScore();
    }
}
