import java.util.Scanner;

public class Game {

    static Map map = Map.getInstance(); // not sure why static

    static void createGame(){// not sure why static & why here
        Scanner scanner = new Scanner(System.in);
        System.out.println("To Create a new game type 'create_game'");
        while (true)
        {
            String command = scanner.nextLine();
            if (command.equals("create_game")) break;
            System.out.println("No Game Created");
        }
        System.out.println("Game Created");
    }

    static void startGame(){
        Scanner scanner = new Scanner(System.in);
        String command="";
        System.out.println("Please insert Player names in separate lines");
        Banker banker = new Banker(); // to be singleton
        while ((!command.equals("start_game")) || banker.players.size()<2)
        {
            command = scanner.nextLine();
            banker.addPlayer(command);
        }
        // TODO time ??
        System.out.println("Game Started");
    }

    static void move(int dice, Player player) // not sure why static & why here
    {
        player.index += dice;
        map.houses[player.index].action(player);
    }

    static void executeCommands(String command) {// not sure why static & why here
        switch (command) {
            case "":
                break;
            case "time":
                //showTime()
                break;
            case "buy":
                //buy || player.buy || banker.buy
                break;
            case "sell":
                //sell
                break;
            case "build":
                //TODO build
                break;
            case "fly":
                //fly
                break;
            case "free":
                //free
                break;
            case "invest":
                //invest
                break;
            case "property":
                //property
                break;
            case "index":
                //index
                break;
            case "rank":
                //rank
                break;
            default:
                System.out.println("not a vali command");
        }
    }

    public static void main(String[] args){

        String command = "";
        int dice = 0;
        int round = 1;
        Banker banker = new Banker(); // to be singleton
        createGame();
        startGame();
        Scanner scanner = new Scanner(System.in);
        while (banker.survivors() > 1) {
            System.out.println("round : " + round);
            for (int i = 0; i < banker.players.size(); i++) {
                if (banker.players.get(i).lost != true) {
                    command = ""; // resets command
                    System.out.println(banker.players.get(i).name + "'s Turn");
                    dice = scanner.nextInt();
                    if (dice > 0 && dice < 7) {
                        move(dice, banker.players.get(i));
                    }
                    while (!command.equals("pass")) {
                        executeCommands(command);
                    }
                    command = scanner.nextLine();
                }
            }
        }
    }

}
