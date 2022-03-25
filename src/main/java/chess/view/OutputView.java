package chess.view;

import chess.domain.Abscissa;
import chess.domain.Board;
import chess.domain.Ordinate;
import chess.domain.Position;
import chess.domain.piece.Piece;
import java.util.Map;
import java.util.Optional;

public class OutputView {

    private static final String CHESS_GAME_INIT_MESSAGE = "> 체스 게임을 시작합니다.";
    private static final String EXAMPLE_START_MESSAGE = "> 게임 시작 : start";
    private static final String EXAMPLE_END_MESSAGE = "> 게임 종료 : end";
    private static final String EXAMPLE_MOVE_MESSAGE = "> 게임 이동 : move source위치 target위치 - 예. move b2 b3";
    private static final String NONE_PIECE = ".";

    public static void printGameInitMessage() {
        System.out.println(CHESS_GAME_INIT_MESSAGE);
        System.out.println(EXAMPLE_START_MESSAGE);
        System.out.println(EXAMPLE_END_MESSAGE);
        System.out.println(EXAMPLE_MOVE_MESSAGE);
    }

    public static void printInitialChessBoard(Board board) {
        for (Ordinate ordinate : Ordinate.values()) {
            printBoardRowLine(ordinate, board);
        }
    }

    public static void printBoardRowLine(Ordinate ordinate, Board board) {
        Map<Position, Piece> chessBoard = board.getBoard();
        for (Abscissa value : Abscissa.values()) {
            Optional<Piece> pieceOptional = Optional.ofNullable(
                chessBoard.get(Position.valueOf(value, ordinate)));
            String printFormat = pieceOptional.map(PieceMapper::from).orElse(NONE_PIECE);
            System.out.print(printFormat);
        }
        System.out.print(System.lineSeparator());
    }
}
