package com.jbk.config;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.jbk.Trade;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

	@Autowired
	private DataSource dataSource;

	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	// create reader
	public FlatFileItemReader<Trade> reader() {
		FlatFileItemReader<Trade> reader = new FlatFileItemReader<>();
		reader.setResource(new ClassPathResource("trade.csv"));
		reader.setLineMapper(getLineMapper());
		reader.setLinesToSkip(1);
		return reader;
	}

	@Bean
	public LineMapper<Trade> getLineMapper() {
		DefaultLineMapper<Trade> lineMapper = new DefaultLineMapper<>();
		DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
		lineTokenizer.setNames(
				new String[] { "Direction", "Year", "Weekday", "Country", "Commodity", "Transport_Mode", "Measure" });
		lineTokenizer.setIncludedFields(new int[] { 0, 1, 2, 3, 4, 5, 6 });

		BeanWrapperFieldSetMapper<Trade> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
		fieldSetMapper.setTargetType(Trade.class);

		lineMapper.setLineTokenizer(lineTokenizer);
		lineMapper.setFieldSetMapper(fieldSetMapper);
		return lineMapper;
	}

	// create processor
	@Bean
	public TradeProcessor processor() {
		return new TradeProcessor();
	}

	// create writer
	@Bean
	public JdbcBatchItemWriter<Trade> writer() {
		JdbcBatchItemWriter<Trade> writer = new JdbcBatchItemWriter<>();

		writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>());
		writer.setSql(
				"insert into trade(direction,year,weekday,country,commodity,transportMode,measure) values(:direction,:year,:weekday,:country,:commodity,:transportMode,:measure)");
		writer.setDataSource(this.dataSource);
		return writer;
	}

	// Create JOb

	@Bean
	public Job job() {

		return this.jobBuilderFactory.get("TRADE-IMPORT-JOB").incrementer(new RunIdIncrementer()).flow(step1()).end()
				.build();

	}

	// create steps
	@Bean
	public Step step1() {
		return this.stepBuilderFactory.get("step1").<Trade,Trade>chunk(10)
		.reader(reader())
		.processor(processor())
		.writer(writer())
		.build();
		 
	}

}
