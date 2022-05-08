package com.jbk.config;

import org.springframework.batch.item.ItemProcessor;

import com.jbk.Trade;

public class TradeProcessor implements ItemProcessor<Trade, Trade> {

	@Override
	public Trade process(Trade trade) throws Exception {
		return trade;
	}

}
