package chess.domain;

import chess.domain.piece.Piece;
import chess.domain.player.Player;
import java.util.List;

public class ChessGame {

    private final Player whitePlayer;
    private final Player blackPlayer;

    public ChessGame(Player whitePlayer, Player blackPlayer) {
        this.whitePlayer = whitePlayer;
        this.blackPlayer = blackPlayer;
    }

    public List<Piece> move(final Player currentPlayer, final Player opponentPlayer,
            final Position currentPosition, final Position destinationPosition) {
        validateMovable(currentPlayer, currentPosition, destinationPosition);
        final boolean hasPieceOfCurrentPlayer = currentPlayer.hasPiece(destinationPosition);
        final boolean hasPieceOfOpponentPlayer = opponentPlayer.hasPiece(destinationPosition);
        if (!hasPieceOfCurrentPlayer && !hasPieceOfOpponentPlayer) {
            currentPlayer.move(currentPosition, destinationPosition);
        }
        if (hasPieceOfOpponentPlayer) {
            currentPlayer.capture(currentPosition, destinationPosition);
        }
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
}
