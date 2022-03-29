package chess.domain;

import chess.domain.piece.*;
import chess.domain.game.GameStatus;
import chess.domain.game.Score;
import chess.domain.position.Direction;
import chess.domain.position.Position;

import java.util.*;

public class ChessBoard {

    private Map<Position, ChessPiece> chessBoard;
    private Color currentTurn = Color.WHITE;
    private GameStatus gameStatus;

    public ChessBoard(Map<Position, ChessPiece> board) {
        this.chessBoard = board;
        this.gameStatus = GameStatus.READY;
    }

    public void move(Position from, Position to) {
        ChessPiece me = findPiece(from)
                .orElseThrow(() -> new IllegalArgumentException("해당 위치에 기물이 존재하지 않습니다."));

        validateTurn(me);

        checkMove(from, to, me);

        if (findPiece(to).isEmpty()) {
            checkPawnStraightMove(from, to, me);
            movePiece(from, to, me);
            return;
        }

        if (enemyExist(me, to)) {
            checkPawnCrossMove(from, to, me);
            movePiece(from, to, me);
        }
    }

    private void validateTurn(ChessPiece me) {
        if (me.isEnemyTurn(currentTurn)) {
            throw new IllegalArgumentException(currentTurn.name() + "의 차례입니다.");
        }
    }

    private void checkPawnStraightMove(Position from, Position to, ChessPiece me) {
        if (me instanceof Pawn && isCross(from, to)) {
            throw new IllegalArgumentException("폰은 대각선에 상대 기물이 존재해야합니다");
        }
    }

    private boolean isCross(Position from, Position to) {
        return to.findDirection(from) != Direction.N && to.findDirection(from) != Direction.S;
    }

    private void checkPawnCrossMove(Position from, Position to, ChessPiece me) {
        if (me instanceof Pawn && isStraight(from, to)) {
            throw new IllegalArgumentException("폰은 대각선 이동으로 적을 잡을 수 있습니다.");
        }
    }

    private boolean isStraight(Position from, Position to) {
        return to.findDirection(from).equals(Direction.N) || to.findDirection(from).equals(Direction.S);
    }

    private void checkMove(Position from, Position to, ChessPiece me) {
        me.canMove(from, to);
        Stack<Position> routes = me.findRoute(from, to);

        while (!routes.isEmpty()) {
            checkHurdle(routes.pop());
        }
    }

    private void checkHurdle(Position position) {
        if (findPiece(position).isPresent()) {
            throw new IllegalArgumentException("이동 경로 사이에 다른 기물이 있습니다.");
        }
    }

    private void movePiece(Position from, Position to, ChessPiece me) {
        checkMate(to);
        chessBoard.put(to, me);
        chessBoard.remove(from);
        currentTurn = currentTurn.toOpposite();
    }

    private void checkMate(Position to) {
        if (chessBoard.get(to) instanceof King) {
            gameStatus = GameStatus.END;
        }
    }

    public int countPiece() {
        return chessBoard.size();
    }

    public Optional<ChessPiece> findPiece(Position position) {
        return Optional.ofNullable(chessBoard.get(position));
    }

    public boolean enemyExist(ChessPiece me, Position to) {
        Optional<ChessPiece> possiblePiece = findPiece(to);
        if (possiblePiece.isEmpty()) {
            throw new IllegalArgumentException("폰은 대각선에 상대 기물이 존재해야합니다");
        }

        ChessPiece piece = possiblePiece.get();
        if (piece.isSameColor(me)) {
            throw new IllegalArgumentException("같은색 기물입니다.");
        }

        return true;
    }

    public Map<Color, Double> calculateScore() {
        Score score = new Score(chessBoard);
        return score.calculateScore();
    }

    public boolean isReady() {
        return gameStatus.isReady();
    }

    public boolean isEnd() {
        return gameStatus.isEnd();
    }

    public boolean isPlaying() {
        return gameStatus.isPlaying();
    }

    public void start() {
        gameStatus = GameStatus.PLAYING;
    }
}
