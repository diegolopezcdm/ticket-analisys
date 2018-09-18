package com.hiper.ticket_analisys;

import org.apache.log4j.Logger;

import com.hiper.ticket_analisys.service.TicketServiceAdapter;
import com.hiper.ticket_analisys.service.impl.TicketServiceAdapterImpl;

/**
 * Hello world!
 *
 */
public class Main 
{
	private static final  Logger logger = Logger.getLogger(Main.class);

    public static void main( String[] args )
    {
    	TicketServiceAdapter service = new TicketServiceAdapterImpl();
		int highestValue = service.getLargestQueue("AGENCIA_CAMACHO", "C");
		logger.info("The highestCount value is "+ highestValue);
    }
}
