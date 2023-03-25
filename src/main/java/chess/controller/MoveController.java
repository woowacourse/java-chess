package chess.controller;

import chess.dto.MoveHistory;
import chess.service.ChessGameService;
import chess.view.OutputView;
import java.util.List;

public class MoveController implements Controller {
    private static final int SOURCE_INDEX = 0;
    private static final int TARGET_INDEX = 1;

    private final List<String> parameters;

    public MoveController(List<String> parameters) {
        this.parameters = parameters;
    }

    @Override
    // 이 부분에서 세 개의 파라미터 chessGameService, outputView, chessRequest를 가진 메소드로 interface의 메소드를 수정하고
    // ChessRequest를 넘겨 chessRequest.getSourcePosition() 하는 방식은 이상한가요?
    // source position의 인덱스가 0인 것을 view에서 결정하는 방법은 이것밖에 떠오르지 않습니다...
    // 이렇게 되면 chessRequest는 모든 컨트롤러가 받지만, getSourcePosition()이라는 메소드는 이 부분에서 밖에 쓰이지 않기 때문에 고민입니다..
    public void execute(ChessGameService chessGameService, OutputView outputView) {
        if (chessGameService.isNotStart()) {
            throw new IllegalArgumentException("start를 해주세요");
        }
        MoveHistory moveHistory = new MoveHistory(parameters.get(SOURCE_INDEX), parameters.get(TARGET_INDEX));
        chessGameService.move(moveHistory);
        outputView.printBoard(chessGameService.getGameResult());
    }
}
