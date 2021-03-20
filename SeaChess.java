package ConsoleSeaChess;


import java.util.*;

public class SeaChess {

    public static final String TEXT_RESET = "\u001B[0m";
    public static final String TEXT_RED = "\u001B[31m";
    public static final String TEXT_WHITE = "\u001B[37m";

    public static class Player {
        String name, symbol;
        int score;

        public void setName(String name) {
            this.name = name;
        }

        public void setScore(int score) {
            this.score = score;
        }

        public void setSymbol(String symbol) {
            this.symbol = symbol;
        }

        public String getName() {
            return name;
        }

        public String getSymbol() {
            return symbol;
        }

        public int getScore() {
            return score;
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        if (requestRulesAccept(scan).equalsIgnoreCase("No")) {
            System.out.println(TEXT_WHITE + "Goodbye!" + TEXT_RESET);
            return;
        }


        printEmptyLines(10);

        System.out.println("Enjoy the game!");
        printEmptyLines(2);

        String gameMode = requestGameMode(scan);
        Player secondPlayer;

        String[][] board = new String[3][3];
        board[0][0] = "1";
        board[0][1] = "2";
        board[0][2] = "3";
        board[1][0] = "4";
        board[1][1] = "5";
        board[1][2] = "6";
        board[2][0] = "7";
        board[2][1] = "8";
        board[2][2] = "9";

        Player firstPlayer = requestFirstPlayerNameAndSymbols(scan);
        if (firstPlayer.getSymbol().equals("X")) {
            secondPlayer = requestSecondPlayerName(scan, "O", firstPlayer.getName());
        } else {
            secondPlayer = requestSecondPlayerName(scan, "X", firstPlayer.getName());
        }

        if (gameMode.equals("1")) {

            while (searchForFreeBoardNumber(board)) {
                printPlayersNamesAndSymbols(firstPlayer, secondPlayer);
                printBoard(board, "non final");
                putInputInTheBoard(requestPlayerInput(scan, firstPlayer, board), firstPlayer.symbol, board);
                if (checkForLineOrDiagonal(board)) {
                    printPlayersNamesAndSymbols(firstPlayer, secondPlayer);
                    printBoard(board, "final");
                    printWinner(firstPlayer.getName());
                    return;
                }

                if (checkBoardForRemiResult(board)) {
                    printPlayersNamesAndSymbols(firstPlayer, secondPlayer);
                    printBoard(board, "non final");
                    System.out.println("No winner!");
                    return;
                }

                printPlayersNamesAndSymbols(firstPlayer, secondPlayer);
                printPlayersNamesAndSymbols(firstPlayer, secondPlayer);
                printBoard(board, "non final");
                putInputInTheBoard(requestPlayerInput(scan, secondPlayer, board), secondPlayer.symbol, board);
                if (checkForLineOrDiagonal(board)) {
                    printPlayersNamesAndSymbols(firstPlayer, secondPlayer);
                    printBoard(board, "final");
                    printWinner(secondPlayer.getName());
                    return;
                }

                if (checkBoardForRemiResult(board)) {
                    printPlayersNamesAndSymbols(firstPlayer, secondPlayer);
                    printBoard(board, "non final");
                    System.out.println("No winner!");
                    return;
                }


            }

        }

        //TODO write mode 2

        LinkedHashMap<Integer, String> resultsHistory = new LinkedHashMap<>();

        int gamesCount = 0;

        boolean isFinish = false;
        while (!isFinish) {
            gamesCount++;
            printEmptyLines(3);
            System.out.println("Game № " + gamesCount);
            printEmptyLines(1);
            board[0][0] = "1";
            board[0][1] = "2";
            board[0][2] = "3";
            board[1][0] = "4";
            board[1][1] = "5";
            board[1][2] = "6";
            board[2][0] = "7";
            board[2][1] = "8";
            board[2][2] = "9";

            while (searchForFreeBoardNumber(board)) {

                printPlayersNamesAndSymbols(firstPlayer, secondPlayer);
                printBoard(board, "non final");


                if (gamesCount % 2 != 0) {

                    putInputInTheBoard(
                            requestPlayerInput(scan, firstPlayer, board), firstPlayer.getSymbol(), board
                    );
                    if (checkForLineOrDiagonal(board)) {
                        firstPlayer.setScore(firstPlayer.getScore() + 1);
                        printPlayersNamesAndSymbols(firstPlayer, secondPlayer);
                        printBoard(board, "final");
                        System.out.printf(TEXT_RED + "%s wins in this board!%n" + TEXT_RESET, firstPlayer.getName());
                        resultsHistory.put(gamesCount, firstPlayer.getName());
                        break;

                    } else if (checkBoardForRemiResult(board)) {
                        printPlayersNamesAndSymbols(firstPlayer, secondPlayer);
                        printBoard(board, "non final");
                        System.out.println(TEXT_RED + "No winner in this board!" + TEXT_RESET);
                        printEmptyLines(1);
                        break;
                    }
                    printPlayersNamesAndSymbols(firstPlayer, secondPlayer);
                    printBoard(board, "non final");
                    putInputInTheBoard(requestPlayerInput(scan, secondPlayer, board), secondPlayer.getSymbol(), board);
                    if (checkForLineOrDiagonal(board)) {
                        secondPlayer.setScore(secondPlayer.getScore() + 1);
                        printPlayersNamesAndSymbols(firstPlayer, secondPlayer);
                        printBoard(board, "final");
                        System.out.printf(TEXT_RED + "%s wins in this board!%n" + TEXT_RESET, secondPlayer.getName());
                        resultsHistory.put(gamesCount, secondPlayer.getName());
                        break;
                    } else if (checkBoardForRemiResult(board)) {
                        printPlayersNamesAndSymbols(firstPlayer, secondPlayer);
                        printBoard(board, "non final");
                        System.out.println(TEXT_RED + "No winner in this board!" + TEXT_RESET);
                        printEmptyLines(1);
                        break;
                    }

                } else {

                    putInputInTheBoard(requestPlayerInput(scan, secondPlayer, board), secondPlayer.getSymbol(), board);
                    if (checkForLineOrDiagonal(board)) {
                        secondPlayer.setScore(secondPlayer.getScore() + 1);
                        printPlayersNamesAndSymbols(firstPlayer, secondPlayer);
                        printBoard(board, "final");
                        System.out.printf(TEXT_RED + "%s wins in this board!%n" + TEXT_RESET, secondPlayer.getName());
                        resultsHistory.put(gamesCount, secondPlayer.getName());
                        break;
                    } else if (checkBoardForRemiResult(board)) {
                        printPlayersNamesAndSymbols(firstPlayer, secondPlayer);
                        printBoard(board, "non final");
                        System.out.println(TEXT_RED + "No winner in this board!" + TEXT_RESET);
                        printEmptyLines(1);
                        break;
                    }
                    putInputInTheBoard(requestPlayerInput(scan, firstPlayer, board), firstPlayer.getSymbol(), board);
                    printBoard(board, "non final");
                    if (checkForLineOrDiagonal(board)) {
                        firstPlayer.setScore(firstPlayer.getScore() + 1);
                        printPlayersNamesAndSymbols(firstPlayer, secondPlayer);
                        printBoard(board, "final");
                        System.out.printf(TEXT_RED + "%s wins in this board!%n" + TEXT_RESET, firstPlayer.getName());
                        resultsHistory.put(gamesCount, firstPlayer.getName());
                        break;
                    } else if (checkBoardForRemiResult(board)) {
                        printPlayersNamesAndSymbols(firstPlayer, secondPlayer);
                        printBoard(board, "non final");
                        System.out.println(TEXT_RED + "No winner in this board!" + TEXT_RESET);
                        printEmptyLines(1);
                        break;
                    }
                }

            }
            printEmptyLines(1);
            printScore(firstPlayer, secondPlayer);
            printEmptyLines(1);
            isFinish = !askForMoreGames(scan);
        }

        printEmptyLines(3);
        System.out.printf("You played %d games.%nResults are:", gamesCount);
        printEmptyLines(2);
        printResultsHistory(resultsHistory);
        printEmptyLines(1);
        System.out.println("Total scores are: ");
        System.out.printf("%s -> %d -- %s -> %d%n", firstPlayer.getName(), firstPlayer.getScore(), secondPlayer.getName(), secondPlayer.getScore());
        printEmptyLines(1);
        if (firstPlayer.getScore() == secondPlayer.getScore()) { //TODO Remi result
            System.out.println("No winner!");
            System.out.println("Scores are equal.");
            return;
        }
        if (firstPlayer.getScore() > secondPlayer.getScore()) { // TODO print results and winner
            printWinner(firstPlayer.getName());
        } else {
            printWinner(secondPlayer.getName());
        }


        //TODO VALIDATE EMPTY INPUT


    }


