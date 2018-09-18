package com.hiper.ticket_analisys.source;

import java.util.List;

import com.hiper.ticket_analisys.bean.Ticket;


public interface TicketSourceAdapter {
	
	public List<Ticket> getTickets(String agencia, String tipo);

}
