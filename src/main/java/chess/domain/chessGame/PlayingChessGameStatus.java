package chess.domain.chessGame;

import chess.domain.board.Board;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import java.util.EnumMap;
import java.util.Map;

public class PlayingChessGameStatus implements ChessGameStatus {

    @Override
    public ChessGameStatus start() {
        throw new IllegalArgumentException("이미 플레이중인 게임입니다.");
    }

    @Override
    public void validateMove(String currentPositionSymbol, String nextPositionSymbol, Piece movingPiece) {
        Position currentPosition = Position.from(currentPositionSymbol);
        Position nextPosition = Position.from(nextPositionSymbol);
        checkCurrentAndNextPosition(currentPosition, nextPosition);
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

    @Override
    public ChessGameStatus end() {
        return new EndChessGameStatus();
    }

    @Override
    public boolean isPlaying() {
        return true;
    }

    @Override
    public Map<Position, String> getPrintingBoard(Board board) {
        return board.getPrintingBoard();
    }

    @Override
    public Map<Color, Double> getScores(Board board) {
        Map<Color, Double> scores = new EnumMap<>(Color.class);
        scores.put(Color.WHITE, board.calculateScoreByColor(Color.WHITE));
        scores.put(Color.BLACK, board.calculateScoreByColor(Color.BLACK));
        return scores;
    }

    @Override
    public String getName() {
        return "playing";
    }
}
