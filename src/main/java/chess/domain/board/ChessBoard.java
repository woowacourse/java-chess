package chess.domain.board;

import chess.domain.chesspiece.Blank;
import chess.domain.chesspiece.ChessPiece;
import chess.domain.chesspiece.Pawn;
import chess.domain.game.Score;
import chess.domain.game.Team;
import chess.domain.move.MovingInfo;
import chess.domain.move.Position;
import chess.domain.move.Route;
import chess.generator.AllRouteGenerator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static chess.domain.chesspiece.ChessPieceInfo.KING;

public class ChessBoard {
    private static final Team INIT_TEAM = Team.WHITE;

    private List<Row> board;
    private Team nowPlayingTeam;
    private boolean isGameEnd;

    public ChessBoard(List<Row> board) {
        this.board = new ArrayList<>(board);
        this.nowPlayingTeam = INIT_TEAM;
        this.isGameEnd = false;
    }

    public List<Row> getBoard() {
        return board;
    }

    public void move(MovingInfo movingInfo) {
        ChessPiece chessPiece = getChessPiece(movingInfo.getStartPosition());

        checkNowPlayingTeam(chessPiece);
        if (nowPlayingTeam == Team.BLACK) {
            reverseMove(chessPiece, movingInfo);
            return;
        }
        moveOperation(chessPiece, movingInfo);
    }

    private void reverseMove(ChessPiece chessPiece, MovingInfo movingInfo) {
        movingInfo = reverseMovingInfo(movingInfo);
        reverseBoard();
        moveOperation(chessPiece, movingInfo);
        reverseBoard();
    }

    private void moveOperation(ChessPiece chessPiece, MovingInfo movingInfo) {
        Route canMoveRoute = findRoute(chessPiece, movingInfo);

        validateRoute(chessPiece, canMoveRoute, movingInfo);
        executeMove(chessPiece, movingInfo);
    }

    private void checkNowPlayingTeam(ChessPiece chessPiece) {
        if (chessPiece.getTeam() != nowPlayingTeam) {
            throw new IllegalArgumentException();
        }
    }

    private MovingInfo reverseMovingInfo(MovingInfo movingInfo) {
        MovingInfo reverseMovingInfo;
        Position startPosition = movingInfo.getStartPosition();
        Position targetPosition = movingInfo.getTargetPosition();
        Position ReverseStartPosition = Position.of(reverseCoordinate(startPosition.getX()), reverseCoordinate(startPosition.getY()));
        Position ReverseTargetPosition = Position.of(reverseCoordinate(targetPosition.getX()), reverseCoordinate(targetPosition.getY()));

        reverseMovingInfo = new MovingInfo(ReverseStartPosition, ReverseTargetPosition);
        return reverseMovingInfo;
    }

    private int reverseCoordinate(int coordinate) {
        return ((8 + 1) - coordinate);
    }

    private void reverseBoard() {
        List<Row> reversedBoard = new ArrayList<>();

        for (int i = 7; i >= 0; i--) {
            Row reversedRow = board.get(i);
            Collections.reverse(reversedRow.getChessPieces());
            reversedBoard.add(reversedRow);
        }
        this.board = reversedBoard;
    }

    private Route findRoute(ChessPiece chessPiece, MovingInfo movingInfo) {
        Position startPosition = movingInfo.getStartPosition();
        Position targetPosition = movingInfo.getTargetPosition();
        List<Route> allRoute = AllRouteGenerator.getAllRoute(chessPiece, startPosition);

        return allRoute.stream()
                .filter(o -> o.hasPosition(targetPosition))
                .findFirst()
                .orElse(null);
    }

    private void validateRoute(ChessPiece chessPiece, Route canMoveRoute, MovingInfo movingInfo) {
        validateRouteNull(canMoveRoute);
        validateRouteLocation(canMoveRoute, movingInfo);
        validateRouteTarget(chessPiece, movingInfo);
    }

    private void validateRouteNull(Route canMoveRoute) {
        if (canMoveRoute == null) {
            throw new IllegalArgumentException();
        }
    }

    private void validateRouteLocation(Route canMoveRoute, MovingInfo movingInfo) {
        for (Position position : canMoveRoute.getRoute()) {
            checkBlank(position, movingInfo);
        }
    }

    private void checkBlank(Position position, MovingInfo movingInfo) {
        Position targetPosition = movingInfo.getTargetPosition();
        if (!position.equals(targetPosition) && !isBlank(position)) {
            throw new IllegalArgumentException();
        }
    }

