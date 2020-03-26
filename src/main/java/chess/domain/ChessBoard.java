package chess.domain;

import chess.domain.chesspiece.Blank;
import chess.domain.chesspiece.ChessPiece;
import chess.generator.AllRouteGenerator;

import java.util.ArrayList;
import java.util.List;

public class ChessBoard {
    private final List<Row> board;

    public ChessBoard(List<Row> board) {
        this.board = new ArrayList<>(board);
    }

    public List<Row> getBoard() {
        return board;
    }

    public void move(Position startPosition, Position targetPosition) {
//        for (int i = 7; i >= 0; i--) {
//            for (int j = 0; j < 8; j++) {
//                System.out.print(board.get(i).get(j).getPosition() + " ");
//            }
//            System.out.println();
//        }

        ChessPiece chessPiece = getChessPiece(startPosition);

        Route canMoveRoute = findRoute(chessPiece, startPosition, targetPosition);

        if (!validateRoute(chessPiece, canMoveRoute, targetPosition)) {
            System.out.println("fail to move");
            System.out.println();
            return;
        }

        //move 동작
//        Row row = board.get(startPosition.getX() - 1);
        clearPosition(startPosition);
        setPosition(chessPiece, targetPosition);
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

    private boolean validateRoute(ChessPiece chessPiece, Route canMoveRoute, Position targetPosition) {
        //해당하는 루트가 없을 때
        if (canMoveRoute == null) {
            return false;
        }
        System.out.println(canMoveRoute.getRoute().toString());
        for (Position position : canMoveRoute.getRoute()) {
            //목적지 도달하여 break;
            if (position.equals(targetPosition)) {
                break;
            }
            //목적지로 가는 도중 말이 있을 경우
            if (!isBlank(position)) {
                System.out.println(position.toString());
                System.out.println("가는 길에 말이 있음");
                return false;
            }
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

    private boolean isBlank(Position position) {
        ChessPiece chessPiece = getChessPiece(position);
        return chessPiece instanceof Blank;
    }

    private ChessPiece getChessPiece(Position position) {
        return board.get(position.getX() - 1).get(position.getY() - 1);
    }

    public double getTotalScore(Team team) {
        double score = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                ChessPiece chessPiece = board.get(i).get(j);
                if (chessPiece.getTeam() == team) {
                    score += board.get(i).get(j).getScore();
                }
            }
        }
        return score;
    }
}
