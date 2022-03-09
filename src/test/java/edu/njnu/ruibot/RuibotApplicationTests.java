package edu.njnu.ruibot;

import edu.njnu.ruibot.pojo.QueryRecord;
import edu.njnu.ruibot.service.PredictionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class RuibotApplicationTests {

	@Autowired
	PredictionService predictionService;

	@Test
	void contextLoads() {
	}

	@Test
	void testExecution() {
		String desc = "张博墉强奸了习近平";
		System.out.println(predictionService.prediction(desc, 1));
	}

	@Test
	void testGetPredictions() {
		List<QueryRecord> historyRecords = predictionService.getHistoryRecords(2);
		for (QueryRecord historyRecord : historyRecords) {
			System.out.println(historyRecord);
		}
	}
}
