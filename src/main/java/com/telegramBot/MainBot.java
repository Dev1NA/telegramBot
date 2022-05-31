package com.telegramBot;

import com.telegramBot.repo.Repo;
import com.telegramBot.repo.UserFromTelegram;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

@Component
public class MainBot extends TelegramLongPollingBot {

    private Repo repo;

    @Autowired
    public void initRepo(Repo repo) {
        this.repo = repo;
    }

    @Value("${bot.username}")
    private String username;

    @Value("${bot.token}")
    private String token;

    @Override
    public void onUpdateReceived(Update update) {
        if(update.hasMessage() && update.getMessage().hasText()) {
            String chatId = update.getMessage().getChatId().toString();

            SendMessage sm = new SendMessage();
            sm.setChatId(chatId);
            sm.setText("Hello, bro-" + update.getMessage().getFrom().getFirstName() + "!");
            repo.save(new UserFromTelegram(update.getMessage().getFrom().getFirstName()));
            try {
                execute(sm);

            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String getBotUsername() {
        return username;
    }

    @Override
    public String getBotToken() {
        return token;
    }

//    public static InlineKeyboardMarkup sendInlineKeyBoardMessage(String chatId) {
//        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
//        InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
//        inlineKeyboardButton1.setText("Save user");
//        inlineKeyboardButton1.setCallbackData("Button \"Save user\" has been pressed");
//
//        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
//        List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
//        keyboardButtonsRow1.add(inlineKeyboardButton1);
//        rowList.add(keyboardButtonsRow1);
//
//        inlineKeyboardMarkup.setKeyboard(rowList);
//
//        return inlineKeyboardMarkup;
//
//    }

}
