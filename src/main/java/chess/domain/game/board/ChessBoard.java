package chess.domain.game.board;

import chess.domain.game.Color;
import chess.domain.game.GameStatus;
import chess.domain.game.Score;
import chess.domain.piece.ChessPiece;
import chess.domain.position.Direction;
import chess.domain.position.Position;

import java.util.Map;
import java.util.Optional;
import java.util.Stack;

public class ChessBoard {

    private final Map<Position, ChessPiece> chessBoard;
    private Color currentTurn = Color.WHITE;
    private GameStatus gameStatus = GameStatus.READY;

    public ChessBoard(Map<Position, ChessPiece> board) {
        this.chessBoard = board;
    }

    public void move(Position source, Position target) {
        ChessPiece piece = checkPieceMove(source, target);

        if (isEmptyBlock(target)) {
            moveEmptyPosition(source, target, piece);
            return;
        }

        if (enemyExist(piece, target)) {
            catchAndMove(source, target, piece);
        }
    }

    private ChessPiece checkPieceMove(Position source, Position target) {
        ChessPiece piece = findPiece(source)
                .orElseThrow(() -> new IllegalArgumentException("해당 위치에 기물이 존재하지 않습니다."));

        checkMove(source, target, piece);
        return piece;
    }

    private void checkMove(Position source, Position target, ChessPiece piece) {
        validateTurn(piece);
        piece.checkMovable(source, target);
        Stack<Position> routes = piece.findRoute(source, target);

        while (!routes.isEmpty()) {
            checkHurdle(routes.pop());
        }
    }

    private void validateTurn(ChessPiece piece) {
        if (currentTurn != piece.getColor()) {
            throw new IllegalArgumentException(currentTurn.name() + "의 차례입니다.");
        }
    }

    private void checkHurdle(Position position) {
        if (findPiece(position).isPresent()) {
            throw new IllegalArgumentException("이동 경로 사이에 다른 기물이 있습니다.");
        }
    }

    private boolean isEmptyBlock(Position target) {
        return findPiece(target).isEmpty();
    }

    private void moveEmptyPosition(Position source, Position target, ChessPiece piece) {
        checkPawnStraightMove(source, target, piece);
        movePiece(source, target, piece);
    }

    private void catchAndMove(Position source, Position target, ChessPiece piece) {
        checkPawnCrossMove(source, target, piece);
        movePiece(source, target, piece);
    }

    private void checkPawnStraightMove(Position source, Position target, ChessPiece piece) {
        if (piece.isPawn() && isCross(source, target)) {
            throw new IllegalArgumentException("폰은 대각선에 상대 기물이 존재해야합니다");
        }
    }

    private boolean isCross(Position source, Position target) {
        return target.findDirection(source) != Direction.N && target.findDirection(source) != Direction.S;
    }

    private void checkPawnCrossMove(Position source, Position target, ChessPiece piece) {
        if (piece.isPawn() && isStraight(source, target)) {
            throw new IllegalArgumentException("폰은 대각선 이동으로 적을 잡을 수 있습니다.");
        }
    }

    private boolean isStraight(Position source, Position target) {
        return target.findDirection(source) == Direction.N || target.findDirection(source) == Direction.S;
    }

    private void movePiece(Position source, Position target, ChessPiece piece) {
        checkMate(target);
        chessBoard.put(target, piece);
        chessBoard.remove(source);
        currentTurn = currentTurn.toOpposite();
    }

    private void checkMate(Position target) {
        ChessPiece pieceOfTo = chessBoard.get(target);
        if (pieceOfTo != null && pieceOfTo.isKing()) {
            gameStatus = GameStatus.END;
        }
    }


    public int countPiece() {
        return chessBoard.size();
    }

    public Optional<ChessPiece> findPiece(Position position) {
        return Optional.ofNullable(chessBoard.get(position));
    }

    public boolean enemyExist(ChessPiece piece, Position target) {
        Optional<ChessPiece> possiblePiece = findPiece(target);
        if (possiblePiece.isEmpty()) {
            throw new IllegalArgumentException("폰은 대각선에 상대 기물이 존재해야합니다");
        }

        ChessPiece targetPiece = possiblePiece.get();
        if (targetPiece.isSameColorPiece(piece)) {
            throw new IllegalArgumentException("같은색 기물입니다.");
        }

        return true;
    }

    public Map<Color, Double> calculateScore() {
        Score score = new Score(chessBoard);
        return score.calculateScore();
    }

    public Color decideWinner() {
        return currentTurn.toOpposite();
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