    public static String requestRulesAccept(Scanner scan) {

        printEmptyLines(1);
        System.out.println("Welcome to the Console Sea chess!");
        printEmptyLines(2);
        System.out.println("Rules: ");
        printEmptyLines(1);
        System.out.println("1. The game can be played by two people.");
        System.out.println("2. Every player haves has unique symbol.");
        System.out.println("3. The symbol can be \"X\" or \"O\" (Latin alphabetic characters).");
        System.out.println("3. After the first player's choice, the second player's symbol will be saved automatically.");
        System.out.println("4. The player which symbols make a LINE or DIAGONAL - WINS.");
        System.out.println("5. The game has two modes:");
        System.out.println("Mode 1: The game will end after a player has won, or no more free places in the board.");
        System.out.println("        After you've finished the game, you will see the winner's name and the program will end.");
        System.out.println("        The game will finish after every match and you will lose the score history.");
        System.out.println("        If you play the \"Mode 1\", the game will always start with player1!");
        System.out.println("Mode 2: After you've finished the board, you will be asked if you want to still keep playing.");
        System.out.println("        Answer with \"Yes\" or \"No\"!");
        System.out.println("        Every time you play \"Mode 2\", players will be rotated!");
        System.out.println("7. After setting your names, symbols and game mode, you will see the game board in the console.");
        System.out.println("8. On the board you will see numbers and you will have instructions how to play.");
        System.out.println("9. The game only can be finished by entering \"No\", after the question (after finishing the board).");
        printEmptyLines(3);
        System.out.println("Please enter \"Yes\" to accept the rules or \"No\" to exit!");

        String acceptResponse = scan.nextLine().trim();
        while (!acceptResponse.equalsIgnoreCase("Yes") &&
                !acceptResponse.equalsIgnoreCase("No")) {
            System.out.println(TEXT_RED + "Invalid input." + TEXT_RESET);
            System.out.println(TEXT_RED + "Please enter \"Yes\" or \"No\"!" + TEXT_RESET);
            printEmptyLines(1);
            acceptResponse = scan.nextLine().trim();
        }
        return acceptResponse;
    }

