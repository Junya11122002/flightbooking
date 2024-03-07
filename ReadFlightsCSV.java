import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ReadFlightsCSV
{
    public Flight[] getNextFlight()
    {
        //Variables
        Flight flightArray[] = new Flight[getFileLength()];
        String line;
        int flightIndex = 0;

        //Read
        try
        {
            BufferedReader reader = new BufferedReader(new FileReader("flights.csv"));
            
            while ((line = reader.readLine()) != null)
            {
                int startIndex = 0;
                int endIndex = 0;
                
                endIndex = line.indexOf(',');
                String airport = line.substring(startIndex, endIndex);
                startIndex = endIndex + 1;
                
                endIndex = line.indexOf(',', startIndex);                       
                String takeOffTime = line.substring(startIndex, endIndex);
                startIndex = endIndex + 1;
                
                String arrivalTime = line.substring(startIndex, line.length());
                
                flightArray[flightIndex] = new Flight(airport, takeOffTime, arrivalTime);

                flightIndex++;
            }
            
            reader.close(); 
        }
        catch(FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }

        return flightArray;
    }

    public int getFileLength()
    {
        int length = 0;

        try
        {
            BufferedReader dummyReader = new BufferedReader(new FileReader("flights.csv"));
            while (dummyReader.readLine() != null)
            {
                length++;
            }

            dummyReader.close();
        }
        catch(FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        
        return length;
    }
}
