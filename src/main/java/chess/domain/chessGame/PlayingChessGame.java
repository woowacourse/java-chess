package chess.domain.chessGame;

import chess.domain.Board;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import java.util.Map;

public class PlayingChessGame implements ChessGame {
    private final Board board;
    private Color turnColor = Color.WHITE;

    public PlayingChessGame(Board board) {
        this.board = board;
    }

    @Override
    public ChessGame start() {
        throw new IllegalArgumentException("이미 플레이중인 게임입니다.");
    }

    @Override
    public ChessGame move(String currentPositionSymbol, String nextPositionSymbol) {
        Position currentPosition = Position.from(currentPositionSymbol);
        Position nextPosition = Position.from(nextPositionSymbol);
        checkCurrentAndNextPosition(currentPosition, nextPosition);
        checkTurn(currentPosition);
        board.move(currentPosition, nextPosition);
        turnColor = turnColor.getOppositeColor();
        return this;
    }

    //TODO 올바른 포지션 체크 예외를 포지션 객체에서 하게 리팩토링 필요
    private void checkCurrentAndNextPosition(Position currentPosition, Position nextPosition) {
        checkInvalidPosition(currentPosition);
        checkInvalidPosition(nextPosition);
    }

    private void checkInvalidPosition(Position position) {
        if (position.isInvalid()) {
            throw new IllegalArgumentException("기물이 이동할 수 없는 위치입니다.");
        }
    }

    private void checkTurn(Position currentPosition) {
        Piece movingPiece = board.findPieceByPosition(currentPosition);
        if (movingPiece.isOpponent(turnColor)) {
            throw new IllegalArgumentException("상대방 기물의 턴입니다.");
        }
    }

    @Override
    public ChessGame end() {
        return new EndChessGame();
    }

    @Override
    public boolean isPlaying() {
        return true;
    }

    @Override
    public Map<Position, String> getPrintingBoard() {
        return board.getPrintingBoard();
    }
}