    public static String requestGameMode(Scanner scan) {
        System.out.print("Please choose a game mode \"1\" (a single board) or \"2\" (more than one board)! -> ");
        String gameMode = scan.nextLine();
        printEmptyLines(2);

        while (gameMode.length() != 1 || !Character.isDigit(gameMode.charAt(0)) || (!gameMode.equals("1") && !gameMode.equals("2"))) {
            System.out.println(TEXT_RED + "Invalid mode!" + TEXT_RESET);
            System.out.println("Please enter \"1\" or \"2\"!");
            gameMode = scan.nextLine();
        }
        return gameMode;
    }

    public static Player requestFirstPlayerNameAndSymbols(Scanner scan) {
        Player player = new Player();
        System.out.print("Please enter first player's name -> ");
        player.setName(scan.nextLine().trim());
        while (player.getName().isEmpty()) {
            System.out.println(TEXT_RED + "Invalid name!" + TEXT_RESET);
            printEmptyLines(1);
            System.out.print("Please enter a valid name! -> ");
            player.setName(scan.nextLine().trim());
        }
        System.out.printf("%s, please enter your symbol (\"X\" or \"O\") -> ", player.getName());
        player.setSymbol(scan.nextLine().trim().toUpperCase());
        while (!player.getSymbol().equalsIgnoreCase("X") && !player.getSymbol().equalsIgnoreCase("O")) {
            System.out.println(TEXT_RED + "Invalid symbol!" + TEXT_RESET);
            printEmptyLines(1);
            System.out.print("Please select a symbol from \"X\" and \"O\" (Latin alphabetic characters)! -> ");
            player.setSymbol(scan.nextLine().trim().toUpperCase());
        }
        return player;
    }

