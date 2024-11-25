import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int player1Chances = 6, player2Chances = 6, player3Chances = 6, player4Chances = 6;
        String[] arr = {"J","Q","K","A"};
        String[] table_type = {"A","K","Q"};
        

        
        
        while(true){
            ArrayList<String> firstPerson = new ArrayList<>();
            ArrayList<String> secondPlayer = new ArrayList<>();
            ArrayList<String> thirdPlayer = new ArrayList<>();
            ArrayList<String> fourthPlayer = new ArrayList<>();
            System.out.println("player1Chances: " + player1Chances);
            System.out.println("player2Chances: " + player2Chances);
            System.out.println("player3Chances: " + player3Chances);
            System.out.println("player4Chances: " + player4Chances);            
            String table = getTable(table_type);
            System.out.println("Game Begins!");
            System.out.println("this is " + table + " tables");
            
            firstPerson = distribute_cards(firstPerson,arr);
            secondPlayer = distribute_cards(secondPlayer,arr);
            thirdPlayer = distribute_cards(thirdPlayer, arr);
            fourthPlayer = distribute_cards(fourthPlayer, arr);
            ArrayList<String> previos_cards = new ArrayList<>();
            String CurrentPlayer = "";
            String previousPlayer = "";


            while(true){
                
                
                Scanner scan = new Scanner(System.in);
                // ------------------- Player 1 -------------------------//
                System.out.println("Player 1");
                previousPlayer = CurrentPlayer; // check for the logic
                CurrentPlayer = "first player";
                System.out.println(firstPerson);
                if (previousPlayer != ""){
                    System.out.println("lie(0) or 1 to continue");
                    int choice = scan.nextInt();

                    if (choice == 0){
                        System.out.println(previos_cards + "previous players cards");
                        Boolean result = checkResult(previos_cards,table);
                        System.out.println(result);
                        if (result){
                            System.out.println("luck funtion working....");
                            if (luck(CurrentPlayer)){
                                player1Chances -= 1;
                                break;
                            }
                            System.out.println("Lucky Player: " + CurrentPlayer);
                        }
                        else{
                            if (luck(CurrentPlayer)){
                                player2Chances -= 1;
                                break;
                            }
                            System.out.println("Lucky Player" + CurrentPlayer);
                        }
                    }
                }

                
                previos_cards = gameLogic(firstPerson);
                System.out.println(firstPerson);


                // ---------------------------------- Player 2 --------------------------------//
                System.out.println("player 2");
                System.out.println(secondPlayer);
                
                System.out.println("lie(0) or continue(1)...");
                int choice = scan.nextInt();
                previousPlayer = CurrentPlayer;
                CurrentPlayer = "second player";
                
                if (choice== 0){
                    Boolean result = checkResult(previos_cards,table);
                    System.out.println(result);
                    if (result){
                        if (luck(previousPlayer)){
                            player1Chances -= 1;
                            break;
                        }
                        
                        System.out.println("Lucky Player: " + previousPlayer);
                    }
                    else{
                        if (luck(CurrentPlayer)){
                            player2Chances -= 1;
                            break;
                        }
                        System.out.println("Lucky Player" + CurrentPlayer);
                    }
                }
                
                previos_cards = gameLogic(secondPlayer);
                System.out.println(secondPlayer);
                
                
                // ------------------------------  3rd player --------------------------//                
                System.out.println("player 3");
                System.out.println(thirdPlayer);
                previousPlayer = CurrentPlayer;
                CurrentPlayer = "third player";
                
                System.out.println("lie(0) or continue(1)...");
                choice = scan.nextInt();
                if (choice == 0){
                    System.out.println(previos_cards + "previous players cards");
                    if (checkResult(previos_cards,table)){
                        if (luck(previousPlayer)){
                            player2Chances -= 1;
                            break;
                        }
                        
                        System.out.println("Lucky Player: " + CurrentPlayer);
                    }
                    else{
                        if (luck(CurrentPlayer)){
                            player3Chances -= 1;
                            break;
                        }
                        System.out.println("Lucky Player" + CurrentPlayer);
                    }
                }
                previos_cards = gameLogic(thirdPlayer);
                System.out.println(thirdPlayer);

                // ------------------------------  4th player --------------------------//

                System.out.println("player 4");
                System.out.println(fourthPlayer);
                previousPlayer = CurrentPlayer;
                CurrentPlayer = "fourth player";
                System.out.println("lie(0) or continue(1)...");
                choice = scan.nextInt();
                if (choice == 0){
                    if (checkResult(previos_cards,table)){
                        if (luck(previousPlayer)){
                            player3Chances -= 1;
                            break;
                        }
                        
                        System.out.println("Lucky Player: " + CurrentPlayer);
                    }
                    else{
                        if (luck(CurrentPlayer)){
                            player4Chances -= 1;
                            break;
                        }
                        System.out.println("Lucky Player" + CurrentPlayer);
                    }
                }
                CurrentPlayer = "fourth player";
                previos_cards = gameLogic(fourthPlayer);
                // System.out.println(thirdPlayer);

            }
        }
    
        
    }

    public static ArrayList<String> distribute_cards(ArrayList<String> player,String[] arr){
            Random rand = new Random();
            for (int i = 0; i < 5;i++){
                int card_index = rand.nextInt(4);
                player.add(arr[card_index]);
        }
        return player;
    }

    public static ArrayList<String> gameLogic(ArrayList<String> player){
            Scanner in = new Scanner(System.in);
            // System.out.println("lie press 0 or else 1 to continue...");

            System.out.println("Enter number of cards you want use: ");
            int n = in.nextInt();
            int[] input = new int[n];
            System.out.println("index number of card: ");
            for(int i = 0; i < n; i++){
                int index = in.nextInt();
                input[i] = index;
            }
            Arrays.sort(input);
            ArrayList<String> rem = new ArrayList<String>();
            for (int i=input.length-1; i>-1; i--){
                rem.add(player.remove(input[i]));

            }
            
            return rem;
    }
    public static boolean checkResult(ArrayList<String> cards,String table){
        // currently checking for ace table
        Boolean check = true;
        // System.out.println(cards+"cards inside check result");
        // System.out.println(cards.get(0).equals("J")+"this is valid");
        for (int i = 0; i < cards.size(); i++) {
            // System.out.println("Current card: " + cards.get(i));
            if ((cards.get(i).equals("J") || cards.get(i).equals(table))) {
                // System.out.println("Condition met for card: " + cards.get(i));
                check = false;
            }
            else{
                check = true;
                break;
            }
        }
        return check;
    }

    public static Boolean luck(String player){
        // get the chances
        // ranint check for the luck (we can run 2 ranint if the same value then kill or if the value is 0 kill and if value is dont't kill)
        // if kill remove the player from the game
        Random rand = new Random();
        int luck = rand.nextInt(2);
        System.out.println(luck);
        if (luck == 0){
            System.out.println(player + " got killed!");
            return true;
        }
        System.out.println(player + " didn't killed continued the game!");
        return false;
    }

    public static String getTable(String[] tables){
        Random rand = new Random();
        int table = rand.nextInt(3);
        return tables[table];

    }
}
