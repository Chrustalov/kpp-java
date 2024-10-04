package meetings;

import meetings.Actions.*;
import meetings.Models.User;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<User> users = new ArrayList<>();
        ZoneId timeZone = ZoneId.of("Europe/Kiev");
        Scanner scanner = new Scanner(System.in);

        boolean running = true;

        while (running) {
            printMenu();

            int action = Integer.parseInt(scanner.nextLine());

            switch (action) {
                case 1:
                    Action createUserAction = new CreateUserAction(users, scanner);
                    createUserAction.execute();
                    break;

                case 2:
                    Action addAvailabilityAction = new AddAvailabilityTimeAction(users, scanner);
                    addAvailabilityAction.execute();
                    break;

                case 3:
                    Action findMeetingAction = new FindMeetingAction(users, timeZone, scanner);
                    findMeetingAction.execute();
                    break;

                case 4:
                    Action findLongestTimeAction = new FindLongestCommonTimeAction(users, timeZone);
                    findLongestTimeAction.execute();
                    break;

                case 5:
                    running = false;
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid action. Please try again.");
                    break;
            }
        }

        scanner.close();
    }

    private static void printMenu() {
        System.out.println("Choose an action:");
        System.out.println("1. Create User");
        System.out.println("2. Add Availability to User");
        System.out.println("3. Find a Meeting for all Users");
        System.out.println("4. Find Longest Common Available Time");
        System.out.println("5. Exit");
    }
}