    public static Player requestSecondPlayerName(Scanner scan, String symbol, String firstPlayerName) {
        Player player = new Player();
        System.out.print("Please enter second player's name -> ");
        player.setName(scan.nextLine().trim());
        while (player.getName().isEmpty()) {
            System.out.println(TEXT_RED + "Invalid name!" + TEXT_RESET);
            printEmptyLines(1);
            System.out.print("Please enter a valid name! -> ");
            player.setName(scan.nextLine().trim());
        }
        while (player.getName().equalsIgnoreCase(firstPlayerName)) {
            System.out.println(TEXT_RED + "Duplicated name!" + TEXT_RESET);
            printEmptyLines(1);
            System.out.println("Please enter another name!");
            player.setName(scan.nextLine().trim());
        }
        printEmptyLines(3);
        player.setSymbol(symbol);
        return player;
    }

    public static void printPlayersNamesAndSymbols(Player a, Player b) {
        System.out.printf("%s's symbol -> \"%s\"  ,  %s's symbol ->  \"%s\"%n",
                a.getName(), a.getSymbol(), b.getName(), b.getSymbol());
        printEmptyLines(1);
    }

    public static void printBoard(String[][] board, String state) {
        if (state.equalsIgnoreCase("final")) {
            ArrayDeque<Integer> queue = findMatchIndexes(board);
            int currentRow = queue.peek() != null ? queue.poll() : -1;
            int currentColl = queue.peek() != null ? queue.poll() : -1;
            System.out.println("  ******* ");
            for (int i = 0; i < 3; i++) {
                System.out.print(" * ");
                for (int j = 0; j < 3; j++) {
                    if (i == currentRow && j == currentColl) {
                        currentRow = queue.peek() != null ? queue.poll() : -1;
                        currentColl = queue.peek() != null ? queue.poll() : -1;
                        System.out.print(TEXT_WHITE + board[i][j] + " " + TEXT_RESET);

                    } else {
                        System.out.print(board[i][j] + " ");
                    }
                }
                System.out.print("*");
                printEmptyLines(1);
            }

            System.out.println("  ******* ");

            return;
        }
        System.out.println("  ******* ");
        for (String[] array : board) {
            System.out.print(" * ");
            for (String string : array) {
                System.out.print(string + " ");
            }
            System.out.print("*");
            printEmptyLines(1);
        }
        System.out.println("  ******* ");
        printEmptyLines(1);
    }

