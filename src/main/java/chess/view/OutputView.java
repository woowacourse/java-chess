package chess.view;

import static chess.view.PieceView.BISHOP;
import static chess.view.PieceView.EMPTY;
import static chess.view.PieceView.KING;
import static chess.view.PieceView.KNIGHT;
import static chess.view.PieceView.PAWN;
import static chess.view.PieceView.QUEEN;
import static chess.view.PieceView.ROOK;

import chess.domain.Position;
import chess.domain.Score;
import chess.domain.Team;
import chess.domain.board.Board;
import chess.domain.piece.Bishop;
import chess.domain.piece.Empty;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class OutputView {

    private static final int MIN_RANK = 1;
    private static final int MAX_RANK = 8;
    private static final int MIN_FILE = 1;
    private static final int MAX_FILE = 8;

    public void printStartMessage() {
        System.out.println(
                "> 체스 게임을 시작합니다.\n"
                + "> 게임 시작 : start\n"
                + "> 게임 종료 : end\n"
                + "> 게임 이동 : move source위치 target위치 - 예. move b2 b3"
        );
    }

    public void printGameList(List<Integer> allGameId) {
        StringBuilder sb = new StringBuilder();
        System.out.print("게임 목록(아이디) : ");
        if (allGameId.isEmpty()) {
            System.out.println("진행중인 게임이 없습니다!");
            return;
        }
        for (Integer gameId : allGameId) {
            sb.append(gameId).append(", ");
        }
        System.out.println(sb);
    }

    public void printBoard(Board board) {
        Map<Position, Piece> chessBoard = board.getBoard();

        for (int rank = MAX_RANK; rank >= MIN_RANK; rank--) {
            iterateFile(chessBoard, rank);
        }
        System.out.println();
    }

    private void iterateFile(Map<Position, Piece> chessBoard, int rank) {
        for (int file = MIN_FILE; file <= MAX_FILE; file++) {
            printBoardUnit(chessBoard, rank, file);
        }
        System.out.println();
    }

    private void printBoardUnit(Map<Position, Piece> chessBoard, int rank, int file) {
        Piece piece = chessBoard.get(new Position(rank, file));
        printPiece(piece);
    }

    private void printPiece(Piece piece) {
        printIfKing(piece);
        printIfQueen(piece);
        printIfKnight(piece);
        printIfBishop(piece);
        printIfRook(piece);
        printIfPawn(piece);
        printIfEmpty(piece);
    }

    private void printIfKing(Piece piece) {
        if (piece.getClass() == King.class) {
            System.out.print(KING.getPieceView(piece.getTeam()));
        }
    }

    private void printIfQueen(Piece piece) {
        if (piece.getClass() == Queen.class) {
            System.out.print(QUEEN.getPieceView(piece.getTeam()));
        }
    }

    private void printIfKnight(Piece piece) {
        if (piece.getClass() == Knight.class) {
            System.out.print(KNIGHT.getPieceView(piece.getTeam()));
        }
    }

    private void printIfBishop(Piece piece) {
        if (piece.getClass() == Bishop.class) {
            System.out.print(BISHOP.getPieceView(piece.getTeam()));
        }
    }

    private void printIfRook(Piece piece) {
        if (piece.getClass() == Rook.class) {
            System.out.print(ROOK.getPieceView(piece.getTeam()));
        }
    }

    private void printIfPawn(Piece piece) {
        if (piece.getClass() == Pawn.class) {
            System.out.print(PAWN.getPieceView(piece.getTeam()));
        }
    }

    private void printIfEmpty(Piece piece) {
        if (piece.getClass() == Empty.class) {
            System.out.print(EMPTY.getPieceView(piece.getTeam()));
        }
    }

    public void printStatus(Map<Team, Score> scores, Optional<Team> winner) {
        System.out.println("[현재 점수]");
        System.out.println(Team.WHITE + "팀 : " + scores.get(Team.WHITE).getScore());
        System.out.println(Team.BLACK + "팀 : " + scores.get(Team.BLACK).getScore());
        printWinner(winner);
    }

    public void printWinner(Optional<Team> winner) {
        if (winner.isEmpty()) {
            System.out.println("무승부입니다.\n");
            return;
        }
        System.out.println("우승자는 " + winner.get() + "팀 입니다🎉\n");
    }

    public void printExceptionMessage(Exception exception) {
        System.out.println(exception.getMessage());
    }
}
