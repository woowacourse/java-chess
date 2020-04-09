package chess.domain.board;

import chess.domain.chesspiece.Blank;
import chess.domain.chesspiece.ChessPiece;
import chess.domain.chesspiece.Pawn;
import chess.domain.game.GameStatus;
import chess.domain.game.Team;
import chess.domain.move.MovingInfo;
import chess.domain.move.Position;
import chess.domain.move.Route;
import chess.generator.AllRouteGenerator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static chess.domain.chesspiece.ChessPieceInfo.KING;
import static chess.domain.game.Team.BLACK;

public class Board {
    private static final int DIAGONAL_GAP = 1;
    private static final int INDEX_CORRECTION_NUMBER = 1;
    public static final int MAX_INDEX = 7;
    public static final int MIN_INDEX = 0;

    private List<Row> board;
    private GameStatus gameStatus;

    Board(List<Row> board, GameStatus gameStatus) {
        this.board = new ArrayList<>(board);
        this.gameStatus = gameStatus;
    }

    public List<Row> getBoard() {
        return board;
    }

    public void move(MovingInfo movingInfo) {
        ChessPiece chessPiece = getChessPiece(movingInfo.getStartPosition());

        checkGameEnd();
        checkNowPlayingTeam(chessPiece);
        if (gameStatus.getNowPlayingTeam() == BLACK) {
            reverseMove(chessPiece, movingInfo);
            return;
        }
        moveOperation(chessPiece, movingInfo);
    }

    private void checkGameEnd() {
        if (gameStatus.isGameEnd()) {
            throw new IllegalArgumentException("King이 죽어서 게임이 종료되었습니다.");
        }
    }

    private void reverseMove(ChessPiece chessPiece, MovingInfo movingInfo) {
        movingInfo = MovingInfo.reverseMovingInfo(movingInfo);

        reverseBoard();
        try {
            moveOperation(chessPiece, movingInfo);
        } finally {
            reverseBoard();
        }
    }

    private void moveOperation(ChessPiece chessPiece, MovingInfo movingInfo) {
        Route candidateRoute = findRoute(chessPiece, movingInfo);

        validateRoute(chessPiece, candidateRoute, movingInfo);
        executeMove(chessPiece, movingInfo);
    }

    private void checkNowPlayingTeam(ChessPiece chessPiece) {
        Team nowPlayingTeam = gameStatus.getNowPlayingTeam();
        if (chessPiece.getTeam() != nowPlayingTeam) {
            throw new IllegalArgumentException(nowPlayingTeam.getTeamName() + " 차례입니다.");
        }
    }

    private void reverseBoard() {
        List<Row> reversedBoard = new ArrayList<>();

        for (int i = Board.MAX_INDEX; i >= Board.MIN_INDEX; i--) {
            Row reversedRow = board.get(i);
            Collections.reverse(reversedRow.getChessPieces());
            reversedBoard.add(reversedRow);
        }
        this.board = reversedBoard;
    }

