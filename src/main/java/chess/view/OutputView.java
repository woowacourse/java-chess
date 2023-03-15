package chess.view;

import chess.dto.ChessBoardDto;
import chess.dto.PieceDto;

import java.util.List;
import java.util.stream.Collectors;

public class OutputView {

    public void printInitGame() {
        System.out.println("체스 게임을 시작합니다.");
        System.out.println("게임 시작은 start, 종료는 end 명령을 입력하세요.");
    }

    public void printChessBoard(final ChessBoardDto chessBoardDto) {
        List<PieceDto> pieceDtos = chessBoardDto.getPieceDtos();

        for (int i = 0; i < 64; i += 8) {
            List<String> origin = pieceDtos.stream()
                    .map(PieceDto::getView)
                    .collect(Collectors.toList());
            List<String> strings = origin.subList(i, Math.min(i + 8, origin.size()));
            strings.forEach(System.out::print);
            System.out.println();
        }
    }
}
