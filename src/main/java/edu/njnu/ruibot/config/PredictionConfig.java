package edu.njnu.ruibot.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PredictionConfig {
	@Value("${prediction.python-path}")
	public String pythonPath;

	@Value("${prediction.prediction-path}")
	public String predictionPath;

	@Value("${prediction.lawCase-path}")
	public String lawCasePath;
}
