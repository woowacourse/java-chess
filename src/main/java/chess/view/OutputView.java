package chess.view;

import chess.domain.board.coordinate.Column;
import chess.domain.board.coordinate.Coordinate;
import chess.domain.board.coordinate.Row;
import chess.domain.piece.Empty;
import chess.domain.piece.Piece;
import chess.domain.piece.Team;
import chess.dto.ChessBoardDto;
import chess.dto.ScoreDto;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {
    private static final String NEW_LINE = System.lineSeparator();
    private static final String EMPTY = "EMPTY";
    private static final String DRAW = "DRAW";
    
    private OutputView() {
        throw new IllegalStateException("인스턴스를 생성할 수 없는 객체입니다.");
    }

    public static void noticeGameStart(){
        println("> 체스 게임을 시작합니다.");
        println("> 게임 시작 : start");
        println("> 게임 로드 : load");
        println("> 게임 종료 : end");
        println("> 게임 이동 : move source위치 target위치 - 예. move b2 b3");
    }
    
    
    public static void noticeNewGame() {
        println("게임을 새로 시작합니다.");
    }
    
    public static void noticeLoadGame() {
        println("이어서 게임을 시작합니다.");
    }
    
    public static void printChessBoard(ChessBoardDto chessBoardDto) {
        Map<Coordinate, Piece> pieces = chessBoardDto.pieces();
        println(parseChessBoardToDisplay(pieces));
        println("abcdefgh\n");

    }
    
    private static String parseChessBoardToDisplay(Map<Coordinate, Piece> pieces) {
        return coordinates().stream()
                .sorted()
                .map(coordinate -> parseRowToDisplay(pieces, coordinate))
                .collect(Collectors.joining());
    }
    
    private static List<Coordinate> coordinates() {
        return Arrays.stream(Row.values())
                .map(OutputView::initLineCoordinates)
                .flatMap(Collection::stream)
                .collect(Collectors.toUnmodifiableList());
    }
    
    private static List<Coordinate> initLineCoordinates(Row row) {
        return Arrays.stream(Column.values())
                .map(column -> new Coordinate(column, row))
                .collect(Collectors.toUnmodifiableList());
    }
    
    private static String parseRowToDisplay(Map<Coordinate, Piece> pieces, Coordinate coordinate) {
        String pieceToDisplay = parsePieceToDisplay(pieces.getOrDefault(coordinate, Empty.getInstance()));
        if (coordinate.isLastColumn()) {
            return pieceToDisplay + " " + coordinate.row() + NEW_LINE;
        }
        
        return pieceToDisplay;
    }
    
    private static String parsePieceToDisplay(Piece piece) {
        String symbol = PieceSymbolConverter.convert(piece.pieceType());
        if (piece.isTeam(Team.BLACK)) {
            return symbol.toUpperCase();
        }

        return symbol;
    }
    
    public static void printErrorMessage(String message) {
        println("[ERROR]" + message + "\n");
    }
    
    public static void println(String message) {
        System.out.println(message);
    }
    
    public static void noticeIntermediateResult() {
        println("> 중간 결과");
    }
    
    public static void noticeFinalResult() {
        println("> 최종 결과");
    }
    
    public static void printScore(ScoreDto scoreDto) {
        System.out.printf("White Team Score : %.1f%n", scoreDto.whiteScore());
        System.out.printf("Black Team Score : %.1f%n", scoreDto.blackScore());
    }
    
    public static void printWinnerTeam(ScoreDto scoreDto) {
        System.out.printf("Winner Team : %s%n", parseWinnerTeam(scoreDto));
    }
    
    private static String parseWinnerTeam(ScoreDto scoreDto) {
        String winnerTeam = scoreDto.winnerTeam();
        if (winnerTeam.equals(EMPTY)) {
            return DRAW;
        }
        return winnerTeam;
    }
}