    private Route findRoute(ChessPiece chessPiece, MovingInfo movingInfo) {
        Position startPosition = movingInfo.getStartPosition();
        Position targetPosition = movingInfo.getTargetPosition();
        List<Route> allRoute = AllRouteGenerator.generateAllRoute(chessPiece, startPosition);

        return allRoute.stream()
                .filter(o -> o.hasPosition(targetPosition))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("말이 해당 위치로 이동할 수 없습니다."));
    }

    private void validateRoute(ChessPiece chessPiece, Route candidateRoute, MovingInfo movingInfo) {
        validateRouteLocation(candidateRoute, movingInfo);
        validateRouteTarget(chessPiece, movingInfo);
    }

    private void validateRouteLocation(Route candidateRoute, MovingInfo movingInfo) {
        Position targetPosition = movingInfo.getTargetPosition();
        for (Position position : candidateRoute.getRoute()) {
            if (position.equals(targetPosition)) {
                break;
            }
            checkBlank(position);
        }
    }

    private void checkBlank(Position position) {
        if (!isBlank(position)) {
            throw new IllegalArgumentException("말 이동경로에 다른 말이 있습니다.");
        }
    }

    private void validateRouteTarget(ChessPiece chessPiece, MovingInfo movingInfo) {
        if (chessPiece instanceof Pawn) {
            checkPawnMove(movingInfo);
        }
        checkTargetTeam(chessPiece, movingInfo);
    }

    private void checkTargetTeam(ChessPiece chessPiece, MovingInfo movingInfo) {
        Position targetPosition = movingInfo.getTargetPosition();
        ChessPiece targetChessPiece = getChessPiece(targetPosition);

        if (targetChessPiece.isSameTeam(chessPiece.getTeam())) {
            throw new IllegalArgumentException("말의 도착점에 아군의 말이 있습니다.");
        }
    }

    private void checkPawnMove(MovingInfo movingInfo) {
        Position startPosition = movingInfo.getStartPosition();
        Position targetPosition = movingInfo.getTargetPosition();
        int dy = targetPosition.getY() - startPosition.getY();

        if (isDiagonalMovement(dy) && isBlank(targetPosition)) {
            throw new IllegalArgumentException("대각선으로는 공격할 때만 움직일 수 있습니다.");
        }
        if (!isDiagonalMovement(dy) && !isBlank(targetPosition)) {
            throw new IllegalArgumentException("상대의 말이 있어서 움직일 수 없습니다.");
        }
    }

    private boolean isDiagonalMovement(int dy) {
        return Math.abs(dy) == DIAGONAL_GAP;
    }

    private boolean isBlank(Position position) {
        ChessPiece chessPiece = getChessPiece(position);

        return chessPiece instanceof Blank;
    }

    private void executeMove(ChessPiece chessPiece, MovingInfo movingInfo) {
        updateGameEnd(movingInfo.getTargetPosition());
        clearPosition(movingInfo.getStartPosition());
        setPosition(chessPiece, movingInfo.getTargetPosition());
        gameStatus.changePlayingTeam();
        updateIfPawn(chessPiece);
    }

    private void clearPosition(Position startPosition) {
        int indexOfX = startPosition.getX() - INDEX_CORRECTION_NUMBER;
        int indexOfY = startPosition.getY() - INDEX_CORRECTION_NUMBER;
        Row row = board.get(indexOfX);

        row.modifyRow(indexOfY, new Blank());
        board.set(indexOfX, row);
    }

    public void setPosition(ChessPiece chessPiece, Position targetPosition) {
        int indexOfX = targetPosition.getX() - INDEX_CORRECTION_NUMBER;
        int indexOfY = targetPosition.getY() - INDEX_CORRECTION_NUMBER;
        Row row = board.get(indexOfX);

        row.modifyRow(indexOfY, chessPiece);
        board.set(indexOfX, row);
    }

    private void updateGameEnd(Position targetPosition) {
        int indexOfX = targetPosition.getX() - INDEX_CORRECTION_NUMBER;
        int indexOfY = targetPosition.getY() - INDEX_CORRECTION_NUMBER;
        Row row = board.get(indexOfX);
        System.out.println(targetPosition.getX() + " , " + targetPosition.getY());
        ChessPiece targetChessPiece = row.get(indexOfY);
        String chessPieceName = targetChessPiece.getName();
        String lowerCaseChessPieceName = chessPieceName.toLowerCase();

        if (lowerCaseChessPieceName.equals(KING.getName())) {
            gameStatus.updateGameEnd();
        }
    }

    private void updateIfPawn(ChessPiece chessPiece) {
        if (chessPiece instanceof Pawn) {
            ((Pawn) chessPiece).firstMoveComplete();
        }
    }

    private ChessPiece getChessPiece(Position position) {
        int indexOfX = position.getX() - INDEX_CORRECTION_NUMBER;
        int indexOfY = position.getY() - INDEX_CORRECTION_NUMBER;

        return board.get(indexOfX).get(indexOfY);
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }
}
