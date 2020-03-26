package chess.domain;

import chess.domain.chesspiece.Blank;
import chess.domain.chesspiece.ChessPiece;
import chess.domain.chesspiece.Pawn;
import chess.generator.AllRouteGenerator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ChessBoard {
    private List<Row> board;

    public ChessBoard(List<Row> board) {
        this.board = new ArrayList<>(board);
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

        if (!validateRoute(chessPiece, canMoveRoute, startPosition, targetPosition)) {
            return;
        }

        clearPosition(startPosition);
        setPosition(chessPiece, targetPosition);
        reverseBoard();
    }

    private void clearPosition(Position startPosition) {
        Row row = board.get(startPosition.getX() - 1);
        row.modifyRow(startPosition.getY() - 1, new Blank(Team.BLANK));
        board.set(startPosition.getX() - 1, row);
    }

    private void setPosition(ChessPiece chessPiece, Position targetPosition) {
        Row row = board.get(targetPosition.getX() - 1);
        row.modifyRow(targetPosition.getY() - 1, chessPiece);
        board.set(targetPosition.getX() - 1, row);
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
            return false;
        }
        System.out.println(canMoveRoute.getRoute().toString());
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

    public double getTotalScore(Team team) {
        double score = 0;
        int cnt = 0;
        for (int i = 0; i < 8; i++) {
            	int pawnCnt = 0;
            for (int j = 0; j < 8; j++) {
                ChessPiece chessPiece = board.get(j).get(i);

                if (chessPiece.getTeam() == team) {
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

    public double sumTeamScore(Team team) {
        for (Row row : board) {
            List<ChessPiece> chessPieces = row.findByTeam(team);
            chessPieces.stream()
                .mapToDouble(chessPiece -> chessPiece.getScore())
                .sum();
        }
    }
}
