package chessgame.view;

import chessgame.dto.ChessBoardDto;

public class OutputView {
    private OutputView() {
        throw new UnsupportedOperationException("생성할 수 없습니다.");
    }


    public static void printCommandOptions() {
        System.out.println("> 체스 게임을 시작합니다.");
        System.out.println("> 게임 시작 : start");
        System.out.println("> 게임 종료 : end");
        System.out.print("> 게임 이동 : move source위치 target위치 - 예. move b2 b3");
    }

    public static void printChessBoard(final ChessBoardDto chessBoardDto) {
        for (var vertical = ChessBoardDto.VERTICAL_START_INDEX; vertical >= 0; vertical--) {
            printHorizontalLine(chessBoardDto, vertical);
        }
    }

    private static void printHorizontalLine(final ChessBoardDto dto, int vertical) {
        final var sb = new StringBuilder();
        for (var horizontal = 0; horizontal < ChessBoardDto.HORIZONTAL_END_INDEX; horizontal++) {
            sb.append(dto.findByPointIndex(horizontal, vertical));
        }
        System.out.println(sb);
    }

    public static void printExceptionMessage(String message) {
        System.out.println("[ERROR] " + message);
    }
}
