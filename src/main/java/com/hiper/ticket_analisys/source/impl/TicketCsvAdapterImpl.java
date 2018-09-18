package com.hiper.ticket_analisys.source.impl;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.hiper.ticket_analisys.bean.Ticket;
import com.hiper.ticket_analisys.source.TicketSourceAdapter;

public class TicketCsvAdapterImpl implements TicketSourceAdapter {

	private static final Logger logger = Logger.getLogger(TicketCsvAdapterImpl.class);

	private String csvFile = "tickets20180915.csv";
	private String cvsSplitBy = ",";
	private SimpleDateFormat formatter = new SimpleDateFormat("hh:mm:ss");

	public List<Ticket> getTickets(String agencia, String tipo) {
		List<Ticket> tickets = new ArrayList<Ticket>();

		try (Stream<String> stream = Files.lines(Paths.get(ClassLoader.getSystemResource(csvFile).toURI()))) {

			//filter by agencia if present
			tickets = stream.filter(line -> {

				if (StringUtils.isEmpty(agencia) || line.split(cvsSplitBy)[0].equalsIgnoreCase(agencia)) {
					return true;
				}
				return false;
			})
			//filter by tipo if present		
			.filter(line -> {

				if (StringUtils.isEmpty(tipo) || line.split(cvsSplitBy)[2].equalsIgnoreCase(tipo)) {
					return true;
				}

				return false;

			})
			//convert row to object
			.map(line -> {
				String[] row = line.split(cvsSplitBy);
				try {
					return new Ticket(row[0], row[2], formatter.parse(row[4]), formatter.parse(row[5]));
				} catch (ParseException e) {
					logger.error(e.getMessage(), e);
				}
				return null;
			}).collect(Collectors.toList());

			Collections.sort(tickets);

		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		} catch (URISyntaxException e1) {
			logger.error(e1.getMessage(), e1);
		}

		return tickets;

	}

}
