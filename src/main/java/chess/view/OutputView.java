package chess.view;

import chess.domain.game.Position;
import chess.domain.piece.*;
import chess.domain.userAccess.room.Room;

import java.util.List;
import java.util.Map;

import static chess.view.PieceView.*;

public class OutputView {

    private static final int MIN_RANK = 1;
    private static final int MAX_RANK = 8;
    private static final int MIN_FILE = 1;
    private static final int MAX_FILE = 8;
    private static final String EMPTY_PIECE = ".";
    private static final String BLACK_TEAM = "블랙팀";
    private static final String WHITE_TEAM = "화이트팀";
    private static final String SCORE_DELIMITER = ": ";

    public void printStartMessage() {
        System.out.println("> 체스 게임을 시작합니다.\n"
                + "> 게임 시작 : start\n"
                + "> 게임 종료 : end\n"
                + "> 게임 이동 : move source위치 target위치 - 예. move b2 b3");
    }

    public void printBoard(Map<Position, Piece> board) {
        for (int rank = MAX_RANK; rank >= MIN_RANK; rank--) {
            iterateFile(board, rank);
            System.out.println();
        }
        System.out.println();
    }

    private void iterateFile(Map<Position, Piece> board, int rank) {
        for (int file = MIN_FILE; file <= MAX_FILE; file++) {
            printBoardUnit(board, rank, file);
        }
    }

    private void printBoardUnit(Map<Position, Piece> board, int rank, int file) {
        if (!board.containsKey(new Position(rank, file))) {
            System.out.print(EMPTY_PIECE);
        }
        if (board.containsKey(new Position(rank, file))) {
            Piece piece = board.get(new Position(rank, file));
            printPiece(piece);
        }
    }

    private void printPiece(Piece piece) {
        printIfKing(piece);
        printIfQueen(piece);
        printIfKnight(piece);
        printIfBishop(piece);
        printIfRook(piece);
        printIfPawn(piece);
    }

    private void printIfKing(Piece piece) {
        if (piece.getClass() == King.class) {
            System.out.print(KING.getPieceView(piece.team()));
        }
    }

    private void printIfQueen(Piece piece) {
        if (piece.getClass() == Queen.class) {
            System.out.print(QUEEN.getPieceView(piece.team()));
        }
    }

    private void printIfKnight(Piece piece) {
        if (piece.getClass() == Knight.class) {
            System.out.print(KNIGHT.getPieceView(piece.team()));
        }
    }

    private void printIfBishop(Piece piece) {
        if (piece.getClass() == Bishop.class) {
            System.out.print(BISHOP.getPieceView(piece.team()));
        }
    }

    private void printIfRook(Piece piece) {
        if (piece.getClass() == Rook.class) {
            System.out.print(ROOK.getPieceView(piece.team()));
        }
    }

    private void printIfPawn(Piece piece) {
        if (piece.getClass() == Pawn.class) {
            System.out.print(PAWN.getPieceView(piece.team()));
        }
    }

    public void printInputStatusMessage() {
        System.out.println("게임이 끝났습니다.\n" + "점수와 결과를 보고싶으면 status를 입력해주세요. 보고싶지 않으면 end를 입력해주세요.");
    }

    public void printStatus(double blackScore, double whiteScore) {
        printBlackScore(blackScore);
        printWhiteScore(whiteScore);
        printWhoIsWin(blackScore, whiteScore);
    }

    private void printBlackScore(double score) {
        System.out.println(BLACK_TEAM + SCORE_DELIMITER + score + "점");
    }

    private void printWhiteScore(double score) {
        System.out.println(WHITE_TEAM + SCORE_DELIMITER + score + "점");
    }

    private void printWhoIsWin(double blackScore, double whiteScore) {
        if (Double.compare(blackScore, whiteScore) == 0) {
            System.out.println("비겼습니다.");
            return;
        }
        if (Double.compare(blackScore, whiteScore) > 0) {
            System.out.println("블랙팀이 이겼습니다.");
            return;
        }
        System.out.println("화이트팀이 이겼습니다.");
    }

    public void printExceptionMessage(Exception exception) {
        System.out.println(exception.getMessage());
        System.out.println();
    }

    public void printInputIdMessage() {
        System.out.println("아이디를 입력해주세요.");
    }

    public void printMakeNewRoomMessage() {
        System.out.println("새로운 게임을 만듭니다.");
    }

    public void printPlaySavedRoomMessage() {
        System.out.println("저장되어 있던 게임을 실행합니다.");
    }

    public void printSavedRooms(List<Room> rooms) {
        for (Room room : rooms) {
            System.out.println(room.roomId() + "번 방");
        }
    }

    public void printSelectRoomMessage() {
        System.out.println("재시작하려면 게임 방의 숫자를 입력해주세요.\n" + "새로운 게임을 만들고 싶으시면 0을 입력해주세요.");
    }

    public void printWantSaveGame() {
        System.out.println("게임을 저장하시려면 y 아니면 n을 입력해주세요.");
    }
}
