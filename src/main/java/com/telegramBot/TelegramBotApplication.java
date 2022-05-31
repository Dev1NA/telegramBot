package com.telegramBot;

import com.telegramBot.repo.Repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TelegramBotApplication {
	public static void main(String[] args) {
		SpringApplication.run(TelegramBotApplication.class, args);
	}

}
