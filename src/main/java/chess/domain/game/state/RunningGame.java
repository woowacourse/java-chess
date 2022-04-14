package chess.domain.game.state;

import chess.domain.piece.King;
import java.util.Map;

import chess.domain.piece.Piece;
import chess.domain.piece.position.Position;
import chess.domain.piece.property.Color;

public class RunningGame extends StartedGame {

    private final ChessBoard board;
    private Player player;

    public RunningGame(ChessBoard board, Player player) {
        this.board = board;
        this.player = player;
    }

    @Override
    public GameState move(Position source, Position target) {
        validatePosition(source);

        if (isKingKilled(target)) {
            board.move(source, target);
            return new EndGame();
        }

        board.move(source, target);
        player = player.change();

        return new RunningGame(board, player);
    }

    private void validatePosition(Position source) {
        board.validateExist(source);
        checkMyPiece(board.getPiece(source));
    }

    private boolean isKingKilled(Position target) {
        return board.isFilled(target) && board.getPiece(target).getClass().equals(King.class);
    }

    private void checkMyPiece(Piece sourcePiece) {
        if (!player.isMyPiece(sourcePiece)) {
            throw new IllegalArgumentException("자신의 말만 움직일 수 있습니다.");
        }
    }

    @Override
    public Map<Color, Double> status() {
        return board.computeScore();
    }

    @Override
    public Map<Position, Piece> getBoard() {
        return board.getBoard();
    }

    @Override
    public String getTurn() {
        return player.name();
    }
}
