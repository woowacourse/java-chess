package chess.view;

import java.util.Map;
import java.util.function.BiConsumer;

import chess.controller.Command;
import chess.domain.board.LineNumber;
import chess.dto.BoardResponse;
import chess.dto.Response;

public class OutputView {

    private static final Map<Command, BiConsumer<OutputView, Response>> RESPONSE_PRINTER = Map.of(
        Command.START, (outputView, response) -> outputView.printBoard(response),
        Command.FINISH, (outputView, ignored) -> outputView.printEnd(),
        Command.MOVE, (outputView, response) -> outputView.printBoard(response),
        Command.STATUS, (outputView, response) -> outputView.printScore(response)
    );

    public void printIntroduction() {
        System.out.println("> 체스 게임을 시작합니다.\n"
            + "> 게임 시작 : start\n"
            + "> 게임 종료 : end\n"
            + "> 게임 이동 : move source위치 target위치 - 예. move b2 b3\n"
            + "> 점수 확인 : status");
    }

    public void printResponse(Command inputCommand, Response response) {
        BiConsumer<OutputView, Response> printer = findPrinter(inputCommand);
        printer.accept(this, response);
    }

    private BiConsumer<OutputView, Response> findPrinter(Command inputCommand) {
        if (!RESPONSE_PRINTER.containsKey(inputCommand)) {
            throw new IllegalArgumentException("");
        }
        return RESPONSE_PRINTER.get(inputCommand);
    }

    private void printBoard(Response response) {
        Map<String, String> information = response.getInformation();

        String board = toBoard(information);
        System.out.println(board);
        System.out.println(response.getMetaInformation() + "의 차례입니다.");
    }

    private String toBoard(Map<String, String> information) {
        StringBuilder builder = new StringBuilder();
        for (int verticalIndex = LineNumber.MAX; verticalIndex >= LineNumber.MIN; verticalIndex--) {
            builder.append(toLine(information, verticalIndex)).append("\n");
        }
        return builder.toString();
    }

    private String toLine(Map<String, String> information, int verticalIndex) {
        StringBuilder builder = new StringBuilder();
        for (int horizontalIndex = LineNumber.MIN; horizontalIndex <= LineNumber.MAX; horizontalIndex++) {
            builder.append(information.get(toKey(verticalIndex, horizontalIndex)));
        }
        return builder.toString();
    }

    private static String toKey(int verticalIndex, int horizontalIndex) {
        return String.valueOf(verticalIndex * BoardResponse.DECIMAL + horizontalIndex);
    }

    private void printScore(Response response) {
        Map<String, String> information = response.getInformation();
        information.entrySet()
            .forEach(this::printScorePerColor);
        System.out.println("현재 승부 결과는 " + response.getMetaInformation() + " 입니다.");
    }

    private void printScorePerColor(Map.Entry<String, String> entry) {
        String color = entry.getKey();
        String score = entry.getValue();
        System.out.println(color + "점수는 " + score + " 입니다.");
    }

    public void printException(RuntimeException e) {
        System.out.println(e.getMessage());
    }

    public void printEnd() {
        System.out.println("게임이 종료되었습니다.");
    }
}
