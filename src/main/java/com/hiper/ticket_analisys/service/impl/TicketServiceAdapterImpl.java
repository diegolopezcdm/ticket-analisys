package com.hiper.ticket_analisys.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.hiper.ticket_analisys.bean.Ticket;
import com.hiper.ticket_analisys.service.TicketServiceAdapter;
import com.hiper.ticket_analisys.source.TicketSourceAdapter;
import com.hiper.ticket_analisys.source.impl.TicketCsvAdapterImpl;


public class TicketServiceAdapterImpl implements TicketServiceAdapter{
	
	private static final  Logger logger = Logger.getLogger(TicketServiceAdapterImpl.class);

	private TicketSourceAdapter dataSource = new TicketCsvAdapterImpl();
	
	
	public int getLargestQueue(String agencia, String tipo){
	
		int highestCount = 0;
		
		List<Ticket> tickets = dataSource.getTickets(agencia, tipo);
		
		//people waiting in queue
		List<Date> generadosList = new ArrayList<Date>();
		//people leaving the queue, so they are being attending
		List<Date> generadosToRemoveList = new ArrayList<Date>();
		
		for (Ticket ticket : tickets) {

			//add current person to the queue
			generadosList.add(ticket.getAsignado());
			
			//check for people leaving the queue
			for (Date genetadoDate : generadosList) {
				
				if(genetadoDate.before(ticket.getGenerado())){
					generadosToRemoveList.add(genetadoDate);
				}
				
			}
			
			//removing people that are already being attending
			generadosList.removeAll(generadosToRemoveList);
			generadosToRemoveList.clear();
			
			//updates the highest value in queue
			if(generadosList.size()>highestCount)
				highestCount = generadosList.size();
			
			
		}
		
		logger.debug("highestCount value : " + highestCount);
		return highestCount;
		
	}

}