    public static String requestPlayerInput(Scanner scan, Player player, String[][] board) {
        System.out.println(player.name + ", please choose a number from the board!");
        String input = scan.nextLine().trim();

        while (true) {

            boolean foundError = false;
            while (input.length() != 1 || !Character.isDigit(input.charAt(0))) {
                System.out.println(TEXT_RED + "Invalid input!" + TEXT_RESET);
                System.out.println("Please enter a number from the board!");
                input = scan.nextLine().trim();
                foundError = true;
            }
            if (foundError) {
                continue;
            }
            while (input.length() != 1 || input.charAt(0) < '1' || input.charAt(0) > '9') {
                System.out.println(TEXT_RED + "Invalid number!" + TEXT_RESET);
                System.out.println("Please enter a number from the board!");
                input = scan.nextLine().trim();
                foundError = true;
            }
            if (foundError) {
                continue;
            }
            while (input.charAt(0) == '1' && !board[0][0].equals("1")) {
                System.out.println(TEXT_RED + "This number was used!" + TEXT_RESET);
                System.out.println("Please enter a valid number from the board!");
                input = scan.nextLine();
                foundError = true;
            }
            if (foundError) {
                continue;
            }
            while (input.charAt(0) == '2' && !board[0][1].equals("2")) {
                System.out.println(TEXT_RED + "This number was used!" + TEXT_RESET);
                System.out.println("Please enter a valid number from the board!");
                input = scan.nextLine();
                foundError = true;
            }
            if (foundError) {
                continue;
            }
            while (input.charAt(0) == '3' && !board[0][2].equals("3")) {
                System.out.println(TEXT_RED + "This number was used!" + TEXT_RESET);
                System.out.println("Please enter a valid number from the board!");
                input = scan.nextLine();
                foundError = true;
            }
            if (foundError) {
                continue;
            }
            while (input.charAt(0) == '4' && !board[1][0].equals("4")) {
                System.out.println(TEXT_RED + "This number was used!" + TEXT_RESET);
                System.out.println("Please enter a valid number from the board!");
                input = scan.nextLine();
                foundError = true;
            }
            if (foundError) {
                continue;
            }
            while (input.charAt(0) == '5' && !board[1][1].equals("5")) {
                System.out.println(TEXT_RED + "This number was used!" + TEXT_RESET);
                System.out.println("Please enter a valid number from the board!");
                input = scan.nextLine();
                foundError = true;
            }
            if (foundError) {
                continue;
            }
            while (input.charAt(0) == '6' && !board[1][2].equals("6")) {
                System.out.println(TEXT_RED + "This number was used!" + TEXT_RESET);
                System.out.println("Please enter a valid number from the board!");
                input = scan.nextLine();
                foundError = true;
            }
            if (foundError) {
                continue;
            }
            while (input.charAt(0) == '7' && !board[2][0].equals("7")) {
                System.out.println(TEXT_RED + "This number was used!" + TEXT_RESET);
                System.out.println("Please enter a valid number from the board!");
                input = scan.nextLine();
                foundError = true;
            }
            if (foundError) {
                continue;
            }
            while (input.charAt(0) == '8' && !board[2][1].equals("8")) {
                System.out.println(TEXT_RED + "This number was used!" + TEXT_RESET);
                System.out.println("Please enter a valid number from the board!");
                input = scan.nextLine();
                foundError = true;
            }
            if (foundError) {
                continue;
            }
            while (input.charAt(0) == '9' && !board[2][2].equals("9")) {
                System.out.println(TEXT_RED + "This number was used!" + TEXT_RESET);
                System.out.println("Please enter a valid number from the board!");
                input = scan.nextLine();
                foundError = true;
            }
            if (foundError) {
                continue;
            }

            return input.trim();
        }
    }

    public static void putInputInTheBoard(String input, String symbol, String[][] board) {
        if (input.equals("1")) {
            board[0][0] = symbol;
        }
        if (input.equals("2")) {
            board[0][1] = symbol;
        }
        if (input.equals("3")) {
            board[0][2] = symbol;
        }
        if (input.equals("4")) {
            board[1][0] = symbol;
        }
        if (input.equals("5")) {
            board[1][1] = symbol;
        }
        if (input.equals("6")) {
            board[1][2] = symbol;
        }
        if (input.equals("7")) {
            board[2][0] = symbol;
        }
        if (input.equals("8")) {
            board[2][1] = symbol;
        }
        if (input.equals("9")) {
            board[2][2] = symbol;
        }

    }

    public static boolean checkForLineOrDiagonal(String[][] board) {
        if (board[0][0].equals(board[0][1]) && board[0][1].equals(board[0][2])) {
            return true;
        }
        if (board[1][0].equals(board[1][1]) && board[1][1].equals(board[1][2])) {
            return true;
        }
        if (board[2][0].equals(board[2][1]) && board[2][1].equals(board[2][2])) {
            return true;
        }
        if (board[0][0].equals(board[1][0]) && board[1][0].equals(board[2][0])) {
            return true;
        }
        if (board[0][1].equals(board[1][1]) && board[1][1].equals(board[2][1])) {
            return true;
        }
        if (board[0][2].equals(board[1][2]) && board[1][2].equals(board[2][2])) {
            return true;
        }
        if (board[0][0].equals(board[1][1]) && board[1][1].equals(board[2][2])) {
            return true;
        }
        return board[2][0].equals(board[1][1]) && board[1][1].equals(board[0][2]);
    }

    private static boolean checkBoardForRemiResult(String[][] board) {
        return !board[0][0].equals("1") &&
                !board[0][1].equals("2") &&
                !board[0][2].equals("3") &&
                !board[1][0].equals("4") &&
                !board[1][1].equals("5") &&
                !board[1][2].equals("6") &&
                !board[2][0].equals("7") &&
                !board[2][1].equals("8") &&
                !board[2][2].equals("9") &&
                !checkForLineOrDiagonal(board);
    }

