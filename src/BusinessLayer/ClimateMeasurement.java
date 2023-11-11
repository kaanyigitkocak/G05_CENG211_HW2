package BusinessLayer;

public class ClimateMeasurement {
    private int year;
    private int month;

    public ClimateMeasurement(int year, int month) {
        this.year = year;
        this.month = month;
    }   
    
    public int getYear() {
    	return year;
    }
    
    public int getMonth() {
    	return month;
    }
}
