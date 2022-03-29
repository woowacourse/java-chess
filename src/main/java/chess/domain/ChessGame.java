package chess.domain;

import chess.domain.piece.Piece;
import chess.domain.player.Player;
import chess.domain.player.Team;
import java.util.List;

public class ChessGame {

    private final Player whitePlayer;
    private final Player blackPlayer;

    private Team turn;

    public ChessGame(Player whitePlayer, Player blackPlayer) {
        this.whitePlayer = whitePlayer;
        this.blackPlayer = blackPlayer;
        this.turn = Team.WHITE;
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
        final boolean hasPieceOfOpponentPlayer = opponentPlayer.hasPiece(destinationPosition);
        if (hasPieceOfOpponentPlayer) {
            return capture(currentPlayer, opponentPlayer, currentPosition, destinationPosition);
        }
        currentPlayer.move(currentPosition, destinationPosition);
        return currentPlayer.findAll();
    }

    private List<Piece> capture(Player currentPlayer, Player opponentPlayer, Position currentPosition,
            Position destinationPosition) {
        currentPlayer.capture(currentPosition, destinationPosition);
        opponentPlayer.remove(destinationPosition);
        return currentPlayer.findAll();
    }

    private void validateMovable(final Player currentPlayer, final Position currentPosition,
            final Position destinationPosition) {
        if (!currentPlayer.hasPiece(currentPosition)) {
            throw new IllegalArgumentException("선택한 출발 위치에 체스말이 존재하지 않습니다.");
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
        turn = turn.next();
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
}
