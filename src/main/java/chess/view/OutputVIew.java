package chess.view;


import chess.domain.*;
import chess.domain.coordinate.ChessCoordinate;
import chess.domain.coordinate.ChessXCoordinate;
import chess.domain.coordinate.ChessYCoordinate;

import java.util.List;
import java.util.Map;

public class OutputVIew {

    public static void printInputGuide() {
        System.out.println("체스 게임을 시작합니다.");
        System.out.println("게임 시작 : start");
        System.out.println("게임 종료 : end");
        System.out.println("게임 이동 : move source위치 target위치 - 예. move b2 b3");
    }

    public static void printBoardState(Map<ChessCoordinate, PieceType> board) {
        List<ChessXCoordinate> xAxis = ChessXCoordinate.allAscendingCoordinates();
        List<ChessYCoordinate> yAxis = ChessYCoordinate.allAscendingCoordinates();
        yAxis.forEach(y -> {
            xAxis.forEach(x -> System.out.print(getStateToString(board.get(ChessCoordinate.valueOf(x, y)))));
            System.out.println();
        });
    }

    private static String getStateToString(PieceType pieceType) {
        if (pieceType == PieceType.NONE) {
            return ".";
        }
        if (pieceType.getTeam() == Team.BLACK) {
            return getBlackStateToString(pieceType);
        }
        if (pieceType.getTeam() == Team.WHITE) {
            return getWhiteStateToString(pieceType);
        }
        throw new IllegalArgumentException("알 수 없는 말 유형입니다");
    }

    private static String getWhiteStateToString(PieceType pieceType) {
        if (pieceType == PieceType.ROOK_WHITE) {
            return "r";
        }
        if (pieceType == PieceType.KNIGHT_WHITE) {
            return "n";
        }
        if (pieceType == PieceType.BISHOP_WHITE) {
            return "b";
        }
        if (pieceType == PieceType.QUEEN_WHITE) {
            return "q";
        }
        if (pieceType == PieceType.KING_WHITE) {
            return "k";
        }
        if (pieceType == PieceType.PAWN_WHITE) {
            return "p";
        }
        throw new IllegalArgumentException("알 수 없는 말 유형입니다");
    }

    private static String getBlackStateToString(PieceType pieceType) {
        if (pieceType == PieceType.ROOK_BLACK) {
            return "R";
        }
        if (pieceType == PieceType.KNIGHT_BLACK) {
            return "N";
        }
        if (pieceType == PieceType.BISHOP_BLACK) {
            return "B";
        }
        if (pieceType == PieceType.QUEEN_BLACK) {
            return "Q";
        }
        if (pieceType == PieceType.KING_BLACK) {
            return "K";
        }
        if (pieceType == PieceType.PAWN_BLACK) {
            return "P";
        }
        throw new IllegalArgumentException("알 수 없는 말 유형입니다");
    }

    public static void printError(String message) {
        System.out.println(message);
    }

    public static void printScore(double white, double black) {
        System.out.println("W " + white + " : B " + black);
    }
}
