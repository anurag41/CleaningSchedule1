package scheduling;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ReadInput {
 static int roomCleaned;
 static int numberOfStaff;
	
//	public void setRoomCleaned(int t){
//		roomCleaned = t;
//	}
//	public int getRoomCleaned(){
//		return roomCleaned;
//	}
	
	public Map<Integer, ArrayList<String>> ReadInputMethod() {

		//File input = new File("/home/anurag/input1");
		int n;
		//int roomCleaned; // it tells about the number of room clean in half hour.
		Map<Integer, ArrayList<String>> roomToTimeslots = new HashMap<Integer, ArrayList<String>>();
		Scanner read = null;
		try {
			read = new Scanner(System.in);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//System.out.println("Enetr the number of Students: ");
		n = read.nextInt();
		roomCleaned= read.nextInt();
		numberOfStaff = read.nextInt();
		//System.out.println("ReadInput roomCleaned ="+ roomCleaned);
		
		String timeSlot;
		for (int i = 0; i < n; i++) {
			//System.out.println("Enetr the room number: ");
			int roomNumber = read.nextInt();
			//System.out.println("Enetr the time slots for room " + roomNumber + ": ");
			ArrayList<String> timeSlots = new ArrayList<String>();
			while (!(timeSlot = read.next()).equals(";")) {
				timeSlots.add(timeSlot);
			}
			roomToTimeslots.put(roomNumber, timeSlots);
		}

		return roomToTimeslots;

	}

}