    private void validateRouteTarget(ChessPiece chessPiece, MovingInfo movingInfo) {
        if (chessPiece instanceof Pawn) {
            checkPawnMove(movingInfo);
        }
        checkTargetTeam(chessPiece, movingInfo);
    }

    private void checkTargetTeam(ChessPiece chessPiece, MovingInfo movingInfo) {
        if (getChessPiece(movingInfo.getTargetPosition()).isSameTeam(chessPiece.getTeam())) {
            throw new IllegalArgumentException();
        }
    }

    private void checkPawnMove(MovingInfo movingInfo) {
        Position startPosition = movingInfo.getStartPosition();
        Position targetPosition = movingInfo.getTargetPosition();
        int dy = targetPosition.getY() - startPosition.getY();

        if (Math.abs(dy) == 1 && isBlank(targetPosition)) {
            throw new IllegalArgumentException();
        }
        if (Math.abs(dy) == 0 && !isBlank(targetPosition)) {
            throw new IllegalArgumentException();
        }
    }

    private boolean isBlank(Position position) {
        ChessPiece chessPiece = getChessPiece(position);

        return chessPiece instanceof Blank;
    }

    private void executeMove(ChessPiece chessPiece, MovingInfo movingInfo) {
        clearPosition(movingInfo.getStartPosition());
        setPosition(chessPiece, movingInfo.getTargetPosition());
        changePlayingTeam();
        updateIfPawn(chessPiece);
    }

    private void clearPosition(Position startPosition) {
        Row row = board.get(startPosition.getX() - 1);

        row.modifyRow(startPosition.getY() - 1, new Blank(Team.BLANK));
        board.set(startPosition.getX() - 1, row);
    }

    private void setPosition(ChessPiece chessPiece, Position targetPosition) {
        Row row = board.get(targetPosition.getX() - 1);

        checkGameEnd(row, targetPosition);
        row.modifyRow(targetPosition.getY() - 1, chessPiece);
        board.set(targetPosition.getX() - 1, row);
    }

    private void checkGameEnd(Row row, Position targetPosition) {
        ChessPiece targetChessPiece = row.get(targetPosition.getY() - 1);
        String chessPieceName = targetChessPiece.getName();
        String lowerCaseChessPieceName = chessPieceName.toLowerCase();

        if (lowerCaseChessPieceName.equals(KING.getName())) {
            isGameEnd = true;
        }
    }

    private void changePlayingTeam() {
        this.nowPlayingTeam = Team.getOpponentTeam(this.nowPlayingTeam);
    }

    private void updateIfPawn(ChessPiece chessPiece) {
        if (chessPiece instanceof Pawn) {
            ((Pawn) chessPiece).firstMoveComplete();
        }
    }

    public boolean isGameEnd() {
        return isGameEnd;
    }

    public double getTotalScore() {
        Score score = Score.of(0);

        for (int j = 0; j < 8; j++) {
            score = getColumnScore(score, j);
        }
        return score.getScore();
    }

    private Score getColumnScore(Score score, int j) {
        int pawnCount = 0;

        for (int i = 0; i < 8; i++) {
            ChessPiece chessPiece = board.get(i).get(j);

            pawnCount = getColumnPawnCount(chessPiece);
            score = addIfSameTeam(score, chessPiece);
        }
        score = subtractSameColumnPawnScore(score, pawnCount);
        return score;
    }

    private int getColumnPawnCount(ChessPiece chessPiece) {
        int pawnCount = 0;
        if (chessPiece.getTeam() == nowPlayingTeam && chessPiece instanceof Pawn) {
            pawnCount++;
        }
        return pawnCount;
    }

    private Score addIfSameTeam(Score score, ChessPiece chessPiece) {
        if (chessPiece.getTeam() == nowPlayingTeam) {
            return score.add(chessPiece.getPoint());
        }
        return score;
    }

    private Score subtractSameColumnPawnScore(Score score, int pawnCount) {
        if (pawnCount >= 2) {
            return score.subtract(pawnCount * 0.5);
        }
        return score;
    }

    private ChessPiece getChessPiece(Position position) {
        return board.get(position.getX() - 1).get(position.getY() - 1);
    }

    public Team getNowPlayingTeam() {
        return nowPlayingTeam;
    }
}
