import java.time.LocalDateTime;
public class Appointment {
    String appID, appDesc;
    LocalDateTime appDate;
    public Appointment(String aID, LocalDateTime aDate, String aDesc) {
        appID = aID;
        appDate = aDate;
        appDesc = aDesc;
    }
}