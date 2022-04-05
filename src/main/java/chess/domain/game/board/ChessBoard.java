package chess.domain.game.board;

import chess.domain.game.Color;
import chess.domain.game.GameStatus;
import chess.domain.game.Score;
import chess.domain.piece.ChessPiece;
import chess.domain.piece.Pawn;
import chess.domain.position.Direction;
import chess.domain.position.Position;

import java.util.Map;
import java.util.Optional;
import java.util.Stack;
import java.util.stream.Collectors;

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

        Stack<Position> routes = findPieceRoute(source, target, piece);

        while (!routes.isEmpty()) {
            checkHurdle(routes.pop());
        }
    }

    private Stack<Position> findPieceRoute(Position source, Position target, ChessPiece piece) {
        if(piece.isKnight()){
            return new Stack<>();
        }
        return makeRoute(source, target);
    }

    private Stack<Position> makeRoute(final Position from, Position to) {
           Stack<Position> routes = new Stack<>();
           Direction direction = to.findDirection(from);

           Position newFrom = new Position(from.getValue());

           while (!newFrom.equals(to)) {
               Position nextPosition = newFrom.toNextPosition(direction);
               routes.add(new Position(nextPosition.getValue()));
               newFrom = nextPosition;
           }

           routes.pop();

           return routes;
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
        if(piece.isPawn()){
            checkPawnStraightMove(source, target, piece);
        }
        movePiece(source, target, piece);
    }

    private void catchAndMove(Position source, Position target, ChessPiece piece) {
        if(piece.isPawn()){
            checkPawnCrossMove(source, target, piece);
        }
        movePiece(source, target, piece);
    }

    private void checkPawnStraightMove(Position source, Position target, ChessPiece piece) {
        Pawn pawn = (Pawn) piece;
        pawn.validateStraight(source,target);
    }

    private void checkPawnCrossMove(Position source, Position target, ChessPiece piece) {
        Pawn pawn = (Pawn) piece;
        pawn.validateCross(source,target);
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

        validatePawnCrossMove(possiblePiece);
        validateOtherColor(piece, possiblePiece);

        return true;
    }

    private void validatePawnCrossMove(Optional<ChessPiece> possiblePiece) {
        if (possiblePiece.isEmpty()) {
            throw new IllegalArgumentException("폰은 대각선에 상대 기물이 존재해야합니다");
        }
    }

    private void validateOtherColor(ChessPiece piece, Optional<ChessPiece> possiblePiece) {
        ChessPiece targetPiece = possiblePiece.get();
        if (targetPiece.isSameColorPiece(piece)) {
            throw new IllegalArgumentException("같은색 기물입니다.");
        }
    }

    public Map<Color, Double> calculateScore() {
        Score score = new Score(chessBoard);
        return score.calculateScore();
    }

    public Map<String, ChessPiece> convertToMap(){
        return chessBoard.entrySet()
                .stream()
                .collect(Collectors.toMap(m -> m.getKey().getValue(), m -> m.getValue() ));
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
