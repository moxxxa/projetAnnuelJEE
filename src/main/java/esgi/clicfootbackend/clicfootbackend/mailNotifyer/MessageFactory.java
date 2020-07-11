package esgi.clicfootbackend.clicfootbackend.mailNotifyer;

public class MessageFactory {
    public static String welcomeMessage(String lastName) {
        return "Thank you for subscribing " + lastName + ", were glad to have you in our community";
    }

    public static String passwordChangeMessage() {
        return "Your password have just been successfuly updated";
    }

    public static String accountUpdatedMessage() {
        return "Your personnal informations has been updated successfuly";
    }

    public static String satisticsPlayerRequestAvailable() {
        return "Your request regarding the player statistics is now available in the search section";
    }

    public static String satisticsTeamRequestAvailable() {
        return "Your request regarding the team statistics is now available in the search section";
    }

    public static String pronosticsRequestAvailable() {
        return "Your request regarding the pronostic prediction is now available in the search section";
    }

    public static String tournamentRequestAvailable() {
        return "your request regarding the tournament prediction is now availbale in the search section";
    }

    public static String accountDeleted() {
        return "Your account has been deleted, we will miss you ...";
    }
}
