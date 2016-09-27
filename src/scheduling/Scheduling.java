package scheduling;

import java.util.*;

public class Scheduling {

	public static final  Map<Integer , String> slotMapping = new TreeMap<Integer , String>() {
		{
			put(480,"8:00");
			put(510,"8:30");
			put(540,"9:00");
			put(570,"9:30");
			put(600,"10:00");
			put(630,"10:30");
			put(660,"11:00");
			put(690,"11:30");
			put(720,"12:00");
			put(750,"12:30");
			put(780,"13:00");
			put(810,"13:30");
			put(840,"14:00");
			put(870,"14:30");
			put(900,"15:00");
			put(930,"15:30");
			put(960,"16:00");
			put(990,"16:30");
			put(1020,"17:00");
			put(1050,"17:30");
			put(1080,"18:00");
			put(1110,"18:30");
			put(1140,"19:00");
			
		}
	};
	public static void main(String[] args) {
		
		Map<Integer, ArrayList<String>> roomToTimeslots = new HashMap<Integer, ArrayList<String>>();
		Map<Integer, ArrayList<Integer>> TimeslotsToRomms = new HashMap<Integer, ArrayList<Integer>>();
		Map<Integer, ArrayList<Integer>> finalAllocation = new HashMap<Integer, ArrayList<Integer>>();
		
		ReadInput read = new ReadInput();
		
		//System.out.println("In Process1  "+read.roomCleaned);
		roomToTimeslots = read.ReadInputMethod();
		//System.out.println(read.roomCleaned);
		Process process = new Process();
		TimeslotsToRomms = process.ReverseMapping(roomToTimeslots);
		
		finalAllocation = process.MapSlotToRoom(TimeslotsToRomms);
		
		Map<Integer, ArrayList<Integer>> result = new TreeMap<Integer, ArrayList<Integer>>(finalAllocation);
		
		for (Map.Entry<Integer, ArrayList<Integer>> entry : result.entrySet()) {
			//System.out.format("%1s%11s%10s%1s\n","Slot : ",slotMapping.get(entry.getKey())+"-"+slotMapping.get(entry.getKey()+30),"Rooms: ",entry.getValue());
		   // Integer i=null;
			 Iterator itr=entry.getValue().iterator();
			 System.out.println("Slot : " + slotMapping.get(entry.getKey()) +"-"+slotMapping.get(entry.getKey()+30));
			// System.out.println("Rooms:cleaned by staff  " + 1);
			 int count=0;
			for(int j=1;j<=ReadInput.numberOfStaff;j++)	
			{   if(count == 0 && j ==1 )
			   System.out.println("Rooms:cleaned by staff  " + j);
			    else if(count == ((ReadInput.roomCleaned)+1) && j > 1)
			    {
				  System.out.println("Rooms:cleaned by staff  " + j);
				 }
			    else if(count < ((ReadInput.roomCleaned)+1))
			    	break;

			 count=0;
            while(itr.hasNext())
            	{
            	if((++count%((ReadInput.roomCleaned)+1)==0))
            		break;
            	
            	 System.out.print(itr.next()+" ");
			}
              if(count == 0)
            	  break;
              System.out.println();
              
            	
			}
            System.out.println();

		   
		}
		
	}

}
