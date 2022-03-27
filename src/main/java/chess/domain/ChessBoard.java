package chess.domain;

import chess.domain.chessPiece.*;
import chess.domain.position.Direction;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;

import java.util.*;
import java.util.stream.Collectors;

public class ChessBoard {

    private Map<Position, ChessPiece> chessBoard;
    private Color currentTurn = Color.WHITE;
    private GameStatus gameStatus;

    public ChessBoard() {
        this.chessBoard = new HashMap<>();
        this.gameStatus = GameStatus.READY;
        init();
    }

    ChessBoard(Map<Position, ChessPiece> chessBoard) {
        this.chessBoard = chessBoard;
        this.gameStatus = GameStatus.READY;
    }

    private void init() {
        for (Color value : Color.values()) {
            List<ChessPiece> pieces = List.of(
                    new King(value),
                    new Queen(value),
                    new Pawn(value),
                    new Rook(value),
                    new Bishop(value),
                    new Knight(value));
            addPiece(pieces);
        }
    }

    private void addPiece(List<ChessPiece> pieces) {
        for (ChessPiece chessPiece : pieces) {
            initByPiece(chessPiece);
        }
    }

    private void initByPiece(ChessPiece chessPiece) {
        if (chessPiece.isBlack()) {
            addBlackPiece(chessPiece);
            return;
        }
        addWhitePiece(chessPiece);
    }

    private void addWhitePiece(ChessPiece chessPiece) {
        for (Position position : chessPiece.getInitWhitePosition()) {
            chessBoard.put(position, chessPiece);
        }
    }

    private void addBlackPiece(ChessPiece chessPiece) {
        for (Position position : chessPiece.getInitBlackPosition()) {
            chessBoard.put(position, chessPiece);
        }
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
        ChessPiece piece = chessBoard.get(position);
        if (piece == null) {
            return Optional.empty();
        }

        return Optional.of(piece);
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
        return Arrays.stream(Color.values())
                .collect(Collectors.toMap(
                        color -> color,
                        this::getSum,
                        (exist, replacement) -> exist));
    }

    private double getSum(Color color) {
        double sumExceptPawnScore = chessBoard.values().stream()
                .filter((chessPiece) -> chessPiece.isSameColor(color))
                .filter((chessPiece) -> !(chessPiece instanceof Pawn))
                .mapToDouble(ChessPiece::getValue)
                .sum();

        return sumExceptPawnScore + getSumPawn(color);
    }

    private double getSumPawn(Color color) {
        double totalPawnScore = 0;
        for (Rank rank : Rank.values()) {
            double pawnCount = countSameRankPawn(color, rank);
            totalPawnScore += sumPawnScore(pawnCount);
        }
        return totalPawnScore;
    }

    private double countSameRankPawn(Color color, Rank rank) {
        return Arrays.stream(File.values())
                .map((file) -> findPiece(new Position(rank, file)))
                .filter((possiblePiece) -> isMyPawn(color, possiblePiece))
                .count();
    }

    private boolean isMyPawn(Color color, Optional<ChessPiece> possiblePiece) {
        if (possiblePiece.isEmpty()) {
            return false;
        }

        ChessPiece chessPiece = possiblePiece.get();
        if (!(chessPiece instanceof Pawn) || !chessPiece.isSameColor(color)) {
            return false;
        }

        return true;
    }

    private double sumPawnScore(double pawnCount) {
        if (isOnlyPawnInFile(pawnCount)) {
            return 1;
        }
        return pawnCount * 0.5;
    }

    private boolean isOnlyPawnInFile(double pawnCount) {
        return pawnCount == 1;
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
