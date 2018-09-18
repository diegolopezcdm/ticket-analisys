package com.hiper.ticket_analisys.service.impl;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.hiper.ticket_analisys.bean.Ticket;
import com.hiper.ticket_analisys.source.TicketSourceAdapter;

@RunWith(MockitoJUnitRunner.class)
public class TicketServiceAdapterImplTest {
	
	@Mock
    private TicketSourceAdapter ticketSourceAdapterMock;
    @InjectMocks
    private TicketServiceAdapterImpl service;    
    private List<Ticket> tickets;
    private List<Ticket> camachoTickets;
    private List<Ticket> megaplazaTickets;
    private List<Ticket> ferreroTickets;
    private List<Ticket> megaplazaPRTickets;
	private SimpleDateFormat formatter = new SimpleDateFormat("hh:mm:ss");

    
    
    @Before
    public void setUp() throws ParseException {
    	camachoTickets = new ArrayList<Ticket>();
    	camachoTickets.add(new Ticket("AGENCIA_CAMACHO", "C", formatter.parse("9:27:30 AM"), formatter.parse("9:27:42 AM")));
    	camachoTickets.add(new Ticket("AGENCIA_CAMACHO", "C", formatter.parse("9:28:30 AM"), formatter.parse("9:28:42 AM")));
    	camachoTickets.add(new Ticket("AGENCIA_CAMACHO", "C", formatter.parse("9:29:30 AM"), formatter.parse("9:35:42 AM")));
    	camachoTickets.add(new Ticket("AGENCIA_CAMACHO", "C", formatter.parse("9:30:30 AM"), formatter.parse("9:36:42 AM")));
    	camachoTickets.add(new Ticket("AGENCIA_CAMACHO", "C", formatter.parse("9:34:30 AM"), formatter.parse("9:37:42 AM")));
    	camachoTickets.add(new Ticket("AGENCIA_CAMACHO", "C", formatter.parse("9:35:30 AM"), formatter.parse("9:38:42 AM")));
    	camachoTickets.add(new Ticket("AGENCIA_CAMACHO", "C", formatter.parse("9:36:30 AM"), formatter.parse("9:39:42 AM")));

    	ferreroTickets = new ArrayList<Ticket>();
    	ferreroTickets.add(new Ticket("AGENCIA_RAUL_FERRERO", "U", formatter.parse("10:29:16 AM"), formatter.parse("10:30:16 AM")));
    	ferreroTickets.add(new Ticket("AGENCIA_RAUL_FERRERO", "U", formatter.parse("10:29:50 AM"), formatter.parse("10:30:50 AM")));
    	ferreroTickets.add(new Ticket("AGENCIA_RAUL_FERRERO", "U", formatter.parse("10:31:16 AM"), formatter.parse("10:32:16 AM")));
    	ferreroTickets.add(new Ticket("AGENCIA_RAUL_FERRERO", "U", formatter.parse("10:31:20 AM"), formatter.parse("10:33:16 AM")));
    	ferreroTickets.add(new Ticket("AGENCIA_RAUL_FERRERO", "U", formatter.parse("10:34:16 AM"), formatter.parse("10:35:16 AM")));
    	ferreroTickets.add(new Ticket("AGENCIA_RAUL_FERRERO", "U", formatter.parse("10:36:16 AM"), formatter.parse("10:37:16 AM")));
    	ferreroTickets.add(new Ticket("AGENCIA_RAUL_FERRERO", "U", formatter.parse("10:38:16 AM"), formatter.parse("10:39:16 AM")));
    	
    	megaplazaTickets = new ArrayList<Ticket>();
    	megaplazaTickets.add(new Ticket("AGENCIA_MEGA_PLAZA_1", "U", formatter.parse("11:30:06 AM"), formatter.parse("11:31:31 AM")));
    	megaplazaTickets.add(new Ticket("AGENCIA_MEGA_PLAZA_1", "U", formatter.parse("11:32:06 AM"), formatter.parse("11:33:31 AM")));
    	megaplazaTickets.add(new Ticket("AGENCIA_MEGA_PLAZA_1", "U", formatter.parse("11:34:06 AM"), formatter.parse("11:35:31 AM")));
    	megaplazaTickets.add(new Ticket("AGENCIA_MEGA_PLAZA_1", "U", formatter.parse("11:36:06 AM"), formatter.parse("11:37:31 AM")));
    	megaplazaTickets.add(new Ticket("AGENCIA_MEGA_PLAZA_1", "U", formatter.parse("11:38:06 AM"), formatter.parse("11:39:31 AM")));
    	megaplazaTickets.add(new Ticket("AGENCIA_MEGA_PLAZA_1", "U", formatter.parse("11:40:06 AM"), formatter.parse("11:41:31 AM")));
    	megaplazaTickets.add(new Ticket("AGENCIA_MEGA_PLAZA_1", "U", formatter.parse("11:42:06 AM"), formatter.parse("11:42:31 AM")));
    	
    	megaplazaPRTickets = new ArrayList<Ticket>();
    	megaplazaPRTickets.add(new Ticket("AGENCIA_MEGA_PLAZA_1", "PR", formatter.parse("11:45:06 AM"), formatter.parse("11:48:31 AM")));
    	megaplazaPRTickets.add(new Ticket("AGENCIA_MEGA_PLAZA_1", "PR", formatter.parse("11:46:06 AM"), formatter.parse("11:49:31 AM")));
    	megaplazaPRTickets.add(new Ticket("AGENCIA_MEGA_PLAZA_1", "PR", formatter.parse("11:47:06 AM"), formatter.parse("11:50:31 AM")));
    	
    	tickets = new ArrayList<Ticket>();
    	tickets.addAll(camachoTickets);
    	tickets.addAll(ferreroTickets);
    	tickets.addAll(megaplazaTickets);
    	tickets.addAll(megaplazaPRTickets);


    }
    
    @Test
    public void testGetLargestQueue_whenNoFilter() {
    	when(ticketSourceAdapterMock.getTickets(null, "")).thenReturn(tickets);
        assertThat(service.getLargestQueue(null, ""), is(4));
    }
    
    @Test
    public void testGetLargestQueue_whenAgenciaCamacho() {
        when(ticketSourceAdapterMock.getTickets("AGENCIA_CAMACHO", "")).thenReturn(camachoTickets);
        assertThat(service.getLargestQueue("AGENCIA_CAMACHO", ""), is(4));
    }
    
    @Test
    public void testGetLargestQueue_whenAgenciaMegaPlaza() {
    	  when(ticketSourceAdapterMock.getTickets("AGENCIA_MEGA_PLAZA_1", null)).thenReturn(megaplazaTickets);
          assertThat(service.getLargestQueue("AGENCIA_MEGA_PLAZA_1", null), is(1));
    }
    
    @Test
    public void testGetLargestQueue_whenAgenciaMegaPlazaAndTipoPR() {
    	  when(ticketSourceAdapterMock.getTickets("AGENCIA_MEGA_PLAZA_1", "PR")).thenReturn(megaplazaPRTickets);
          assertThat(service.getLargestQueue("AGENCIA_MEGA_PLAZA_1", "PR"), is(3));
    }

}
