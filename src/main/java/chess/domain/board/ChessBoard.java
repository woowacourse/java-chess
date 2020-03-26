package chess.domain.board;

import chess.domain.team.Team;
import chess.domain.chesspiece.Blank;
import chess.domain.chesspiece.ChessPiece;
import chess.domain.chesspiece.Pawn;
import chess.generator.AllRouteGenerator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ChessBoard {
    private static final Team INIT_TEAM = Team.WHITE;
    private static final String KING = "k";

    private List<Row> board;
    private Team nowPlayingTeam;
    private boolean isGameEnd;

    public ChessBoard(List<Row> board) {
        this.board = new ArrayList<>(board);
        this.nowPlayingTeam = INIT_TEAM;
        this.isGameEnd = false;
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

    public List<Row> getBoard() {
        return board;
    }

    public void move(Position startPosition, Position targetPosition) {
        ChessPiece chessPiece = getChessPiece(startPosition);
        Route canMoveRoute = findRoute(chessPiece, startPosition, targetPosition);

        validateMove(chessPiece, canMoveRoute, startPosition, targetPosition);
        executeMove(startPosition,chessPiece,targetPosition);
    }

    private void validateMove(ChessPiece chessPiece, Route canMoveRoute, Position startPosition, Position targetPosition) {
        if (!validateRoute(chessPiece, canMoveRoute, startPosition, targetPosition)) {
            System.out.println("fail to move");
            System.out.println();
            return;
        }
    }

    private void executeMove(Position startPosition, ChessPiece chessPiece, Position targetPosition) {
        clearPosition(startPosition);
        setPosition(chessPiece, targetPosition);
        toggleNowPlayingTeam();
        reverseBoard();
    }

    private void toggleNowPlayingTeam() {
        this.nowPlayingTeam = Team.getOpponentTeam(this.nowPlayingTeam);
    }

    private void clearPosition(Position startPosition) {
        Row row = board.get(startPosition.getX() - 1);

        row.modifyRow(startPosition.getY() - 1, new Blank(Team.BLANK));
        board.set(startPosition.getX() - 1, row);
    }

    private void setPosition(ChessPiece chessPiece, Position targetPosition) {
        Row row = board.get(targetPosition.getX() - 1);

        checkGameEnd(row,targetPosition);
        row.modifyRow(targetPosition.getY() - 1, chessPiece);
        board.set(targetPosition.getX() - 1, row);
    }

    private void checkGameEnd(Row row, Position targetPosition) {
        ChessPiece targetChessPiece =row.get(targetPosition.getY() - 1);
        String chessPieceName = targetChessPiece.getName();
        String lowerCaseChessPieceName = chessPieceName.toLowerCase();

        if(lowerCaseChessPieceName.equals(KING)){
            isGameEnd = true;
        }
    }

    private Route findRoute(ChessPiece chessPiece, Position startPosition, Position targetPosition) {
        List<Route> allRoute = AllRouteGenerator.getAllRoute(chessPiece, startPosition);
        for (Route route : allRoute) {
            if (route.hasPosition(targetPosition)) {
                return route;
            }
        }
        return null;
    }

    private boolean validateRoute(ChessPiece chessPiece, Route canMoveRoute, Position startPosition, Position targetPosition) {
        Position lastPosition = startPosition;
        if (canMoveRoute == null) {
            System.out.println("can't find route");
            return false;
        }
        for (Position position : canMoveRoute.getRoute()) {
            if (position.equals(targetPosition)) {
                if (!checkPawnMove(chessPiece, lastPosition, targetPosition)) {
                    return false;
                }
                break;
            }
            if (!isBlank(position)) {
                return false;
            }
            lastPosition = position;
        }
        if (getChessPiece(targetPosition).isSameTeam(chessPiece.getTeam())) {
            return false;
        }
        return true;
    }

    private boolean checkPawnMove(ChessPiece chessPiece, Position lastPosition, Position targetPosition) {
        if (chessPiece instanceof Pawn && lastPosition != null) {
            int dy = targetPosition.getY() - lastPosition.getY();
            if (Math.abs(dy) == 1) {
                return !isBlank(targetPosition);
            }
            if (Math.abs(dy) == 0) {
                return isBlank(targetPosition);
            }
        }
        return true;
    }

    private boolean isBlank(Position position) {
        ChessPiece chessPiece = getChessPiece(position);
        return chessPiece instanceof Blank;
    }

    private ChessPiece getChessPiece(Position position) {
        return board.get(position.getX() - 1).get(position.getY() - 1);
    }

    public double getTotalScore() {
        double score = 0;
        int cnt = 0;
        for (int i = 0; i < 8; i++) {
            	int pawnCnt = 0;
            for (int j = 0; j < 8; j++) {
                ChessPiece chessPiece = board.get(j).get(i);

                if (chessPiece.getTeam() == nowPlayingTeam) {
					if (chessPiece.getClass() == Pawn.class) {
						pawnCnt++;
					}
                    score += board.get(j).get(i).getScore();
                }
            }
            if (pawnCnt >= 2) {
            	cnt += pawnCnt;
			}
        }
        return score - cnt * 0.5;
    }

    public boolean isGameEnd() {
        return isGameEnd;
    }

    public Team getNowPlayingTeam() {
        return nowPlayingTeam;
    }
}