    private static void printScore(Player firstPlayer, Player secondPlayer) {
        System.out.println("Current score:");
        printEmptyLines(1);
        System.out.printf("%s's current score -> %d    ", firstPlayer.getName(), firstPlayer.getScore());
        System.out.printf("%s's current score -> %d%n%n", secondPlayer.getName(), secondPlayer.getScore());
    }

    private static boolean askForMoreGames(Scanner scan) {
        System.out.println("Do you want to still playing?");
        String answer = scan.nextLine().trim().toLowerCase(Locale.ROOT);
        while (!answer.equalsIgnoreCase("yes") && !answer.equalsIgnoreCase("no")) {
            System.out.println(TEXT_RED + "Invalid answer!" + TEXT_RESET);
            printEmptyLines(1);
            System.out.println("Please enter \"Yes\" or \"No\"!");
            answer = scan.nextLine().trim().toLowerCase(Locale.ROOT);
        }
        return answer.equalsIgnoreCase("yes");
    }



    public static void printWinner(String winnerName) {
        for (int i = 0; i < 19 + winnerName.length(); i++) {
            System.out.print("*");
        }
        System.out.printf("%n* The winner is %s! *%n", winnerName);
        for (int i = 0; i < 19 + winnerName.length(); i++) {
            System.out.print("*");
        }
    }

    public static void printResultsHistory(Map<Integer, String> map) {
        printEmptyLines(1);
        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            System.out.printf("Game № %d -> %s%n", entry.getKey(), entry.getValue());
        }

    }

    public static boolean searchForFreeBoardNumber(String[][] board) {
        return board[0][0].equals("1") ||
                board[0][1].equals("2") ||
                board[0][2].equals("3") ||
                board[1][0].equals("4") ||
                board[1][1].equals("5") ||
                board[1][2].equals("6") ||
                board[2][0].equals("7") ||
                board[2][1].equals("8") ||
                board[2][2].equals("9");

    }

    public static ArrayDeque<Integer> findMatchIndexes(String[][] board) {
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        if (board[0][0].equals(board[0][1]) && board[0][1].equals(board[0][2])) {
            queue.offer(0);
            queue.offer(0);
            queue.offer(0);
            queue.offer(1);
            queue.offer(0);
            queue.offer(2);
            return queue;
        }
        if (board[1][0].equals(board[1][1]) && board[1][1].equals(board[1][2])) {
            queue.offer(1);
            queue.offer(0);
            queue.offer(1);
            queue.offer(1);
            queue.offer(1);
            queue.offer(2);
            return queue;
        }
        if (board[2][0].equals(board[2][1]) && board[2][1].equals(board[2][2])) {
            queue.offer(2);
            queue.offer(0);
            queue.offer(2);
            queue.offer(1);
            queue.offer(2);
            queue.offer(2);
            return queue;
        }
        if (board[0][0].equals(board[1][0]) && board[1][0].equals(board[2][0])) {
            queue.offer(0);
            queue.offer(0);
            queue.offer(1);
            queue.offer(0);
            queue.offer(2);
            queue.offer(0);
            return queue;
        }
        if (board[0][1].equals(board[1][1]) && board[1][1].equals(board[2][1])) {
            queue.offer(0);
            queue.offer(1);
            queue.offer(1);
            queue.offer(1);
            queue.offer(2);
            queue.offer(1);
            return queue;
        }
        if (board[0][2].equals(board[1][2]) && board[1][2].equals(board[2][2])) {
            queue.offer(0);
            queue.offer(2);
            queue.offer(1);
            queue.offer(2);
            queue.offer(2);
            queue.offer(2);
            return queue;
        }
        if (board[0][0].equals(board[1][1]) && board[1][1].equals(board[2][2])) {
            queue.offer(0);
            queue.offer(0);
            queue.offer(1);
            queue.offer(1);
            queue.offer(2);
            queue.offer(2);
            return queue;
        }
        if (board[2][0].equals(board[1][1]) && board[1][1].equals(board[0][2])) {
            queue.offer(2);
            queue.offer(0);
            queue.offer(1);
            queue.offer(1);
            queue.offer(0);
            queue.offer(2);

        }
        return queue;
    }


    public static void printEmptyLines(int count) {
        for (int i = 0; i < count; i++) {
            System.out.println();
        }
    }

}