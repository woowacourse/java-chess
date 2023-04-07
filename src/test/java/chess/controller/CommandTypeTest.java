package chess.controller;

//@DisplayName("CommandType 클래스")
//public class CommandTypeTest {
//
//    @Nested
//    @DisplayName("from")
//    class from {
//        @Nested
//        @DisplayName("사용자 입력으로")
//        class given_code {
//            @ParameterizedTest(name = "{0}가 주어지면 객체 {1}을 반환한다")
//            @CsvSource({"start,START", "move,MOVE", "status,STATUS", "end,END"})
//            void it_returns_gameStatus(String code, CommandType gameStatus) {
//                assertThat(CommandType.from(code)).isEqualTo(gameStatus);
//            }
//
//            @ParameterizedTest(name = "{0}가 주어지면 예외를 던진다")
//            @ValueSource(strings = {"Move", "eNd", "e nd", "  "})
//            void it_throws_exception(String code) {
//                assertThatThrownBy(() -> CommandType.from(code))
//                        .isInstanceOf(IllegalArgumentException.class)
//                        .hasMessage("존재하지 않는 명령어입니다");
//            }
//        }
//    }
//}
