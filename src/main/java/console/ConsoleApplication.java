package console;

import console.controller.ConsoleController;

public class ConsoleApplication {

    public static void main(String[] args) {
        ConsoleController controller = new ConsoleController();
        controller.run();
    }
}
