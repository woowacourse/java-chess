package chess.domain.chessboard;

import chess.domain.GameStatus;
import chess.domain.chesspiece.ChessPiece;
import chess.domain.chesspiece.Color;
import chess.domain.chesspiece.King;
import chess.domain.chesspiece.Pawn;
import chess.domain.position.Direction;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.Stack;
import java.util.stream.Collectors;

public class ChessBoard {

    private final Map<Position, ChessPiece> chessBoard;
    private GameStatus gameStatus;
    private Color currentTurn = Color.WHITE;

    ChessBoard(final Map<Position, ChessPiece> chessBoard) {
        this.chessBoard = chessBoard;
        this.gameStatus = GameStatus.READY;
    }

    public void move(final Position from, final Position to) {
        final ChessPiece me = findPiece(from)
                .orElseThrow(() -> new IllegalArgumentException("해당 위치에 기물이 존재하지 않습니다."));

        if (me.isEnemyTurn(currentTurn)) {
            throw new IllegalArgumentException(currentTurn.name() + "의 차례입니다.");
        }

        checkMove(from, to, me);

        final Optional<ChessPiece> possibleTargetPiece = findPiece(to);
        if (possibleTargetPiece.isEmpty()) {
            checkPawnStraightMove(from, to, me);
            movePiece(from, to, me);
            return;
        }

        final ChessPiece targetPiece = possibleTargetPiece.get();
        checkEnemy(me, targetPiece);
        checkPawnCrossMove(from, to, me);
        movePiece(from, to, me);
    }

    public Optional<ChessPiece> findPiece(final Position position) {
        final ChessPiece piece = chessBoard.get(position);
        if (piece == null) {
            return Optional.empty();
        }

        return Optional.of(piece);
    }

    private void checkMove(final Position from, final Position to, final ChessPiece me) {
        me.canMove(from, to);
        final Stack<Position> routes = me.findRoute(from, to);

        while (!routes.isEmpty()) {
            checkHurdle(routes.pop());
        }
    }

    private void checkHurdle(final Position position) {
        if (findPiece(position).isPresent()) {
            throw new IllegalArgumentException("이동 경로 사이에 다른 기물이 있습니다.");
        }
    }

    private void checkPawnStraightMove(final Position from, final Position to, final ChessPiece me) {
        if (me instanceof Pawn && isCross(from, to)) {
            throw new IllegalArgumentException("폰은 상대 기물이 존재할 때만 대각선으로 이동할 수 있습니다.");
        }
    }

    private boolean isCross(final Position from, final Position to) {
        return to.findDirection(from) != Direction.N && to.findDirection(from) != Direction.S;
    }

    private void checkPawnCrossMove(final Position from, final Position to, final ChessPiece me) {
        if (me instanceof Pawn && isStraight(from, to)) {
            throw new IllegalArgumentException("폰은 대각선 이동으로만 적을 잡을 수 있습니다.");
        }
    }

    private boolean isStraight(final Position from, final Position to) {
        return to.findDirection(from) == Direction.N || to.findDirection(from) == Direction.S;
    }

    private void checkEnemy(final ChessPiece me, final ChessPiece targetPiece) {
        if (targetPiece.isSameColor(me)) {
            throw new IllegalArgumentException("같은색 기물입니다.");
        }
    }

    private void movePiece(final Position from, final Position to, final ChessPiece me) {
        if (chessBoard.get(to) instanceof King) {
            gameStatus = GameStatus.END;
        }
        chessBoard.put(to, me);
        chessBoard.remove(from);
        currentTurn = currentTurn.toOpposite();
    }

    public Map<Color, Double> calculateScore() {
        return Arrays.stream(Color.values())
                .collect(Collectors.toMap(
                        color -> color,
                        this::getSum));
    }

    private double getSum(final Color color) {
        final double sumExceptPawnScore = chessBoard.values().stream()
                .filter((chessPiece) -> chessPiece.isSameColor(color))
                .filter((chessPiece) -> !(chessPiece instanceof Pawn))
                .mapToDouble(ChessPiece::getValue)
                .sum();

        return sumExceptPawnScore + getSumPawn(color);
    }

    private double getSumPawn(final Color color) {
        double totalPawnScore = 0;
        for (final Rank rank : Rank.values()) {
            final double pawnCount = countSameRankPawn(color, rank);
            totalPawnScore += sumPawnScore(pawnCount);
        }
        return totalPawnScore;
    }

    private double countSameRankPawn(final Color color, final Rank rank) {
        return Arrays.stream(File.values())
                .map((file) -> findPiece(Position.of(rank, file)))
                .filter((possiblePiece) -> isMyPawn(color, possiblePiece))
                .count();
    }

    private boolean isMyPawn(final Color color, final Optional<ChessPiece> possiblePiece) {
        if (possiblePiece.isEmpty()) {
            return false;
        }

        final ChessPiece chessPiece = possiblePiece.get();
        return chessPiece instanceof Pawn && chessPiece.isSameColor(color);
    }

    private double sumPawnScore(final double pawnCount) {
        if (pawnCount == 1) {
            return 1;
        }
        return pawnCount * 0.5;
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
