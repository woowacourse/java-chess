package chess.domain;

import chess.domain.chesspiece.Blank;
import chess.domain.chesspiece.ChessPiece;
import chess.domain.chesspiece.Pawn;
import chess.generator.AllRouteGenerator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
//        for(int i=0;i<8;i++){
//            for(int j=0;j<8;j++){
//                System.out.print(board.get(i).get(j).getName());
//            }
//            System.out.println();
//        }
        ChessPiece chessPiece = getChessPiece(startPosition);

        Route canMoveRoute = findRoute(chessPiece, startPosition, targetPosition);

        if (!validateRoute(chessPiece, canMoveRoute, startPosition, targetPosition)) {
            System.out.println("fail to move");
            System.out.println();
            return;
        }

        //move 동작
//        Row row = board.get(startPosition.getX() - 1);
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
        //해당하는 루트가 없을 때
        if (canMoveRoute == null) {
            System.out.println("can't find route");
            return false;
        }
        System.out.println(canMoveRoute.getRoute().toString());
        for (Position position : canMoveRoute.getRoute()) {
            //목적지 도달하여 break;
            if (position.equals(targetPosition)) {
                if (!checkPawnMove(chessPiece, lastPosition, targetPosition)) {
                    return false;
                }
                break;
            }
            //목적지로 가는 도중 말이 있을 경우
            if (!isBlank(position)) {
                System.out.println(position.toString());
                System.out.println("가는 길에 말이 있음");
                return false;
            }
            lastPosition = position;
        }
        //목적지의 말이 같은 팀일 경우
        if (getChessPiece(targetPosition).isSameTeam(chessPiece.getTeam())) {
            System.out.println("같은 팀");
            return false;
        }
        //이동 성공
        System.out.println("\nmove success\n");
        return true;
    }

    private boolean checkPawnMove(ChessPiece chessPiece, Position lastPosition, Position targetPosition) {
        if (chessPiece instanceof Pawn && lastPosition != null) {
            int dy = targetPosition.getY() - lastPosition.getY();
            if (Math.abs(dy) == 1) {
                System.out.println("aa");
                //대각선 목적지에 적이 있으면 이동 가능 return true
                return !isBlank(targetPosition);
            }
            if (Math.abs(dy) == 0) {
                System.out.println("bb");
                //정면 목적지에 적이 없으면 이동 가능 return true
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
}
