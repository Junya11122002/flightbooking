public class Flight 
{
    //Variables
    public String airportName;
    public String takeOffTime;
    public String arrivalTime;

    public Flight()     //Default
    {
        airportName = "N/A";
        takeOffTime = "N/A";
        arrivalTime = "N/A";
    }

    public Flight(String airport, String takeOff, String arrive)     //Parameters
    {
        airportName = airport;
        takeOffTime = takeOff;
        arrivalTime = arrive;
    }
    
}
