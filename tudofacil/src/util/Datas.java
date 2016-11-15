package util;

import java.util.Date;


public class Datas {
	public static long diasEntreDatas(Date data1, Date data2) {
        long tempo1 = data1.getTime();
        long tempo2 = data2.getTime();  
        
        long difTempo = tempo1 - tempo2;  
        long dias = difTempo/1000/60/60/24;
		
        return dias;

	}

}
