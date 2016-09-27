package scheduling;
/*
 * Cleaning schedule
 * @author: Anurag Sharma
 * */
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Process {
     
	Map<Integer, Integer> rank = new HashMap<Integer, Integer>();
	
	
	
	//ReadInput r = new ReadInput();
	int roomCleaned1 =  ReadInput.roomCleaned;
	int numberOfStaff = ReadInput.numberOfStaff;
	//
	
	public Map<Integer, ArrayList<Integer>> ReverseMapping(Map<Integer, ArrayList<String>> roomToTimeslots) {
		//roomCleaned1 = ReadInput.roomCleaned;
		//int roomCleaned1 = ReadInput.roomCleaned ;
	//System.out.println("In Process  "+ReadInput.roomCleaned);	
	//System.out.println("In Process  roomCleaned ="+roomCleaned1);
	//System.out.println("In Process  numberOfSatff ="+numberOfStaff);
		

		Map<Integer, ArrayList<Integer>> TimeslotsToRomms = new HashMap<Integer, ArrayList<Integer>>();

		for (Map.Entry<Integer, ArrayList<String>> entry : roomToTimeslots.entrySet()) {

			for (String slot : entry.getValue()) {

				String sStartTime, sEndTime;
				int startTime, endTime;
				String[] tempSlot = slot.split("-"); // hh:mm-hh:mm
				sStartTime = tempSlot[0]; // hh:mm
				sEndTime = tempSlot[1];
				String[] TempStart = sStartTime.split(":"); // hh:mm
				String[] TempEnd = sEndTime.split(":");
				startTime = Integer.parseInt(TempStart[0]) * 60 + Integer.parseInt(TempStart[1]);
				endTime = Integer.parseInt(TempEnd[0]) * 60 + Integer.parseInt(TempEnd[1]);

				Set<Integer> Slots = Scheduling.slotMapping.keySet();
				int cur = 0;
				for (int k : Slots) {
					// System.out.println("****" + k);
					ArrayList<Integer> roomList = new ArrayList<Integer>();
					if (cur != 0) {
						if (startTime >= cur && startTime < k) {
							int roomRank = 1;
							if (rank.get(entry.getKey()) != null) {
								roomRank = rank.get(entry.getKey());
								roomRank++;
							}
							rank.put(entry.getKey(), roomRank);
							if (TimeslotsToRomms.get(cur) != null) {
								ArrayList<Integer> temproomList = TimeslotsToRomms.get(cur);
								roomList.addAll(temproomList);
							}

							roomList.add(entry.getKey());
							TimeslotsToRomms.put(cur, roomList);
							// break;
						}

						if (endTime > cur && endTime <= k) {
							int roomRank = 1;
							if (rank.get(entry.getKey()) != null) {
								roomRank = rank.get(entry.getKey());
								roomRank++;
							}
							rank.put(entry.getKey(), roomRank);
							if (TimeslotsToRomms.get(cur) != null) {
								ArrayList<Integer> temproomList = TimeslotsToRomms.get(cur);
								roomList.addAll(temproomList);
							}

							roomList.add(entry.getKey());
							TimeslotsToRomms.put(cur, roomList);
							// break;
						}

					}
					cur = k;

				}

			}

		}
		/*for (Map.Entry<Integer, Integer> entry : rank.entrySet()) {
			System.out.println("room " + entry.getKey() + ", rank " + entry.getValue());
		}*/
		
		
		return TimeslotsToRomms;

	}

	 

	private static <KeyType, ValueType> Map<KeyType, ValueType> sortByComparator(Map<KeyType, ValueType> unsortMap) {

		// Convert Map to List
		List<Map.Entry<KeyType, ValueType>> list = new LinkedList<Map.Entry<KeyType, ValueType>>(unsortMap.entrySet());

		// Sort list with comparator, to compare the Map values
		Collections.sort(list, new Comparator<Map.Entry<KeyType, ValueType>>() {
			public int compare(Map.Entry<KeyType, ValueType> o1, Map.Entry<KeyType, ValueType> o2) {
				return ((Integer) o1.getValue()).compareTo((Integer) o2.getValue());
			}
		});

		// Convert sorted map back to a Map
		Map<KeyType, ValueType> sortedMap = new LinkedHashMap<KeyType, ValueType>();
		for (Iterator<Map.Entry<KeyType, ValueType>> it = list.iterator(); it.hasNext();) {
			Map.Entry<KeyType, ValueType> entry = it.next();
			sortedMap.put(entry.getKey(), entry.getValue());
		}
		return sortedMap;
	}
	
	public Map<Integer, ArrayList<Integer>> MapSlotToRoom(Map<Integer, ArrayList<Integer>> timeslotsToRomms) {
		Map<Integer, ArrayList<Integer>> finalAllocation = new HashMap<Integer, ArrayList<Integer>>();
		Map<Integer, Integer> mapSortedByRank = sortByComparator(rank);

		//System.out.println("Printing sorted map**");
		
		for (Map.Entry<Integer, ArrayList<Integer>> slot : timeslotsToRomms.entrySet()) {
			ArrayList<Integer> roomList = new ArrayList<Integer>();
			int i = 0;
			//System.out.println(slot.getKey());
			for (Map.Entry<Integer, Integer> entry : mapSortedByRank.entrySet()) {
				
				
				for (int roomNo : slot.getValue()) {
                     
					if (entry.getKey() == roomNo && i < (roomCleaned1*numberOfStaff) && entry.getValue() != 0) {

						roomList.add(roomNo);
						i++;
						entry.setValue(0);
						break;
					}

				}
				finalAllocation.put(slot.getKey(), roomList);
			}

		}
		for (Map.Entry<Integer, Integer> entry : mapSortedByRank.entrySet()) {
			if(entry.getValue()!=0)
			System.out.println("Rooms Remaining: "+ entry.getKey());
		}

		return finalAllocation;
	}

}