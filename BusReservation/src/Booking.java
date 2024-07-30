import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.SQLException;
import java.util.*;

public class Booking {
	String passengerName;
	int busNo;
	Date date;
	
	Booking(Scanner scan) {
		System.out.println("Enter Passenger name : ");
		passengerName = scan.nextLine(); //Changed to nextLine()
		
		//Validate bus number
		while(true) {
			System.out.println("Enter bus no: ");
			if(scan.hasNextInt()) {
				busNo = scan.nextInt();
				scan.nextLine(); // Consume the new line character left by nextInt()
				break;
			} else {
				System.out.println("Invalid input. Please enter a valid bus number.");
				scan.nextLine(); //Clear the invalid input
			}
		}
		System.out.println("Enter date dd-mm-yyyy");
		String dateInput = scan.nextLine();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy");
		
		try {
			date = dateFormat.parse(dateInput);
		} catch(ParseException e) {
			e.printStackTrace();
		}
	}
	
	public boolean isAvailable() throws SQLException {
		BusDAO busdao = new BusDAO();
		BookingDAO bookingdao = new BookingDAO();
		int capacity = busdao.getCapacity(busNo);
		int booked = bookingdao.getBookedCount(busNo, date);
		return booked < capacity;
	}
}