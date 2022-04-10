import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Game {

    static Banker banker = Banker.getInstance();
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

    static long startGame(){
        Scanner scanner = new Scanner(System.in);
        String command="";
        long timeMilis=Long.MAX_VALUE;
        System.out.println("Please insert Player names in separate lines");
        while ((!command.equals("start_game"))|| banker.players.size()<2)
        {
            command = scanner.nextLine();
            if (command.matches("Time \\d+? min")){
                timeMilis= Long.parseLong(command.substring(5,command.length()-4))*60_000;
                continue;
            }
            if(!command.equals("start_game"))
            {
                banker.addPlayer(command);
            }
            else
            {
                if(banker.players.size()<2)
                    System.out.println("at least 2 players should be added.");
            }
        }
        System.out.println("Game Started");
        return timeMilis;
    }

    static void move(int dice, Player player) // not sure why static & why here
    {
        if (player.inPrison){
            if (dice==1) {
                player.inPrison = false;
                move(dice,player);
            }
            else {
                if (player.budget<10){
                    banker.debt(10,player);
                }
                else {player.budget-=10;}
            }
            return;
        }

        player.index += dice;
        player.index = player.index%24 ;
        map.houses[player.index].action(player);
    }

    static void endgame()
    {
        Player[] ranking = banker.rank();
        System.out.println(ranking[0].name+ " YOU Won");
        for (int i = 1; i <ranking.length ; i++)
        {
            System.out.println(ranking[i].name+ " You Lost");
        }
    }

    static int getDice(){
        int dice=0;
        Scanner scanner = new Scanner(System.in);
        try{
            dice = scanner.nextInt();
        }
        catch (Exception inputMismatchException){
            System.out.println("enter a valid number");
            dice=getDice();
        }
        return dice;
    }
    static int dice(){
        int dice;
        dice=getDice();
        while (!(dice > 0 && dice < 7)) {
            System.out.println("lamasab tas "+dice+" dareh ?!!");
            dice = getDice();
        }
        return dice;
    }

    static void executeCommands(String command,Player player,long timeStart,long timeMilis) {// not sure why static & why here
        String firstPartCommand =command ;
        if(command.matches("fly \\d+?"))
        {
            firstPartCommand ="fly";
        }
        else if(command.matches("sell \\d+?"))
        {
            firstPartCommand = "sell";
        }
        else if(command.equals("sell")|| command.equals("fly"))
            firstPartCommand = "123";//not a valid command
        switch (firstPartCommand) {
            case "":
                break;
            case "time": {
                if (timeMilis == Long.MAX_VALUE) {
                    System.out.println(" Not a timed match");
                } else {
                    long timeEnd = System.currentTimeMillis();
                    System.out.println((timeMilis + timeStart - timeEnd) / 1_000 + "secends left");
                }
                break;
            }
            case "buy":
                try {
                    banker.buy(player);
                } catch (ClassCastException classCastException) {
                    System.out.println("this block is not buyable");
                }
                break;
            case "sell":
            {
                try {
                int index = Integer.parseInt(command.substring(5));
                banker.sell(index-1,player);
                break;
            }
                catch (NumberFormatException numberFormatException){
            }
        }
                break;
            case "build": {
                try {
                    banker.build(player);
                } catch (ClassCastException classCastException) {
                    System.out.println("you can not build in this block");
                }
                break;
            }
            case "fly":{
                try {
                    int index = Integer.parseInt(command.substring(4));
                    banker.fly(index,player);
                    break;
                }
                catch (ClassCastException classCastException){
                    System.out.println("you are not in Airport");
                    break;
                }
                catch (NumberFormatException numberFormatException){
                }
            }
                break;
            case "free": {
                try {
                    banker.free(player);
                } catch (ClassCastException classCastException) {
                    System.out.println("this commend is not valid here");
                }
                break;
            }
            case "invest":{
                try {
                    banker.invest(player);
                } catch (ClassCastException classCastException) {
                    System.out.println("you are not in the bank");
                }
                break;
            }
            case "property":
            {
                System.out.println("budget: "+player.budget+"$");
                for (int i=0 ; i <player.properties.size(); i++){
                    System.out.println("in index "+(player.properties.get(i)+1) + " you have a '"+map.houses[player.properties.get(i)].getClass().getSimpleName()+"'");
                }
                System.out.println("cards : " + player.cards);
            }
                break;
            case "index": {
                System.out.println("index: "+(player.index+1) + " you are in a '"+map.houses[player.index].getClass().getSimpleName()+"' block");
                break;
            }
            case "rank":
                Player[] ranking = banker.rank();
                System.out.println(Arrays.asList(ranking));
                break;
            default:
                System.out.println("not a vali command");
        }
    }

    public static void main(String[] args){

        String command = "";
        int dice = 0;
        int round = 1;
        createGame();
        long timeMilis = startGame();
        long timeStart=System.currentTimeMillis();
        long timeEnd = 0;
        Scanner scanner = new Scanner(System.in);

        while (banker.survivors() > 1 && timeEnd-timeStart<timeMilis) {
            System.out.println("round : " + round++);
            for (int i = 0; i < banker.players.size(); i++) {
                if (banker.players.get(i).lost != true) {
                    Player player = banker.players.get(i);
                    command = ""; // resets command
                    System.out.println(player.name + "'s Turn");
                    dice=dice();
                    if (dice==6){
                        if (player.lastDice==6){
                            player.lastDice=0;
                            player.index=12;
                            player.inPrison=true;
                            continue;
                        }
                        else {
                            i--;
                        }
                    }
                    move(dice, player);
                    while (!command.equals("pass")) {
                        executeCommands(command,player,timeStart,timeMilis);
                        command = scanner.nextLine();
                    }
                    player.lastDice=dice;
                }

            }
            System.out.println("banker's turn");
            command = scanner.nextLine();
            while (!command.equals("pass"))
            {
                if(command.matches("switch \\w+ \\w+"))
                {
                    String[] parts = command.split(" ");
                    for (int i = 0; i <3 ; i++) {
                        parts[i] = parts[i].replaceAll(" ","");
                    }
                    banker.swtich(parts[1],parts[2]);
                }
                command = scanner.nextLine();
            }
            timeEnd=System.currentTimeMillis();
        }
        endgame();
    }

}
